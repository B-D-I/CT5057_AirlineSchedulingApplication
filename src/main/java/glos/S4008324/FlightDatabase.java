package glos.S4008324;

import java.io.*;
import java.util.*;

/**
 * Flight Database provides all functionality to Flight related requirements
 */
public class FlightDatabase implements Database {

    public FlightDatabase() {
        createFlightObjectsMap();
    }

    /**
     * This method reads Flights.txt data and creates a Flight object. Each Flight object is stored in a HashMap
     * This method also creates the Airports Graph from the Flights.txt departure and destination airports,
     * and converts the departure dates into an Integer, to be Radix sorted and Binary searched.
     * @return: A HashMap containing Flight objects and the associated flight number as the key
     */
    public HashMap<String, Flight> createFlightObjectsMap() {
        try {
            File myObj = new File("src/main/java/glos/S4008324/TxtFiles/Flights.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                // read and assign flights.txt information
                String flightNo = myReader.nextLine();
                String departingAirport = myReader.nextLine();
                String departureDate = myReader.nextLine();
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
                f.setDepartureDate(departureDate);
                f.setDestination(destinationAirport);
                f.setSeatingList((HashMap<String, String>) seatingMap);

                // fill airport graph with locations
                airports.addEdge(departingAirport, destinationAirport, true);

                // add all flight objects created from flights.txt to arrayList
                allFlightsMap.put(flightNo, f);

                // remove hyphen and set date as integer
                String updated = departureDate.replace("-", "");
                int dates = Integer.parseInt(updated);
                // add dates to arrayList
                flightDates.add(dates);

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

    /**
     * This method is used to receive a Radix sorted array of departure dates, and provide a sorted list of dates,
     * along with the option to check the position of a flight, using Binary Search
     * @param option: either print or search array
     * @param date: the departure date to be searched
     */
    private void radixSortArray(String option, int date){
        HashMap<String, Flight> flightMap = createFlightObjectsMap();
        // convert the ArrayList to array and get length
        int[] arr = flightDates.stream().mapToInt(i -> i).toArray();
        // Radix sort the array
        DepartureDatesRadixSort.radixSort(arr, arr.length);
        if (option.equals("print")) {
            // print sorted array
            System.out.println("Sorted departure dates");
            for (int j : arr) System.out.print(j + " ");
        } else if (option.equals("search")){
            System.out.println("\n\t\tTHIS FLIGHT IS IN " + Database.search(arr, date) + " FLIGHTS TIME!\n\n");
        }
    }

    /**
     * This method provides all flight information for a specific flight
     */
    public void flightStatus() {
        SeatDatabase seatingDatabase = new SeatDatabase();
        HashMap<String, Flight> flightMap = createFlightObjectsMap();
        System.out.println("Enter flight number: ");
        String flightNumber = scanner.nextLine().trim();
        for (Flight flight : flightMap.values()) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                String flightInfoDisplay = String.format("""
                                                        
                                            Flight %s Information:
                                    
                                            Departure Airport: %s
                                            Departure Date: %s
                                            Destination Airport: %s
                                    
                                            Available Seats For This Flight:
                                            (Seat Number | Seat Class)
                                        
                                 %s
                                """, flightNumber, flight.getDeparture(), flight.getDepartureDate(), flight.getDestination(),
                        flight.getSeatingList());
                System.out.println(flightInfoDisplay);
                // get the departure date timing
                // remove hyphen and convert to int
                String date = flight.getDepartureDate();
                String updated = date.replace("-", "");
                int dates = Integer.parseInt(updated);
                // sort using radix sort and search using binary search
                radixSortArray("search", dates);
            }
        }
        seatingDatabase.printScheduledPassengers(flightNumber);
    }
    public void printAirports() {
        System.out.println("\nAirports:\n" + airports);
    }

    /**
     * This method receives the edges from the Airports graph and confirms if vertices (airports) routes are connected
     * @param departure: departure airport
     * @param destination: destination airport
     * @return: boolean value, of if connected
     */
    public boolean checkRoute(String departure, String destination) {
        return airports.hasRoute(departure, destination);
    }
    public boolean checkAirport(String airport) {
        return airports.hasAirport(airport);
    }

    /**
     * Method to assign a seat class to a scheduling passenger. If seat class is available then it will be assigned, if not
     * the passenger will be offered the option to go onto waiting list.
     * @param passenger: the passenger being scheduled
     * @param flightNumber: flight number for scheduling
     */
    public void selectSeatClass(Passenger passenger, String flightNumber) {
        WaitingListSeat waitingListSeat = new WaitingListSeat();
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
        } else {
            System.out.println("\nPlease make a correct seat class selection\n");
            selectSeatClass(passenger, flightNumber);
        }
        // get the flight object from required flight number
        Flight flight = allFlightsMap.get(flightNumber);
        // get the seating list and date from chosen flight
        String flightDate = flight.getDepartureDate();
        HashMap<String, String> seatingList = flight.getSeatingList();
        // if seat available, assign
        if (seatingList.containsValue(seatClass)) {
            assignSeat(seatingList, seatClass, flightNumber, flightDate, passenger);
            // else offer waiting list
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
                waitingListSeat.addPassengerToWaitingList(flightNumber, seatClass, passenger);
            }
        }
    }
    /**
     * This method assigns a specific seat number to the passenger. There is also the option to include luggage
     * @param seatingList: The HashMap of seating for associated flight
     * @param seatClass: The previously selected seat class
     * @param flightNumber: The associated flight number
     * @param flightDate: The departure date of the flight
     * @param passenger: The passenger being scheduled
     */
    private void assignSeat(HashMap<String, String> seatingList, String seatClass, String flightNumber, String flightDate, Passenger passenger) {
        for (Map.Entry<String, String> seats : seatingList.entrySet()) {
            if (seats.getValue().equals(seatClass)) {
                System.out.println(seats.getKey() + ":" + seats.getValue());
            }
        }
        System.out.println("""
                                
                Select Seat Number:
                                
                """);
        String seatSelect = scanner.nextLine();

        boolean luggage = false;
        String seatNumber = null;
        Boolean seatPresent = null;

        for (Map.Entry<String, String> seat : seatingList.entrySet()) {
            if (seatSelect.equals(seat.getKey())) {
                System.out.println("\nYou have selected seat: " + seatSelect);
                seatNumber = seatSelect;
                seatPresent = true;
            }
        }
        if (Boolean.TRUE.equals(seatPresent)) {
            System.out.println("Do you wish to include luggage? (Y|N)");
            String confirmLuggage = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
            if (confirmLuggage.equals("Y")){
                luggage = true;
            } else if (confirmLuggage.equals("N")){
                luggage = false;
            }
            schedulePassenger(seatingList, seatNumber, flightNumber, seatClass, flightDate, passenger, luggage);
        } else {
            System.out.println("\nThis seat is not available. Select an available seat: \n");
            assignSeat(seatingList, seatClass, flightNumber, flightDate, passenger);
        }
    }
    /**
     * The schedulePassenger method ensures the selected seat is removed from the flight, saves the booking, creates an
     * invoice for the booking, with a charge defined by the selections made.
     * @param seatList: The HashMap of seating for associated flight
     * @param seatingNumber: The selected seat number
     * @param flightNumber: The associated flight number
     * @param seatClass: The previously selected seat class
     * @param flightDate: The departure date of the flight
     * @param passenger: The passenger being scheduled
     * @param luggage: boolean; is luggage included
     */
    private void schedulePassenger(HashMap<String, String> seatList, String seatingNumber, String flightNumber, String seatClass, String flightDate, Passenger passenger, boolean luggage) {
        UnallocatedSeat seat = new UnallocatedSeat();
        ScheduledSeat scheduledSeating = new ScheduledSeat();
        // remove seat number from seating hashmap, and update flights.txt hashmap
        seatList.remove(seatingNumber);
        System.out.println(seatList);
        seat.modifyFlightSeating(allFlightsMap);

        // save booked passenger onto schedule seating
        scheduledSeating.addPassengerToScheduledSeat(flightNumber, seatingNumber, seatClass, passenger);

        // create invoice
        FlightInvoice flightInvoice = new FlightInvoice();
        flightInvoice.setInvoiceDate(flightDate);
        // charge of the flight is based on the flight number
        flightInvoice.setInvoiceCharge(setCharge(flightNumber, seatClass, luggage));
        flightInvoice.setInvoiceID(flightNumber + passenger.getPassportNumber());
        flightInvoice.addInvoiceToTxt(flightInvoice);
    }
    /**
     * setCharge method defines the charges for a schedule. The charge is calculated from the flight, seat class and
     * whether luggage is included.
     * @param flightNumber: The associated flight
     * @param seatClass: The chosen seat class
     * @param luggage: boolean, is luggage include
     * @return: integer of the invoice charge
     */
    private int setCharge(String flightNumber, String seatClass, boolean luggage) {
        int businessFee = 1000;
        int firstFee = 800;
        int economyFee = 400;
        int totalCharge = 0;
        switch (seatClass) {
            case "First" -> {
                int flightChargeOne = Integer.parseInt(flightNumber);
                totalCharge = flightChargeOne + firstFee;
                if (luggage){
                    totalCharge += 100;
                }
            }
            case "Business" -> {
                int flightChargeOne = Integer.parseInt(flightNumber);
                totalCharge = flightChargeOne + businessFee;
                if (luggage){
                    totalCharge += 100;
                }
            }
            case "Economy" -> {
                int flightChargeOne = Integer.parseInt(flightNumber);
                totalCharge = flightChargeOne + economyFee;
                if (luggage){
                    totalCharge += 100;
                }
            }
        } return totalCharge;
    }
    // check credentials for advanced options
    public boolean restrictedMenuLogin(String username, String password) {
        String adminUsername = "nathan";
        String adminPassword = "password";
        return username.equals(adminUsername) && password.equals(adminPassword);
    }
}


