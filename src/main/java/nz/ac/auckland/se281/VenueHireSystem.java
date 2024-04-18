package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

abstract class venueParameters {
  protected String venue_name;
  protected String venue_code;
  protected String capacity;

  abstract String getName();

  abstract String getCode();

  abstract String getCapacity();
}

class Venue extends venueParameters {
  private String hire_fee;

  public Venue(String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    venue_name = venueName;
    venue_code = venueCode;
    capacity = capacityInput;
    hire_fee = hireFeeInput;
  }

  public String getName() {
    return this.venue_name;
  }

  public String getCode() {
    return this.venue_code;
  }

  public String getCapacity() {
    return this.capacity;
  }

  public String getHireFee() {
    return this.hire_fee;
  }
}

class Booking extends venueParameters {
  private String date_booked;
  private String booking_reference;
  private ArrayList<CateringType> catering_list = new ArrayList<CateringType>();

  public Booking(
      String venueName,
      String venueCode,
      String adjCapacity,
      String date_booked,
      String booking_reference) {
    this.venue_name = venueName;
    this.venue_code = venueCode;
    this.capacity = adjCapacity;
    this.date_booked = date_booked;
    this.booking_reference = booking_reference;
  }

  public ArrayList<CateringType> getCateringTypes() {
    return catering_list;
  }

  public Integer getCateringTotal() {
    Integer sum = 0;
    for (int i = 0; i < catering_list.size(); i++) {
      sum += catering_list.get(i).getCostPerPerson() * Integer.parseInt(this.getCapacity());
    }
    return sum;
  }

  public String getName() {
    return this.venue_name;
  }

  public String getCode() {
    return this.venue_code;
  }

  public String getCapacity() {
    return this.capacity;
  }

  public String getDateBooked() {
    return this.date_booked;
  }

  public String getBookingRef() {
    return this.booking_reference;
  }
}

public class VenueHireSystem {

  protected ArrayList<Venue> venue_list;
  protected ArrayList<Booking> booking_list;
  protected String system_date;

  public VenueHireSystem() {
    this.system_date = "";
    this.booking_list = new ArrayList<Booking>();
    this.venue_list = new ArrayList<Venue>();
  }

