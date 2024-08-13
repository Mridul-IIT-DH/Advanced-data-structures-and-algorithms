import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HamiltonianCycle {
  public static void main(String[] args) {
    String filename = "graphs.txt";
    int hamiltonianCycleCount = 0;

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

          List<Integer> path = new ArrayList<>();
          for (int i = 0; i < n; i++) {
            path.add(i);
          }

          if (hasHamiltonianCycle(graph, path)) {
            hamiltonianCycleCount++;
            printHamiltonianCycle(path); // Print the Hamiltonian cycle path
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("Hamiltonian Cycle Count: " + hamiltonianCycleCount);
  }

  private static boolean hasHamiltonianCycle(int[][] graph, List<Integer> path) {
    // Check if the last vertex in the path is connected to the first vertex
    if (graph[path.get(path.size() - 1)][path.get(0)] == 1) {
      return true; // Hamiltonian cycle found
    }
    return false; // No cycle found
  }

  private static void printHamiltonianCycle(List<Integer> path) {
    System.out.print("Hamiltonian Cycle Path: ");
    for (int vertex : path) {
      System.out.print(vertex + " ");
    }
    System.out.println(path.get(0)); // Print the starting vertex to complete the cycle
  }
}