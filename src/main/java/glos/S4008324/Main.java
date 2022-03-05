package glos.S4008324;

public class Main {

    public static void main(String[] args) {

//        new ApplicationMenu();
        SeatDatabase seatDatabase = new SeatDatabase();
        seatDatabase.deletePassengerFromFlight("499", "121212");

    }

    // TO DO:

    //  >> PLACE THE PASSENGERS FROM THE FLIGHTS WAITING-LIST INTO A QUEUE AND OFFER FIRST PERSON THE SEAT
    //      >> ERROR: WHEN DELETING THE QUEUE MEMBER FROM .TXT, ONLY HEAD PASSENGER IS WRITTEN
    //      >> NEED TO DELETE THE PASSENGER FROM QUEUE .TXT
    //      >> AND THEN ADD TO SCHEDULED SEATING
    //      >> IF NO PASSENGER WANTS SEAT, ADD THE SEAT AND CLASS BACK INTO FLIGHTS.TXT

    /// *** INCLUDE SORTING AND SEARCHING ALGORITHMS *** IMPORTANT

    /// UNIT TESTING + GO THROUGH AND CHECK ACCESS MODIFIERS, CODE REDUCTION, OOP
}
