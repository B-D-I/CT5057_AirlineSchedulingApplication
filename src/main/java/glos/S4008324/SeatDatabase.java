package glos.S4008324;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SeatDatabase implements Database {

    private WaitingListDatabase waitingListDatabase = new WaitingListDatabase();
    private UnallocatedSeat unallocatedSeat = new UnallocatedSeat();
    private HashMap<String, ScheduledSeat> scheduledPassengers = new HashMap<>();

    // for the flights.txt
    public final HashMap<Integer, String> aircraftSeatAllocation = new HashMap<>();

    private int seatCount;
    private int economySeatAmount, firstSeatAmount, businessSeatAmount;

    // allocate seats for aircraft (not with passengers)
    public HashMap<Integer, String> allocateAircraftSeatClass() {

        try {
            System.out.println("First seat amount: ");
            firstSeatAmount = scanner.nextByte();

            System.out.println("Business seat amount: ");
            businessSeatAmount = scanner.nextByte();

            System.out.println("Economy seat amount: ");
            economySeatAmount = scanner.nextByte();
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
        seatCount = economySeatAmount + firstSeatAmount + businessSeatAmount;

        // auto-fill the hashmap from the ranges given
        for (int i = 1; i < firstSeatAmount + 1; i++) {
            aircraftSeatAllocation.put(i, "First");
        }
        for (int j = firstSeatAmount + 1; j < firstSeatAmount + businessSeatAmount + 1; j++) {
            aircraftSeatAllocation.put(j, "Business");
        }
        for (int k = firstSeatAmount + businessSeatAmount + 1; k < firstSeatAmount + businessSeatAmount + economySeatAmount + 1; k++)
            aircraftSeatAllocation.put(k, "Economy");

        return aircraftSeatAllocation;
    }

    // edit seat class (not passengers)
    public void editAircraftSeatClassAllocation(String departure, String destination, String dateDeparture, String flightNumber, HashMap<Integer, String> seatList) {
        System.out.println("Enter the number of the seat you wish to change: ");
        int seatNo = scanner.nextInt();
        scanner.nextLine();
        System.out.println("""
                Now select the seat class you wish to change to:

                F - first

                B - business

                E - economy

                """);
        String seatClass = scanner.nextLine().trim().toUpperCase(Locale.ROOT);

        if (seatClass.equals("F")) {
            seatClass = "First";
        } else if (seatClass.equals("B")) {
            seatClass = "Business";
        } else if (seatClass.equals("E")) {
            seatClass = "Economy";
        }

        seatList.put(seatNo, seatClass);
        System.out.println("\nSeating List: \n");
        seatList.entrySet().forEach(System.out::println);

        System.out.println("\nWould you like to edit this seating list further? (Y|N)");
        String editSeating = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
        if (editSeating.equals("N")) {
            unallocatedSeat.updateFlightTxt(departure, destination, dateDeparture, flightNumber, seatList);
        } else if (editSeating.equals("Y")) {
            editAircraftSeatClassAllocation(departure, destination, dateDeparture, flightNumber, seatList);
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
    // private >>
    public void deletePassengerFromFlight(String flightNumber, String passengerPassportNumber){
        // put the scheduled passengers from .txt into hashmap (passport number : scheduledSeat object)
        HashMap<String, ScheduledSeat> scheduledPassengers = createScheduledPassengers(flightNumber);

        String deletedSeatNumber;
        String deletedSeatClass;
        // check if passport number matches a scheduled passenger's
        for (ScheduledSeat scheduledSeat: scheduledPassengers.values()) {
            if(scheduledSeat.getSeatPassengerPassportNumber().equals(passengerPassportNumber)){
                System.out.println("\nPassenger: " +scheduledSeat.getSeatPassengerName());
                deletedSeatNumber = scheduledSeat.getSeatNumber();
                deletedSeatClass = scheduledSeat.getSeatClass();
                System.out.println("\nPassenger has been removed from flight: " + flightNumber );
                System.out.println("Seat: " + deletedSeatNumber + " : " + deletedSeatClass+ "\n");

    // THIS WORKS FINE >>
//                // remove from hashmap
//                scheduledPassengers.remove(passengerPassportNumber);
//                System.out.println(scheduledPassengers);
//                // to refresh the .txt file with amendment
//                scheduledSeat.modifyScheduledSeating(scheduledPassengers, flightNumber);


                // offer the seat to waiting list queue
                waitingListDatabase.offerFreeSeat(flightNumber, deletedSeatNumber, deletedSeatClass);
        }
        }
    }
}


