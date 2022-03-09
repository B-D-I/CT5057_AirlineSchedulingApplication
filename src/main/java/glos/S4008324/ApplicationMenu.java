package glos.S4008324;

import java.util.Locale;
import java.util.Scanner;

/**
 * This class contains the application's menu options. The class has been created using the Singleton design pattern to
 * ensure only one instance of this class can be created.
 */
class ApplicationMenu {

    FlightDatabase flightDatabase = new FlightDatabase();
    PassengerDatabase passengerDatabase = new PassengerDatabase();
    SeatDatabase seatDatabase = new SeatDatabase();
    Flight flight = new Flight();
    Scanner scanRead = new Scanner(System.in);

    private static ApplicationMenu menu = null;

    public ApplicationMenu() {
        startApplication();
    }

    public static ApplicationMenu getMenu() {
        if (menu == null) {
            menu = new ApplicationMenu();
        }
        return menu;
    }
    private void startApplication(){
        System.out.println("""
                
                  **      WELCOME TO FLIGHT SCHEDULER         **
                
                  **     PRESS ENTER TO START APPLICATION    **
                
                """);
        scanRead.nextLine();
        adminMenu();
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
                                        
                    A - advanced 
                                    
                    """);
            String adminSelect = scanRead.nextLine().trim().toUpperCase(Locale.ROOT);
            switch (adminSelect) {
                case "S" -> passengerDatabase.schedulePassenger();
                case "C" -> seatDatabase.cancelPassenger();
                case "P" -> passengerDatabase.passengerStatus();
                case "F" -> flightDatabase.flightStatus();
                case "Q" -> startApplication();
                case "A" -> restrictedMenuLogin();
                default -> System.out.println("error");
            }
        }
    }
    // Menu for further options
    private void restrictedMenuLogin() {
        System.out.println("Enter username: ");
        String username = scanRead.nextLine();
        System.out.println("Enter password: ");
        String password = scanRead.nextLine();
        if (username.equals(flightDatabase.getDbUsername()) && password.equals(flightDatabase.getDbPassword())) {
            System.out.println("""
                    Select Option:
                    
                    C: create a new flight
                    
                    I: check an invoice
                    
                    """);
            String option = scanRead.nextLine().trim().toUpperCase(Locale.ROOT);
            if (option.equals("C")) {
                flight.createFlight();
            } else if (option.equals("I")){
                System.out.println("Enter Invoice ID: ");
                int id = scanRead.nextInt();
                scanRead.nextLine();
                InvoiceDatabase invoiceDatabase = new InvoiceDatabase();
                invoiceDatabase.searchInvoiceID(id);
            }
        } else {
            adminMenu();
        }
    }
}