  // check if venue already booked
  public String checkBookings(String v_code) {
    String[] prev_last_date = {this.system_date, ""};
    for (int i = 0; i < this.booking_list.size(); i++) {
      if ((this.booking_list.get(i).getCode().contains(v_code))) {
        prev_last_date[1] = this.booking_list.get(i).getDateBooked();
        if (check5(0, prev_last_date[0], prev_last_date) == 0) {
          prev_last_date[0] = prev_last_date[1];
        }
      }
    }
    prev_last_date[1] = this.system_date;
    String[] dateParts_options = prev_last_date[0].split("/");

    if (check5(0, prev_last_date[0], prev_last_date) != 1) {
      return system_date;
    }
    Integer day = Integer.parseInt(dateParts_options[0]);
    day++;
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

  // loop used in printVenues to print all the venues
  private void printLoop(int size) {
    for (int i = 0; i < size; i++) {
      MessageCli.VENUE_ENTRY.printMessage(
          this.venue_list.get(i).getName(),
          this.venue_list.get(i).getCode(),
          this.venue_list.get(i).getCapacity(),
          this.venue_list.get(i).getHireFee(),
          checkBookings(this.venue_list.get(i).getCode()));
    }
  }

  public void printVenues() {
    Integer size = this.venue_list.size();
    // changes wording of the header (plural vs singular) depending on how many venues in the list
    if (size <= 0) {
      MessageCli.NO_VENUES.printMessage();
    } else if (size == 1) {
      MessageCli.NUMBER_VENUES.printMessage("is", numToWords.main(size), "");
      printLoop(size);
    } else if (size < 10) {
      MessageCli.NUMBER_VENUES.printMessage("are", numToWords.main(size), "s");
      printLoop(size);
    } else if (size >= 10) {
      MessageCli.NUMBER_VENUES.printMessage("are", size.toString(), "s");
      printLoop(size);
    }
  }

  // checks if new venue has an empty venue name
  private int emptyVenueName(String venueName, int fails) {
    if (venueName.replaceAll("\\s", "").isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      fails++;
    }
    return fails;
  }

  // checks if the capacity input for the new venue is valid (integer)
  private int invalidCapacity(String capacityInput, int fails) {
    if (!canParseInt.main(capacityInput)) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      fails++;
    } else if (Integer.parseInt(capacityInput) < 0) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
      fails++;
    }
    return fails;
  }

  // checks if the hire fee input is a positive number for the new venue
  private int positiveHireFee(String hireFeeInput, int fails) {
    if (!canParseInt.main(hireFeeInput)) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      fails++;
    } else if (Integer.parseInt(hireFeeInput) < 0) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
      fails++;
    }
    return fails;
  }

  // checks the venue list if there is already a venue with the same venue code
  private int duplicateCode(int fails, String venueCode, String venueName) {
    String v_name = "";
    for (int i = 0; i < this.venue_list.size(); i++) {
      //  uses loop to check if there is a duplicate venue code
      if (this.venue_list.get(i).getCode().contains(venueCode)) {
        v_name = this.venue_list.get(i).getName();
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, v_name);
        fails++;
        // ends loop if an identical venue code is found
        break;
      }
    }
    return fails;
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    Venue new_venue = new Venue(venueName, venueCode, capacityInput, hireFeeInput);
    // checks all fail cases and counts how many fail cases there are
    int fails = 0;
    fails = emptyVenueName(venueName, fails);
    fails = invalidCapacity(capacityInput, fails);
    fails = positiveHireFee(hireFeeInput, fails);
    fails = duplicateCode(fails, venueCode, venueName);

    // creates new venue to venue list if there are no issues/fails
    if (fails <= 0) {
      this.venue_list.add(new_venue);
      MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
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
  private int checkStart(String[] options) {
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
      if ((this.booking_list.get(i).getCode().contains(options[0]))) {
        if ((this.booking_list.get(i).getDateBooked().contains(options[1]))) {
          booked = true;
          ven_name = this.booking_list.get(i).getName();
        }
      }
      if (i == this.booking_list.size() - 1 && booked) {
        fails += 1;
        MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(ven_name, options[1]);
      }
    }
    // checks venue list if the venue code is valid for booking
    for (int i = 0; i < this.venue_list.size(); i++) {
      if ((this.venue_list.get(i).getCode().contains(options[0]))) {
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
    Integer fails = checkStart(options);
    fails = check3_4(fails, options);
    fails = check5(fails, system_date, options);

    // makes booking if there are no fails
    if (fails <= 0) {
      String ven_name = "";
      double ven_capacity = 0;
      Integer adjusted_capacity = Integer.parseInt(options[3]);
      // gets venue name
      for (int i = 0; i < this.venue_list.size(); i++) {
        if ((((Venue) this.venue_list.get(i)).getCode().contains(options[0]))) {
          ven_name = ((Venue) this.venue_list.get(i)).getName();
          ven_capacity = Integer.parseInt(((Venue) this.venue_list.get(i)).getCapacity());
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
      String booking_reference = BookingReferenceGenerator.generateBookingReference();
      booking_list.add(
          new Booking(
              ven_name, options[0], adjusted_capacity.toString(), options[1], booking_reference));
      MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
          booking_reference, ven_name, options[1], adjusted_capacity.toString());
    }
  }

  public void printBookings(String venueCode) {
    String name = "";
    Boolean count = false;

    // checks if venue code exists in venue list
    for (int i = 0; i < this.venue_list.size(); i++) {
      if ((this.venue_list.get(i).venue_code.contains(venueCode))) {
        name = this.venue_list.get(i).venue_name;
        break;
      } else if (i == this.venue_list.size() - 1) {
        MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
        return;
      }
    }
    // prints the header and loops through the booking list to print all booking entries for that
    // venue
    MessageCli.PRINT_BOOKINGS_HEADER.printMessage(name);
    for (int i = 0; i < this.booking_list.size(); i++) {
      if ((((Booking) this.booking_list.get(i)).getCode().contains(venueCode))) {
        count = true;
        MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(
            this.booking_list.get(i).getBookingRef(), this.booking_list.get(i).getDateBooked());
      }
    }
    // if there are no bookings, prints a message
    if (!count) {
      MessageCli.PRINT_BOOKINGS_NONE.printMessage(name);
    }
  }

  private Booking checkReference(String bookingReference) {
    Booking booking = null;
    for (int i = 0; i < this.booking_list.size(); i++) {
      if ((this.booking_list.get(i).getBookingRef().contains(bookingReference))) {
        booking = this.booking_list.get(i);
        break;
      }
    }
    return booking;
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    Booking booking = null;
    booking = checkReference(bookingReference);
    if (booking != null) {
      booking.getCateringTypes().add(cateringType);
      MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
          "Catering (" + cateringType.getName() + ")", bookingReference);
    } else {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Catering", bookingReference);
    }
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {}
}
