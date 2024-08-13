# Detailed Documentation for Euler Tour Code Using Permutations

### Introduction
An **Euler tour** of a simple undirected graph $ G $ is a cycle that visits every edge exactly once. A connected graph has an Euler tour if and only if every vertex has an even degree. This documentation explains the code that implements the Euler tour algorithm using permutations, breaking down each part of the code for clarity, including detailed explanations of the methods with examples.

### Main Code
```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EulerTourUsingPermutation {
    public static void main(String[] args) {
        String filename = "graphs.txt";
        int eulerTourCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 2) {
                    int n = Integer.parseInt(parts[0]);
                    int m = Integer.parseInt(parts[1]);
                    int[][] graph = new int[n][n];

                    for (int i = 0; i < m; i++) {
                        line = reader.readLine();
                        parts = line.trim().split("\\s+");
                        int u = Integer.parseInt(parts[0]);
                        int v = Integer.parseInt(parts[1]);
                        graph[u][v] = 1;
                        graph[v][u] = 1;
                    }

                    List<Integer> eulerTourPath = new ArrayList<>();
                    if (hasEulerTour(graph, n, eulerTourPath)) {
                        System.out.println("Euler Tour Path:");
                        for (int vertex : eulerTourPath) {
                            System.out.print(vertex + " ");
                        }
                        System.out.println();
                        eulerTourCount++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Euler Tour Count: " + eulerTourCount);
    }

    private static boolean hasEulerTour(int[][] graph, int n, List<Integer> eulerTourPath) {
        // Check if all vertices have even degrees
        for (int i = 0; i < n; i++) {
            int degree = 0;
            for (int j = 0; j < n; j++) {
                degree += graph[i][j];
            }
            if (degree % 2 != 0) {
                return false;
            }
        }

        // Generate all permutations of vertices
        List<Integer> vertices = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vertices.add(i);
        }
        return generatePermutations(vertices, graph, eulerTourPath);
    }

    private static boolean generatePermutations(List<Integer> vertices, int[][] graph, List<Integer> eulerTourPath) {
        return permute(vertices, 0, graph, eulerTourPath);
    }

    private static boolean permute(List<Integer> vertices, int start, int[][] graph, List<Integer> eulerTourPath) {
        if (start == vertices.size() - 1) {
            return checkEulerPath(vertices, graph, eulerTourPath);
        }

        for (int i = start; i < vertices.size(); i++) {
            swap(vertices, start, i);
            if (permute(vertices, start + 1, graph, eulerTourPath)) {
                return true;
            }
            swap(vertices, start, i); // backtrack
        }
        return false;
    }

    private static boolean checkEulerPath(List<Integer> vertices, int[][] graph, List<Integer> eulerTourPath) {
        // Check if the path represented by vertices is a valid Euler tour
        for (int i = 0; i < vertices.size() - 1; i++) {
            int u = vertices.get(i);
            int v = vertices.get(i + 1);
            if (graph[u][v] == 0) {
                return false; // No edge exists
            }
            graph[u][v] = 0; // Remove edge
            graph[v][u] = 0; // Remove edge
        }
        eulerTourPath.addAll(vertices); // Add the valid path to the eulerTourPath
        return true;
    }

    private static void swap(List<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
```

### Code Explanation

1. **Imports**:
   ```java
   import java.io.BufferedReader;
   import java.io.FileReader;
   import java.io.IOException;
   import java.util.ArrayList;
   import java.util.List;
   ```
   - **`BufferedReader`**: For efficient reading of text from a character input stream.
   - **`FileReader`**: To read the contents of a file.
   - **`IOException`**: To handle input/output exceptions.
   - **`ArrayList` and `List`**: For dynamic array operations, specifically to store the Euler tour path and vertices.

2. **Class Declaration**:
   ```java
   public class EulerTourUsingPermutation {
   ```
   - This declares the class `EulerTourUsingPermutation`, which contains the main method and other methods for processing the graph.

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
   - The `try-with-resources` statement ensures that the reader is closed automatically after use.

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

### Example
Let's consider the following input line from the file:
```plaintext
5 10
```
- **Step 1**: The line is read into the variable `line`.
- **Step 2**: After trimming and splitting, the `parts` array will look like this:
  ```java
  parts = ["5", "10"]
  ```
