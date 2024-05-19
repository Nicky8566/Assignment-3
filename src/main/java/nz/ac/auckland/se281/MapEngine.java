package nz.ac.auckland.se281;

import static nz.ac.auckland.se281.Utils.capitalizeFirstLetterOfEachWord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import nz.ac.auckland.se281.Exceptions.CountryNotFoundException;

// import the utils method to capitalize the first letter of each word

/** This class is the main entry point. */
public class MapEngine {
  private Map<String, List<String>> countiresInfo;
  private Map<String, List<String>> adjacenciesInfo;
  // make a new hashset for the visisted continets
  private HashSet<String> visitedContinents;
  private int totalTaxFees;

  public MapEngine() {
    // add other code here if you want
    // intialize the maps
    countiresInfo = new HashMap<>();
    adjacenciesInfo = new HashMap<>();
    loadMap(); // keep this mehtod invocation
  }

  /** invoked one time only when constracting the MapEngine class. */
  private void loadMap() {
    List<String> countries = Utils.readCountries();
    List<String> adjacencies = Utils.readAdjacencies();
    // add code here to create your data structures

    // iterate through the countries list
    for (String c : countries) {
      // split the sountry string when theres a , into 3 parts
      String[] parts = c.split(",");
      // create a new country object with the 3 parts
      String country = parts[0];
      String continent = parts[1];
      String taxFees = parts[2];
      List<String> info = new ArrayList<>();
      info.add(continent);
      info.add(taxFees);
      // add the country object to the hashmap
      countiresInfo.put(country, info);
    }
    // iterate through the adjacencies list
    for (String a : adjacencies) {
      // split the adjacency string when theres a , into 2 parts
      String[] parts = a.split(",");
      // create a new adjacency object with the 2 parts
      String country = parts[0];
      List<String> adjCountries = new ArrayList<>();
      for (int i = 1; i < parts.length; i++) {
        adjCountries.add(parts[i]);
      }
      // add the adjacency object to the hashmap
      adjacenciesInfo.put(country, adjCountries);
    }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    // add code here
    // get the country name from the user
    MessageCli.INSERT_COUNTRY.printMessage();
    String countryName = getValidCountry();
    MessageCli.COUNTRY_INFO.printMessage(
        countryName, countiresInfo.get(countryName).get(0), countiresInfo.get(countryName).get(1));
  }

  private String getValidCountry() {
    while (true) {
      try {
        String country = Utils.scanner.nextLine();
        country = capitalizeFirstLetterOfEachWord(country);
        validateCountryExists(country);
        return country;
      } catch (CountryNotFoundException e) {
        MessageCli.INVALID_COUNTRY.printMessage(e.getMessage());
      }
    }
  }

  private void validateCountryExists(String country) throws CountryNotFoundException {
    if (!countiresInfo.containsKey(country)) {
      throw new CountryNotFoundException(country);
    }
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {
    MessageCli.INSERT_SOURCE.printMessage();
    String source = getValidCountry();
    MessageCli.INSERT_DESTINATION.printMessage();
    String destination = getValidCountry();
    if (destination.equals(source)) {
      MessageCli.NO_CROSSBORDER_TRAVEL.printMessage();
      return;
    }
    MessageCli.ROUTE_INFO.printMessage(shortestPathBFS(source, destination).toString());
    MessageCli.CONTINENT_INFO.printMessage(visitedContinents.toString());
    MessageCli.TAX_INFO.printMessage(Integer.toString(totalTaxFees));
  }

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
