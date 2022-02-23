package glos.S4008324;

import java.util.HashMap;
import java.util.Scanner;

public class Flight {

    Scanner scanner = new Scanner(System.in);

    private String flightNumber;
    private String departure ;
    private String destination;
    private String departureDate;


    public HashMap<String, String> SeatingList;

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setDeparture(String departure) {
        this.departure = departure ;
    }
    public String getDeparture() {
        return departure;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    public String getDestination() {
        return destination;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }
    public String getDepartureDate() {
        return departureDate;
    }

    public void setSeatingList(HashMap<String, String> seatingList){
        this.SeatingList = seatingList;
    }

    public HashMap<String, String> getSeatingList() {
        return SeatingList;
    }



    @Override
    public String toString()
    {
        return flightNumber+", "+departure+", "+destination+", "+departureDate+", "+ SeatingList;
    }


}

