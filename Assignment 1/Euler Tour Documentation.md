# Detailed Documentation for Euler Tour Code

### Introduction
An **Euler tour** of a simple undirected graph $G$ is a cycle that visits every edge exactly once. A connected graph has an Euler tour if and only if every vertex has an even degree. This documentation explains the code that implements the Euler tour algorithm in detail, breaking down each part of the code for clarity.

### Code Explanation

1. **Imports**:
   ```java
   import java.io.BufferedReader;
   import java.io.FileReader;
   import java.io.IOException;
   import java.util.ArrayList;
   import java.util.List;
   ```
   - **`BufferedReader`**: Used for reading text from a character input stream efficiently.
   - **`FileReader`**: Used to read the contents of a file.
   - **`IOException`**: Catches any input/output exceptions that may occur during file operations.
   - **`ArrayList` and `List`**: Used for dynamic array operations, specifically to store the Euler tour path.

2. **Class Declaration**:
   ```java
   public class EulerTour {
   ```
   - This declares the class `EulerTour`, which contains the main method and other methods for processing the graph.

3. **Main Method**:
   ```java
   public static void main(String[] args) {
   ```
   - The entry point of the program where execution begins.

4. **Variable Initialization**:
   ```java
   String filename = "graphs.txt";
   int eulerTourCount = 0;
   ```
   - **`filename`**: The name of the file containing graph data.
   - **`eulerTourCount`**: A counter to keep track of the number of graphs that have an Euler tour.

5. **File Reading**:
   ```java
   try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
   ```
   - Opens the specified file for reading using a `BufferedReader`.
   - The `try-with-resources` statement automatically closes the reader after use.

6. **Reading Lines**:
   ```java
   String line;
   while ((line = reader.readLine()) != null) {
   ```
   - Reads each line from the file until there are no more lines to read.

7. **Processing Graph Metadata**:
   ```java
   String[] parts = line.trim().split("\\s+");
   if (parts.length == 2) {
       int n = Integer.parseInt(parts[0]);
       int m = Integer.parseInt(parts[1]);
       int[][] graph = new int[n][n];
   ```
   - The line is split into parts to extract the number of vertices (`n`) and edges (`m`).
   - An adjacency matrix `graph` of size $ n \times n $ is created to represent the graph.

   #### **Explanation**

      1. **Reading the Line**:
         - The line variable contains a string that represents the first line of the graph data, which includes two integers separated by whitespace. For example, if the line reads:
         ```
         5 10
         ```
         - This indicates that there are 5 vertices and 10 edges in the graph.

      2. **Trimming and Splitting the Line**:
         ```java
         String[] parts = line.trim().split("\\s+");
         ```
         - **`line.trim()`**: This method removes any leading or trailing whitespace from the line string.
         - **`split("\\s+")`**: This method splits the trimmed line into an array of strings based on one or more whitespace characters. The resulting array, `parts`, will contain the individual components of the line.

      3. **Checking the Number of Parts**:
         ```java
         if (parts.length == 2) {
         ```
         - This condition checks if the `parts` array contains exactly two elements. If it does, it means that the line contains valid input for the number of vertices and edges.

      4. **Extracting Number of Vertices and Edges**:
         ```java
         int n = Integer.parseInt(parts[0]); // Number of vertices
         int m = Integer.parseInt(parts[1]); // Number of edges
         ```
         - **`Integer.parseInt(parts)`**: Converts the first element of the `parts` array (which represents the number of vertices) from a string to an integer and assigns it to `n`.
         - **`Integer.parseInt(parts[1])`**: Converts the second element of the `parts` array (which represents the number of edges) from a string to an integer and assigns it to `m`.

      5. **Creating the Adjacency Matrix**:
         ```java
         int[][] graph = new int[n][n]; // Create an adjacency matrix
         ```
         - An adjacency matrix `graph` of size `n x n` is created to represent the graph. This matrix will be used to store the connections (edges) between the vertices.
         - Each entry `graph[i][j]` will be set to `1` if there is an edge between vertex `i` and vertex `j`, and `0` otherwise.

         #### **Example**

            Let's consider the following input line from the file:

            ```
            5 10
            ```

            - **Step 1**: The line is read into the variable `line`.
            - **Step 2**: After trimming and splitting, the `parts` array will look like this:
            ```java
            parts = ["5", "10"]
            ```
            - **Step 3**: The condition `parts.length == 2` is satisfied since there are two elements in the array.
            - **Step 4**: The number of vertices `n` is set to `5`, and the number of edges `m` is set to `10`.
            - **Step 5**: An adjacency matrix of size `5 x 5` is created, initialized to all zeros:
            ```java
            graph = [
                  [0, 0, 0, 0, 0],
                  [0, 0, 0, 0, 0],
                  [0, 0, 0, 0, 0],
                  [0, 0, 0, 0, 0],
                  [0, 0, 0, 0, 0]
            ]
            ```

            This adjacency matrix will later be populated with edges as they are read from the subsequent lines of the input file.

            ### Conclusion

            This part of the code is essential for initializing the graph representation based on the input file. By extracting the number of vertices and edges, the program can dynamically allocate the necessary data structures to represent the graph, allowing for further processing such as checking for Euler tours or Hamiltonian cycles. Understanding this step is crucial for grasping how the graph is constructed and manipulated in the subsequent parts of the program.
            
