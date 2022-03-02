package glos.S4008324;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SeatDatabase implements Database {

    Queue<HashMap<HashMap<String, String>, String>> waitingQueue = new LinkedList<>();
    HashMap<HashMap<String, String>, HashMap<String, String>> scheduledPassengers = new HashMap<>();
    /**
     * This method creates a queue (FIFO) of all waiting passengers (contained in HashMap)
     * @param flightNumber: This is associated flight number for the correct .txt file
     * @return waitingQueue: an updated queue of all waiting passengers
     */
    public Queue<HashMap<HashMap<String, String>, String>> createWaitingListObject(String flightNumber){
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

                // A hashmap containing passenger passport and name
                HashMap<String, String> waitingPassenger = new HashMap<>();
                waitingPassenger.put(waitingList.getWaitingPassengerPassportNumber(), waitingList.getWaitingPassengerName());

                // The passenger hashmap is placed inside another hashmap, with the value as seat class
                HashMap<HashMap<String, String>, String> waitingPassengerAndSeatClass = new HashMap<>();
                waitingPassengerAndSeatClass.put(waitingPassenger, waitingList.getSeatClass());

                // add HashMaps to Queue
                waitingQueue.add(waitingPassengerAndSeatClass);
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
        Queue<HashMap<HashMap<String, String>, String>> waitingQueue = createWaitingListObject(flightNumber);
        System.out.println("Waiting Passenger's Passport Number & Seat Class: "+waitingQueue);
    }

    // HashMap<String, String>
    public void offerFreeSeat(String flightNumber){
        Queue<HashMap<HashMap<String, String>, String>> waitingQueue= createWaitingListObject(flightNumber);
        System.out.println("The passenger next in line for this seat is: "+waitingQueue.peek());
        System.out.println("Do you wish to book this passenger onto the plane? (Y|N) ");
        String bookWaitingPassenger = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
        if (bookWaitingPassenger.equals("Y")){
            System.out.println("...");
        } // return the hashmap ?
    }


    private HashMap<HashMap<String, String>, HashMap<String, String>> createScheduledPassengers(String flightNumber){
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

                // create a hashmap, containing a passenger hashmap and seat hashmap
                HashMap<String, String> bookedPassenger = new HashMap<>();
                HashMap<String, String> bookedSeat = new HashMap<>();

                bookedPassenger.put(scheduledSeat.getBookedPassengerPassportNumber(), scheduledSeat.getBookedPassengerName());
                bookedSeat.put(scheduledSeat.getSeatNumber(), scheduledSeat.getSeatClass());

                scheduledPassengers.put(bookedPassenger, bookedSeat);

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
        HashMap<HashMap<String, String>, HashMap<String, String>> scheduledPassengers = createScheduledPassengers(flightNumber);
        System.out.println("Passengers on flight: "+flightNumber+" are:\n"+scheduledPassengers);
    }

}


