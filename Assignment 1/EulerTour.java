import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EulerTour {
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

  private static void dfs(int[][] graph, int vertex, boolean[] visited, List<Integer> eulerTourPath) {
    visited[vertex] = true;
    for (int neighbor = 0; neighbor < graph.length; neighbor++) {
      if (graph[vertex][neighbor] == 1) {
        graph[vertex][neighbor] = 0; // Remove edge
        graph[neighbor][vertex] = 0; // Remove edge
        dfs(graph, neighbor, visited, eulerTourPath);
      }
    }
    eulerTourPath.add(0, vertex); // Add vertex to the path
  }
}