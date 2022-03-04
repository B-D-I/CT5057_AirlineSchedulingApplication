package glos.S4008324;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class WaitingList extends Seat{

    public void addPassengerToWaitingList(String flightNumber, String seatClass, Passenger passenger) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("src/main/java/glos/S4008324/TxtFiles/WaitingList" + flightNumber + ".txt", true));
            out.write("\n" + passenger.getPassportNumber() + "\n" + passenger.getName() + "\n" + seatClass + "\n");
            out.close();

//            HashMap<String, String> waitingPassengers = new HashMap<>();
//            waitingPassengers.put(passenger.getPassportNumber(), flightNumber);
            BufferedWriter out2 = new BufferedWriter(new FileWriter("src/main/java/glos/S4008324/TxtFiles/WaitingLists.txt", true));
            out2.write("\n" + passenger.getPassportNumber() + "\n" + flightNumber + "\n");
            out2.close();

            System.out.println("\nPassenger added to waiting list");

        } catch (IOException e) {
            e.printStackTrace();
        } scanner.nextLine();
    }


//    public void updateWaitingList(Queue<HashMap<Passenger, String>> waitingQueue)

    // This is only updating the first passenger of the queue
//        public void updateWaitingList(Queue<HashMap<String, WaitingList>> waitingQueue, String flightNumber){
//            try {
//                BufferedWriter out = new BufferedWriter(new FileWriter("src/main/java/glos/S4008324/TxtFiles/WaitingList" + flightNumber + ".txt", false));
//
//
//                // ISSUE IS HERE: need to iterate and update all queue elements
////                for (WaitingListPassengers waitingListPassengers : waitingQueue.element().values())
////
////                out.write(waitingListPassengers.getWaitingPassengerPassportNumber() + "\n" + waitingListPassengers.getWaitingPassengerName() +
////                        "\n" + waitingListPassengers.getSeatClass() + "\n\n");
////
////                out.close();
//
//                // OR
//
//                //                waitingQueue.forEach(
////                        out.write(getWaitingPassengerPassportNumber() + "\n" + getWaitingPassengerName() +
////                                        "\n" + getSeatClass() + "\n\n");
////                                out.close();
//
//                System.out.println("Waiting list updated");
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

