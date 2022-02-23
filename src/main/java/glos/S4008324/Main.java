package glos.S4008324;

public class Main {

    public static void main(String[] args) {

        new ApplicationMenu();

    }

    // TO DO:
    ///  PassengerDatabase > passengerRoute : find flight number from destination and departure, pass flight number and passenger to Flight > scheduleSeat
    ////  scheduleSeat needs to remove selected seat number from flight and allocate passenger to it. Also, if seat not available, need to offer the option to select another seat or go on wait list (queue)

    ///// Flight > addFlightRoutes: needs to add departure & destinations from allFlightsMap and fill graph

    //// remove passenger from flight and free up the seat. Also give option to add passenger from waiting list onto flight

    //// view passenger status for booked flight / waiting lists + positions

    //// view flight information: flight number, airports, seats and passengers on seats
}
