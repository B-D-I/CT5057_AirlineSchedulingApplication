package glos.S4008324;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ScheduledSeat extends Seat{

    public String getSeatPassengerPassportNumber() {
        return seatPassengerPassportNumber;
    }
    public void setSeatPassengerPassportNumber(String seatPassengerPassportNumber) {
        this.seatPassengerPassportNumber = seatPassengerPassportNumber;
    }
    public String getSeatPassengerName() {
        return seatPassengerName;
    }
    public void setSeatPassengerName(String seatPassengerName) {
        this.seatPassengerName = seatPassengerName;
    }
    public String getSeatNumber() {
        return seatNumber;
    }
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString()
    {return "Passenger Name: "+getSeatPassengerName()+"\tPassport Number: "+getSeatPassengerPassportNumber()+"\tSeat Class: " + getSeatClass() + " \tSeat Number: " + getSeatNumber();}


    public void addPassengerToScheduledSeat(String flightNumber, String seatNumber, String seatClass, Passenger passenger) {
        try{
            // add to specific flight seating
            BufferedWriter out = new BufferedWriter(new FileWriter("src/main/java/glos/S4008324/TxtFiles/ScheduledSeating" + flightNumber + ".txt", true));
            out.write("\n" + passenger.getPassportNumber() + "\n" + passenger.getName() + "\n" + seatClass + "\n" + seatNumber + "\n");
            out.close();

            // add to generic passenger bookings
//            HashMap<String, String> bookedPassengers = new HashMap<>();
//            bookedPassengers.put(passenger.getPassportNumber(), flightNumber);
            BufferedWriter out2 = new BufferedWriter(new FileWriter("src/main/java/glos/S4008324/TxtFiles/BookedFlights.txt", true));
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
            BufferedWriter out = new BufferedWriter(new FileWriter("src/main/java/glos/S4008324/TxtFiles/ScheduledSeating" + flightNumber + ".txt", false));
            for(ScheduledSeat scheduledSeat: scheduledPassengers.values())
                out.write(scheduledSeat.getSeatPassengerPassportNumber() + "\n" + scheduledSeat.getSeatPassengerName() +
                        "\n" + scheduledSeat.getSeatClass() + "\n" + scheduledSeat.getSeatNumber() + "\n\n");
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
