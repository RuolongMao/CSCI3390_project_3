# Large Scale Data Processing: Project 3

**Group Member: Hoiting Mok, Ruolong Mao**

1. **(4 points)** Implement the `verifyMIS` function. The function accepts a Graph[Int, Int] object as its input. Each vertex of the graph is labeled with 1 or -1, indicating whether or not a vertex is in the MIS. `verifyMIS` should return `true` if the labeled vertices form an MIS and `false` otherwise. To execute the function, run the following:
```
// Linux
spark-submit --class project_3.main --master local[*] target/scala-2.12/project_3_2.12-1.0.jar verify [path_to_graph] [path_to_MIS]

// Unix
spark-submit --class "project_3.main" --master "local[*]" target/scala-2.12/project_3_2.12-1.0.jar verify [path_to_graph] [path_to_MIS]
```
Apply `verifyMIS` locally with the parameter combinations listed in the table below and **fill in all blanks**.
|        Graph file       |           MIS file           | Is an MIS? |
| ----------------------- | ---------------------------- | ---------- |
| small_edges.csv         | small_edges_MIS.csv          | Yes        |
| small_edges.csv         | small_edges_non_MIS.csv      | No         |
| line_100_edges.csv      | line_100_MIS_test_1.csv      | **Yes**          |
| line_100_edges.csv      | line_100_MIS_test_2.csv      | **No**          |
| twitter_10000_edges.csv | twitter_10000_MIS_test_1.csv | **No**          |
| twitter_10000_edges.csv | twitter_10000_MIS_test_2.csv | **Yes**          |

**Command**: spark-submit --class "project_3.main" --master "local[*]" target/scala-2.12/project_3_2.12-1.0.jar verify small_edges.csv small_edges_MIS.csv  
**Result**: Yes  
**Output**:  
<img width="624" alt="Screenshot 2025-04-10 at 12 37 04 PM" src="https://github.com/user-attachments/assets/a1efc68e-e2cb-4d54-84e4-c5b26bbbba43" />

**Command**: spark-submit --class "project_3.main" --master "local[*]" target/scala-2.12/project_3_2.12-1.0.jar verify small_edges.csv small_edges_non_MIS.csv  
**Result**: No  
**Output**:  
<img width="627" alt="Screenshot 2025-04-10 at 12 38 02 PM" src="https://github.com/user-attachments/assets/56e0a9f9-9080-49b4-bd15-c6e29e9d6a0c" />

**Command**: spark-submit --class "project_3.main" --master "local[*]" target/scala-2.12/project_3_2.12-1.0.jar verify line_100_edges.csv line_100_MIS_test_1.csv  
**Result**: Yes  
**Output**:  
<img width="631" alt="Screenshot 2025-04-10 at 12 39 19 PM" src="https://github.com/user-attachments/assets/6adb3d48-2e8f-4253-88c5-2c6d1240219e" />

**Command**: spark-submit --class "project_3.main" --master "local[*]" target/scala-2.12/project_3_2.12-1.0.jar verify line_100_edges.csv line_100_MIS_test_2.csv  
**Result**: No  
**Output**:  
<img width="626" alt="Screenshot 2025-04-10 at 12 39 56 PM" src="https://github.com/user-attachments/assets/a0a1a91c-1110-4aff-b0f0-02be755ca7ec" />

**Command**: spark-submit --class "project_3.main" --master "local[*]" target/scala-2.12/project_3_2.12-1.0.jar verify twitter_10000_edges.csv twitter_10000_MIS_test_1.csv  
**Result**: No  
**Output**:   
<img width="624" alt="Screenshot 2025-04-10 at 12 44 54 PM" src="https://github.com/user-attachments/assets/610e556e-87d5-4916-ab07-989ab48130b6" />

