package glos.S4008324;

import java.util.HashMap;
import java.util.Locale;

final class PassengerDatabase extends Database {

    private final HashMap<String, Object> passengerDetailMap = new HashMap<>();

    FlightDatabase flightDatabase = new FlightDatabase();

    public void schedulePassenger() {

        System.out.println("Enter passenger's full name: ");
        String passengerName = scanner.nextLine().trim().toUpperCase(Locale.ROOT);

        System.out.println("Enter passenger passport number: ");
        String passengerPassportNumber = scanner.nextLine().trim().toUpperCase(Locale.ROOT);

        System.out.println("Enter passenger age: ");
        byte passengerAge = scanner.nextByte();

        if (passengerAge > 18) {
            passenger.setName(passengerName);
            passenger.setPassportNumber(passengerPassportNumber);
            passenger.setAge(passengerAge);

            // update passenger hashmap - this is used to store all passenger information
            passengerDetailMap.put(passengerPassportNumber, passenger);

            // call passenger route method
            passengerRoute(passenger);
        }
        else {
                System.out.println("\nPassenger must be at least 18 years old to buy a ticket\n");
                new ApplicationMenu();
        }
    }

        private void passengerRoute(Passenger passenger){
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

                    // find the flight number for the route, then call schedule-seat function
                    String flightNumber = null;
                    for (Flight flight : flightDatabase.allFlightsMap.values()) {
                        if (flight.getDeparture().equals(departureAirport) && flight.getDestination().equals(destinationAirport)) {
                            flightNumber = flight.getFlightNumber();
                            System.out.println("\nThe flight for this journey is: " + flightNumber + "\n");
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

