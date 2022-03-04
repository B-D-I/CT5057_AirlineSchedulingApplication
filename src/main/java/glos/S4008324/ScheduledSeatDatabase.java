package glos.S4008324;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ScheduledSeatDatabase implements Database {

    WaitingListSeatDatabase waitingListSeatDatabase = new WaitingListSeatDatabase();
    HashMap<String, ScheduledSeat> scheduledPassengers = new HashMap<>();

    /**
     * This method returns a HashMap containing a booked passenger HashMap (passport number and name) along with a
     * booked seat HashMap (seat number and class)
     * @param flightNumber: This is associated flight number for the correct .txt file
     * @return: An update HashMap of all booked passengers and their seat
     */
    private HashMap<String, ScheduledSeat> createScheduledPassengers(String flightNumber){
        try {
            File myObj = new File("src/main/java/glos/S4008324/TxtFiles/ScheduledSeating"+flightNumber+".txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

                String passportNumber = myReader.nextLine();
                String passengerName = myReader.nextLine();
                String seatClass = myReader.nextLine();
                String seatNumber = myReader.nextLine();

                ScheduledSeat scheduledSeat = new ScheduledSeat();
                scheduledSeat.setSeatPassengerPassportNumber(passportNumber);
                scheduledSeat.setSeatPassengerName(passengerName);
                scheduledSeat.setSeatClass(seatClass);
                scheduledSeat.setSeatNumber(seatNumber);

                // Passport : Seat
                scheduledPassengers.put(scheduledSeat.getSeatPassengerPassportNumber(), scheduledSeat);

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
            if(scheduledSeat.getSeatPassengerPassportNumber().equals(passengerPassportNumber)){
                System.out.println("\nPassenger: " +scheduledSeat.getSeatPassengerName());
                deletedSeatNumber = scheduledSeat.getSeatNumber();
                deletedSeatClass = scheduledSeat.getSeatClass();
                System.out.println("\nPassenger has been removed from flight: " + flightNumber + "\n");

                // remove from hashmap
                scheduledPassengers.remove(passengerPassportNumber);
                System.out.println(scheduledPassengers);
                // to refresh the .txt file with amendment
                scheduledSeat.modifyScheduledSeating(scheduledPassengers, flightNumber);


                // offer the seat to waiting list queue
                waitingListSeatDatabase.offerFreeSeat(flightNumber, deletedSeatNumber, deletedSeatClass);
        }
        }
    }
}