**Command**: spark-submit --class "project_3.main" --master "local[*]" target/scala-2.12/project_3_2.12-1.0.jar verify twitter_10000_edges.csv twitter_10000_MIS_test_2.csv  
**Result**: Yes  
**Output**:   
<img width="626" alt="Screenshot 2025-04-10 at 12 45 33 PM" src="https://github.com/user-attachments/assets/253c1dc6-d78f-4d58-b778-7d8067bb0570" />


2. **(3 points)** Implement the `LubyMIS` function. 
Apply `LubyMIS` locally on the graph files listed below and report the number of iterations and running time that the MIS algorithm consumes for **each file**. You may need to include additional print statements in `LubyMIS` in order to acquire this information. Finally, verify your outputs with `verifyMIS`.

|        Graph file       |
| ----------------------- |
| small_edges.csv         |
| line_100_edges.csv      |
| twitter_100_edges.csv   |
| twitter_1000_edges.csv  |
| twitter_10000_edges.csv |  


**Command**: spark-submit --class project_3.main --master local[\*] target/scala-2.12/project_3_2.12-1.0.jar compute ./input_graphs/small_edges.csv ./output_graphs/small_edges  
**No. Iterations**:  
<img width="250" alt="Screenshot 2025-04-10 at 4 12 11 PM" src="https://github.com/user-attachments/assets/1bce1b9b-df27-4c5d-9b77-bc84bc01f69f" />  
**Verification**: spark-submit --class project_3.main --master local[\*] target/scala-2.12/project_3_2.12-1.0.jar verify ./input_graphs/small_edges.csv ./output_graphs/small_edges/[last output filename].csv  
<img width="150" alt="Screenshot 2025-04-10 at 4 12 50 PM" src="https://github.com/user-attachments/assets/7563a38e-7cfb-4dd4-9b80-acfe250da566" />  

**Command**: spark-submit --class project_3.main --master local[\*] target/scala-2.12/project_3_2.12-1.0.jar compute ./input_graphs/line_100_edges.csv ./output_graphs/line_100  
**No. Iterations**:  
<img width="250" alt="Screenshot 2025-04-10 at 3 57 31 PM" src="https://github.com/user-attachments/assets/a3d322ac-3379-49e1-80c3-91452b54f8ba" />  
**Verification Cmd**: spark-submit --class project_3.main --master local[\*] target/scala-2.12/project_3_2.12-1.0.jar verify ./input_graphs/line_100_edges.csv ./output_graphs/line_100/[last output filename].csv  
<img width="150" alt="Screenshot 2025-04-10 at 3 58 53 PM" src="https://github.com/user-attachments/assets/e5cf0fe8-0c17-44e0-8ecd-50ae30fc9082" />  

**Command**: spark-submit --class project_3.main --master local[\*] target/scala-2.12/project_3_2.12-1.0.jar compute ./input_graphs/twitter_100_edges.csv 
./output_graphs/twitter_100  
**No. Iterations**:  
<img width="250" alt="Screenshot 2025-04-10 at 4 01 15 PM" src="https://github.com/user-attachments/assets/e2e51b38-4bbb-4d54-a43e-a602d2ce45d1" />  
**Verification Cmd**: spark-submit --class project_3.main --master local[\*] target/scala-2.12/project_3_2.12-1.0.jar verify ./input_graphs/twitter_100_edges.csv ./output_graphs/twitter_100/[last output filename].csv  
<img width="150" alt="Screenshot 2025-04-10 at 4 02 28 PM" src="https://github.com/user-attachments/assets/8e0e260b-6c19-4f87-a094-de704894ae85" />  

**Command**: spark-submit --class project_3.main --master local[\*] target/scala-2.12/project_3_2.12-1.0.jar compute ./input_graphs/twitter_1000_edges.csvn./output_graphs/twitter_1k  
**No. Iterations**:  
<img width="250" alt="Screenshot 2025-04-10 at 4 06 29 PM" src="https://github.com/user-attachments/assets/a6f9c01b-8063-417a-b3d2-a87c645c5d7e" />  
**Verification Cmd**: spark-submit --class project_3.main --master local[\*] target/scala-2.12/project_3_2.12-1.0.jar verify ./input_graphs/twitter_1000_edges.csv ./output_graphs/twitter_1k/[last output filename].csv      
<img width="150" alt="Screenshot 2025-04-10 at 4 05 37 PM" src="https://github.com/user-attachments/assets/8eee82fe-8ca4-435b-9a4e-3cb9bd9508ee" />


