package glos.S4008324;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InvoiceDatabase implements Database{

    private InvoiceBinarySearchTree invoiceBinarySearchTree = new InvoiceBinarySearchTree();

    public InvoiceBinarySearchTree createInvoiceObject() {
        try {
            File myObj = new File("src/main/java/glos/S4008324/TxtFiles/Invoices.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                // read and assign flights.txt information
                String invoiceID = myReader.nextLine();
                String invoiceCharge = myReader.nextLine();
                String invoiceDate = myReader.nextLine();

                InvoiceBinarySearchTree invoiceBinarySearchTree = new InvoiceBinarySearchTree();
                invoiceBinarySearchTree.insert(Integer.parseInt(invoiceID));

                System.out.println(invoiceCharge);
//                System.out.println(Integer.parseInt(invoiceID) + Integer.parseInt(invoiceID));

                // could create a separate reader to store all invoice objects
//                String invoiceCharge = myReader.nextLine();
//                String invoiceDate = myReader.nextLine();
//                FlightInvoice flightInvoice = new FlightInvoice();
//                flightInvoice.setInvoiceID(invoiceID);
//                flightInvoice.setInvoiceCharge(Integer.parseInt(invoiceCharge));
//                flightInvoice.setInvoiceDate(invoiceDate);


                if (myReader.hasNextLine()) {
                    myReader.nextLine();
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error" + e);
        } return invoiceBinarySearchTree;
    }

    public void printInvoiceIDs(){
        InvoiceBinarySearchTree invoiceBinarySearchTree = createInvoiceObject();
        invoiceBinarySearchTree.inorder();
    }
}


