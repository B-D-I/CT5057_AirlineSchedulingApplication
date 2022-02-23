package glos.S4008324;

public class Passenger {

    private String name;
    private String passportNumber;
    private Byte age;

    public Passenger() {
        // can be on multiple flights
        // can be on waiting list for multiple flights / seats
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

    public void setAge(Byte age) {
    }

    // toString overridden to allow the name of passenger object to be displayed
    @Override
    public String toString(){
        return "Passenger: " + getName() + "\t Passport Number: " + getPassportNumber();
    }

}
