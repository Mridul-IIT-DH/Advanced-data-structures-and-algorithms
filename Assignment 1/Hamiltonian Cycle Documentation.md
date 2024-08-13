# Detailed Documentation for Hamiltonian Cycle Code

### Introduction
A **Hamiltonian cycle** in a graph is a cycle that visits each vertex exactly once and returns to the starting vertex. Finding a Hamiltonian cycle is an NP-complete problem, meaning it is computationally difficult to solve for large graphs. This documentation explains the code that checks for Hamiltonian cycles in a graph, breaking down each part of the code for clarity.

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
   - **`ArrayList` and `List`**: Used for dynamic array operations, specifically to store the Hamiltonian cycle path.

2. **Class Declaration**:
   ```java
   public class HamiltonianCycle {
   ```
   - This declares the class `HamiltonianCycle`, which contains the main method and other methods for processing the graph.

3. **Main Method**:
   ```java
   public static void main(String[] args) {
   ```
   - The entry point of the program where execution begins.

4. **Variable Initialization**:
   ```java
   String filename = "graphs.txt";
   int hamiltonianCycleCount = 0;
   ```
   - **`filename`**: The name of the file containing graph data.
   - **`hamiltonianCycleCount`**: A counter to keep track of the number of graphs that have a Hamiltonian cycle.

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
]
```

### Conclusion
This part of the code is essential for initializing the graph representation based on the input file. By extracting the number of vertices and edges, the program can dynamically allocate the necessary data structures to represent the graph, allowing for further processing such as checking for Hamiltonian cycles. Understanding this step is crucial for grasping how the graph is constructed and manipulated in the subsequent parts of the program.

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
   - This loop iterates `m` times, where `m` is the number of edges specified in the graph metadata.

2. **Reading the Edge Line**:
   ```java
   line = reader.readLine(); // Read the next line for the edge
   ```
   - The `readLine()` method reads the next line from the input file, which contains the two vertices that form an edge.

3. **Splitting the Edge Line**:
   ```java
   parts = line.trim().split("\\s+"); // Split the edge line
   ```
   - The `trim()` method removes any leading or trailing whitespace from the line.
   - The `split("\\s+")` method splits the trimmed line into an array of strings based on one or more whitespace characters.

4. **Extracting Vertex Identifiers**:
   ```java
   int u = Integer.parseInt(parts[0]); // Start vertex
   int v = Integer.parseInt(parts[1]); // End vertex
   ```
   - Converts the first element of the `parts` array (representing the start vertex) from a string to an integer and assigns it to `u`.
   - Converts the second element (representing the end vertex) from a string to an integer and assigns it to `v`.

5. **Updating the Adjacency Matrix**:
   ```java
   graph[u][v] = 1; // Set the edge in the adjacency matrix
   graph[v][u] = 1; // Ensure the graph is undirected
   ```
   - Updates the adjacency matrix to indicate the presence of an edge between vertices `u` and `v`.

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
This part of the code is essential for constructing the graph's representation in memory. By reading the edges from the input file and updating the adjacency matrix, the program prepares the graph for further processing, such as checking for Hamiltonian cycles. Understanding this step is crucial for grasping how the graph is built and manipulated in the subsequent parts of the program.

9. **Checking for Hamiltonian Cycle**:
   ```java
   if (hasHamiltonianCycle(graph, path)) {
       hamiltonianCycleCount++;
       printHamiltonianCycle(path); // Print the Hamiltonian cycle path
   }
   ```
   - Calls the `hasHamiltonianCycle` method to check if the graph has a Hamiltonian cycle.
   - If a Hamiltonian cycle exists, it increments the `hamiltonianCycleCount` and calls the `printHamiltonianCycle` method to print the cycle path.

#### Detailed Explanation
1. **Checking for a Hamiltonian Cycle**:
   ```java
   if (hasHamiltonianCycle(graph, path)) {
   ```
   - The `hasHamiltonianCycle` method is called with the adjacency matrix `graph` and the `path` list.
   - This method checks whether the last vertex in the path is connected to the first vertex, forming a Hamiltonian cycle.

2. **Incrementing the Hamiltonian Cycle Count**:
   ```java
   hamiltonianCycleCount++;
   ```
   - This line increments the `hamiltonianCycleCount` variable by 1, indicating that one more Hamiltonian cycle has been found.

3. **Printing the Hamiltonian Cycle Path**:
   ```java
   printHamiltonianCycle(path); // Print the Hamiltonian cycle path
   ```
   - Calls the `printHamiltonianCycle` method to print the vertices in the Hamiltonian cycle path.

### Code Explanation for `printHamiltonianCycle` Method
The `printHamiltonianCycle` method is responsible for printing the vertices in the Hamiltonian cycle path. Here's the detailed breakdown of the method:
```java
private static void printHamiltonianCycle(List<Integer> path) {
    System.out.print("Hamiltonian Cycle Path: ");
    for (int vertex : path) {
        System.out.print(vertex + " ");
    }
    System.out.println(path.get(0)); // Print the starting vertex to complete the cycle
}
```

#### Detailed Explanation
1. **Method Signature**:
   - The method is declared as `private static`, meaning it can be called without creating an instance of the class.
   - It takes one parameter:
     - `path`: A list containing the vertices in the order they are visited in the Hamiltonian cycle.

2. **Printing the Cycle Path**:
   ```java
   System.out.print("Hamiltonian Cycle Path: ");
   ```
   - Prints the message "Hamiltonian Cycle Path: " to indicate that the following output will represent the cycle path.

3. **Iterating Through the Path**:
   ```java
   for (int vertex : path) {
       System.out.print(vertex + " ");
   }
   ```
   - This loop iterates through each vertex in the `path` list and prints it, separated by spaces.

4. **Completing the Cycle**:
   ```java
   System.out.println(path.get(0)); // Print the starting vertex to complete the cycle
   ```
   - After printing all vertices, it prints the starting vertex (the first vertex in the path) to complete the cycle representation.

### Conclusion
This documentation provides a detailed explanation of the Hamiltonian cycle detection algorithm implemented in the provided Java code. By understanding each component, from file reading to graph representation and Hamiltonian cycle checking, one can appreciate the algorithm's functionality and its application in graph theory.