package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** This class is the main entry point. */
public class MapEngine {
  private String country;
  private String continent;
  private String taxFees;
  private ArrayList<String> info;
  private Map<String, List<String>> countiresInfo;

  public MapEngine() {
    // add other code here if you want
    loadMap(); // keep this mehtod invocation
  }

  /** invoked one time only when constracting the MapEngine class. */
  private void loadMap() {
    List<String> countries = Utils.readCountries();
    List<String> adjacencies = Utils.readAdjacencies();
    // add code here to create your data structures
    // intiate new hashmap

    for (String c : countries) {
      // split the sountry string when theres a , into 3 parts
      String[] parts = c.split(",");
      // create a new country object with the 3 parts
      country = parts[0];
      continent = parts[1];
      taxFees = parts[2];
      info = new ArrayList<String>();
      info.add(continent);
      info.add(taxFees);
      // add the country object to the hashmap
      countiresInfo.put(country, info);
    }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    // add code here
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}
}
