package nz.ac.auckland.se281.temp_exceptions;

public class CountryNotFoundException extends Exception {
  public CountryNotFoundException(String message) {
    super(message);
  }
}
