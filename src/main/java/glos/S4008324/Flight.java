package glos.S4008324;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

/**
 * The Flight class contains all attributes associated to a flight, accessible via getter and setter methods.
 */
public class Flight extends AirlineObject{

    private String flightNumber;
    private String departure ;
    private String destination;
    private String departureDate;

    UnallocatedSeat unallocatedSeat = new UnallocatedSeat();
    SeatDatabase seatDatabase = new SeatDatabase();

    // Seat Number : Seat class
    public HashMap<String, String> SeatingList;

    // Getter and Setter methods
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
    /**
     * This method is required to receive new flight information to be written to Flights.txt
     */
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
        HashMap<Integer, String> flightSeatingClass = seatDatabase.allocateAircraftSeatClass();
        System.out.println("\nSeating List: \n");
        flightSeatingClass.entrySet().forEach(System.out::println);

        // OPTION TO EDIT THE SEATING LIST
        System.out.println("\nWould you like to edit this seating list? (Y|N)");
        String editSeating = scanRead.nextLine().trim().toUpperCase(Locale.ROOT);
        if (editSeating.equals("Y")) {
            seatDatabase.editAircraftSeatClassAllocation(departure, destination, dateDeparture, flightNumber, flightSeatingClass);
        } else if (editSeating.equals("N")) {
            unallocatedSeat.updateFlightTxt(departure, destination, dateDeparture, flightNumber, flightSeatingClass);
        }
    }
}

