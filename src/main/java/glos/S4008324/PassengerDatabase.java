package glos.S4008324;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

final class PassengerDatabase implements Database {


    FlightDatabase flightDatabase = new FlightDatabase();
    Passenger passenger = new Passenger();

    HashMap<String, String> passengerDetailMap = new HashMap<>();

    public void schedulePassenger() {
        System.out.println("Enter passenger's full name: ");
        String passengerName = scanner.nextLine().trim().toUpperCase(Locale.ROOT);

        System.out.println("Enter passenger passport number: ");
        String passengerPassportNumber = scanner.nextLine().trim().toUpperCase(Locale.ROOT);

            try {
                System.out.println("Enter passenger age: ");
                byte passengerAge = scanner.nextByte();
                if (passengerAge > 18) {
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

    private void passengerRoute(Passenger passenger) {
        scanner.nextLine();
        System.out.println("Enter departure airport: ");
        String departureAirport = scanner.nextLine().trim().toUpperCase(Locale.ROOT);

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

    // PASSPORT NUMBER : FLIGHT NUMBER (BookedFlights or WaitingList)
    public HashMap<String, String> createPassengerBookingsObject(String listType) {
        try{
            File myObj = new File("src/main/java/glos/S4008324/TxtFiles/"+listType+".txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

                String passengerPassport = myReader.nextLine();
                String flightNumber = myReader.nextLine();
                passengerDetailMap.put(passengerPassport, flightNumber);

                if (myReader.hasNextLine()) {
                    myReader.nextLine();
                }
            }
            myReader.close();
        } catch(
                FileNotFoundException e){
            System.out.println("Error" + e);
        }
        return passengerDetailMap;
    }


        public void passengerStatus(){
            System.out.println("Enter passport number of passenger: ");
            String passportNumber = scanner.nextLine().trim();

            HashMap<String, String> passengerBookingsMap = createPassengerBookingsObject("BookedFlights");
            HashMap<String, String> passengerWaitingMap = createPassengerBookingsObject("WaitingLists");

            String bookings = null;
            String waiting = null;
            for (String passengers : passengerBookingsMap.keySet()){
                if (passengers.equals(passportNumber)){
                    bookings =  passengerBookingsMap.get(passengers);
                }
            }
            for (String passengers : passengerWaitingMap.keySet()){
                if (passengers.equals(passportNumber)){
                    waiting = passengerWaitingMap.get(passengers);
                }
            }
            System.out.println("Bookings: " + bookings);
            System.out.println("Waiting: " + waiting);





            // NEED TO FIND ALL FLIGHTS AND WAITING LISTS THEY ARE ON
            // COULD CREATE A .TXT FOR PASSENGERS AND UPDATE WITH NEW FLIGHTS ETC..
        }
    }

