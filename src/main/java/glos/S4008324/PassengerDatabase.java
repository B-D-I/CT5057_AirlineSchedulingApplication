package glos.S4008324;

import java.util.HashMap;
import java.util.Locale;

final class PassengerDatabase extends Database {

    private final HashMap<String, Object> passengerDetailMap = new HashMap<>();

    Flight flight = new Flight();
    FlightDatabase flightDatabase = new FlightDatabase();
    HashMap<String, Flight> allFlightsMap = flightDatabase.allFlightsMap;




    public void createPassenger() {
        System.out.println("Enter passenger's full name: ");
        String passengerName = scanner.nextLine().trim().toUpperCase(Locale.ROOT);

        System.out.println("Enter passenger passport number: ");
        String passengerPassportNumber = scanner.nextLine().trim().toUpperCase(Locale.ROOT);

        // THIS IS NOT WORKING!!!!!!!!!!!!!!!!!!!
//        System.out.println("Enter passenger age: ");
//        byte passengerAge = scanner.nextByte();
//        if (passengerAge < 18) {
//            System.out.println("Does passenger have a guardian? (Y|N) ");
//            String ageConsent = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
//            if (ageConsent.equals("N")) {
//                System.out.println("This passenger cannot travel without an adult present");
//            }
//        }
        passenger.setName(passengerName);
        passenger.setPassportNumber(passengerPassportNumber);
//        passenger.setAge(passengerAge);

        // update passenger hashmap - this is used to store all passenger information
        passengerDetailMap.put(passengerPassportNumber, passenger);

        // call passenger route method
        passengerRoute(passenger);
    }

    private void passengerRoute(Passenger passenger) {
        scanner.nextLine();
        System.out.println("Enter departure airport: ");
        String departureAirport = scanner.nextLine().trim().toUpperCase(Locale.ROOT);

        if (!flightDatabase.checkAirport(departureAirport)) {
            flightDatabase.printAirports();
            System.out.println("\n --- We do not fly from this airport, please select another ---\n");
        } else {

            System.out.println("Enter destination airport: ");
            String destinationAirport = scanner.nextLine().trim().toUpperCase(Locale.ROOT);

            if (flightDatabase.checkRoute(departureAirport, destinationAirport)) {

                // need to check th flight number between the two routes and schedule a passenger to the flight !!!!!!!!!!!!
                String flightNumber = null;
                for (Flight flight : flightDatabase.allFlightsMap.values()) {
                    if (flight.getDeparture().equals(departureAirport) && flight.getDestination().equals(destinationAirport)) {
                        flightNumber = flight.getFlightNumber();
                        flightDatabase.scheduleSeat(passenger, flightNumber);
                    }
                }

            } else if (!flightDatabase.checkRoute(departureAirport, destinationAirport)) {
                flightDatabase.printAirports();
                System.out.println("\n --- Please select a different destination airport using the timetable above ---\n");

                }
            }
        }
    }