8. **Reading Edges**:
   ```java
   for (int i = 0; i < m; i++) {
       line = reader.readLine();
       parts = line.trim().split("\\s+");
       int u = Integer.parseInt(parts[0]);
       int v = Integer.parseInt(parts[1]);
       graph[u][v] = 1;
       graph[v][u] = 1;
   }
   ```
   - This loop reads the edges of the graph.
   - For each edge, it reads the line, splits it to get the vertices `u` and `v`, and updates the adjacency matrix to indicate the presence of an edge between `u` and `v`.

   #### **Detailed Explanation**

   1. **Loop through the Number of Edges**:
      ```java
      for (int i = 0; i < m; i++) {
      ```
      - This loop iterates `m` times, where `m` is the number of edges specified in the graph metadata. Each iteration corresponds to reading one edge from the input.

   2. **Reading the Edge Line**:
      ```java
      line = reader.readLine(); // Read the next line for the edge
      ```
      - The `readLine()` method reads the next line from the input file, which contains the two vertices that form an edge. For example, if the next line reads:
      ```
      0 1
      ```
      - This indicates that there is an edge between vertex 0 and vertex 1.

   3. **Splitting the Edge Line**:
      ```java
      parts = line.trim().split("\\s+"); // Split the edge line
      ```
      - The `trim()` method removes any leading or trailing whitespace from the line.
      - The `split("\\s+")` method splits the trimmed line into an array of strings based on one or more whitespace characters. The resulting `parts` array will contain the individual vertex identifiers.

   4. **Extracting Vertex Identifiers**:
      ```java
      int u = Integer.parseInt(parts[0]); // Start vertex
      int v = Integer.parseInt(parts[1]); // End vertex
      ```
      - The first element of the `parts` array (representing the start vertex) is converted from a string to an integer and assigned to `u`.
      - The second element (representing the end vertex) is similarly converted and assigned to `v`.

   5. **Updating the Adjacency Matrix**:
      ```java
      graph[u][v] = 1; // Set the edge in the adjacency matrix
      graph[v][u] = 1; // Ensure the graph is undirected
      ```
      - The adjacency matrix `graph` is updated to indicate the presence of an edge between vertices `u` and `v`.
      - `graph[u][v] = 1` sets the entry in the matrix to 1, indicating an edge from `u` to `v`.
      - `graph[v][u] = 1` sets the entry in the matrix to 1 as well, ensuring that the graph is undirected (i.e., if there is an edge from `u` to `v`, there is also an edge from `v` to `u`).

      #### Example

      Let's consider the following input lines from the file:

      ```
      5 10
      0 1
      0 4
      0 3
      0 2
      1 2
      1 3
      1 4
      2 3
      2 4
      3 4
      ```

      - **Step 1**: The first line indicates that there are `5` vertices and `10` edges.
      - **Step 2**: The loop starts, and during the first iteration (`i = 0`), the line `0 1` is read.
      - **Step 3**: The line is split into `parts`:
      ```java
      parts = ["0", "1"]
      ```
      - **Step 4**: The vertices are extracted:
      - `u = 0`
      - `v = 1`
      - **Step 5**: The adjacency matrix is updated:
      - After processing the edge `0 1`, the matrix will look like this:
      ```
      [
         [0, 1, 0, 0, 0],
         [1, 0, 0, 0, 0],
         [0, 0, 0, 0, 0],
         [0, 0, 0, 0, 0],
         [0, 0, 0, 0, 0]
      ]
      ```

      - **Subsequent Iterations**:
      - In the next iterations, the edges `0 4`, `0 3`, `0 2`, etc., will be processed similarly, updating the adjacency matrix accordingly.
      
      - **Final Adjacency Matrix**:
      After all edges have been processed, the final adjacency matrix will look like this:
      ```
      [
         [0, 1, 1, 1, 1],
         [1, 0, 1, 1, 1],
         [1, 1, 0, 1, 1],
         [1, 1, 1, 0, 1],
         [1, 1, 1, 1, 0]
      ]
      ```

      ### Conclusion

      This part of the code is essential for constructing the graph's representation in memory. By reading the edges from the input file and updating the adjacency matrix, the program prepares the graph for further processing, such as checking for Euler tours or Hamiltonian cycles. Understanding this step is crucial for grasping how the graph is built and manipulated in the subsequent parts of the program.

