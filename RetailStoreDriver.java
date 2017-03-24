/**
 * Class: CSCI 1125-30
 * Semester: Spring 2017
 * @author Dawood Khan
 * Homework Assignment #3
 * 
 * Description: This class uses RetailTransaction, ProductCatalog, FileInputProcessor,
 * FileOutputProcessor, RetailTransactionParser, and any classes they contain to take in input
 * from an input file containing product information to take into a ProductCatalog object and then
 * transactions to generate receipts and summaries for.
 * 
 */
import java.io.*;

public class RetailStoreDriver {
	
	// constants
    private final static String CELL_PHONE_STRING = "cell phone";
    private final static String HEADPHONES_STRING = "headphones";
    private final static String TRANSACTION_STRING = "Transaction";

    /**
     * This function takes a RetailTransaction through an input, initializes it, and uses 
     * RetailTransaction to do a transaction and catalog to get products and not scan
     * invalid items which are defined as an invalid productID.
     * @param inputFile		Where input is coming in from handled by FileInputProcessor
     * @param transactionFields The first line of the transaction details like sale/return
     * @param catalog 	What are the products being sold currently
     * @return An initialized RetailTransaction object and the inputFile being alterred and points to
     * after End Transaction.
     */
    private static RetailTransaction readTransaction(FileInputProcessor inputFile,
                                                     String[] transactionFields,
                                                     ProductCatalog catalog) {
        // must first initialize transaction with important info about transaction like Date.
    	RetailTransaction transaction = 
        		RetailTransactionParser.getTransactionFromFields(transactionFields);
        boolean doneReadingTransaction = false;
        // Will stop once it hits End Transaction
        while(!doneReadingTransaction) {
            String[] productFields = inputFile.processLine();
            
            if("End Transaction".equals(productFields[0])) {
                doneReadingTransaction = true;
            }
            else
            {
            	// get the Product being sold
                Product product = catalog.findProductbyID(productFields[0]);
                
                /*
                 *  as long as it is a valid product, scan it and add it to the transaction
                 *  parsing the quantity and number of warranties.
                 */
                if(!(product == null)){
                	transaction.scanItem(product,  Integer.parseInt(productFields[1]), 
                			Integer.parseInt(productFields[2]), catalog);
                }
            }
        }
        return transaction;
}

    public static void main(String[] args) throws IOException {
    	
        FileInputProcessor inputFile = new FileInputProcessor();
        inputFile.openInputFile("retail_store.txt"); 		// open input file for output
        FileOutputProcessor outputFile = new FileOutputProcessor();
        outputFile.openOutputFile("transaction_summary.txt"); // open output file for output
        ProductCatalog catalog = new ProductCatalog(); // initialize inventory of objects
        double totalSales = 0; 	// total sale amount
        // builds summaries of transaction and summary of all transactions
        StringBuilder transactionSummary = new StringBuilder(); 
        
        try {
            String[] lineFields = new String[10]; 	// to store the data separated by a ,

            // Loop through all products in the catalog first //
            while(inputFile.notEmpty()) {
            	lineFields = inputFile.processLine(); // get next line of input

                switch(lineFields[0]) {
                /*
                 * If it is a CellPhone or Headphone, add it to the catalog.
                 * If it is a transaction, read it in, generate the receipt, and using string 
                 * builder append the transaction summary.
                 */
                    case CELL_PHONE_STRING:
                        catalog.addProduct(
                        		RetailTransactionParser.getCellPhoneFromFields(lineFields));
                        break;
                   
                    case HEADPHONES_STRING:
                        catalog.addProduct(
                        		RetailTransactionParser.getHeadphonesFromFields(lineFields));
                        break;
                        
                    case TRANSACTION_STRING:
                    	RetailTransaction currentTransaction = readTransaction(
                    			inputFile, lineFields, catalog);
                        currentTransaction.generateReceipt();
                        transactionSummary.append(currentTransaction.getTransactionSummary());
                        transactionSummary.append("\n");

                       totalSales += currentTransaction.calculateTransactionTotal();
                       // if the product name isn't cell phone or headphones don't accept it
                       default:
                }
            }
            // append summary of all sales through how much the total of all transaction was.
            transactionSummary.append(String.format("Total Sales: %s",
                    RetailTransaction.getTotalAsFormattedString(totalSales)));
         // output the transaction summaries and final summaries
            outputFile.printLine(transactionSummary);	// to transaction_summary.txt
            System.out.println(transactionSummary); 	// to the console after receipts

        } catch(FileNotFoundException fnfe) {
            System.exit(1);
            
        } finally {
            inputFile.closeInputFile();
            outputFile.closeOutputFile();
        }   
    }
}
