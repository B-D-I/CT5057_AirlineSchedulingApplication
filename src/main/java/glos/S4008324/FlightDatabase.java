package glos.S4008324;

import java.io.*;
import java.util.*;

public class FlightDatabase extends Database {

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

    // all flights in flights.txt
    HashMap<String, Flight> allFlightsMap = new HashMap<>();

    HashMap<String, ArrayList<String>> allocatedSeatList = new HashMap<>();



    Airports<String> airports = new Airports<>();

    private HashMap<String, Flight> createFlightObjectsMap() {

        try {
            File myObj = new File("src/main/java/glos/S4008324/Flights.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

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


                Flight f = new Flight();

                f.setFlightNumber(flightNo);
                f.setDeparture(departingAirport);
                f.setDepartureDate(date);
                f.setDestination(destinationAirport);
                //f.setSeatingList(seatingList);
                f.setSeatingList((HashMap<String, String>) seatingMap);

                // Fill airport graph with airport departure and destinations               ----------------       airports arent being allocated???
//                addFlightRoutes(departingAirport, destinationAirport);
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
        boolean hasRoute = airports.hasRoute(departure, destination);
        return hasRoute;
    }

    public boolean checkAirport(String airport) {
        boolean hasAirport = airports.hasAirport(airport);
        return hasAirport;
    }


    public void scheduleSeat(Passenger passenger, String flightNumber) {

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

            // NEED TO ONLY DISPLAY THE SEATS FROM SELECTED SEAT CLASS !!!!!!!!!

            // Display seat number with seat class
            System.out.println(flight.getSeatingList().values(seatClass)); // for the correct seat class

            System.out.println("Do you wish to select a seat? (Y|N)");
            String selectSeat = scanner.nextLine().trim().toUpperCase(Locale.ROOT);


            if (selectSeat.equals("Y")) {
                // Manually pick the seatNo
                System.out.println("Enter seat number: ");
                String seatNumber = scanner.nextLine();

                // RANDOM SEAT ALLOCATION...................... unable to convert the int back to string?
//            else if (selectSeat.equals("N") {
//                for (int i = 0; i < flight.getSeatingList().size(); i++){
//                    int randomSeatNum = random.nextInt(1, flight.getSeatingList().size() +1);
//                    while (flight.getSeatingList().containsKey(randomSeatNum)){
//                        randomSeatNum = random.nextInt(1, flight.getSeatingList().size() +1);
//                        Integer.toString(randomSeatNum);
//                        seatNumber = randomSeatNum;
//                    }
//                }


                // delete it from the non-allocated seating list
                seatingList.remove(seatNumber);
                System.out.println(seatingList);

                // need to add to allocated seat list etc..

                // creat a hashmap of chosen seat number and class and add it to hashmap with passenger
                ArrayList<String> flightInfo = new ArrayList<>();
                flightInfo.add("Flight number: " + flightNumber);
                flightInfo.add("Seat number: " + seatNumber);
                flightInfo.add("Seat class: " + seatClass);

                // add details to allocated seating
                allocatedSeatList.put(passenger.getPassportNumber(), flightInfo);

                System.out.println("\n\n");
                System.out.println(allocatedSeatList);


            } else if (!seatingList.containsValue(seatClass)) {
                // give option to select another seat or go on waiting list (queue)
            }
        }
    }

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