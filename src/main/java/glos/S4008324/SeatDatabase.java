package glos.S4008324;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SeatDatabase implements Database {

    Queue<HashMap<String, String>> waitingQueue = new LinkedList<>();

    public Queue<HashMap<String, String> > createWaitingListObject(String flightNumber){
        try {
            File myObj = new File("src/main/java/glos/S4008324/WaitingList"+flightNumber+".txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

                String passportNumber = myReader.nextLine();
                String passengerName = myReader.nextLine();
                String seatClass = myReader.nextLine();

                WaitingListEntry waitingListEntry = new WaitingListEntry();
                waitingListEntry.setWaitingPassengerPassportNumber(passportNumber);
                waitingListEntry.setWaitingPassengerName(passengerName);
                waitingListEntry.setSeatClass(seatClass);

                // put
                HashMap<String, String> waitingPassenger = new HashMap<>();
                waitingPassenger.put(waitingListEntry.getWaitingPassengerPassportNumber(), waitingListEntry.getSeatClass());

                waitingQueue.add(waitingPassenger);
                if (myReader.hasNextLine()) {
                    myReader.nextLine();
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error" + e);
        } return waitingQueue;
    }

    public void printWaitList(String flightNumber){
        Queue<HashMap<String, String> > waitingQueue = createWaitingListObject(flightNumber);
        System.out.println("Passenger Passport Number & Seat Class: "+waitingQueue);



        // THIS IS WRONG !!!!!!!!!! I NEED MULTIPLE HASHMAPS INSIDE ONE QUEUE, NOT A QUEUE FOR EACH HASHMAP!!!!!!!!!!!!
        // CREATE-WAIT-LIST-OBJECT -->> THIS NEEDS TO CREATE HASHMAPS OF EACH WAITING --> THEN PLACE ALL HASHMAPS INTO A QUEUE
    }
}


