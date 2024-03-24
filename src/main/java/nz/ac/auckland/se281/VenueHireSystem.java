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
  public static boolean main(String string) {
    try {
      Integer.parseInt(string);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}

public class VenueHireSystem {

  ArrayList<Venue> venue_list = new ArrayList<Venue>();

  public VenueHireSystem() {

  }

  public void printVenues() {
    Integer size = this.venue_list.size();
    // TODO implement this method
    if (size == 1) {
      MessageCli.NUMBER_VENUES.printMessage("is","one","");
      for (int i=0;i < size;i++) {
        MessageCli.VENUE_ENTRY.printMessage(((Venue) this.venue_list.get(i)).venue_name,((Venue) this.venue_list.get(i)).venue_code,((Venue) this.venue_list.get(i)).capacity_input,((Venue) this.venue_list.get(i)).hire_fee);
      }
    }
    else if (size == 2) {
      MessageCli.NUMBER_VENUES.printMessage("are","two","s");
      for (int i=0;i < size;i++) {
        MessageCli.VENUE_ENTRY.printMessage(((Venue) this.venue_list.get(i)).venue_name,((Venue) this.venue_list.get(i)).venue_code,((Venue) this.venue_list.get(i)).capacity_input,((Venue) this.venue_list.get(i)).hire_fee);
      }
    }
    else if (size > 2) {
      MessageCli.NUMBER_VENUES.printMessage("are",size.toString(),"s");
      for (int i=0;i < size;i++) {
        MessageCli.VENUE_ENTRY.printMessage(((Venue) this.venue_list.get(i)).venue_name,((Venue) this.venue_list.get(i)).venue_code,((Venue) this.venue_list.get(i)).capacity_input,((Venue) this.venue_list.get(i)).hire_fee);
      }    
    }
    else if (size <= 0) {
      MessageCli.NO_VENUES.printMessage();
    }
  }

  public void createVenue(String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    // TODO implement this method
    Venue new_venue = new Venue(venueName, venueCode, capacityInput, hireFeeInput);
    Boolean valid_venue = true;
    if (venueName.replaceAll("\\s", "").isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      valid_venue = false;
    }

    if (!canParseInt.main(capacityInput)) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      valid_venue = false;
    }
    else if (Integer.parseInt(capacityInput) < 0) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity"," positive");
      valid_venue = false;
    }

    if (!canParseInt.main(hireFeeInput)) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      valid_venue = false;
    }
    else if (Integer.parseInt(hireFeeInput) < 0) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee"," positive");
      valid_venue = false;
    }

    String v_name = "";
    if (valid_venue) {
      for (int i=0;i < this.venue_list.size();i++) {
        if (((Venue) this.venue_list.get(i)).venue_code.contains(venueCode)) {
          v_name = ((Venue) this.venue_list.get(i)).venue_name;
          MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode,v_name);
          break;
        }
        else if (i==this.venue_list.size()-1) {
          this.venue_list.add(new_venue);
          MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName,venueCode);
        }
      }
      if (this.venue_list.size() == 0) {
        this.venue_list.add(new_venue);
        MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName,venueCode);
      }
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
