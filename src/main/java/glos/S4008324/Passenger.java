package glos.S4008324;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Passenger {

    private String name;
    private String passportNumber;

//    public ArrayList<String> bookedFlights;
//    public ArrayList<String> flightWaitingLists;
//
//    public ArrayList<String> getBookedFlights() {
//        return bookedFlights;
//    }
//    public void setBookedFlights(ArrayList<String> bookedFlights) {
//        this.bookedFlights = bookedFlights;
//    }
//    public ArrayList<String> getFlightWaitingLists() {
//        return flightWaitingLists;
//    }
//    public void setFlightWaitingLists(ArrayList<String> flightWaitingLists) {
//        this.flightWaitingLists = flightWaitingLists;
//    }

    public HashMap<String, String> bookedFlight;
    public HashMap<String, String> waitingFlight;
    public void setBookedFlight(HashMap<String, String> seatingList){
        this.bookedFlight = seatingList;
    }

    public HashMap<String, String> getSeatingList() {
        return bookedFlight;
    }
    public void setWaitingFlight(HashMap<String, String> seatingList){
        this.waitingFlight = seatingList;
    }
    public HashMap<String, String> getWaitingFlight() {
        return waitingFlight;
    }




    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    // toString overridden to allow the name of passenger object to be displayed
    @Override
    public String toString(){
        return "Passenger: " + getName() + "\tPassport Number: " + getPassportNumber();
    }


    // Update .txt with all passenger scheduled seats and waiting lists >> either BookedFlights or WaitingFlight
//    public void addPassengerToPassengerStatus(HashMap<String, String> passportAndFlight, String listType) {
//        try {
//            BufferedWriter out = new BufferedWriter(new FileWriter("src/main/java/glos/S4008324/TxtFiles/"+listType+".txt", true));
//            out.write("\n" + passenger.getPassportNumber() + "\n" + passenger.getName() + "\n");
//            out.close();
//            System.out.println("\nPassenger details updated");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }





}
