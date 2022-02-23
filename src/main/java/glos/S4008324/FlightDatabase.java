package glos.S4008324;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class FlightDatabase extends Database{

    public FlightDatabase(){
    }

    public String getDbUsername() {
        return "nathan";
    }
    public String getDbPassword() {
        return "password";
    }

    HashMap<String, Flight> allFlightsMap = new HashMap<>();

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
                addFlightRoutes(departingAirport, destinationAirport);

                // add all flight objects created from flights.txt to arrayList
                allFlightsMap.put(flightNo, f);
                if (myReader.hasNextLine()){
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

    public void showAllFlights(){
        HashMap<String, Flight> flightMap = createFlightObjectsMap();
        flightMap.entrySet().forEach(System.out::println);
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

        // allFlightMap == hashmap of all flights in flights.txt   ---- this is empty?
        Flight flight = allFlightsMap.get(flightNumber);


        // get the seating list from chosen flight
        HashMap<String, String> SeatingList = flight.getSeatingList();

        if (SeatingList.containsValue(seatClass)) {

            //Display seat number with seat class
            System.out.println(flight.getSeatingList()); // for the correct seat class

            //Pick the seatNo
            System.out.println("Enter seat number: ");
            String seatNumber = scanner.nextLine();

            //Add to the seat allocation (give manual and random allocate option)

//            allocatedSeats.put(passenger.getPassportNumber(), seatNumber seatClass);

            // delete it from the seating list
            SeatingList.remove(seatNumber);
        } else if (!SeatingList.containsValue(seatClass)) {
            // give option to select another seat or go on waiting list (queue)
        }
    }



    public void addFlightRoutes(String departure, String destination){
        HashMap<String, Flight> flightMap = createFlightObjectsMap();
        // allFlightsMap -> get all departures and destinations and fill the graph -------

        // example airports >
//        airports.addEdge("BHM", "CDG", true);
//        airports.addEdge("LHR", "MIA", true);
        airports.addEdge(departure, destination, true);
    }

    public void printAirports(){
        System.out.println("Airports:\n" + airports.toString());
    }

    public boolean checkRoute(String departure, String destination){
        boolean hasRoute = airports.hasRoute(departure, destination);
        return hasRoute;
    }

    public boolean checkAirport(String airport){
        boolean hasAirport = airports.hasAirport(airport);
        return hasAirport;
    }
}
