package glos.S4008324;

public class Main {

    public static void main(String[] args) {

//        new ApplicationMenu();
        SeatDatabase seatDatabase = new SeatDatabase();
        seatDatabase.printWaitList("202");
        // THIS IS WRONG !!!!!!!!!! I NEED MULTIPLE HASHMAPS INSIDE ONE QUEUE, NOT A QUEUE FOR EACH HASHMAP!!!!!!!!!!!!
        // CREATE-WAIT-LIST-OBJECT -->> THIS NEEDS TO CREATE HASHMAPS OF EACH WAITING --> THEN PLACE ALL HASHMAPS INTO A QUEUE
    }

    // TO DO:

    // REMOVE PASSENGER FROM FLIGHT:
    //  >> SELECT A PASSENGER FROM THE CORRECT FLIGHTS SCHEDULED-SEATING FILE AND REMOVE IT >>>>>> consider creating scheduled seating class etc... >> this may be easier for flight / passenger status
    //  >> PLACE THE PASSENGERS FROM THE FLIGHTS WAITING-LIST INTO A QUEUE AND OFFER FIRST PERSON THE SEAT
    //  >> IF NOT, ADD THE SEAT AND CLASS BACK INTO FLIGHTS.TXT

    // VIEW PASSENGER STATUS:
    // >> CHOOSE PASSENGER AND PRINT ALL FLIGHTS THEY ARE BOOKED ON, AND ANY WAITING LISTS (WITH POSITION IN LIST)

    // VIEW FLIGHT INFORMATION:
    // >> ENTER FLIGHT NUMBER AND PRINT AIRPORTS, DATE, ALL SEATS AND PASSENGER OF SEAT

    /// INCLUDE SORTING AND SEARCHING ALGORITHMS
}