9. **Checking for Euler Tour**:
   ```java
   List<Integer> eulerTourPath = new ArrayList<>();
   if (hasEulerTour(graph, n, eulerTourPath)) {
       System.out.println("Euler Tour Path:");
       for (int vertex : eulerTourPath) {
           System.out.print(vertex + " ");
       }
       System.out.println();
       eulerTourCount++;
   }
   ```
   - Calls the `hasEulerTour` method to check if the graph has an Euler tour.
   - If an Euler tour exists, it prints the path and increments the `eulerTourCount`.

   #### **Detailed Explanation**

   1. **Creating the Euler Tour Path List**:
      ```java
      List<Integer> eulerTourPath = new ArrayList<>();
      ```
      - This line initializes a new `ArrayList` called `eulerTourPath` that will be used to store the vertices in the order they are visited during the Euler tour. The list is dynamic, allowing it to grow as needed.

   2. **Checking for an Euler Tour**:
      ```java
      if (hasEulerTour(graph, n, eulerTourPath)) {
      ```
      - The `hasEulerTour` method is called with three arguments: the adjacency matrix `graph`, the number of vertices `n`, and the `eulerTourPath` list.
      - This method checks whether the graph has an Euler tour. If it does, the method populates `eulerTourPath` with the vertices visited in the Euler tour and returns `true`.

   3. **Printing the Euler Tour Path**:
      ```java
      System.out.println("Euler Tour Path:");
      for (int vertex : eulerTourPath) {
         System.out.print(vertex + " ");
      }
      System.out.println();
      ```
      - If the graph has an Euler tour, the program prints "Euler Tour Path:" followed by the vertices in the order they were visited.
      - The `for` loop iterates through each vertex in the `eulerTourPath` list and prints it, separated by spaces.
      - After printing all vertices, a newline is printed for better formatting.

   4. **Incrementing the Euler Tour Count**:
      ```java
      eulerTourCount++;
      ```
      - This line increments the `eulerTourCount` variable by 1, indicating that one more Euler tour has been found.

      #### Example

      Let's consider an example graph represented by the following input:

      ```
      5 10
      0 1
      0 4
      0 3
      0 2
      1 2
      1 3
      1 4
      2 3
      2 4
      3 4
      ```

      - **Step 1**: The program reads the graph's metadata, which indicates that there are `5` vertices and `10` edges.
      - **Step 2**: The adjacency matrix is constructed based on the edges read from the input. The final adjacency matrix will look like this:
      ```
      [
         [0, 1, 1, 1, 1],
         [1, 0, 1, 1, 1],
         [1, 1, 0, 1, 1],
         [1, 1, 1, 0, 1],
         [1, 1, 1, 1, 0]
      ]
      ```

      - **Step 3**: The `hasEulerTour` method is called. It checks the degrees of each vertex:
      - Vertex 0: degree = 4 (even)
      - Vertex 1: degree = 4 (even)
      - Vertex 2: degree = 4 (even)
      - Vertex 3: degree = 4 (even)
      - Vertex 4: degree = 4 (even)

      - **Step 4**: Since all vertices have even degrees, the graph has an Euler tour. The `hasEulerTour` method populates the `eulerTourPath` list with the vertices in the order they are visited. For example, the path could be:
      ```
      eulerTourPath = [0, 1, 2, 3, 4, 0, 4, 1, 3, 2]
      ```

      - **Step 5**: The program prints the Euler tour path:
      ```
      Euler Tour Path:
      0 1 2 3 4 0 4 1 3 2 
      ```

      - **Step 6**: The `eulerTourCount` is incremented to reflect that one Euler tour has been found.

      ### Conclusion

      This part of the code is essential for determining whether the graph has an Euler tour and for outputting the path if it exists. By maintaining a list of visited vertices and checking the conditions for an Euler tour, the program effectively demonstrates the properties of Eulerian graphs. Understanding this segment is crucial for grasping how the Euler tour is constructed and represented in the program.

