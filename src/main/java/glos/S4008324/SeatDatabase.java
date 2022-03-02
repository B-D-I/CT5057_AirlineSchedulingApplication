package glos.S4008324;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SeatDatabase implements Database {

    ScheduledSeat scheduledSeat = new ScheduledSeat();
    WaitingList waitingList = new WaitingList();
    Queue<HashMap<String, WaitingList>> waitingQueue = new LinkedList<>();
//    HashMap<String, HashMap<String, String>> scheduledPassengers = new HashMap<>();
    HashMap<String, ScheduledSeat> scheduledPassengers = new HashMap<>();
    /**
     * This method creates a queue (FIFO) of all waiting passengers (contained in HashMap)
     * @param flightNumber: This is associated flight number for the correct .txt file
     * @return waitingQueue: an updated queue of all waiting passengers
     */
    public Queue<HashMap<String, WaitingList>> createWaitingListObject(String flightNumber){
        try {
            File myObj = new File("src/main/java/glos/S4008324/WaitingList"+flightNumber+".txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

                String passportNumber = myReader.nextLine();
                String passengerName = myReader.nextLine();
                String seatClass = myReader.nextLine();

                // create a waiting list object
                WaitingList waitingList = new WaitingList();
                waitingList.setWaitingPassengerPassportNumber(passportNumber);
                waitingList.setWaitingPassengerName(passengerName);
                waitingList.setSeatClass(seatClass);

                // HashMap with passenger passport number and required seat class
                HashMap<String, WaitingList> waitingPassengerPassportAndWaitingList= new HashMap<>();
                waitingPassengerPassportAndWaitingList.put(waitingList.getWaitingPassengerPassportNumber(), waitingList);

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
        Queue<HashMap<String, WaitingList>>  waitingQueue = createWaitingListObject(flightNumber);
        System.out.println("Waiting Passenger's Passport Number & Seat Class: "+waitingQueue);
    }


    public void offerFreeSeat(String flightNumber) {

        Queue<HashMap<String, WaitingList>> waitingQueue = createWaitingListObject(flightNumber);
        System.out.println("\nThe passenger next in line for this seat is: " + waitingQueue.peek());
        System.out.println("Do you wish to book this passenger onto the plane? (Y|N) ");
        String bookWaitingPassenger = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
        if (bookWaitingPassenger.equals("N")) {
            HashMap<String, WaitingList> waitingPassenger = waitingQueue.poll();
            waitingList.updateWaitingList(waitingQueue, flightNumber);
            // update with a waiting list function

            // ADD PASSENGER TO SCHEDULED LIST (CONFIRM SEAT CLASS)
//        } else {
//            // remove passenger and check next passenger etc..
//            // if no new passenger then seat needs updating in flights.txt
//        }
        }
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

        HashMap<String, ScheduledSeat> scheduledPassengers = createScheduledPassengers(flightNumber);

            if (scheduledPassengers.containsKey(passengerPassportNumber)){
                System.out.println("Deleting: "+passengerPassportNumber);

                // need to remove the passenger from the flight >>> must update the .txt
                scheduledPassengers.remove(passengerPassportNumber);
                System.out.println(scheduledPassengers);

                // to refresh the .txt file with amendment
                scheduledSeat.modifyScheduledSeating(scheduledPassengers, flightNumber);

                offerFreeSeat(flightNumber);
        }

    }
}


