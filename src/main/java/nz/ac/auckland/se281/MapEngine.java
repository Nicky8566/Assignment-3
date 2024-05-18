package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** This class is the main entry point. */
public class MapEngine {
  private String country;
  private String continent;
  private String taxFees;
  private Map<String, List<String>> countiresInfo;
  private Map<String, List<String>> adjacenciesInfo;

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
      country = parts[0];
      continent = parts[1];
      taxFees = parts[2];
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
      country = parts[0];
      List<String> adjCountries = new ArrayList<>();
      for (String adj : parts) {
        adjCountries.add(adj);
      }
      // add the adjacency object to the hashmap
      adjacenciesInfo.put(country, adjCountries);
    }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    // add code here
    // get the country name from the user
    String countryName = Utils.scanner.nextLine();
    MessageCli.COUNTRY_INFO.printMessage(countryName, countiresInfo.get(countryName).get(0),
        countiresInfo.get(countryName).get(1));
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}
}
