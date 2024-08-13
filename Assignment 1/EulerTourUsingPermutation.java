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

  private static boolean checkEulerPath(List<Integer> vertices, int[][] graph,
      List<Integer> eulerTourPath) {
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