package glos.S4008324;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Queue;

public class WaitingListSeat extends Seat{

    @Override
    public String toString()
    {return "Passenger Name: "+getSeatPassengerName()+"\t\tPassport Number: "+getSeatPassengerPassportNumber()+"\t\tSeat Class: " + getSeatClass();}


    public void addPassengerToWaitingList(String flightNumber, String seatClass, Passenger passenger) {
        try {
            // add to specific flight form
            BufferedWriter out = new BufferedWriter(new FileWriter("src/main/java/glos/S4008324/TxtFiles/WaitingList" + flightNumber + ".txt", true));
            out.write("\n" + passenger.getPassportNumber() + "\n" + passenger.getName() + "\n" + seatClass + "\n");
            out.close();
            // add to generic passenger form
            BufferedWriter out2 = new BufferedWriter(new FileWriter("src/main/java/glos/S4008324/TxtFiles/WaitingLists.txt", true));
            out2.write("\n" + passenger.getPassportNumber() + "\n" + flightNumber + "\n");
            out2.close();

            System.out.println("\nPassenger added to waiting list");

        } catch (IOException e) {
            e.printStackTrace();
        } scanner.nextLine();
    }


    public void modifyScheduledSeating(Queue<HashMap<String, WaitingListSeat>> waitingQueue , String flightNumber){
        try {
            BufferedWriter outAgain = new BufferedWriter(new FileWriter("src/main/java/glos/S4008324/TxtFiles/WaitingList" + flightNumber + ".txt", false));
            for (HashMap<String, WaitingListSeat> passengers : waitingQueue) {
                for (WaitingListSeat waitingListSeat : passengers.values()) {
                    outAgain.write(waitingListSeat.getSeatPassengerPassportNumber() + "\n" + waitingListSeat.getSeatPassengerName() +
                            "\n" + waitingListSeat.getSeatClass() + "\n\n");
                }
            } outAgain.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

