package nz.ac.auckland.se281.exceptions;

public class CountryNotFoundException extends Exception {
  /** takes a message and passes it to the super class. */
  public CountryNotFoundException(String message) {
    super(message);
  }
}