**Command**: spark-submit --class project_3.main --master local[\*] target/scala-2.12/project_3_2.12-1.0.jar compute ./input_graphs/twitter_10000_edges.csv ./output_graphs/twitter_10k  
**No. Iterations**:  
<img width="273" alt="Screenshot 2025-04-10 at 4 03 27 PM" src="https://github.com/user-attachments/assets/81bbbe84-985a-4588-bc13-5b2b905e5782" />  
**Verification Cmd**: spark-submit --class project_3.main --master local[\*] target/scala-2.12/project_3_2.12-1.0.jar verify ./input_graphs/twitter_10000_edges.csv ./output_graphs/twitter_10k/[last output filename].csv    
<img width="174" alt="Screenshot 2025-04-10 at 4 08 22 PM" src="https://github.com/user-attachments/assets/6d4f1bdc-e1ac-4c3a-b0ae-860d44b75cdd" />



3. **(3 points)**  
a. Run `LubyMIS` on `twitter_original_edges.csv` in GCP with 3x4 cores (vCPUs). Report the number of iterations, running time, and remaining active vertices (i.e. vertices whose status has yet to be determined) at the end of **each iteration**. You may need to include additional print statements in `LubyMIS` in order to acquire this information. Finally, verify your outputs with `verifyMIS`.  
b. Run `LubyMIS` on `twitter_original_edges.csv` with 4x2 cores (vCPUs) and then 2x2 cores (vCPUs). Compare the running times between the 3 jobs with varying core specifications that you submitted in **3a** and **3b**.

**Configuration: 3x4 cores (12 vCPUs)**:  
![image](https://github.com/user-attachments/assets/9d764d84-ca49-45dd-92c8-e76a92dd7411)

a1. **LubyMIS Result**:  
![image](https://github.com/user-attachments/assets/cdad5e82-db68-473a-89a0-fbcb4ad82cc2)

a2. **VerifyMIS Result**:  
![image](https://github.com/user-attachments/assets/603e299f-efb2-4157-a1da-f2216f748f04)

b1. **Configuration: 4x2 cores (8 vCPUs)**:  
![image](https://github.com/user-attachments/assets/030a99ae-b4a6-46a0-9f46-d5c1f8e43c94)

**Result**:  
![image](https://github.com/user-attachments/assets/2ce520d8-97e9-4b3e-ae87-00b2cacc0d98)

b2. **Configuration: 2x2 cores (4 vCPUs)**:  
![image](https://github.com/user-attachments/assets/c939d2f8-fd79-4706-83c9-64a331d46c1e)

**Result**:  
![image](https://github.com/user-attachments/assets/d7af7d82-b773-4e07-9d78-286a7c3818d0)

**Performance Comparison**:
| Configuration | Total Cores | Running Time (s) |
|--------------|------------|-----------------|
| 3x4 cores    | 12         | 78s       |
| 4x2 cores    | 8          | 159s        |
| 2x2 cores    | 4          | 247s       |

**Analysis**:
The 3x4 core configuration (12 vCPUs) cluster completes the task in 78 seconds and has the best performance. As we reduce the number of cores, we observe a significant increase in execution time:
- 4x2 cores (8 vCPUs) takes approximately 2.04x longer than 3x4 cores
- 2x2 cores (4 vCPUs) takes approximately 3.17x longer than 3x4 cores
 
This suggests that the LubyMIS algorithm benefits significantly from parallelization. Moreover, the non-linear relationship may attribute from the memory access time and communication time between cores.
