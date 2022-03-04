package glos.S4008324;

import java.util.Scanner;

abstract class Seat {
    Scanner scanner = new Scanner(System.in);
    String seatPassengerPassportNumber = null;
    String seatPassengerName = null;
    String seatClass = null;
    String seatNumber = null;

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }
    public String getSeatClass() {
        return seatClass;
    }
}
