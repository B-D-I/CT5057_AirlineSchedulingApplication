package glos.S4008324;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Queue;

public class WaitingListPassengers {

    private String waitingPassengerPassportNumber;
    private String waitingPassengerName;
    private String seatClass;

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

    @Override
    public String toString()
    {
        return waitingPassengerPassportNumber+", "+waitingPassengerName+", "+seatClass;
    }

    public void addPassengerToWaitingList(String flightNumber, String seatClass, Passenger passenger) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("src/main/java/glos/S4008324/WaitingList" + flightNumber + ".txt", true));
            out.write("\n" + passenger.getPassportNumber() + "\n" + passenger.getName() + "\n" + seatClass + "\n");
            out.close();

            System.out.println("\nPassenger added to waiting list");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This is only updating the first passenger of the queue
        public void updateWaitingList(Queue<HashMap<String, WaitingListPassengers>> waitingQueue, String flightNumber){
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter("src/main/java/glos/S4008324/WaitingList" + flightNumber + ".txt", false));


                // ISSUE IS HERE: need to iterate and update all queue elements
//                for (WaitingListPassengers waitingListPassengers : waitingQueue.element().values())
//
//                out.write(waitingListPassengers.getWaitingPassengerPassportNumber() + "\n" + waitingListPassengers.getWaitingPassengerName() +
//                        "\n" + waitingListPassengers.getSeatClass() + "\n\n");
//
//                out.close();

                // OR

                //                waitingQueue.forEach(
//                        out.write(getWaitingPassengerPassportNumber() + "\n" + getWaitingPassengerName() +
//                                        "\n" + getSeatClass() + "\n\n");
//                                out.close();

                System.out.println("Waiting list updated");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