- **Step 3**: The condition `parts.length == 2` is satisfied since there are two elements in the array.
- **Step 4**: The number of vertices `n` is set to `5`, and the number of edges `m` is set to `10`.
- **Step 5**: An adjacency matrix of size $ 5 \times 5 $ is created, initialized to all zeros:
```java
graph = [
    [0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0]
];
```

### Conclusion
This part of the code is essential for initializing the graph representation based on the input file. By extracting the number of vertices and edges, the program can dynamically allocate the necessary data structures to represent the graph, allowing for further processing such as checking for Euler tours. Understanding this step is crucial for grasping how the graph is constructed and manipulated in the subsequent parts of the program.

8. **Reading Edges**:
   ```java
   for (int i = 0; i < m; i++) {
       line = reader.readLine();
       parts = line.trim().split("\\s+");
       int u = Integer.parseInt(parts[0]);
       int v = Integer.parseInt(parts[1]);
       graph[u][v] = 1;
       graph[v][u] = 1;
   ```
   - This loop reads the edges of the graph.
   - For each edge, it reads the line, splits it to get the vertices `u` and `v`, and updates the adjacency matrix to indicate the presence of an edge between `u` and `v`.

#### Detailed Explanation
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
   ```plaintext
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
```plaintext
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
```java
[
    [0, 1, 0, 0, 0],
    [1, 0, 0, 0, 0],
    [0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0]
]
```
- **Subsequent Iterations**: The edges `0 4`, `0 3`, `0 2`, etc., will be processed similarly, updating the adjacency matrix accordingly.
- **Final Adjacency Matrix**: After all edges have been processed, the final adjacency matrix will look like this:
```java
[
    [0, 1, 1, 1, 1],
    [1, 0, 1, 1, 1],
    [1, 1, 0, 1, 1],
    [1, 1, 1, 0, 1],
    [1, 1, 1, 1, 0]
]
```

