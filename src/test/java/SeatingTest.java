//import glos.S4008324.ScheduledSeat;
//import glos.S4008324.SeatDatabase;
////import org.junit.*;
//import org.apache.commons.collections.functors.NullIsFalsePredicate;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Timeout;
//
//import java.io.FileNotFoundException;
//import java.util.HashMap;
//import java.util.function.BooleanSupplier;
//
//public class SeatingTest {
//
//    SeatDatabase seatDatabase = new SeatDatabase();
//
//    @Test
//    public void testAircraftSeatDataStructure(){
//        HashMap<String, ScheduledSeat>  exp = new HashMap<>();
//        HashMap<String, ScheduledSeat>  test1 = seatDatabase.createScheduledPassengers("101");
//        //HashMap<String, ScheduledSeat>  test2 = seatDatabase.createScheduledPassengers("10000001");
////        Assertions.assertTrue((HashMap<String, ScheduledSeat>), test1);
//       // Assertions.fail(test1, test2);
//       // Assertions.fail(test2, "FileNotFoundException");
//        Assertions.assertInstanceOf(exp.getClass(), test1.getClass() );
//        //Assertions.assertFalse((HashMap) test2);
//    }
//}
