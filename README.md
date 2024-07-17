# Venue Hire System

A Venue Hire that has the follow commands/features:

COMMANDS       |  ARGUMENTS    |  DESCRIPTION

PRINT_VENUES   |  [no args]    |  Print details of all the venues.

CREATE_VENUE   | [4 arguments] |  Create a new venue with the given <VENUE_NAME>, <VENUE_CODE>, <VENUE_CAPACITY>, and <HIRE_FEE>

SET_DATE       | [1 argument]  |  Set the system's date to the specified date in DD/MM/YYYY format

PRINT_DATE     |  [no args]    |  Print the system's current date

MAKE_BOOKING   |  [no args]    |  Request a new booking

PRINT_BOOKINGS | [1 argument]  |  Print all bookings for the specified <VENUE_CODE>

ADD_CATERING   | [1 argument]  |  Add catering service to the specified <BOOKING_REFERENCE>

ADD_MUSIC      | [1 argument]  |  Add music service to the specified <BOOKING_REFERENCE>

ADD_FLORAL     | [1 argument]  |  Add floral service to the specified <BOOKING_REFERENCE>

VIEW_INVOICE   | [1 argument]  |  View full invoice details for the specified <BOOKING_REFERENCE>

HELP           |   [no args]   |  Print usage

EXIT           |   [no args]   |  Exit the application
