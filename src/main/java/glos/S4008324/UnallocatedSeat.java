package glos.S4008324;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Unallocated Seat class provides text file writing and modification
 */
final class UnallocatedSeat extends Seat {
    public void updateFlightTxt(String departure, String destination, String dateDeparture, String flightNumber, HashMap<Integer, String> seatList) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("src/main/java/glos/S4008324/TxtFiles/Flights.txt", true));
            out.write("\n" + flightNumber + "\n" + departure + "\n" + dateDeparture +
                    "\n" + destination + "\n" + seatList.toString() + "\n");
            out.close();

            System.out.println("\nFlight added");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void modifyFlightSeating(HashMap<String, Flight> flightMap){
        try {
                BufferedWriter out = new BufferedWriter(new FileWriter("src/main/java/glos/S4008324/TxtFiles/Flights.txt", false));
                for(Flight flight: flightMap.values())
                out.write(flight.getFlightNumber() + "\n" + flight.getDeparture() + "\n" + flight.getDepartureDate() +
                        "\n" + flight.getDestination() + "\n" + flight.getSeatingList().toString()+ "\n\n");
                out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}