package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Collections;

public abstract class numToWords {
  public static String main(Integer num) {
    ArrayList<String> conversion = new ArrayList<String>();
    Collections.addAll(
        conversion, "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
    return conversion.get(num);
  }
}
