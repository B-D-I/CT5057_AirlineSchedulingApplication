package glos.S4008324;

import com.opencsv.CSVWriter;
import jdk.swing.interop.SwingInterOpUtils;

import java.io.*;
import java.util.*;

public class FlightDatabase implements Database {

    // all flights in flights.txt
    HashMap<String, Flight> allFlightsMap = new HashMap<>();

    HashMap<String, ArrayList<String>> allocatedSeatList = new HashMap<>();

    Queue<HashMap<String, ArrayList<String>>> waitingList = new LinkedList<>();

    Airports<String> airports = new Airports<>();

    private Random random;

    public FlightDatabase() {
        random = new Random();
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
//        passengerDetailMap.entrySet().forEach(System.out::println);
//        System.out.println("Passenger Passport Number: " + passenger.toString());

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
            // view all seats with:       System.out.println(seatingList);
            System.out.println("\nThere are no " + seatClass + " seats available");
            System.out.println("""
                    Would you like to select an alternative seat class, or go onto the waiting list? 
                    
                    S: select different seat
                    
                    W: waiting list
                    
                    """);
            String seatSelect = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
            if (seatSelect.equals("S")) {
                selectSeatClass(passenger, flightNumber);
            } else if (seatSelect.equals("W")){
                assignWaitingList(seatClass, flightNumber, passenger);
                }
        }
    }

    private void assignSeat(HashMap<String, String> seatingList, String seatClass, String flightNumber, Passenger passenger) {
        for (Map.Entry<String, String> seats : seatingList.entrySet()) {
            if (seats.getValue().equals(seatClass)) {
//                    System.out.println(seats+"\n");
//                    System.out.println(seatingList);
                System.out.println(seats.getKey() + ":" + seats.getValue());
            }
        }

        System.out.println("\nDo you wish to select a specific seat? (Y|N)");
        String selectSeat = scanner.nextLine().trim().toUpperCase(Locale.ROOT);

        if (selectSeat.equals("Y")) {
            // Manually pick the seatNo
            System.out.println("Enter seat number: ");
            String seatNumber = scanner.nextLine();

//          NOT WORKING>>>
            schedulePassenger(seatingList, seatNumber, flightNumber, seatClass, passenger);

            // RANDOM SEAT ALLOCATION...................... unable to convert the int back to string?
//        } else if (selectSeat.equals("N")) {
//            for (int i = 0; i < flight.getSeatingList().size(); i++) {
//                int randomSeatNum = random.nextInt(1, flight.getSeatingList().size() + 1);
//                while (flight.getSeatingList().containsKey(randomSeatNum)) {
//                    randomSeatNum = random.nextInt(1, flight.getSeatingList().size() + 1);
//                    Integer.toString(randomSeatNum);
//                    String seatNumber = randomSeatNum;
//                    schedulePassenger(seatingList, toString(seatNumber));
//                }
//            }
        }
    }

        private void schedulePassenger(HashMap<String, String> seatList, String seatingNumber, String flightNumber, String seatClass, Passenger passenger) {
            // test - not working
            System.out.println("working");

            // delete it from the non-allocated seating list
            seatList.remove(seatingNumber);
            System.out.println(seatList);

            // STORE IN CSV ---------- NOT WRITING ??????????
//            File file = new File("src/main/java/glos/S4008324/ScheduledSeating.csv");
//            try{
//                FileWriter outputFile = new FileWriter(file);
//                CSVWriter writer = new CSVWriter(outputFile);
////                ArrayList<String[]> scheduledPassengers = new ArrayList<>();
//
////                scheduledPassengers.add(new String[]{"working", flightNumber, seatNumber, seatClass,
////                        passenger.getPassportNumber(), passenger.getName()});
//
//                String scheduledPassenger[] = {flightNumber, seatNumber, seatClass,
//                        passenger.getPassportNumber(), passenger.getName()}
//
//                writer.writeNext(scheduledPassenger);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }



            // IF PLACING INTO HASHMAP ..
            // creat an Array of chosen seat number and class and add it to hashmap with passenger
//            ArrayList<String> flightInfo = new ArrayList<>();
//            flightInfo.add("Flight number: " + flightNumber);
//            flightInfo.add("Seat number: " + seatNumber);
//            flightInfo.add("Seat class: " + seatClass);
//
//            // add details to allocated seating
//            allocatedSeatList.put(passenger.getPassportNumber(), flightInfo);
//
//            System.out.println("\n\n");
//            System.out.println(allocatedSeatList);


            //    public void updateFlightAllocationTxt(String departure, String destination, String dateDeparture, String flightNumber, HashMap<Integer, String> seatList){
//        try {
//            BufferedWriter out = new BufferedWriter(new FileWriter("src/main/java/glos/S4008324/ScheduledSeating.txt", true));
//            out.write(flightNumber + seatNumber + seatClass + passenger.getPassportNumber() + passenger.getName());
//            out.close();
//
//            System.out.println("\nFlight added");
//            // REPLACE: .replace("{", " ").replace("},", " ")
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


        private void assignWaitingList(String seatClass, String flightNumber, Passenger passenger){


        // CREATE A CSV FILE TO STORE INFO, THEN WHEN INITILIASED, PLACE INTO QUEUE

            // creat an Array of chosen seat number and class and add it to hashmap with passenger
            ArrayList<String> flightInfo = new ArrayList<>();
            flightInfo.add("Flight number: " + flightNumber);
            flightInfo.add("Seat class: " + seatClass);

            // check what can be included into queue....
//            waitingList.add(passenger.getPassportNumber(), flightInfo);
        }

        // need waiting list functions etc....



    private void saveBooking() {
        // ^^^^ CREATE A PERMANENT CSV FILE OF BOOKED SEATS /// or waiting list
    }

    // will write the flight seating list to a txt file .............
//    public void updateFlightAllocationTxt(String departure, String destination, String dateDeparture, String flightNumber, HashMap<Integer, String> seatList){
//        try {
//            BufferedWriter out = new BufferedWriter(new FileWriter("src/main/java/glos/S4008324/Flights.txt", true));
//            out.write("\n" + flightNumber + "\n" + departure + "\n" + dateDeparture +
//                    "\n" + destination + "\n" + seatList.toString()+ "\n");
//            out.close();
//
//            System.out.println("\nFlight added");
//            // REPLACE: .replace("{", " ").replace("},", " ")
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}

