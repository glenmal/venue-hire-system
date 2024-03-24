package nz.ac.auckland.se281;

import java.util.ArrayList;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;


class Venue {
  String venue_name;
  String venue_code;
  String capacity_input;
  String hire_fee;
  Venue(String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    venue_name = venueName;
    venue_code = venueCode;
    capacity_input = capacityInput;
    hire_fee = hireFeeInput;
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
    for () {
      
      }
    if ()
      System.err.println();
    else if ()
       
    else {
      new_venue.add(venueName);
      new_venue.add(venueCode);
      new_venue.add(capacityInput);
      new_venue.add(hireFeeInput);
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
