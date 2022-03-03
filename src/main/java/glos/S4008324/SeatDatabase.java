package glos.S4008324;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SeatDatabase implements Database {

    ScheduledSeat scheduledSeat = new ScheduledSeat();
    WaitingListPassengers waitingListPassengers = new WaitingListPassengers();
    Queue<HashMap<String, WaitingListPassengers>> waitingQueue = new LinkedList<>();
//    HashMap<String, HashMap<String, String>> scheduledPassengers = new HashMap<>();
    HashMap<String, ScheduledSeat> scheduledPassengers = new HashMap<>();
    /**
     * This method creates a queue (FIFO) of all waiting passengers (contained in HashMap)
     * @param flightNumber: This is associated flight number for the correct .txt file
     * @return waitingQueue: an updated queue of all waiting passengers
     */
    public Queue<HashMap<String, WaitingListPassengers>> createWaitingListObject(String flightNumber){
        try {
            File myObj = new File("src/main/java/glos/S4008324/WaitingList"+flightNumber+".txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

                String passportNumber = myReader.nextLine();
                String passengerName = myReader.nextLine();
                String seatClass = myReader.nextLine();

                // create a waiting list object
                WaitingListPassengers waitingListPassengers = new WaitingListPassengers();
                waitingListPassengers.setWaitingPassengerPassportNumber(passportNumber);
                waitingListPassengers.setWaitingPassengerName(passengerName);
                waitingListPassengers.setSeatClass(seatClass);

                // HashMap with passenger passport number and required seat class
                HashMap<String, WaitingListPassengers> waitingPassengerPassportAndWaitingList= new HashMap<>();
                waitingPassengerPassportAndWaitingList.put(waitingListPassengers.getWaitingPassengerPassportNumber(), waitingListPassengers);

                // add HashMaps to Queue
                waitingQueue.add(waitingPassengerPassportAndWaitingList);
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
        Queue<HashMap<String, WaitingListPassengers>>  waitingQueue = createWaitingListObject(flightNumber);
        System.out.println("Waiting Passenger's Passport Number & Seat Class: "+waitingQueue);
    }


    public void offerFreeSeat(String flightNumber, String seatNumber, String seatClass) {

        // queue containing hashmaps of all queue passengers (passport number : waitingList object)
        Queue<HashMap<String, WaitingListPassengers>> waitingQueue = createWaitingListObject(flightNumber);
        // hashmap of head passenger (passport number: object)
        HashMap<String, WaitingListPassengers> nextPassenger = waitingQueue.peek();

//                    for (WaitingListPassengers waitingListPassengers: waitingQueue) {
//                if(waitingListPassengers.getSeatClass().equals(seatClass) {
//
//                }}

        // passenger object values
        assert nextPassenger != null;
        Collection<WaitingListPassengers> passengerName = nextPassenger.values();

        // display passenger next in queue and offer the seat
        System.out.println("""
                    
                    
                    The passenger next on the waiting list is:
                    
                PassportNo | Name | Seat Class   
                """);
        System.out.println(passengerName);
        System.out.println("\n\n");
        System.out.println("Do you wish to book this passenger onto the plane? (Y|N) ");
        String bookWaitingPassenger = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
        // if no, then the that passenger is removed from the waiting list and next passenger is offered the seat
        if (bookWaitingPassenger.equals("N")) {
            // Function to remove poll passenger and ask next passenger until queue empty
            // once queue empty update flights.txt with cancelled seat class and number
        }
        else if (bookWaitingPassenger.equals("Y")){
            // Function to add queue passenger to scheduled seat list
        }
            // passenger next in line must be removed from the queue and then .txt must be refreshed
            // hashmap of the next in line passenger (passport number : waitList object)

            HashMap<String, WaitingListPassengers> removedPassenger = waitingQueue.poll();
            System.out.println(removedPassenger.values());
            System.out.println("Removed: " +removedPassenger);
            System.out.println("\nWaiting List: " +waitingQueue);

            // This is sending the updated list (NOT WORKING)
//            waitingListPassengers.updateWaitingList(waitingQueue, flightNumber);

            // ONCE PASSENGER IS DELETED FROM .TXT AND IS REFRESHED...
            // Waiting passenger needs adding to scheduled flight




//            scheduledSeat.addPassengerToScheduledSeat(flightNumber, seatNumber, seatClass, );

            // update with a waiting list function

            // ADD PASSENGER TO SCHEDULED LIST (CONFIRM SEAT CLASS)
//        } else {
//            // remove passenger and check next passenger etc..
//            // if no new passenger then seat needs updating in flights.txt
//        }
        }


    /**
     * This method returns a HashMap containing a booked passenger HashMap (passport number and name) along with a
     * booked seat HashMap (seat number and class)
     * @param flightNumber: This is associated flight number for the correct .txt file
     * @return: An update HashMap of all booked passengers and their seat
     */
    private HashMap<String, ScheduledSeat> createScheduledPassengers(String flightNumber){
        try {
            File myObj = new File("src/main/java/glos/S4008324/ScheduledSeating"+flightNumber+".txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

                String passportNumber = myReader.nextLine();
                String passengerName = myReader.nextLine();
                String seatClass = myReader.nextLine();
                String seatNumber = myReader.nextLine();

                ScheduledSeat scheduledSeat = new ScheduledSeat();
                scheduledSeat.setBookedPassengerPassportNumber(passportNumber);
                scheduledSeat.setBookedPassengerName(passengerName);
                scheduledSeat.setSeatClass(seatClass);
                scheduledSeat.setSeatNumber(seatNumber);

                scheduledPassengers.put(scheduledSeat.getBookedPassengerPassportNumber(), scheduledSeat);

                if (myReader.hasNextLine()) {
                    myReader.nextLine();
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error" + e);
        } return scheduledPassengers;
    }

    public void printScheduledPassengers(String flightNumber){
        HashMap<String, ScheduledSeat> scheduledPassengers = createScheduledPassengers(flightNumber);
        Set set = scheduledPassengers.entrySet();
        Iterator iterator = set.iterator();
        System.out.println(("""
                             Booked Seats For This Flight:
                    |Passport Number | Passenger | SeatClass | Seat Number 
                """));
        // THIS NEEDS TO PRINT EACH PASSENGER ON SEPARATE LINE
        while(iterator.hasNext()) {
            Map.Entry mapEntry = (Map.Entry) iterator.next();
            System.out.println("     \t\t" + mapEntry.getValue() );
        }
    }

    public void cancelPassenger(){
        System.out.println("Enter passenger passport number: ");
        String passportNumber = scanner.nextLine().trim();
        System.out.println("Enter flight number: ");
        String flightNumber = scanner.nextLine().trim();
        deletePassengerFromFlight(flightNumber, passportNumber);
    }
    private void deletePassengerFromFlight(String flightNumber, String passengerPassportNumber){

        // put the scheduled passengers from .txt into hashmap (passport number : scheduledSeat object)
        HashMap<String, ScheduledSeat> scheduledPassengers = createScheduledPassengers(flightNumber);

        String deletedSeatNumber = null;
        String deletedSeatClass = null;
        // check if passport number matches a scheduled passenger's
        for (ScheduledSeat scheduledSeat: scheduledPassengers.values()) {
            if(scheduledSeat.getBookedPassengerPassportNumber().equals(passengerPassportNumber)){
                System.out.println("\nPassenger: " +scheduledSeat.getBookedPassengerName());
                deletedSeatNumber = scheduledSeat.getSeatNumber();
                deletedSeatClass = scheduledSeat.getSeatClass();
                System.out.println("\nPassenger has been removed from flight: " + flightNumber);

                // remove from hashmap -- THIS IS WORKING CORRECTLY
//                scheduledPassengers.remove(passengerPassportNumber);
//                System.out.println(scheduledPassengers);
//                // to refresh the .txt file with amendment
//                scheduledSeat.modifyScheduledSeating(scheduledPassengers, flightNumber);

                // offer the seat to waiting list queue
                offerFreeSeat(flightNumber, deletedSeatNumber, deletedSeatClass);
        }

        }

    }
}


