import glos.S4008324.Passenger;
import glos.S4008324.PassengerDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class PassengerTest {
    PassengerDatabase passengerDatabase = new PassengerDatabase();
    Passenger passenger = new Passenger();

    String nameFalse = "testFalse";
    String passportFalse = "000002";

    @Test
    public void testPassengerObjects(){
        Assertions.assertNotNull(passengerDatabase.createPassengerBookingsObject("BookedFlights"));
        Assertions.assertNotNull(passengerDatabase.createPassengerBookingsObject("WaitingLists"));
    }

    @Test
    public void testAgeCheck(){
        Assertions.assertTrue(passengerDatabase.checkAge((byte) 18));
        Assertions.assertFalse(passengerDatabase.checkAge((byte) 17));
    }
    @Test
    public void testPassengerName(){
        passenger.setName("test");
        Assertions.assertNotEquals(passenger.getName(), nameFalse);
        Assertions.assertSame("test", passenger.getName());
    }
    @Test
    public void testPassportNumber(){
        passenger.setPassportNumber("000001");
        Assertions.assertNotEquals(passenger.getPassportNumber(), passportFalse);
        Assertions.assertSame("000001", passenger.getPassportNumber());
    }
}
