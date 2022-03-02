package glos.S4008324;

import java.io.*;
import java.util.*;

public class FlightDatabase implements Database {

    // all flights in flights.txt
    HashMap<String, Flight> allFlightsMap = new HashMap<>();

    Airports<String> airports = new Airports<>();
    WaitingList waitingList = new WaitingList();
    SeatDatabase seatDatabase = new SeatDatabase();
    Seating seat = new Seating();

    ScheduledSeat scheduledSeat = new ScheduledSeat();

    public FlightDatabase() {
        createFlightObjectsMap();
    }

    public String getDbUsername() {
        return "nathan";
    }

    public String getDbPassword() {
        return "password";
    }


    private HashMap<String, Flight> createFlightObjectsMap() {

        try {
            File myObj = new File("src/main/java/glos/S4008324/Flights.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

                // read and assign flights.txt information
                String flightNo = myReader.nextLine();
                String departingAirport = myReader.nextLine();
                String date = myReader.nextLine();
                String destinationAirport = myReader.nextLine();
                String seatingList = myReader.nextLine();

                // Convert the seating list String back into a HashMap
                Properties props = new Properties();
                props.load(new StringReader(seatingList.substring(1, seatingList.length() - 1).replace(", ", "\n")));
                Map<String, String> seatingMap = new HashMap<>();
                for (Map.Entry<Object, Object> e : props.entrySet()) {
                    seatingMap.put((String) e.getKey(), (String) e.getValue());
                }

                // create flight object with flights.txt information
                Flight f = new Flight();
                f.setFlightNumber(flightNo);
                f.setDeparture(departingAirport);
                f.setDepartureDate(date);
                f.setDestination(destinationAirport);
                //f.setSeatingList(seatingList);
                f.setSeatingList((HashMap<String, String>) seatingMap);

                // fill airport graph with locations
                airports.addEdge(departingAirport, destinationAirport, true);

                // add all flight objects created from flights.txt to arrayList
                allFlightsMap.put(flightNo, f);
                if (myReader.hasNextLine()) {
                    myReader.nextLine();
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error" + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allFlightsMap;
    }

    public void showAllFlights() {
        HashMap<String, Flight> flightMap = createFlightObjectsMap();
        flightMap.entrySet().forEach(System.out::println);
    }

    public void flightStatus(){
        HashMap<String, Flight> flightMap = createFlightObjectsMap();
        System.out.println("Enter flight number: ");
        String flightNumber = scanner.nextLine().trim();
        for(Flight flight: flightMap.values()){
            if (flight.getFlightNumber().equals(flightNumber)){
                String flightInfoDisplay = String.format("""
                        
                                    Flight %s information:
                            
                                    Departure Airport: %s
                                    Departure Date: %s
                                    Destination Airport: %s
                            
                                    Available Seats For This Flight:
                                    (Seat Number | Seat Class)
                                
                         %s
                        """, flightNumber, flight.getDeparture(), flight.getDepartureDate(), flight.getDestination(),
                        flight.getSeatingList());

                System.out.println(flightInfoDisplay);
            }
        } seatDatabase.printScheduledPassengers(flightNumber);
        // print departure and destination airport, departure date and available seats (flights.txt)
        // print list of taken seats and passengers (scheduledSeats)
    }

    public void printAirports() {
        System.out.println("Airports:\n" + airports.toString());
    }

    public boolean checkRoute(String departure, String destination) {
        return airports.hasRoute(departure, destination);
    }

    public boolean checkAirport(String airport) {
        return airports.hasAirport(airport);
    }


    public void selectSeatClass(Passenger passenger, String flightNumber) {

        System.out.println(passenger.toString());
        System.out.println("""
                Select seat class:
                                    
                F - first
                                    
                B - business
                                    
                E - economy
                                   
                """);

        String seatClass = scanner.nextLine().trim().toUpperCase(Locale.ROOT);

        if (seatClass.equals("F")) {
            seatClass = "First";
        } else if (seatClass.equals("B")) {
            seatClass = "Business";
        } else if (seatClass.equals("E")) {
            seatClass = "Economy";
        }

        // get the flight object from required flight number
        Flight flight = allFlightsMap.get(flightNumber);

        // get the seating list from chosen flight
        HashMap<String, String> seatingList = flight.getSeatingList();

        if (seatingList.containsValue(seatClass)) {
            assignSeat(seatingList, seatClass, flightNumber, passenger);

        } else {
            System.out.println("\nThere are no " + seatClass + " seats available");
            System.out.println("""
                    Would you like to select an alternative seat class, or go onto the waiting list? 
                                        
                    S: select different seat
                                        
                    W: waiting list
                                        
                    """);
            String seatSelect = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
            if (seatSelect.equals("S")) {
                selectSeatClass(passenger, flightNumber);
            } else if (seatSelect.equals("W")) {
                // create waiting list object
                waitingList.addPassengerToWaitingList(flightNumber, seatClass, passenger);
            }
        }
    }

    private void assignSeat(HashMap<String, String> seatingList, String seatClass, String flightNumber, Passenger passenger){

        for (Map.Entry<String, String> seats : seatingList.entrySet()) {
            if (seats.getValue().equals(seatClass)) {
                System.out.println(seats.getKey() + ":" + seats.getValue());
            }
        }
        System.out.println("\nSelect seat number: ");
        String seatSelect = scanner.nextLine();
        String seatNumber = null;
        Boolean seatPresent = null;

        for (Map.Entry<String, String> seat : seatingList.entrySet()) {
            if (seatSelect.equals(seat.getKey())){
                System.out.println("\nYou have selected seat: " + seatSelect);
                seatNumber = seatSelect;
                seatPresent = true;
            }
        }
        if (Boolean.TRUE.equals(seatPresent)){
            schedulePassenger(seatingList, seatNumber, flightNumber, seatClass, passenger);
        } else {
            System.out.println("\nThis seat is not available. Select an available seat: \n");
            assignSeat(seatingList, seatClass, flightNumber, passenger);
        }
    }


    private void schedulePassenger(HashMap<String, String> seatList, String seatingNumber, String flightNumber, String seatClass, Passenger passenger) {

        // remove seat number from seating hashmap, and update flights.txt hashmap
        seatList.remove(seatingNumber);
        System.out.println(seatList);
        seat.modifyFlightSeating(allFlightsMap);

        // save booked passenger onto schedule seating
        scheduledSeat.addPassengerToScheduledSeat(flightNumber, seatingNumber, seatClass, passenger);
    }

}