### Conclusion
This part of the code is essential for constructing the graph's representation in memory. By reading the edges from the input file and updating the adjacency matrix, the program prepares the graph for further processing, such as checking for Euler tours. Understanding this step is crucial for grasping how the graph is built and manipulated in the subsequent parts of the program.

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
   ```
   - Calls the `hasEulerTour` method to check if the graph has an Euler tour.
   - If an Euler tour exists, it prints the path and increments the `eulerTourCount`.

#### Detailed Explanation
1. **Creating the Euler Tour Path List**:
   ```java
   List<Integer> eulerTourPath = new ArrayList<>();
   ```
   - This line initializes a new `ArrayList` called `eulerTourPath` that will be used to store the vertices in the order they are visited during the Euler tour.

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

4. **Incrementing the Euler Tour Count**:
   ```java
   eulerTourCount++;
   ```
   - This line increments the `eulerTourCount` variable by 1, indicating that one more Euler tour has been found.

#### Example
Let's consider an example graph represented by the following input:
```plaintext
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
```java
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
```java
eulerTourPath = [0, 1, 2, 3, 4, 0, 4, 1, 3, 2]
```
- **Step 5**: The program prints the Euler tour path:
```plaintext
Euler Tour Path: 0 1 2 3 4 0 4 1 3 2
```
- **Step 6**: The `eulerTourCount` is incremented to reflect that one Euler tour has been found.

### Method Descriptions

1. **`hasEulerTour(int[][] graph, int n, List<Integer> eulerTourPath)`**:
   - **Purpose**: Checks if the graph has an Euler tour by verifying if every vertex has an even degree and generating all permutations of vertices to find a valid Euler tour path.
   - **Logic**:
     - Iterates through each vertex and counts the number of edges connected to it (degree).
     - If any vertex has an odd degree, it returns `false`.
     - If all vertices have even degrees, it generates all permutations of vertices and checks if any permutation represents a valid Euler tour path.
   - **Returns**: `true` if an Euler tour exists; otherwise, `false`.

#### Example for `hasEulerTour`
Let's assume we have the following adjacency matrix for a graph with 5 vertices:
```java
int[][] graph = {
    {0, 1, 1, 1, 1},
    {1, 0, 1, 1, 1},
    {1, 1, 0, 1, 1},
    {1, 1, 1, 0, 1},
    {1, 1, 1, 1, 0}
};
```
- **Step 1**: The method initializes a loop to check the degree of each vertex.
- **Step 2**: For vertex 0, the degree is calculated as:
  - Degree of vertex 0 = 1 + 1 + 1 + 1 = 4 (even)
- **Step 3**: This process continues for vertices 1, 2, 3, and 4, all of which have even degrees.
- **Step 4**: Since all vertices have even degrees, the method proceeds to generate permutations.

### 2. **`generatePermutations(List<Integer> vertices, int[][] graph, List<Integer> eulerTourPath)`**:
   - **Purpose**: Generates all permutations of vertices and checks if any permutation represents a valid Euler tour path.
   - **Logic**:
     - Calls the `permute` method with the initial list of vertices, starting from index 0.
   - **Returns**: `true` if a valid Euler tour path is found; otherwise, `false`.

#### Example for `generatePermutations`
Assuming we have the vertices `[0, 1, 2, 3, 4]`, the method simply calls:
```java
return permute(vertices, 0, graph, eulerTourPath);
```
- This initiates the permutation generation process starting from index 0.

### 3. **`permute(List<Integer> vertices, int start, int[][] graph, List<Integer> eulerTourPath)`**:
   - **Purpose**: Recursively generates all permutations of vertices starting from the given `start` index.
   - **Logic**:
     - If `start` is equal to `vertices.size() - 1`, it means we have generated a complete permutation, so it calls `checkEulerPath` to check if the permutation represents a valid Euler tour path.
     - Otherwise, it iterates through the remaining vertices starting from `start` and swaps each vertex with the vertex at index `start`.
     - For each swap, it recursively calls `permute` with `start + 1` to generate the next permutation.
     - After the recursive call, it swaps the vertices back to their original positions to backtrack.
   - **Returns**: `true` if a valid Euler tour path is found; otherwise, `false`.

#### Example for `permute`
Assuming we start with the vertices `[0, 1, 2]`:
- **Step 1**: The method starts with `start = 0`.
- **Step 2**: It swaps `0` with itself and calls `permute` with `start = 1`.
- **Step 3**: Now it swaps `1` with itself and calls `permute` with `start = 2`.
- **Step 4**: At this point, `start` equals `vertices.size() - 1`, so it calls `checkEulerPath`.

### 4. **`checkEulerPath(List<Integer> vertices, int[][] graph, List<Integer> eulerTourPath)`**:
   - **Purpose**: Checks if the given permutation of vertices represents a valid Euler tour path.
   - **Logic**:
     - Iterates through the vertices in the given permutation and checks if there exists an edge between consecutive vertices.
     - If an edge exists, it removes the edge from the graph by setting the corresponding entries in the adjacency matrix to 0.
     - If all edges are successfully removed, it means the permutation represents a valid Euler tour path, so it adds the vertices to the `eulerTourPath` list.
   - **Returns**: `true` if the permutation represents a valid Euler tour path; otherwise, `false`.

#### Example for `checkEulerPath`
Given the permutation `[0, 1, 2, 3, 4]`:
- **Step 1**: The method checks the edge between `0` and `1`. Since `graph[1]` is `1`, it proceeds to remove the edge:
  ```java
  graph[0][1] = 0; 
  graph[1][0] = 0;
  ```
- **Step 2**: It checks the edge between `1` and `2`, removes it, and continues this process for all pairs.
- **Step 3**: If all edges are removed successfully, the method adds the vertices to `eulerTourPath`:
```java
eulerTourPath.addAll(vertices); // eulerTourPath becomes [0, 1, 2, 3, 4]
```
- **Step 4**: The method returns `true`, indicating that the permutation represents a valid Euler tour path.

### 5. **`swap(List<Integer> list, int i, int j)`**:
   - **Purpose**: Swaps the elements at indices `i` and `j` in the given list.
   - **Logic**:
     - Stores the element at index `i` in a temporary variable.
     - Sets the element at index `i` to the element at index `j`.
     - Sets the element at index `j` to the temporary variable.

#### Example for `swap`
Given a list `[0, 1, 2]` and indices `0` and `1`:
- **Step 1**: The method stores `0` in a temporary variable.
- **Step 2**: It sets `list` to `list[1]`, resulting in the list becoming `[1, 1, 2]`.
- **Step 3**: It sets `list[1]` to the temporary variable, resulting in the final list being `[1, 0, 2]`.

### Conclusion
This documentation provides a comprehensive breakdown of the Euler tour algorithm implemented using permutations in the provided Java code. By understanding each component, from file reading to graph representation, generating permutations, and checking for valid Euler tour paths, one can appreciate the algorithm's functionality and its application in graph theory. Each method is designed to work together to efficiently determine whether an Euler tour exists and to find the tour if it does.