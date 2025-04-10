# Large Scale Data Processing: Project 3


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

## Submission via GitHub
Delete your project's current **README.md** file (the one you're reading right now) and include your report as a new **README.md** file in the project root directory. Have no fear—the README with the project description is always available for reading in the template repository you created your repository from. For more information on READMEs, feel free to visit [this page](https://docs.github.com/en/github/creating-cloning-and-archiving-repositories/about-readmes) in the GitHub Docs. You'll be writing in [GitHub Flavored Markdown](https://guides.github.com/features/mastering-markdown). Be sure that your repository is up to date and you have pushed all changes you've made to the project's code. When you're ready to submit, simply provide the link to your repository in the Canvas assignment's submission.

## You must do the following to receive full credit:
1. Create your report in the ``README.md`` and push it to your repo.
2. In the report, you must include your (and your group member's) full name in addition to any collaborators.
3. Submit a link to your repo in the Canvas assignment.

## Late submission penalties
Please refer to the course policy.
