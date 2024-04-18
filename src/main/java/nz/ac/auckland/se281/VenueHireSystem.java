package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Collections;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

class numToWords {
  public static String main(Integer num) {
    ArrayList<String> conversion = new ArrayList<String>();
    Collections.addAll(
        conversion, "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
    return conversion.get(num);
  }
}

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

class Booking {
  String venue_name;
  String venue_code;
  String adj_capacity;
  String date_booked = "";

  public Booking(String venueName, String venueCode, String adjCapacity, String date_booked) {
    this.venue_name = venueName;
    this.venue_code = venueCode;
    this.adj_capacity = adjCapacity;
    this.date_booked = date_booked;
  }
}

// checks if a string is an integer
abstract class canParseInt {
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

  ArrayList<Venue> venue_list;
  ArrayList<Booking> booking_list;
  String system_date;

  public VenueHireSystem() {
    this.system_date = "";
    this.booking_list = new ArrayList<Booking>();
    this.venue_list = new ArrayList<Venue>();
  }

  // check if venue already booked
  public String checkBookings(String v_code) {
    String[] prev_last_date = {this.system_date, ""};
    for (int i = 0; i < this.booking_list.size(); i++) {
      if ((((Booking) this.booking_list.get(i)).venue_code.contains(v_code))) {
        prev_last_date[1] = ((Booking) this.booking_list.get(i)).date_booked;
        if (check5(0, prev_last_date[0], prev_last_date) == 0) {
          prev_last_date[0] = prev_last_date[1];
        }
      }
    }
    prev_last_date[1] = this.system_date;
    String[] dateParts_options = prev_last_date[0].split("/");
    Integer day = Integer.parseInt(dateParts_options[0]);
    if (check5(0, prev_last_date[0], prev_last_date) != 1) {
      return system_date;
    } else {
      day++;
    }
    String day_zeros = "";
    Integer month = Integer.parseInt(dateParts_options[1]);
    String month_zeros = "";
    Integer year = Integer.parseInt(dateParts_options[2]);
    if (day < 10) {
      day_zeros = "0";
    }

    if (month < 10) {
      month_zeros = "0";
    }

    return day_zeros
        + day.toString()
        + "/"
        + month_zeros
        + month.toString()
        + "/"
        + year.toString();
  }

  public void printVenues() {
    Integer size = this.venue_list.size();
    // TODO implement this method
    if (size <= 0) {
      MessageCli.NO_VENUES.printMessage();
    } else if (size == 1) {
      MessageCli.NUMBER_VENUES.printMessage("is", numToWords.main(size), "");
      for (int i = 0; i < size; i++) {
        MessageCli.VENUE_ENTRY.printMessage(
            ((Venue) this.venue_list.get(i)).venue_name,
            ((Venue) this.venue_list.get(i)).venue_code,
            ((Venue) this.venue_list.get(i)).capacity_input,
            ((Venue) this.venue_list.get(i)).hire_fee,
            checkBookings(((Venue) this.venue_list.get(i)).venue_code));
      }
    } else if (size < 10) {
      MessageCli.NUMBER_VENUES.printMessage("are", numToWords.main(size), "s");
      for (int i = 0; i < size; i++) {
        MessageCli.VENUE_ENTRY.printMessage(
            ((Venue) this.venue_list.get(i)).venue_name,
            ((Venue) this.venue_list.get(i)).venue_code,
            ((Venue) this.venue_list.get(i)).capacity_input,
            ((Venue) this.venue_list.get(i)).hire_fee,
            checkBookings(((Venue) this.venue_list.get(i)).venue_code));
      }
    } else if (size >= 10) {
      MessageCli.NUMBER_VENUES.printMessage("are", size.toString(), "s");
      for (int i = 0; i < size; i++) {
        MessageCli.VENUE_ENTRY.printMessage(
            ((Venue) this.venue_list.get(i)).venue_name,
            ((Venue) this.venue_list.get(i)).venue_code,
            ((Venue) this.venue_list.get(i)).capacity_input,
            ((Venue) this.venue_list.get(i)).hire_fee,
            checkBookings(((Venue) this.venue_list.get(i)).venue_code));
      }
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    Venue new_venue = new Venue(venueName, venueCode, capacityInput, hireFeeInput);
    Boolean valid_venue = true;
    if (venueName.replaceAll("\\s", "").isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      valid_venue = false;
    }

    if (!canParseInt.main(capacityInput)) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      valid_venue = false;
    } else if (Integer.parseInt(capacityInput) < 0) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
      valid_venue = false;
    }

