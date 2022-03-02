package glos.S4008324;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ScheduledSeat {
    private String bookedPassengerPassportNumber;
    private String bookedPassengerName;
    private String seatClass;
    private String seatNumber;

    public String getBookedPassengerPassportNumber() {
        return bookedPassengerPassportNumber;
    }
    public void setBookedPassengerPassportNumber(String bookedPassengerPassportNumber) {
        this.bookedPassengerPassportNumber = bookedPassengerPassportNumber;
    }

    public String getBookedPassengerName() {
        return bookedPassengerName;
    }
    public void setBookedPassengerName(String bookedPassengerName) {
        this.bookedPassengerName = bookedPassengerName;
    }

    public String getSeatClass() {
        return seatClass;
    }
    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public String getSeatNumber() {
        return seatNumber;
    }
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString()
    {
        return bookedPassengerPassportNumber+", "+bookedPassengerName+", "+seatClass+", "+seatNumber;
    }

    public void addPassengerToScheduledSeat(String flightNumber, String seatNumber, String seatClass, Passenger passenger) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("src/main/java/glos/S4008324/ScheduledSeating" + flightNumber + ".txt", true));
            out.write("\n" + passenger.getPassportNumber() + "\n" + passenger.getName() + "\n" + seatClass + "\n" + seatNumber + "\n");
            out.close();
            System.out.println("\nPassenger added to flight");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void modifyScheduledSeating(HashMap<String, ScheduledSeat> scheduledPassengers, String flightNumber){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("src/main/java/glos/S4008324/ScheduledSeating" + flightNumber + ".txt", false));
            for(ScheduledSeat scheduledSeat: scheduledPassengers.values())
                out.write(scheduledSeat.getBookedPassengerPassportNumber() + "\n" + scheduledSeat.getBookedPassengerName() +
                        "\n" + scheduledSeat.getSeatClass() + "\n" + scheduledSeat.getSeatNumber() + "\n\n");
            out.close();
            System.out.println("Passenger deleted from flight");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
