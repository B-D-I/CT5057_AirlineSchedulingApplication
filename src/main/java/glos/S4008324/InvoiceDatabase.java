package glos.S4008324;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InvoiceDatabase implements Database{

    private InvoiceBinarySearchTree invoiceBinarySearchTree = new InvoiceBinarySearchTree();

    public InvoiceBinarySearchTree createInvoiceInformation(String invoiceDetail) {
        try {
            File myObj = new File("src/main/java/glos/S4008324/TxtFiles/Invoices.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                // read and assign flights.txt information
                String invoiceID = myReader.nextLine();
                String invoiceCharge = myReader.nextLine();
                String invoiceDate = myReader.nextLine();

                int invoiceRequirement = 0;

                if (invoiceDetail.equals("charge")){
                    invoiceRequirement = Integer.parseInt(invoiceCharge);
                } else if (invoiceDetail.equals("id")){
                    invoiceRequirement = Integer.parseInt(invoiceID);
                }
                invoiceBinarySearchTree.insert(invoiceRequirement);

                if (myReader.hasNextLine()) {
                    myReader.nextLine();
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error" + e);
        } return invoiceBinarySearchTree;
    }

    public void printInvoiceIDs(String invoiceDetail){
        InvoiceBinarySearchTree invoiceBinarySearchTree = createInvoiceInformation(invoiceDetail);
        invoiceBinarySearchTree.inorder();
    }

    public void searchInvoiceID(int id){
        InvoiceBinarySearchTree invoiceBinarySearchTree = createInvoiceInformation("id");
        boolean returnValue = invoiceBinarySearchTree.search(id);
        if (returnValue){
            System.out.println("Invoice ID: " + id + " is located in Invoice Record");
        } else {
            System.out.println("There is no record of Invoice: " + id);
        }

    }
}


