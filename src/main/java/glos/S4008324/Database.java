package glos.S4008324;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

interface Database {
    Scanner scanner = new Scanner(System.in);

    //Flights.txt (flightNumber : Flight)
    HashMap<String, Flight> allFlightsMap = new HashMap<>();
    // Flights.txt, unallocated seats (SeatNumber : SeatClass)
    HashMap<Integer, String> aircraftSeatAllocation = new HashMap<>();
    // airports object
    Airports<String> airports = new Airports<>();
    // BookedFlights.txt / WaitingLists.txt (passportNumber : flightNumber)
    HashMap<String, String> passengerSchedulingMap = new HashMap<>();
    // ScheduledSeating"".txt (passportNumber : ScheduledSeat)
    HashMap<String, ScheduledSeat> scheduledPassengers = new HashMap<>();
    // WaitingLists"".txt (passportNumber : WaitingLists(queue))
    Queue<HashMap<String, WaitingListSeat>> waitingQueue = new LinkedList<>();

}
