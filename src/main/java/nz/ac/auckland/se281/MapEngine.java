package nz.ac.auckland.se281;

import static nz.ac.auckland.se281.Utils.capitalizeFirstLetterOfEachWord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nz.ac.auckland.se281.Exceptions.CountryNotFoundException;

// import the utils method to capitalize the first letter of each word

/** This class is the main entry point. */
public class MapEngine {
  private Map<String, List<String>> countiresInfo;
  private Map<String, List<String>> adjacenciesInfo;

  public Map<String, List<String>> getCountiresInfo() {
    return countiresInfo;
  }

  public Map<String, List<String>> getAdjacenciesInfo() {
    return adjacenciesInfo;
  }

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
      } catch (IllegalArgumentException e) {
        MessageCli.INVALID_COUNTRY.printMessage(e.getMessage());
      }
    }
  }

  private void validateCountryExists(String country) throws CountryNotFoundException {
    if (!countiresInfo.containsKey(country)) {
      throw new CountryNotFoundException(country);
    }
    if (!isValidCountryName(country)) {
      throw new IllegalArgumentException(country);
    }
  }

  private boolean isValidCountryName(String country) {
    for (char c : country.toCharArray()) {
      if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
        return false;
      }
    }

    return true;
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {
    MessageCli.INSERT_SOURCE.printMessage();
    String source = getValidCountry();
    MessageCli.INSERT_DESTINATION.printMessage();
    String destination = getValidCountry();
    // make a new graph of type string
    Graph<String> graph = new Graph<>();
    graph.shortestPathBFS(source, destination);
  }
}
