package nz.ac.auckland.se281;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Graph<T> {
  private MapEngine mapEngine = new MapEngine();
  private Map<String, List<String>> countiresInfo = mapEngine.getCountiresInfo();
  private Map<String, List<String>> adjacenciesInfo = mapEngine.getAdjacenciesInfo();

  public void findShortestPath(String start, String end) {
    if (end.equals(start)) {
      MessageCli.NO_CROSSBORDER_TRAVEL.printMessage();
      return;
    }
    Queue<List<String>> queue = new LinkedList<>(); // Queue to hold paths
    Map<String, Boolean> visited = new HashMap<>(); // Tracks visited nodes
    Set<String> visitedContinents = new LinkedHashSet<>(); // Tracks visited continents
    int totalTaxFees = 0; // set up intial tax fees

    // Start with the path containing only the start node
    List<String> startPath = new LinkedList<>();
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
        MessageCli.ROUTE_INFO.printMessage(currentPath.toString());
        MessageCli.CONTINENT_INFO.printMessage(visitedContinents.toString());
        MessageCli.TAX_INFO.printMessage(Integer.toString(totalTaxFees));
      }

      // Explore the nodes neighbours/adjacent nodes
      for (String neighbor : adjacenciesInfo.get(lastNode)) {
        if (!visited.containsKey(neighbor)) { // Check if the neighbor has been visited
          visited.put(neighbor, true);
          // Create a new path to check by adding this current node that isnt checked
          List<String> newPath = new LinkedList<>(currentPath);
          newPath.add(neighbor);
          // will check if this path is the shortest path
          queue.add(newPath);
        }
      }
    }
  }
}
