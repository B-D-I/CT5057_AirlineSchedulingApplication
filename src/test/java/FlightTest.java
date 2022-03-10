import glos.S4008324.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlightTest {
    Flight flight = new Flight();
    FlightDatabase flightDatabase = new FlightDatabase();
    String trueDepart = "LHR";
    String trueDest = "MIA";
    String falseDest = "FFF";

    String flightNumber = "101";
    String departure = "LHR";
    String destination = "MIA";
    String departureDate = "2022-02-19";
    String flightNumberFalse = "000";
    String departureFalse = "LLL";
    String destinationFalse = "MMM";
    String departureDateFalse = "3033-03-33";

    @Test
    public void testFlightObjects(){
        Assertions.assertNotNull(flightDatabase.createFlightObjectsMap());
    }

    @Test
    public void testLogin(){
        String username = "nathan";
        String password = "password";
        String username2 = "testFalse";
        String password2 = "testFalse";
        Assertions.assertTrue(flightDatabase.restrictedMenuLogin(username, password));
        Assertions.assertFalse(flightDatabase.restrictedMenuLogin(username2, password2));
    }
    @Test
    public void testInvoiceObject(){
        InvoiceDatabase invoiceDatabase = new InvoiceDatabase();
        Assertions.assertNotNull(invoiceDatabase.createInvoiceInformation("id"));
    }

    @Test
    public void testHasAirport() {
        Airports<String> airports = new Airports();
        airports.addAirport("LHR");
        String trueAirport = "LHR";
        String falseAirport = "000";

        Assertions.assertTrue(airports.hasAirport(trueAirport));
        Assertions.assertFalse(airports.hasAirport(falseAirport));

    }

    @Test
    public void testFlightInvoice(){
        FlightInvoice flightInvoice = new FlightInvoice();
        String invoiceID = "00001";
        int invoiceCharge = 100;
        String invoiceDate = "0000-00-00";
        Boolean luggageIncluded = false;

        flightInvoice.setInvoiceID(invoiceID);
        flightInvoice.setInvoiceCharge(invoiceCharge);
        flightInvoice.setInvoiceDate(invoiceDate);
        flightInvoice.setLuggageIncluded(luggageIncluded);

        Assertions.assertNotEquals(flightInvoice.getInvoiceID(), "test");
        Assertions.assertSame(invoiceID, flightInvoice.getInvoiceID());
        Assertions.assertNotEquals(flightInvoice.getInvoiceCharge(), 5);
        Assertions.assertSame(invoiceCharge, flightInvoice.getInvoiceCharge());
        Assertions.assertNotEquals(flightInvoice.getInvoiceDate(), "xyz");
        Assertions.assertSame(invoiceDate, flightInvoice.getInvoiceDate());
        Assertions.assertNotEquals(flightInvoice.getLuggageIncluded(), true);
        Assertions.assertSame(luggageIncluded, flightInvoice.getLuggageIncluded());
    }
    @Test
    public void testFlightNumber(){
        flight.setFlightNumber(flightNumber);
        Assertions.assertNotEquals(flight.getFlightNumber(), flightNumberFalse);
        Assertions.assertSame(flightNumber, flight.getFlightNumber());
    }
    @Test
    public void testFlightDeparture(){
        flight.setDeparture(departure);
        Assertions.assertNotEquals(flight.getDeparture(), departureFalse);
        Assertions.assertSame(departure, flight.getDeparture());
    }
    @Test
    public void testFlightDestination(){
        flight.setDestination(destination);
        Assertions.assertNotEquals(flight.getDestination(), destinationFalse);
        Assertions.assertSame(destination, flight.getDestination());
    }
    @Test
    public void testDepartureDate(){
        flight.setDepartureDate(departureDate);
        Assertions.assertNotEquals(flight.getDepartureDate(), departureDateFalse);
        Assertions.assertSame(departureDate, flight.getDepartureDate());
    }

    @Test
    public void testCheckRoute() {
        Assertions.assertTrue(flightDatabase.checkRoute(trueDepart, trueDest));
        Assertions.assertFalse(flightDatabase.checkRoute(trueDepart, falseDest));
    }
    @Test
    public void testCheckAirport(){
        Assertions.assertTrue(flightDatabase.checkAirport(trueDepart));
        Assertions.assertFalse(flightDatabase.checkAirport(falseDest));
    }

    @Test
    public void testAirportCount(){
        Airports<String> airports = new Airports();
        Assertions.assertNotNull(airports.getAirportCount());
    }
}
