package glos.S4008324;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Flight {

    private String flightNumber;
    private String departure ;
    private String destination;
    private String departureDate;

    Scanner scanRead = new Scanner(System.in);
    Seating seating = new Seating();

    public HashMap<String, String> SeatingList;

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setDeparture(String departure) {
        this.departure = departure ;
    }
    public String getDeparture() {
        return departure;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    public String getDestination() {
        return destination;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }
    public String getDepartureDate() {
        return departureDate;
    }

    public void setSeatingList(HashMap<String, String> seatingList){
        this.SeatingList = seatingList;
    }
    public HashMap<String, String> getSeatingList() {
        return SeatingList;
    }



    @Override
    public String toString()
    {
        return flightNumber+", "+departure+", "+destination+", "+departureDate+", "+ SeatingList;
    }


    // FLIGHT OPERATOR METHOD TO CREATE NEW FLIGHT TO TXT
    public void createFlight() {
        System.out.println("Enter departure airport: ");
        String departure = scanRead.nextLine().trim().toUpperCase(Locale.ROOT);

        System.out.println("Enter destination airport: ");
        String destination = scanRead.nextLine().trim().toUpperCase(Locale.ROOT);

        System.out.print("Departure Date (0000-12-03 format): ");

        String dateDeparture = null;
        try {
            String departureDate = scanRead.nextLine();
            LocalDate localDate = LocalDate.parse(departureDate);
            dateDeparture = localDate.toString();
        } catch (DateTimeException e) {
            System.out.println(e);
            System.out.println("\nPlease enter the date in the correct format\n");
            createFlight();
        }

        System.out.println("Enter flight number: ");
        String flightNumber = scanRead.nextLine().trim().toUpperCase(Locale.ROOT);

        // SEATING HASHMAP
        HashMap<Integer, String> flightSeatingClass = seating.allocateAircraftSeatClass();

        System.out.println("\nSeating List: \n");
        flightSeatingClass.entrySet().forEach(System.out::println);

        // OPTION TO EDIT THE SEATING LIST
        System.out.println("\nWould you like to edit this seating list? (Y|N)");
        String editSeating = scanRead.nextLine().trim().toUpperCase(Locale.ROOT);
        if (editSeating.equals("Y")) {
            seating.editAircraftSeatClassAllocation(departure, destination, dateDeparture, flightNumber, flightSeatingClass);
        } else if (editSeating.equals("N")) {
            seating.updateFlightTxt(departure, destination, dateDeparture, flightNumber, flightSeatingClass);
        }
    }
}

