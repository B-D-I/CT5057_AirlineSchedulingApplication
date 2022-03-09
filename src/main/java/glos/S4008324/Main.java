package glos.S4008324;

public class Main {

    public static void main(String[] args) {

        // extract all flight dates into a linked list -> radix sort -> binary search
        // see phone notes

//        new ApplicationMenu();

        FlightDatabase flightDatabase = new FlightDatabase();
        flightDatabase.showDates();

//      DepartureDatesRadixSort departureDatesRadixSort = new DepartureDatesRadixSort();
//      departureDatesRadixSort.getDates();

    }

    // TO DO:

    /// *** INCLUDE RADIX SORT ON DEPARTURE DATES, THEN PERFORM A BINARY SEARCH
    /// UNIT TESTING + GO THROUGH AND CHECK ACCESS MODIFIERS, CODE REDUCTION, OOP + update docstrings
}
