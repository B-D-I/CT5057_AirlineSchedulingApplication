package glos.S4008324;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Seating {

    Scanner scanner = new Scanner(System.in);

    protected int seatCount;

    private int economySeatAmount, firstSeatAmount, businessSeatAmount;

    // for the flights.txt
    public final HashMap<Integer, String> aircraftSeatAllocation = new HashMap<>();

    // allocate seats for aircraft (not with passengers)
    public HashMap<Integer, String> allocateAircraftSeatClass() {

        try {
            System.out.println("First seat amount: ");
            firstSeatAmount = scanner.nextByte();

            System.out.println("Business seat amount: ");
            businessSeatAmount = scanner.nextByte();

            System.out.println("Economy seat amount: ");
            economySeatAmount = scanner.nextByte();
        } catch (InputMismatchException e){
            System.out.println(e);
        }
        seatCount = economySeatAmount + firstSeatAmount + businessSeatAmount;

        // auto-fill the hashmap from the ranges given
        for (int i = 1; i < firstSeatAmount+1; i++) {
            aircraftSeatAllocation.put(i, "First");
        }
        for (int j = firstSeatAmount+1; j < firstSeatAmount+businessSeatAmount+1; j++) {
            aircraftSeatAllocation.put(j, "Business");
        }
        for (int k = firstSeatAmount+businessSeatAmount+1; k < firstSeatAmount+businessSeatAmount+economySeatAmount+1; k++)
            aircraftSeatAllocation.put(k, "Economy");

        return aircraftSeatAllocation;
    }

    // edit seat class (not passengers)
    public void editAircraftSeatClassAllocation(String departure, String destination, String dateDeparture, String flightNumber, HashMap<Integer, String> seatList){
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

        if (seatClass.equals("F")){
            seatClass = "First";
        } else if (seatClass.equals("B")){
            seatClass = "Business";
        } else if (seatClass.equals("E")){
            seatClass = "Economy";
        }

        seatList.put(seatNo, seatClass);
        System.out.println("\nSeating List: \n");
        seatList.entrySet().forEach(System.out::println);

        System.out.println("\nWould you like to edit this seating list further? (Y|N)");
        String editSeating = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
        if (editSeating.equals("N")){
            updateFlightTxt(departure, destination, dateDeparture, flightNumber, seatList);
        } else if (editSeating.equals("Y")){
            editAircraftSeatClassAllocation(departure, destination, dateDeparture, flightNumber, seatList);
        }
    }

    public void updateFlightTxt(String departure, String destination, String dateDeparture, String flightNumber, HashMap<Integer, String> seatList){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("src/main/java/glos/S4008324/Flights.txt", true));
            out.write("\n" + flightNumber + "\n" + departure + "\n" + dateDeparture +
                    "\n" + destination + "\n" + seatList.toString()+ "\n");
            out.close();

            System.out.println("\nFlight added");
            // REPLACE: .replace("{", " ").replace("},", " ")

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
