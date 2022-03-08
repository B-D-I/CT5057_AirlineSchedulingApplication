package glos.S4008324;

import java.util.Scanner;

abstract class Seat {
    protected Scanner scanner = new Scanner(System.in);
    protected String seatPassengerPassportNumber = null;
    protected String seatPassengerName = null;
    protected String seatClass = null;
    protected String seatNumber = null;

    protected void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }
    protected String getSeatClass() {
        return seatClass;
    }

    protected String getSeatPassengerPassportNumber() {
        return seatPassengerPassportNumber;
    }
    protected void setSeatPassengerPassportNumber(String seatPassengerPassportNumber) {
        this.seatPassengerPassportNumber = seatPassengerPassportNumber;
    }
    protected String getSeatPassengerName() {
        return seatPassengerName;
    }
    protected void setSeatPassengerName(String seatPassengerName) {
        this.seatPassengerName = seatPassengerName;
    }
    protected String getSeatNumber() {
        return seatNumber;
    }
    protected void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

}
