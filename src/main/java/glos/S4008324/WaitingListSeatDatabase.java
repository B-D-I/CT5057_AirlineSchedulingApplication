package glos.S4008324;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WaitingListSeatDatabase implements Database{
    WaitingList waitingList = new WaitingList();
    //    Queue<HashMap<String, WaitingList>> waitingQueue = new LinkedList<>();

    Queue<HashMap<Passenger, String>> waitingQueue = new LinkedList<>();

    /**
     * This method creates a queue (FIFO) of all waiting passengers (contained in HashMap)
     * @param flightNumber: This is associated flight number for the correct .txt file
     * @return waitingQueue: an updated queue of all waiting passengers
     */
    // Queue<HashMap<String, WaitingList>>
    public Queue<HashMap<Passenger, String>> createWaitingListObject(String flightNumber){
        try {
            File myObj = new File("src/main/java/glos/S4008324/TxtFiles/WaitingList"+flightNumber+".txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

                String passportNumber = myReader.nextLine();
                String passengerName = myReader.nextLine();
                String seatClass = myReader.nextLine();

                // create a waiting list object
                WaitingList waitingList = new WaitingList();
                waitingList.setSeatClass(seatClass);
//                waitingList.setWaitingPassengerPassportNumber(passportNumber);
//                waitingList.setWaitingPassengerName(passengerName);
//                waitingList.setWaitingPassengerName();

                Passenger waitingPassenger = new Passenger();
                waitingPassenger.setPassportNumber(passportNumber);
                waitingPassenger.setName(passengerName);

                HashMap<Passenger, String> waitingPassengerMap = new HashMap<>();
                waitingPassengerMap.put(waitingPassenger, waitingList.getSeatClass());

                // HashMap with passenger passport number and required seat class
//                HashMap<String, WaitingList> waitingPassengerPassportAndWaitingList= new HashMap<>();
//                waitingPassengerPassportAndWaitingList.put(waitingList.getWaitingPassengerPassportNumber(), waitingList);

                // add HashMaps to Queue
//                waitingQueue.add(waitingPassengerPassportAndWaitingList);
                waitingQueue.add(waitingPassengerMap);
                if (myReader.hasNextLine()) {
                    myReader.nextLine();
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error" + e);
        } return waitingQueue;
    }

    public void printWaitList(String flightNumber) {
        Queue<HashMap<Passenger, String>> waitingQueue = createWaitingListObject(flightNumber);
        System.out.println("\nPassengers in waiting list for flight: " + flightNumber + "\n");
        for (HashMap<Passenger, String> passengers : waitingQueue) {
            System.out.println(passengers);
        }
    }


    public void offerFreeSeat(String flightNumber, String seatNumber, String seatClass){
        Queue<HashMap<Passenger, String>> waitingQueue = createWaitingListObject(flightNumber);
        HashMap<Passenger, String> waitingPassenger = waitingQueue.peek();

        assert waitingPassenger != null;
        System.out.println("Next Passenger:" +waitingPassenger.keySet());
        System.out.println("\nSeat class: " + waitingPassenger.values());



        System.out.println("Do you wish to book this passenger onto the plane? (Y|N) ");
        String bookWaitingPassenger = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
        // if no, then the that passenger is removed from the waiting list and next passenger is offered the seat
        if (bookWaitingPassenger.equals("N")) {
            while(!waitingQueue.isEmpty()){
//                waitingList.updateWaitingList(waitingQueue, flightNumber);
                // Function to remove poll passenger and ask next passenger until queue empty
                // once queue empty update flights.txt with cancelled seat class and number

            }
        }
        else if (bookWaitingPassenger.equals("Y")){
            // NEED THE PASSENGER OBJECT >>>
//            scheduledSeat.addPassengerToScheduledSeat(flightNumber, seatNumber, seatClass, );
            // Function to add queue passenger to scheduled seat list
        }
        // passenger next in line must be removed from the queue and then .txt must be refreshed
        // hashmap of the next in line passenger (passport number : waitList object)


        HashMap<Passenger, String> removedPassenger = waitingQueue.poll();
        System.out.println(removedPassenger.keySet());
        System.out.println("Removed: " +removedPassenger);
        System.out.println("\nWaiting List: " +waitingQueue);

        // This is sending the updated list (NOT WORKING)
//            waitingListPassengers.updateWaitingList(waitingQueue, flightNumber);

        // ONCE PASSENGER IS DELETED FROM .TXT AND IS REFRESHED...
        // Waiting passenger needs adding to scheduled flight

//            scheduledSeat.addPassengerToScheduledSeat(flightNumber, seatNumber, seatClass, );

        // update with a waiting list function
    }


//    public void offerFreeSeat(String flightNumber, String seatNumber, String seatClass) {
//
//        // queue containing hashmaps of all queue passengers (passport number : waitingList object)
//        Queue<HashMap<String, WaitingList>> waitingQueue = createWaitingListObject(flightNumber);
//        // hashmap of head passenger (passport number: object)
//        HashMap<String, WaitingList> nextPassenger = waitingQueue.peek();
//
//
////                    for (WaitingListPassengers waitingListPassengers: waitingQueue) {
////                if(waitingListPassengers.getSeatClass().equals(seatClass) {
////
////                }}
//
//        // passenger object values
//        assert nextPassenger != null;
//        Collection<WaitingList> passengerName = nextPassenger.values();
//
//        // display passenger next in queue and offer the seat
//        System.out.println("""
//
//
//                    The passenger next on the waiting list is:
//
//                PassportNo | Name | Seat Class
//                """);
//        System.out.println(passengerName);
//        System.out.println("\n\n");
//        System.out.println("Do you wish to book this passenger onto the plane? (Y|N) ");
//        String bookWaitingPassenger = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
//        // if no, then the that passenger is removed from the waiting list and next passenger is offered the seat
//        if (bookWaitingPassenger.equals("N")) {
//            // Function to remove poll passenger and ask next passenger until queue empty
//            // once queue empty update flights.txt with cancelled seat class and number
//        }
//        else if (bookWaitingPassenger.equals("Y")){
//            // Function to add queue passenger to scheduled seat list
//        }
//            // passenger next in line must be removed from the queue and then .txt must be refreshed
//            // hashmap of the next in line passenger (passport number : waitList object)
//
//            HashMap<String, WaitingList> removedPassenger = waitingQueue.poll();
//            System.out.println(removedPassenger.values());
//            System.out.println("Removed: " +removedPassenger);
//            System.out.println("\nWaiting List: " +waitingQueue);
//
//            // This is sending the updated list (NOT WORKING)
////            waitingListPassengers.updateWaitingList(waitingQueue, flightNumber);
//
//            // ONCE PASSENGER IS DELETED FROM .TXT AND IS REFRESHED...
//            // Waiting passenger needs adding to scheduled flight
//
////            scheduledSeat.addPassengerToScheduledSeat(flightNumber, seatNumber, seatClass, );
//
//            // update with a waiting list function
//
//            // ADD PASSENGER TO SCHEDULED LIST (CONFIRM SEAT CLASS)
////        } else {
////            // remove passenger and check next passenger etc..
////            // if no new passenger then seat needs updating in flights.txt
////        }
//        }



}
