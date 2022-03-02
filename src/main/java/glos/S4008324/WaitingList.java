package glos.S4008324;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Queue;

public class WaitingList {

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

    // THIS IS REMOVING 2 PASSENGERS ??
        public void updateWaitingList(Queue<HashMap<String, WaitingList>> waitingQueue, String flightNumber){
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter("src/main/java/glos/S4008324/WaitingList" + flightNumber + ".txt", false));

                for (WaitingList waitingList: waitingQueue.element().values())
                out.write(waitingList.getWaitingPassengerPassportNumber() + "\n" + waitingList.getWaitingPassengerName() +
                        "\n" + waitingList.getSeatClass() + "\n\n");

                out.close();
                System.out.println("Passenger deleted from flight");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