10. **Exception Handling**:
    ```java
    } catch (IOException e) {
        e.printStackTrace();
    }
    ```
    - Catches any `IOException` that occurs while reading the file and prints the stack trace.

11. **Printing Results**:
    ```java
    System.out.println("Euler Tour Count: " + eulerTourCount);
    ```
    - Finally, it prints the total count of graphs with Euler tours.

### Method Descriptions

1. **`hasEulerTour(int[][] graph, int n, List<Integer> eulerTourPath)`**:
   - **Purpose**: Checks if the graph has an Euler tour by verifying if every vertex has an even degree.
   - **Logic**:
     - Iterates through each vertex and counts the number of edges connected to it (degree).
     - If any vertex has an odd degree, it returns `false`.
     - If all vertices have even degrees, it calls the `dfs` method to find the Euler tour path.
     - After DFS, it checks if all edges have been visited.
   - **Returns**: `true` if an Euler tour exists; otherwise, `false`.

   ### Code Explanation for `hasEulerTour` Method

   The `hasEulerTour` method is responsible for determining whether a given graph has an Euler tour. An Euler tour is a cycle that visits every edge of the graph exactly once. The method checks two main conditions: whether all vertices have an even degree and whether all edges have been visited.

   Here’s the detailed breakdown of the method:

   ```java
   private static boolean hasEulerTour(int[][] graph, int n, List<Integer> eulerTourPath) {
      for (int i = 0; i < n; i++) {
         int degree = 0;
         for (int j = 0; j < n; j++) {
               degree += graph[i][j];
         }
         if (degree % 2 != 0) {
               return false;
         }
      }

      boolean[] visited = new boolean[n];
      int start = 0; // Starting vertex for Euler tour
      dfs(graph, start, visited, eulerTourPath);

      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; j++) {
               if (graph[i][j] == 1) {
                  return false;
               }
         }
      }

      return true;
   }
   ```

   #### Detailed Explanation

   1. **Method Signature**:
      - The method is declared as `private static`, meaning it can be called without creating an instance of the class.
      - It takes three parameters:
      - `graph`: A 2D array (adjacency matrix) representing the graph.
      - `n`: The number of vertices in the graph.
      - `eulerTourPath`: A list intended to store the vertices of the Euler tour.

   2. **Checking Vertex Degrees**:
      ```java
      for (int i = 0; i < n; i++) {
         int degree = 0;
         for (int j = 0; j < n; j++) {
            degree += graph[i][j];
         }
         if (degree % 2 != 0) {
            return false;
         }
      }
      ```
      - This loop iterates through each vertex `i` in the graph.
      - For each vertex, it initializes a `degree` counter to zero and calculates the degree by summing the values in the adjacency matrix corresponding to that vertex.
      - If any vertex has an odd degree (checked using `degree % 2 != 0`), the method returns `false`, indicating that the graph cannot have an Euler tour.

   3. **Depth-First Search (DFS)**:
      ```java
      boolean[] visited = new boolean[n];
      int start = 0; // Starting vertex for Euler tour
      dfs(graph, start, visited, eulerTourPath);
      ```
      - If all vertices have even degrees, the method initializes a boolean array `visited` to keep track of which vertices have been visited during the DFS.
      - It sets the starting vertex for the Euler tour to `0` and calls the `dfs` method to perform a depth-first search on the graph.

   4. **Checking for Unvisited Edges**:
      ```java
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; j++) {
            if (graph[i][j] == 1) {
                  return false;
            }
         }
      }
      ```
      - After the DFS completes, this nested loop checks the adjacency matrix to ensure that all edges have been visited.
      - If any edge remains (i.e., if `graph[i][j]` is still `1`), the method returns `false`, indicating that not all edges were traversed in the Euler tour.

   5. **Returning True**:
      ```java
      return true;
      ```
      - If all vertices have even degrees and all edges have been visited, the method returns `true`, indicating that the graph has an Euler tour.

      #### Example

      Let's consider a simple example with a graph represented by the following adjacency matrix:

      ```
      5 10
      0 1
      0 4
      0 3
      0 2
      1 2
      1 3
      1 4
      2 3
      2 4
      3 4
      ```

      - **Step 1**: The adjacency matrix for this graph is constructed as follows:
      ```
      [
         [0, 1, 1, 1, 1], // Vertex 0 is connected to 1, 2, 3, 4
         [1, 0, 1, 1, 1], // Vertex 1 is connected to 0, 2, 3, 4
         [1, 1, 0, 1, 1], // Vertex 2 is connected to 0, 1, 3, 4
         [1, 1, 1, 0, 1], // Vertex 3 is connected to 0, 1, 2, 4
         [1, 1, 1, 1, 0]  // Vertex 4 is connected to 0, 1, 2, 3
      ]
      ```

      - **Step 2**: The method `hasEulerTour` is called with `n = 5`.

      - **Step 3**: The method iterates through each vertex:
      - For **Vertex 0**:
         - The degree is calculated as:
            ```
            degree = graph[0][0] + graph[0][1] + graph[0][2] + graph[0][3] + graph[0][4]
                  = 0 + 1 + 1 + 1 + 1 = 4 (even)
            ```
      - For **Vertex 1**:
         - The degree is calculated as:
            ```
            degree = graph[1][0] + graph[1][1] + graph[1][2] + graph[1][3] + graph[1][4]
                  = 1 + 0 + 1 + 1 + 1 = 4 (even)
            ```
      - For **Vertex 2**:
         - The degree is calculated as:
            ```
            degree = graph[2][0] + graph[2][1] + graph[2][2] + graph[2][3] + graph[2][4]
                  = 1 + 1 + 0 + 1 + 1 = 4 (even)
            ```
      - For **Vertex 3**:
         - The degree is calculated as:
            ```
            degree = graph[3][0] + graph[3][1] + graph[3][2] + graph[3][3] + graph[3][4]
                  = 1 + 1 + 1 + 0 + 1 = 4 (even)
            ```
      - For **Vertex 4**:
         - The degree is calculated as:
            ```
            degree = graph[4][0] + graph[4][1] + graph[4][2] + graph[4][3] + graph[4][4]
                  = 1 + 1 + 1 + 1 + 0 = 4 (even)
            ```

      - **Step 4**: Since all vertices have even degrees, the method proceeds to perform a DFS starting from vertex 0.

      - **Step 5**: After the DFS completes, the method checks the adjacency matrix:
      - It verifies that all edges have been visited. Since all edges have been traversed during the DFS, the method returns `true`.

      ### Conclusion

      This part of the code is critical for determining whether the graph can have an Euler tour. By checking the degree of each vertex and ensuring all edges are visited, the method efficiently verifies the necessary conditions for the existence of an Euler tour. Understanding this step is fundamental to grasping how the Euler tour algorithm operates and the properties of Eulerian graphs.

