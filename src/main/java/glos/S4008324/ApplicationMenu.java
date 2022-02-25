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
    Flight flight = new Flight();

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
                case "S" -> passengerDatabase.schedulePassenger();
                case "C" -> System.out.println("cancel");
                case "P" -> System.out.println("passenger status");
                case "F" -> System.out.println("showFlightInfo()");
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
            flight.createFlight();
        } else {
            adminMenu();
        }
    }




}