    if (!canParseInt.main(hireFeeInput)) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      valid_venue = false;
    } else if (Integer.parseInt(hireFeeInput) < 0) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
      valid_venue = false;
    }

    String v_name = "";
    if (valid_venue) {
      if (this.venue_list.size() == 0) {
        this.venue_list.add(new_venue);
        MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
      } else {
        for (int i = 0; i < this.venue_list.size(); i++) {
          //  uses loop to check if there is a duplicate venue code
          if (((Venue) this.venue_list.get(i)).venue_code.contains(venueCode)) {
            v_name = ((Venue) this.venue_list.get(i)).venue_name;
            MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, v_name);
            // ends loop if an identical venue code is found
            break;
          }
          // creates the new entry if the loop is complete and there is no duplicate code
          else if (i == this.venue_list.size() - 1) {
            this.venue_list.add(new_venue);
            MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
          }
        }
      }
    }
  }

  public void setSystemDate(String dateInput) {
    system_date = dateInput;
    MessageCli.DATE_SET.printMessage(dateInput);
  }

  public void printSystemDate() {
    if (system_date.isEmpty()) {
      MessageCli.CURRENT_DATE.printMessage("not set");
    } else {
      MessageCli.CURRENT_DATE.printMessage(system_date);
    }
  }

  // booking venue checks if date is set and if there are no venues in the system, prints a message
  // if it's true and increments the # of fails
  private int checkstart(String[] options) {
    int fails = 0;
    if (system_date.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      fails++;
    }
    if (this.venue_list.size() <= 0) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      fails++;
    }
    return fails;
  }

  // checks if venue code is not in the venue list and if there is a booking already at the same
  // time
  private int check3_4(int fails, String[] options) {
    Boolean contain = false;
    Boolean booked = false;
    String ven_name = "";
    // looks in booking list to check if booking hasn't been made already at the same date
    for (int i = 0; i < this.booking_list.size(); i++) {
      if ((((Booking) this.booking_list.get(i)).venue_code.contains(options[0]))) {
        if ((((Booking) this.booking_list.get(i)).date_booked.contains(options[1]))) {
          booked = true;
          ven_name = ((Booking) this.booking_list.get(i)).venue_name;
        }
      }
      if (i == this.booking_list.size() - 1 && booked) {
        fails += 1;
        MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(ven_name, options[1]);
      }
    }
    // checks venue list if the venue code is valid for booking
    for (int i = 0; i < this.venue_list.size(); i++) {
      if ((((Venue) this.venue_list.get(i)).venue_code.contains(options[0]))) {
        contain = true;
      }
      if (i == this.venue_list.size() - 1 && !contain) {
        fails += 1;
        MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(options[0]);
      }
    }
    return fails;
  }

  // checks if the date booked is in the past, and if so, it prints a message and increments the #
  // of fails
  // it then returns the final fails value
  private int check5(int fails, String date, String[] options) {
    if (!date.isEmpty()) {
      String[] dateParts_options = options[1].split("/");
      int day = Integer.parseInt(dateParts_options[0]);
      int month = Integer.parseInt(dateParts_options[1]);
      int year = Integer.parseInt(dateParts_options[2]);
      String[] dateParts_system = date.split("/");

      if (year < Integer.parseInt(dateParts_system[2])) {
        fails++;
        MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], date);
      } else if (month < Integer.parseInt(dateParts_system[1])) {
        fails++;
        MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], date);
      } else if (day < Integer.parseInt(dateParts_system[0])) {
        fails++;
        MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], date);
      }
    }
    return fails;
  }

  public void makeBooking(String[] options) {

    // starts the fail checks for making a booking and counts how many fails
    Integer fails = checkstart(options);
    fails = check3_4(fails, options);
    fails = check5(fails, system_date, options);

    // makes booking if there are no fails
    if (fails <= 0) {
      String ven_name = "";
      double ven_capacity = 0;
      Integer adjusted_capacity = Integer.parseInt(options[3]);
      // gets venue name
      for (int i = 0; i < this.venue_list.size() - 1; i++) {
        if ((((Venue) this.venue_list.get(i)).venue_code.contains(options[0]))) {
          ven_name = ((Venue) this.venue_list.get(i)).venue_name;
          ven_capacity = Integer.parseInt(((Venue) this.venue_list.get(i)).capacity_input);
          break;
        }
      }

      // adjusts number of attendees if it is less than 25% of the full capacity or greater than
      // 100% of the full capacity
      if (Integer.parseInt(options[3]) < Math.ceil(0.25 * ven_capacity)) {
        adjusted_capacity = (int) Math.ceil(0.25 * ven_capacity);
        Integer full_capacity = (int) ven_capacity;
        MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
            options[3], adjusted_capacity.toString(), full_capacity.toString());
      } else if (Integer.parseInt(options[3]) > ven_capacity) {
        adjusted_capacity = (int) ven_capacity;
        Integer full_capacity = (int) ven_capacity;
        MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
            options[3], adjusted_capacity.toString(), full_capacity.toString());
      }

      booking_list.add(new Booking(ven_name, options[0], adjusted_capacity.toString(), options[1]));
      MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
          BookingReferenceGenerator.generateBookingReference(),
          ven_name,
          options[1],
          adjusted_capacity.toString());
    }
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
