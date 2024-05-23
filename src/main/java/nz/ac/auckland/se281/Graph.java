package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Graph<T> {
  private HashSet<String> visitedContinents;
  private int totalTaxFees;
  MapEngine mapEngine = new MapEngine();
  private Map<String, List<String>> countiresInfo = mapEngine.getCountiresInfo();
  private Map<String, List<String>> adjacenciesInfo = mapEngine.getAdjacenciesInfo();

  public List<String> shortestPathBFS(String start, String end) {

    List<String> path = new ArrayList<>();
    Queue<List<String>> queue = new LinkedList<>(); // Queue to hold paths
    Map<String, Boolean> visited = new HashMap<>(); // Tracks visited nodes

    // setup visited contients and total tax fees for the additional info
    visitedContinents = new LinkedHashSet<>();
    totalTaxFees = 0;
    // Start with the path containing only the start node
    List<String> startPath = new ArrayList<>();
    startPath.add(start);
    queue.add(startPath);
    visited.put(start, true);

    while (!queue.isEmpty()) {
      // grab and remove first element from queue
      List<String> currentPath = queue.poll();
      // get the last node
      String lastNode = currentPath.get(currentPath.size() - 1);

      // Check if the last node is the target node and return the path
      if (lastNode.equals(end)) {
        for (String country : currentPath) {
          visitedContinents.add(countiresInfo.get(country).get(0));
          if (country == start) {
            continue;
          }
          totalTaxFees += Integer.parseInt(countiresInfo.get(country).get(1));
        }
        return currentPath;
      }

      // Explore the nodes neighbours/adjacent nodes
      for (String neighbor : adjacenciesInfo.get(lastNode)) {
        if (!visited.containsKey(neighbor)) { // Check if the neighbor has been visited
          visited.put(neighbor, true);
          // Create a new path to check by adding this current node that isnt checked
          List<String> newPath = new ArrayList<>(currentPath);
          newPath.add(neighbor);
          // will check if this path is the shortest path
          queue.add(newPath);
        }
      }
    }
    // Return empty path if no path exists
    return path;
  }
}
