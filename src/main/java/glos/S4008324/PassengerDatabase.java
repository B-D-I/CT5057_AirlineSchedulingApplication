package glos.S4008324;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Passenger Database contains all functionality for passenger related requirements
 */
public class PassengerDatabase implements Database {
    // request passenger information
    public void schedulePassenger() {
        Passenger passenger = new Passenger();
        System.out.println("Enter passenger's full name: ");
        String passengerName = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
        System.out.println("Enter passenger passport number: ");
        String passengerPassportNumber = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
            try {
                System.out.println("Enter passenger age: ");
                byte passengerAge = scanner.nextByte();
                if (checkAge(passengerAge)) {
                    passenger.setName(passengerName);
                    passenger.setPassportNumber(passengerPassportNumber);
                    passengerRoute(passenger);
                } else {
                    System.out.println("\nPassenger must be at least 18 years old to buy a ticket\n");
                }
            } catch (InputMismatchException e) {
                System.out.println(e);
                System.out.println("Please enter correct data type");
            } scanner.nextLine();
    }
    public boolean checkAge(byte age){
        boolean isOfAge;
        isOfAge = age >= 18;
        return isOfAge;
    }

    /**
     * passengerRoute method utilises the Airports Graph to ensure only known airports and routes can be selected.
     * If a route is available, the flight information will be requested from flightDatabase.
     * @param passenger: Passenger (object) being scheduled
     */
    private void passengerRoute(Passenger passenger) {
        FlightDatabase flightDatabase = new FlightDatabase();
        scanner.nextLine();
        System.out.println("Enter departure airport: ");
        String departureAirport = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
        // check airport is available
        if (!flightDatabase.checkAirport(departureAirport)) {
            flightDatabase.printAirports();
            System.out.println("\n --- We do not fly from this airport, please select another ---\n");
            schedulePassenger();
        } else {
            System.out.println("Enter destination airport: ");
            String destinationAirport = scanner.nextLine().trim().toUpperCase(Locale.ROOT);

            if (flightDatabase.checkRoute(departureAirport, destinationAirport)) {
                // find the flight number for the route, then call schedule-seat function
                String flightNumber = null;
                for (Flight flight : flightDatabase.allFlightsMap.values()) {
                    if (flight.getDeparture().equals(departureAirport) && flight.getDestination().equals(destinationAirport)) {
                        flightNumber = flight.getFlightNumber();
                        System.out.println("\nThe flight for this journey is: " + flightNumber + "\n");
                        System.out.println("The date of departure is: " + flight.getDepartureDate());
                        flightDatabase.selectSeatClass(passenger, flightNumber);
                    }
                }
            } else if (!flightDatabase.checkRoute(departureAirport, destinationAirport)) {
                flightDatabase.printAirports();
                System.out.println("\n --- Please select a different destination airport using the timetable above ---\n");
                schedulePassenger();
            }
        }
    }

    /**
     * This method retrieves general flight bookings and waiting list information. This data is then stored in a HashMap
     * @param listType: Either "BookedFlights" or "WaitingLists"
     * @return: HashMap of requested information
     */
    public HashMap<String, String> createPassengerBookingsObject(String listType) {
        try{
            File myObj = new File("target/classes/glos/S4008324/TxtFiles/"+listType+".txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String passengerPassport = myReader.nextLine();
                String flightNumber = myReader.nextLine();

                passengerSchedulingMap.put(passengerPassport, flightNumber);
                if (myReader.hasNextLine()) {
                    myReader.nextLine();
                }
            }
            myReader.close();
        } catch(
                FileNotFoundException e){
            System.out.println("Error" + e);
        }
        return passengerSchedulingMap;
    }

    /**
     * passengerStatus method provides a passengers booked flights and waiting lists
     */
    public String passengerStatus() {
        System.out.println("Enter passport number of passenger: ");
        return scanner.nextLine().trim();}
    public void retrieveStatus(){
        String passportNumber = passengerStatus();
            // passport : flight number
            HashMap<String, String> passengerBookingsMap = createPassengerBookingsObject("BookedFlights");
            HashMap<String, String> passengerWaitingMap = createPassengerBookingsObject("WaitingLists");
            String passengerFlight = passengerBookingsMap.get(passportNumber);
            String passengerWaitList = passengerWaitingMap.get(passportNumber);

            System.out.println("Passenger: " + passportNumber);
            System.out.println("Flight: "+passengerFlight);
            System.out.println("Waiting List For Flight: "+passengerWaitList);
            if (passengerFlight == null || passengerWaitList == null){
                System.out.println("Passenger not associated to any booked flights");
            } else {
                WaitingListDatabase waitingListDatabase = new WaitingListDatabase();
                // check waiting list database for passenger info and position
                waitingListDatabase.printWaitListPassenger(passengerWaitList, passportNumber);
            }
        }

        // Method to remove a passenger from a flight
        public void removePassengerFromList(String listType, String passportNumber){
        if (listType.equals("BookedFlights")){
            HashMap<String, String> passengerBookingsMap = createPassengerBookingsObject("BookedFlights");
            passengerBookingsMap.remove(passportNumber);

            ScheduledSeat scheduledSeat = new ScheduledSeat();
            scheduledSeat.modifyBookedFlights(passengerBookingsMap);
            System.out.println("Passenger Deleted");
        }
        else if (listType.equals("WaitingLists")){
            HashMap<String, String> passengerWaitingMap = createPassengerBookingsObject("WaitingLists");
            passengerWaitingMap.remove(passportNumber);
            WaitingListSeat waitingListSeat = new WaitingListSeat();
            waitingListSeat.modifyWaitingList(passengerWaitingMap);
        }
    }
}


