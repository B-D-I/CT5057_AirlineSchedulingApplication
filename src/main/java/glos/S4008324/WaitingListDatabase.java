package glos.S4008324;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WaitingListDatabase implements Database {

    WaitingListSeat waitingListSeat = new WaitingListSeat();
    Queue<HashMap<String, WaitingListSeat>> waitingQueue = new LinkedList<>();

    /**
     * This method creates a queue (FIFO) of all waiting passengers (contained in HashMap)
     *
     * @param flightNumber: This is associated flight number for the correct .txt file
     * @return waitingQueue: an updated queue of all waiting passengers
     */
    public Queue<HashMap<String, WaitingListSeat>> createWaitingListObject(String flightNumber) {
        try {
            File myObj = new File("src/main/java/glos/S4008324/TxtFiles/WaitingList" + flightNumber + ".txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

                String passportNumber = myReader.nextLine();
                String passengerName = myReader.nextLine();
                String seatClass = myReader.nextLine();

                WaitingListSeat waitingListSeat = new WaitingListSeat();
                waitingListSeat.setSeatPassengerPassportNumber(passportNumber);
                waitingListSeat.setSeatPassengerName(passengerName);
                waitingListSeat.setSeatClass(seatClass);

                HashMap<String, WaitingListSeat> waitingPassengerMap = new HashMap<>();
                waitingPassengerMap.put(waitingListSeat.getSeatPassengerPassportNumber(), waitingListSeat);

                waitingQueue.add(waitingPassengerMap);
                if (myReader.hasNextLine()) {
                    myReader.nextLine();
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error" + e);
        }
        return waitingQueue;
    }

    public void printFullWaitList(String flightNumber) {
        Queue<HashMap<String, WaitingListSeat>> waitingQueue = createWaitingListObject(flightNumber);
        System.out.println("\nPassengers in waiting list for flight: " + flightNumber + "\n");
        for (HashMap<String, WaitingListSeat> passengers : waitingQueue) {
            System.out.println(passengers.values());
        }
    }

    public void printWaitListPassenger(String flightNumber, String passportNumber) {
        Queue<HashMap<String, WaitingListSeat>> waitingQueue = createWaitingListObject(flightNumber);
        int counter = 1;
        String passenger;
        for (HashMap<String, WaitingListSeat> passengers : waitingQueue) {
            if (passengers.containsKey(passportNumber)) {
                passenger = passengers.values() + "\t\tposition: " + counter;
                System.out.println(passenger);
            }
            counter++;
        }
    }


    public void offerFreeSeat(String flightNumber, String seatNumber, String seatClass) {
        Queue<HashMap<String, WaitingListSeat>> waitingQueue = createWaitingListObject(flightNumber);
        // passport number : waitList
        HashMap<String, WaitingListSeat> waitingPassenger = waitingQueue.peek();

        if (waitingPassenger != null) {
            System.out.println("\nNext Passenger: " + waitingPassenger.values());
            System.out.println("\nDo you wish to book this passenger onto the plane? (Y|N) ");
            String bookWaitingPassenger = scanner.nextLine().trim().toUpperCase(Locale.ROOT);

            if (bookWaitingPassenger.equals("Y")) {
                HashMap<String, WaitingListSeat> passenger = waitingQueue.poll();

                for (WaitingListSeat waitingListSeat : passenger.values()) {
                    // get head passenger information
                    String name = waitingListSeat.seatPassengerName;
                    String passport = waitingListSeat.seatPassengerPassportNumber;
                    String seatingClass = waitingListSeat.seatClass;
                    // create passenger object with information
                    Passenger upgradePassenger = new Passenger();
                    upgradePassenger.setName(name);
                    upgradePassenger.setPassportNumber(passport);
                    // add passenger to scheduled flight
                    ScheduledSeat scheduledSeat = new ScheduledSeat();
                    scheduledSeat.addPassengerToScheduledSeat(flightNumber, seatNumber, seatClass, upgradePassenger);
                    // remove passenger
                    waitingQueue.poll();
                    // rewrite .txt
                    waitingListSeat.modifyScheduledSeating(waitingQueue, flightNumber);
                }
            } else if (bookWaitingPassenger.equals("N")) {
                // remove passenger
                waitingQueue.poll();
                // rewrite .txt
                waitingListSeat.modifyScheduledSeating(waitingQueue, flightNumber);
                // offer next passenger
                offerFreeSeat(flightNumber, seatNumber, seatClass);
            }
        } else {
            System.out.println("There is no waiting list fo this flight");
            // FUNCTION TO PUT SEAT BACK IN FLIGHTS.TXT **************************
        }
    }
}



        // if no, then the that passenger is removed from the waiting list and next passenger is offered the seat
//        if (bookWaitingPassenger.equals("N")) {
//            while(!waitingQueue.isEmpty()){
//                waitingList.updateWaitingList(waitingQueue, flightNumber);
                // Function to remove poll passenger and ask next passenger until queue empty
                // once queue empty update flights.txt with cancelled seat class and number

//            }
//        }

//        else if (bookWaitingPassenger.equals("Y")){
//            // NEED THE PASSENGER OBJECT >>>
////            scheduledSeat.addPassengerToScheduledSeat(flightNumber, seatNumber, seatClass, );
//            // Function to add queue passenger to scheduled seat list
//        }
//        // passenger next in line must be removed from the queue and then .txt must be refreshed
//        // hashmap of the next in line passenger (passport number : waitList object)
//
//
//        HashMap<String, WaitingListSeat> removedPassenger = waitingQueue.poll();
//        System.out.println(removedPassenger.keySet());
//        System.out.println("Removed: " +removedPassenger);
//        System.out.println("\nWaiting List: " +waitingQueue);
//
//        // This is sending the updated list (NOT WORKING)
////            waitingListPassengers.updateWaitingList(waitingQueue, flightNumber);
//
//        // ONCE PASSENGER IS DELETED FROM .TXT AND IS REFRESHED...
//        // Waiting passenger needs adding to scheduled flight
//
////            scheduledSeat.addPassengerToScheduledSeat(flightNumber, seatNumber, seatClass, );
//
//        // update with a waiting list function
//    }
//
//
////    public void offerFreeSeat(String flightNumber, String seatNumber, String seatClass) {
////
////        // queue containing hashmaps of all queue passengers (passport number : waitingList object)
////        Queue<HashMap<String, WaitingList>> waitingQueue = createWaitingListObject(flightNumber);
////        // hashmap of head passenger (passport number: object)
////        HashMap<String, WaitingList> nextPassenger = waitingQueue.peek();
////
////
//////                    for (WaitingListPassengers waitingListPassengers: waitingQueue) {
//////                if(waitingListPassengers.getSeatClass().equals(seatClass) {
//////
//////                }}
////
////        // passenger object values
////        assert nextPassenger != null;
////        Collection<WaitingList> passengerName = nextPassenger.values();
////
////        // display passenger next in queue and offer the seat
////        System.out.println("""
////
////
////                    The passenger next on the waiting list is:
////
////                PassportNo | Name | Seat Class
////                """);
////        System.out.println(passengerName);
////        System.out.println("\n\n");
////        System.out.println("Do you wish to book this passenger onto the plane? (Y|N) ");
////        String bookWaitingPassenger = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
////        // if no, then the that passenger is removed from the waiting list and next passenger is offered the seat
////        if (bookWaitingPassenger.equals("N")) {
////            // Function to remove poll passenger and ask next passenger until queue empty
////            // once queue empty update flights.txt with cancelled seat class and number
////        }
////        else if (bookWaitingPassenger.equals("Y")){
////            // Function to add queue passenger to scheduled seat list
////        }
////            // passenger next in line must be removed from the queue and then .txt must be refreshed
////            // hashmap of the next in line passenger (passport number : waitList object)
////
////            HashMap<String, WaitingList> removedPassenger = waitingQueue.poll();
////            System.out.println(removedPassenger.values());
////            System.out.println("Removed: " +removedPassenger);
////            System.out.println("\nWaiting List: " +waitingQueue);
////
////            // This is sending the updated list (NOT WORKING)
//////            waitingListPassengers.updateWaitingList(waitingQueue, flightNumber);
////
////            // ONCE PASSENGER IS DELETED FROM .TXT AND IS REFRESHED...
////            // Waiting passenger needs adding to scheduled flight
////
//////            scheduledSeat.addPassengerToScheduledSeat(flightNumber, seatNumber, seatClass, );
////
////            // update with a waiting list function
////
////            // ADD PASSENGER TO SCHEDULED LIST (CONFIRM SEAT CLASS)
//////        } else {
//////            // remove passenger and check next passenger etc..
//////            // if no new passenger then seat needs updating in flights.txt
//////        }
////        }
//
//
//
//}
