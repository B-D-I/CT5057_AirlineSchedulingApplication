package glos.S4008324;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WaitingListDatabase implements Database {

    /**
     * This method creates a queue (FIFO) of all waiting passengers (contained in HashMap)
     *
     * @param flightNumber:
     * @return
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

    public void checkWaitingListPassengers(String flightNumber, String seatNumber, String seatClass){
        Queue<HashMap<String, WaitingListSeat>> waitingQueue = createWaitingListObject(flightNumber);
        // passport number : waitList
        offerFreeSeat(waitingQueue, flightNumber, seatNumber, seatClass);
    }
    private void offerFreeSeat(Queue<HashMap<String, WaitingListSeat>> waitingQueue, String flightNumber, String seatNumber, String seatClass) {
        HashMap<String, WaitingListSeat> waitingPassenger = waitingQueue.peek();
        if (waitingPassenger == null) {
            emptyQueueUpdateFlight(flightNumber, seatNumber, seatClass);
        } else {
            offerWaitingListPassenger(waitingQueue, waitingPassenger, flightNumber, seatClass, seatNumber);
        }
    }
        private void emptyQueueUpdateFlight(String flightNumber, String seatNumber, String seatClass){
            System.out.println("There is no waiting list for this flight");
            // create a flight object from flights.txt
            FlightDatabase flightDatabase = new FlightDatabase();
            HashMap<String, Flight> flightMap = flightDatabase.createFlightObjectsMap();
            // get specific flight
            Flight flight = flightMap.get(flightNumber);
            // get seating list of flight
            HashMap<String, String> seatingList = flight.getSeatingList();
            // add the seat back to flight
            seatingList.put(seatNumber, seatClass);

            // update flights.txt
            UnallocatedSeat unallocatedSeat = new UnallocatedSeat();
            unallocatedSeat.modifyFlightSeating(flightMap);
        }

        private void offerWaitingListPassenger(Queue<HashMap<String, WaitingListSeat>> waitingQueue, HashMap<String, WaitingListSeat> waitingPassenger, String flightNumber, String seatNumber, String seatClass ){
            System.out.println("\nNext Passenger: " + waitingPassenger.values());
            System.out.println("\nDo you wish to book this passenger onto the plane? (Y|N) ");
            String bookWaitingPassenger = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
            if (bookWaitingPassenger.equals("Y")) {
                scheduleWaitingPassenger(waitingQueue, waitingPassenger, flightNumber, seatClass, seatNumber);
            } else if (bookWaitingPassenger.equals("N")) {
                offerNextPassenger(waitingQueue, waitingPassenger, flightNumber, seatClass, seatNumber);
            } else {
                System.out.println("Not a valid response");
            }
        }
        private void offerNextPassenger(Queue<HashMap<String, WaitingListSeat>> waitingQueue, HashMap<String, WaitingListSeat> waitingPassenger, String flightNumber, String seatNumber, String seatClass){
            WaitingListSeat waitingListSeat = new WaitingListSeat();
            PassengerDatabase passengerDatabase = new PassengerDatabase();

            // remove passenger
            waitingQueue.poll();
            try {
                // remove from WaitingLists.txt
                for(WaitingListSeat waitingListSeating: waitingPassenger.values()){
                    String passportNumber = waitingListSeating.seatPassengerPassportNumber;
                    passengerDatabase.removePassengerFromList("WaitingLists", passportNumber);
                }
                // I NEED TO GET THE PASSPORT NUMBER FROM HASHMAP KEY:

                // rewrite specific WaitingList.txt
                waitingListSeat.modifyScheduledSeating(waitingQueue, flightNumber);
                // offer next passenger
                offerFreeSeat(waitingQueue, flightNumber, seatNumber, seatClass);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        private void scheduleWaitingPassenger(Queue<HashMap<String, WaitingListSeat>> waitingQueue, HashMap<String, WaitingListSeat> waitingPassenger, String flightNumber, String seatNumber, String seatClass ){
            PassengerDatabase passengerDatabase = new PassengerDatabase();
            waitingQueue.poll();
            for (WaitingListSeat waitingListSeat : waitingPassenger.values()) {
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
                // update .txt
                waitingListSeat.modifyScheduledSeating(waitingQueue, flightNumber);
                passengerDatabase.removePassengerFromList("WaitingLists", passport);
            }
        }
}