2. **`dfs(int[][] graph, int vertex, boolean[] visited, List<Integer> eulerTourPath)`**:
   - **Purpose**: Performs depth-first search to find the Euler tour path.
   - **Logic**:
     - Marks the current vertex as visited.
     - Iterates through all neighbors of the current vertex.
     - If there is an edge to a neighbor, it removes the edge and recursively calls `dfs` for that neighbor.
     - After visiting all neighbors, it adds the current vertex to the beginning of the `eulerTourPath` list.
   - **Effect**: Constructs the Euler tour path as it traverses the graph.

   The `dfs` method is responsible for performing a depth-first search (DFS) on the graph to find an Euler tour path. Here's a detailed explanation of the method with an example:

   ```java
   private static void dfs(int[][] graph, int vertex, boolean[] visited, List<Integer> eulerTourPath) {
      visited[vertex] = true; // Mark the current vertex as visited
      for (int neighbor = 0; neighbor < graph.length; neighbor++) {
         if (graph[vertex][neighbor] == 1) { // If there is an edge between the current vertex and the neighbor
               graph[vertex][neighbor] = 0; // Remove the edge from the current vertex to the neighbor
               graph[neighbor][vertex] = 0; // Remove the edge from the neighbor to the current vertex
               dfs(graph, neighbor, visited, eulerTourPath); // Recursively call dfs for the neighbor vertex
         }
      }
      eulerTourPath.add(0, vertex); // Add the current vertex to the beginning of the eulerTourPath list
   }
   ```

   #### Example

   Let's consider the following graph represented by the adjacency matrix:

   ```
   [
      [0, 1, 1, 1, 1], // Vertex 0 is connected to 1, 2, 3, 4
      [1, 0, 1, 1, 1], // Vertex 1 is connected to 0, 2, 3, 4
      [1, 1, 0, 1, 1], // Vertex 2 is connected to 0, 1, 3, 4
      [1, 1, 1, 0, 1], // Vertex 3 is connected to 0, 1, 2, 4
      [1, 1, 1, 1, 0]  // Vertex 4 is connected to 0, 1, 2, 3
   ]
   ```

   1. **Initial Call**:
      - The `dfs` method is called with the starting vertex `0`, an empty `visited` array, and an empty `eulerTourPath` list.

   2. **Marking Vertex as Visited**:
      - The method marks vertex `0` as visited by setting `visited` to `true`.

   3. **Iterating through Neighbors**:
      - It iterates through all vertices (neighbors) from `0` to `4`.
      - For each neighbor, it checks if there is an edge between the current vertex (`0`) and the neighbor by checking `graph[neighbor]`.

   4. **Removing Edges**:
      - If there is an edge between vertex `0` and the current neighbor (e.g., vertex `1`), the method removes the edge by setting `graph[1]` and `graph[1]` to `0`.

   5. **Recursive Call**:
      - After removing the edge, the method recursively calls `dfs` with the neighbor vertex (e.g., `1`), the same `visited` array, and the same `eulerTourPath` list.

   6. **Marking Neighbor as Visited**:
      - Inside the recursive call, the method marks the neighbor vertex (e.g., `1`) as visited by setting `visited[1]` to `true`.

   7. **Iterating through Neighbors (Recursive)**:
      - It iterates through all vertices (neighbors) from `1` to `4`.
      - For each neighbor, it checks if there is an edge between the current vertex (`1`) and the neighbor.

   8. **Removing Edges (Recursive)**:
      - If there is an edge between vertex `1` and the current neighbor (e.g., vertex `2`), the method removes the edge by setting `graph[1]` and `graph[1]` to `0`.

   9. **Recursive Calls**:
      - The method continues to make recursive calls, exploring all connected vertices and removing edges until no more edges are left.

   10. **Adding Vertex to Path**:
      - After the recursive calls complete, the method adds the current vertex (e.g., `1`) to the beginning of the `eulerTourPath` list using `eulerTourPath.add(0, vertex)`.

   11. **Backtracking**:
      - The method continues to add vertices to the beginning of the `eulerTourPath` list as it backtracks from the recursive calls.

   12. **Final Path**:
      - The final `eulerTourPath` list will contain the vertices in the order they were visited during the DFS, forming an Euler tour path.

   The `dfs` method recursively explores the graph, removing edges as it traverses, and constructs the Euler tour path by adding vertices to the beginning of the `eulerTourPath` list. This process ensures that each edge is visited exactly once, resulting in a valid Euler tour path.

