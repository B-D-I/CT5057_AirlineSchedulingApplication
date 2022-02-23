package glos.S4008324;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

// THIS CLASS HAS BEEN CREATED USING THE SINGLETON DESIGN PATTERN TO ENSURE ONLY ONE INSTANCE OF THIS CLASS CAN BE CREATED
class ApplicationMenu {

    FlightDatabase flightDatabase = new FlightDatabase();
    PassengerDatabase passengerDatabase = new PassengerDatabase();
    Seating seating = new Seating();

    private static ApplicationMenu menu = null;

    Scanner scanRead = new Scanner(System.in);

    public ApplicationMenu() {
        adminMenu();
    }

    public static ApplicationMenu getMenu() {
        if (menu == null) {
            menu = new ApplicationMenu();
        }
        return menu;
    }

    private void adminMenu() {
        while (true) {
            System.out.println("""
                    Select Option:
                                    
                    S - schedule a passenger
                                    
                    C - cancel a passenger
                                    
                    P - passenger status
                                    
                    F - flight status
                                    
                    Q - quit
                                        
                    N - create new flight (restricted)
                                    
                    """);

            String adminSelect = scanRead.nextLine().trim().toUpperCase(Locale.ROOT);
            switch (adminSelect) {
                case "S" -> passengerDatabase.createPassenger();
                case "C" -> System.out.println("cancel");
                case "P" -> System.out.println("passenger status");
                case "F" -> showFlightInfo();
                case "Q" -> System.out.println("quit");
                case "N" -> restrictedMenuLogin();
                default -> System.out.println("error");
            }
        }
    }

    private void restrictedMenuLogin() {
        System.out.println("Enter username: ");
        String username = scanRead.nextLine();
        System.out.println("Enter password: ");
        String password = scanRead.nextLine();
        if (username.equals(flightDatabase.getDbUsername()) && password.equals(flightDatabase.getDbPassword())) {
            createFlight();
        } else {
            adminMenu();
        }
    }

    // FLIGHT OPERATOR METHOD TO CREATE NEW FLIGHT TO TXT
    private void createFlight() {
        System.out.println("Enter departure airport: ");
        String departure = scanRead.nextLine().trim().toUpperCase(Locale.ROOT);

        System.out.println("Enter destination airport: ");
        String destination = scanRead.nextLine().trim().toUpperCase(Locale.ROOT);

        System.out.print("Departure Date (0000-12-03 format): ");

        String dateDeparture = null;
        try {
            String departureDate = scanRead.nextLine();
            LocalDate localDate = LocalDate.parse(departureDate);
            dateDeparture = localDate.toString();
        } catch (DateTimeException e) {
            System.out.println(e);
            System.out.println("\nPlease enter the date in the correct format\n");
            createFlight();
        }

        System.out.println("Enter flight number: ");
        String flightNumber = scanRead.nextLine().trim().toUpperCase(Locale.ROOT);

        // SEATING HASHMAP
        HashMap<Integer, String> flightSeatingClass = seating.allocateAircraftSeatClass();

        System.out.println("\nSeating List: \n");
        flightSeatingClass.entrySet().forEach(System.out::println);

        // OPTION TO EDIT THE SEATING LIST
        System.out.println("\nWould you like to edit this seating list? (Y|N)");
        String editSeating = scanRead.nextLine().trim().toUpperCase(Locale.ROOT);
        if (editSeating.equals("Y")) {
            seating.editAircraftSeatClassAllocation(departure, destination, dateDeparture, flightNumber, flightSeatingClass);
        } else if (editSeating.equals("N")) {
            seating.updateFlightTxt(departure, destination, dateDeparture, flightNumber, flightSeatingClass);
        }
    }

    public void showFlightInfo(){
        flightDatabase.showAllFlights();
    }
}
