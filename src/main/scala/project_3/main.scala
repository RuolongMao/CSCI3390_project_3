package project_3

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.graphx._
import org.apache.spark.storage.StorageLevel
import org.apache.log4j.{Level, Logger}

object main{
  val rootLogger = Logger.getRootLogger()
  rootLogger.setLevel(Level.ERROR)

  Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
  Logger.getLogger("org.spark-project").setLevel(Level.WARN)

  def LubyMIS(g_in: Graph[Int, Int]): Graph[Int, Int] = {
    var g = g_in.mapVertices((_, _) => 0)
    var remainingVertices = g.vertices.filter { case (_, attr) => attr == 0 }.count()
    var iteration = 0

    while (remainingVertices >= 1) {
      iteration += 1
      val randomGraph = g.mapVertices((id, attr) => {
        if (attr == 0) scala.util.Random.nextDouble() else attr
      })

      val localMinima = randomGraph.aggregateMessages[Double](
        triplet => {
          if (triplet.srcAttr > 0 && triplet.dstAttr > 0) {
            if (triplet.srcAttr < triplet.dstAttr) triplet.sendToDst(triplet.srcAttr)
            else triplet.sendToSrc(triplet.dstAttr)
          }
        },
        (a, b) => math.min(a, b)
      )

      val newGraph = g.outerJoinVertices(localMinima) { (vid, oldAttr, minNeighbor) =>
        if (oldAttr == 0) {
          if (minNeighbor.isEmpty) 1
          else -1
        } else oldAttr
      }

      g = newGraph
      remainingVertices = g.vertices.filter { case (_, attr) => attr == 0 }.count()
    }

    g
  }


  def verifyMIS(g_in: Graph[Int, Int]): Boolean = {
    val misVertices = g_in.vertices.filter { case (_, attr) => attr == 1 }.map(_._1).collect().toSet

    val violatingEdges = g_in.edges.filter(e => misVertices.contains(e.srcId) && misVertices.contains(e.dstId))
    if (!violatingEdges.isEmpty()) {
      return false
    }

    val nonMISVertices = g_in.vertices.filter { case (_, attr) => attr != 1 }
    
    val neighborsInMIS = g_in.aggregateMessages[Boolean](
      triplet => {
        if (triplet.dstAttr == 1) triplet.sendToSrc(true)
        if (triplet.srcAttr == 1) triplet.sendToDst(true)
      },
      (a, b) => a || b
    )

    val maximalityViolations = nonMISVertices.leftOuterJoin(neighborsInMIS)
      .filter { case (_, (_, hasNeighbor)) => !hasNeighbor.getOrElse(false) }
      .count()

    if (maximalityViolations > 0) {
      return false
    }

    true
  }


  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("project_3")
    val sc = new SparkContext(conf)
    val spark = SparkSession.builder.config(conf).getOrCreate()
/* You can either use sc or spark */

    if(args.length == 0) {
      println("Usage: project_3 option = {compute, verify}")
      sys.exit(1)
    }
    if(args(0)=="compute") {
      if(args.length != 3) {
        println("Usage: project_3 compute graph_path output_path")
        sys.exit(1)
      }
      val startTimeMillis = System.currentTimeMillis()
      val edges = sc.textFile(args(1)).map(line => {val x = line.split(","); Edge(x(0).toLong, x(1).toLong , 1)} )
      val g = Graph.fromEdges[Int, Int](edges, 0, edgeStorageLevel = StorageLevel.MEMORY_AND_DISK, vertexStorageLevel = StorageLevel.MEMORY_AND_DISK)
      val g2 = LubyMIS(g)

      val endTimeMillis = System.currentTimeMillis()
      val durationSeconds = (endTimeMillis - startTimeMillis) / 1000
      println("==================================")
      println("Luby's algorithm completed in " + durationSeconds + "s.")
      println("==================================")

      val g2df = spark.createDataFrame(g2.vertices)
      g2df.coalesce(1).write.format("csv").mode("overwrite").save(args(2))
    }
    else if(args(0)=="verify") {
      if(args.length != 3) {
        println("Usage: project_3 verify graph_path MIS_path")
        sys.exit(1)
      }

      val edges = sc.textFile(args(1)).map(line => {val x = line.split(","); Edge(x(0).toLong, x(1).toLong , 1)} )
      val vertices = sc.textFile(args(2)).map(line => {val x = line.split(","); (x(0).toLong, x(1).toInt) })
      val g = Graph[Int, Int](vertices, edges, edgeStorageLevel = StorageLevel.MEMORY_AND_DISK, vertexStorageLevel = StorageLevel.MEMORY_AND_DISK)

      val ans = verifyMIS(g)
      if(ans)
        println("Yes")
      else
        println("No")
    }
    else
    {
        println("Usage: project_3 option = {compute, verify}")
        sys.exit(1)
    }
  }
}
