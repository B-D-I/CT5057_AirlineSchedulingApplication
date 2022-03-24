package glos.S4008324;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ScheduledSeat class enables booked seating information to be written to text file, and modified
 */
public final class ScheduledSeat extends Seat{

    @Override
    public String toString()
    {return "Passenger Name: "+getSeatPassengerName()+"\tPassport Number: "+getSeatPassengerPassportNumber()+"\tSeat Class: " + getSeatClass() + " \tSeat Number: " + getSeatNumber();}

    public void addPassengerToScheduledSeat(String flightNumber, String seatNumber, String seatClass, Passenger passenger) {
        try{
            // add to specific flight seating
            BufferedWriter out = new BufferedWriter(new FileWriter("target/classes/glos/S4008324/TxtFiles/ScheduledSeating" + flightNumber + ".txt", true));
            out.write(passenger.getPassportNumber() + "\n" + passenger.getName() + "\n" + seatClass + "\n" + seatNumber + "\n\n");
            out.close();

            // add to generic passenger form
            BufferedWriter out2 = new BufferedWriter(new FileWriter("target/classes/glos/S4008324/TxtFiles/BookedFlights.txt", true));
            out2.write("\n" + passenger.getPassportNumber() + "\n" + flightNumber + "\n");
            out2.close();

            System.out.println("\nPassenger added");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Hashmap (passport number : seat object)
    public void modifyScheduledSeating(HashMap<String, ScheduledSeat> scheduledPassengers, String flightNumber){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("target/classes/glos/S4008324/TxtFiles/ScheduledSeating" + flightNumber + ".txt", false));
            for(ScheduledSeat scheduledSeat: scheduledPassengers.values())
                out.write(scheduledSeat.getSeatPassengerPassportNumber() + "\n" + scheduledSeat.getSeatPassengerName() +
                        "\n" + scheduledSeat.getSeatClass() + "\n" + scheduledSeat.getSeatNumber() + "\n\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void modifyBookedFlights(HashMap<String, String> bookedFlights){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("target/classes/glos/S4008324/TxtFiles/BookedFlights.txt", false));
            for(Map.Entry<String, String> flights: bookedFlights.entrySet()){
                String passport = flights.getKey();
                String flight = flights.getValue();
                out.write(passport + "\n" + flight +"\n\n");
            } out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
