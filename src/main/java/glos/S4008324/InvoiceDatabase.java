package glos.S4008324;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Invoice Database provides all functionality for Invoicing
 */
public class InvoiceDatabase implements Database{

    private InvoiceBinarySearchTree invoiceBinarySearchTree = new InvoiceBinarySearchTree();
    /**
     * This method reads the invoicing txt file and inserts the selected invoice values into a Binary Search Tree
     * @param invoiceDetail: The invoicing value to be inserted
     * @return: A Binary Search Tree of specified data
     */
    public InvoiceBinarySearchTree createInvoiceInformation(String invoiceDetail) {
        try {
            File myObj = new File("../CT5057_AirlineSchedulingApplication/TxtFiles/Invoices.txt");
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

    private void printInvoiceIDs(String invoiceDetail){
        InvoiceBinarySearchTree invoiceBinarySearchTree = createInvoiceInformation(invoiceDetail);
        invoiceBinarySearchTree.inorder();
    }
    // search Binary Search Tree
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


