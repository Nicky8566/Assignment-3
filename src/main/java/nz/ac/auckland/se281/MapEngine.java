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
  private String country;
  private String continent;
  private String taxFees;
  private Map<String, List<String>> countiresInfo;
  private Map<String, List<String>> adjacenciesInfo;
  private String source;
  private String destination;

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
    while (true) {
      try {
        String country = Utils.scanner.nextLine();
        String countryName = capitalizeFirstLetterOfEachWord(country);
        getCountryInfo(countryName);
        MessageCli.COUNTRY_INFO.printMessage(
            countryName,
            countiresInfo.get(countryName).get(0),
            countiresInfo.get(countryName).get(1));
        break;
      } catch (CountryNotFoundException e) {
        MessageCli.INVALID_COUNTRY.printMessage(e.getMessage());
      }
    }
  }

  private void getCountryInfo(String countryName) throws CountryNotFoundException {
    if (!countiresInfo.containsKey(countryName)) {
      throw new CountryNotFoundException(countryName);
    }
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {
    MessageCli.INSERT_SOURCE.printMessage();
    while (true) {
      try {
        source = Utils.scanner.nextLine();
        source = capitalizeFirstLetterOfEachWord(source);
        getCountryInfo(source);
        break;
      } catch (CountryNotFoundException e) {
        MessageCli.INVALID_COUNTRY.printMessage(e.getMessage());
      }
    }
    MessageCli.INSERT_DESTINATION.printMessage();
    while (true) {
      try {
        destination = Utils.scanner.nextLine();
        destination = capitalizeFirstLetterOfEachWord(destination);
        getCountryInfo(destination);
        break;
      } catch (CountryNotFoundException e) {
        MessageCli.INVALID_COUNTRY.printMessage(e.getMessage());
      }
    }
  }
}
