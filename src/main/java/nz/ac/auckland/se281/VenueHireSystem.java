package nz.ac.auckland.se281;

import java.util.ArrayList;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;


class Venue {
  String venue_name;
  String venue_code;
  String capacity_input;
  String hire_fee;
  public Venue(String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    venue_name = venueName;
    venue_code = venueCode;
    capacity_input = capacityInput;
    hire_fee = hireFeeInput;
  }
}

// checks if a string is an integer
class canParseInt {
  public boolean main(String string) {
    try {
      int num = Integer.parseInt(string);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}

public class VenueHireSystem {

  ArrayList<Venue> venue_list;

  public VenueHireSystem() {

  }

  public void printVenues() {
    // TODO implement this method
  }

  public void createVenue(String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    // TODO implement this method
    Venue new_venue = new Venue(hireFeeInput, hireFeeInput, hireFeeInput, hireFeeInput);
    int venue_id = -1;
    for (int i=0; i < this.venue_list.size();i++) {
      if (venueCode == this.venue_list.get(i).venue_code) {
        venue_id = i;
      }
    }
    if (venueName.replaceAll("\\s", "").isEmpty())
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
    else if (venue_id < 0)
      MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode,this.venue_list.get(venue_id).venue_code);
    else {
      try {
        Integer.parseInt(hireFeeInput);
        Integer.parseInt(capacityInput);  
      } catch (NumberFormatException e) {
        // TODO: handle exception

      }

      this.venue_list.add(new_venue);
      MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName,venueCode);
    }
  }

  public void setSystemDate(String dateInput) {
    // TODO implement this method
  }

  public void printSystemDate() {
    // TODO implement this method
  }

  public void makeBooking(String[] options) {
    // TODO implement this method
  }

  public void printBookings(String venueCode) {
    // TODO implement this method
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }
}
