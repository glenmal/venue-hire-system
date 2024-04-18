package nz.ac.auckland.se281;

// checks if a string is an integer
public abstract class canParseInt {
  public static boolean main(String string) {
    try {
      Integer.parseInt(string);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
