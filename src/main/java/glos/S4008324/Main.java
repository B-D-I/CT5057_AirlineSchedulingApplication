package glos.S4008324;

public class Main {

    public static void main(String[] args) {

//        new ApplicationMenu();
        WaitingListSeatDatabase waitingListSeatDatabase = new WaitingListSeatDatabase();
//        waitingListSeatDatabase.printWaitListPassenger("102", "202020");
        PassengerDatabase passengerDatabase = new PassengerDatabase();
        passengerDatabase.passengerStatus();
    }

    // TO DO:

    //  >> PLACE THE PASSENGERS FROM THE FLIGHTS WAITING-LIST INTO A QUEUE AND OFFER FIRST PERSON THE SEAT
    //      >> ERROR: WHEN DELETING THE QUEUE MEMBER FROM .TXT, ONLY HEAD PASSENGER IS WRITTEN
    //      >> NEED TO DELETE THE PASSENGER FROM QUEUE .TXT
    //      >> AND THEN ADD TO SCHEDULED SEATING
    //      >> IF NO PASSENGER WANTS SEAT, ADD THE SEAT AND CLASS BACK INTO FLIGHTS.TXT

    // VIEW PASSENGER STATUS:
    // >> CHOOSE PASSENGER AND PRINT ALL FLIGHTS THEY ARE BOOKED ON, AND ANY WAITING LISTS (WITH POSITION IN LIST)
    // >> --> This only prints passportNumber and a booked/waiting flight

    /// *** INCLUDE SORTING AND SEARCHING ALGORITHMS *** IMPORTANT

    /// UNIT TESTING + GO THROUGH AND CHECK ACCESS MODIFIERS, CODE REDUCTION, OOP
}