### Dry Run Example

Let's consider the following input graph:

```
5 10
0 1
0 4
0 3
0 2
1 2
1 3
1 4
2 3
2 4
3 4
```

1. **Input Processing**:
   - The program reads the graph's metadata: `5` vertices and `10` edges.
   - It initializes an adjacency matrix of size `5x5` and reads the edges, updating the matrix accordingly.

   The adjacency matrix after reading the edges will look like this:
   ```
   [
     [0, 1, 1, 1, 1],
     [1, 0, 1, 1, 1],
     [1, 1, 0, 1, 1],
     [1, 1, 1, 0, 1],
     [1, 1, 1, 1, 0]
   ]
   ```

2. **Euler Tour Check**:
   - The `hasEulerTour` method is called.
   - It calculates the degree of each vertex:
     - Vertex 0: degree = 4 (even)
     - Vertex 1: degree = 4 (even)
     - Vertex 2: degree = 4 (even)
     - Vertex 3: degree = 4 (even)
     - Vertex 4: degree = 4 (even)
   - Since all vertices have even degrees, the graph has an Euler tour.

3. **DFS and Euler Tour Construction**:
   - The `dfs` method is called starting from vertex 0.
   - It performs a depth-first search, removing edges as it traverses the graph.
   - The Euler tour path is constructed by adding vertices to the beginning of the `eulerTourPath` list.

4. **Checking Unvisited Edges**:
   - After the DFS is complete, the method checks the adjacency matrix to ensure that all edges are visited.
   - Since all edges are removed during the DFS, the method returns `true`.

5. **Output**:
   - The program prints the Euler tour path and the count:
     ```
     Euler Tour Path: 0 1 2 3 4 0 4 1 3 2 
     Euler Tour Count: 1
     ```

### Conclusion
The Euler tour algorithm efficiently finds a cycle that contains each edge of a graph exactly once, provided that every vertex has an even degree. By understanding the code and the dry run example, you can appreciate how the algorithm works and its application in graph traversal problems. This documentation provides a comprehensive overview of the code, its logic, and its functionality, ensuring clarity for future reference and understanding of Euler tours.