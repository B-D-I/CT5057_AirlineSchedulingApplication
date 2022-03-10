package glos.S4008324;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The flightInvoice class provides all attributes for an invoice. Along with a method to write Invoices to text files.
 */
public class FlightInvoice {
    Scanner scanner = new Scanner(System.in);
    private String invoiceID;
    private int invoiceCharge;
    private String invoiceDate;
    private Boolean luggageIncluded;

    // Getter and Setter methods
    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }
    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceCharge(int invoiceCharge) {
        this.invoiceCharge = invoiceCharge;
    }
    public int getInvoiceCharge() {
        return invoiceCharge;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }
    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Boolean getLuggageIncluded() {
        return luggageIncluded;
    }
    public void setLuggageIncluded(Boolean luggageIncluded) {
        this.luggageIncluded = luggageIncluded;
    }

    @Override
    public String toString()
    {return "InvoiceID: "+getInvoiceID()+"\nInvoice Charge: "+getInvoiceCharge()+"\nDate: " + getInvoiceDate();}

    public void addInvoiceToTxt(FlightInvoice flightInvoice) {
        try{
            // add to specific flight seating
            BufferedWriter out = new BufferedWriter(new FileWriter("src/main/java/glos/S4008324/TxtFiles/Invoices.txt", true));
            out.write("\n" + flightInvoice.getInvoiceID() + "\n" + flightInvoice.getInvoiceCharge() + "\n" + flightInvoice.getInvoiceDate() + "\n");
            out.close();
            System.out.println("Invoice created: "+flightInvoice.getInvoiceID());
            System.out.println("Charge: "+flightInvoice.getInvoiceCharge());
            scanner.nextLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
