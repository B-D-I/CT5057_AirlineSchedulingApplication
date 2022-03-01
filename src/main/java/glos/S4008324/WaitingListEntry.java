package glos.S4008324;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WaitingListEntry {

    private String waitingPassengerPassportNumber;
    private String waitingPassengerName;
    private String seatClass;
    private String waitingListFlightNumber;

//    HashMap<String, String> waitingPassenger = new HashMap<>();


    public void setWaitingPassengerPassportNumber(String waitingPassengerPassportNumber) {
        this.waitingPassengerPassportNumber = waitingPassengerPassportNumber;
    }
    public String getWaitingPassengerPassportNumber() {
        return waitingPassengerPassportNumber;
    }

    public void setWaitingPassengerName(String waitingPassengerName) {
        this.waitingPassengerName = waitingPassengerName;
    }
    public String getWaitingPassengerName() {
        return waitingPassengerName;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }
    public String getSeatClass() {
        return seatClass;
    }

    public void setWaitingListFlightNumber(String waitingListFlightNumber) {
        this.waitingListFlightNumber = waitingListFlightNumber;
    }

    public void updateFlightTxt(String flightNumber, String seatClass, Passenger passenger) {

//        waitingPassenger.put(passenger.getPassportNumber(), seatClass);
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("src/main/java/glos/S4008324/WaitingList" + flightNumber + ".txt", true));
            out.write("\n" + passenger.getPassportNumber() + "\n" + passenger.getName() + "\n" + seatClass + "\n");
            out.close();

            System.out.println("\nPassenger added to waiting list");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
