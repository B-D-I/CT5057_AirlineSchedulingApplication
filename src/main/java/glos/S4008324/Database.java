package glos.S4008324;

import java.util.*;

/**
 * Database Interface contains common data structure instances used within the application, along
 * with a Static method that provides Binary Searching of an array
 */
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
    // FlightDatabase array list of all departure dates
    ArrayList<Integer> flightDates = new ArrayList<>();

    // static method to perform binary search of an array
    static int binarySearch(int[] numberArray, int targetNumber) {
        int start = 0;
        int end = numberArray.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (numberArray[mid] > targetNumber)
                end = mid - 1;
            else if (numberArray[mid] < targetNumber)
                start = mid + 1;
            else
                return mid;
        }
        return -1;
    }
}
