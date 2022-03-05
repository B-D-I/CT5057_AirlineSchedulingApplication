package glos.S4008324;

public class Passenger {

    private String name;
    private String passportNumber;

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
}
