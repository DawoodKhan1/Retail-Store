/**
 * Class: CSCI 1125-30
 * Semester: Spring 2017
 * @author Dawood Khan
 * Homework Assignment #3
 * 
 * Description: This class implements a RetailTransaction class through keeping track of the 
 * transactionID, register, date, tax and warranty rates, and what items have been purchased.
 * It also handles outputting and formatting of recipts and transaction summaries.
 * 
 */
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class RetailTransaction {

	private long transactionID; 		// unique identifying number of transaction
	private int registerID; 			// what register the transaction was on
	private double taxMultiplier = 1.0725;// how much to multiply the subtotal by to get total
	private double taxRate = .0725; 	// to multiply subtotal to get total tax due
	private TransactionTypeEnum transactionType = TransactionTypeEnum.SALE; // default to sale
	private final double warrantyRate = .1;  // or 10% of item cost
	private ArrayList<LineItem> lineItems = new ArrayList<LineItem>(); // all items purchased
	private String transactionDate; 	// date in the format: YEAR-Month-day hours: min : seconds
	
	/**
	 * Explicit value constructor.
	 * @param transactionID  Unique identifying number of the transaction
	 * @param registerID     What register it was purchased on
	 * @param transactionDate When the purchase was M/D/Y and H:M:S
	 * @param transactionType Sale or Return as enumerated data type
	 */
	public RetailTransaction (long transactionID, int registerID, String transactionDate, 
			TransactionTypeEnum transactionType){
		this.transactionID = transactionID;
		this.registerID = registerID;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
	}
	
	public String getTransactionDate(){
		return transactionDate;
	}
	
	public int getRegisterID() {
		return registerID;
	}
	
	public long getTransactionID(){
		return transactionID;
	}
	
	public void setTransactionType(TransactionTypeEnum transactionType){
		this.transactionType = transactionType;
	}
	
	/**
	 * This method returns a double value formatted as a dollar amount and with () if it 
	 * is a return/-value.
	 * @param salesTotal  How much sales were made, negative if return
	 * @return  the formatted double number as a string
	 */
	public static String getTotalAsFormattedString(double salesTotal) {
		DecimalFormat dollarFormatter = new DecimalFormat ("$#,##0.00");
		dollarFormatter.setPositivePrefix("$"); 	// if positive just need $
		dollarFormatter.setNegativePrefix("($"); 	// negatives need ($xx.x)
		dollarFormatter.setNegativeSuffix(")");
		
		return dollarFormatter.format(salesTotal);
	}
	
	/**
	 * This method adds and item to the retail transaction if it is a valid item.
	 * @param itemScanned  Passing in the Product object containing pertinent info about item
	 * @param quantity 	   How much of the item is the customer buying
	 * @param numberOfWarranties How much warranties, cannot exceed quantity
	 * @param catalog    List of all products in the catalog of the store
	 */
	public void scanItem(Product itemScanned, int quantity, int numberOfWarranties, 
			ProductCatalog catalog){
		int i = 0;
		boolean found = false;
		// Implements a linear search to see if the product in in lineItems already
		while(i < lineItems.size() && !found){
			if(lineItems.get(i).getProduct().getProductID().equals(itemScanned.getProductID())){
				found = true;
			}
			i++;
		}
			
		Product product = catalog.findProductbyID(itemScanned.getProductID());
		// if the product is not in the catalog, do not add it
		if(product != null){
			/*
			 * If it is not found, create a new line items with the parameters passed.
			 * If it is found, simply increment the quantity and number of warranties purchased.
			 */
			if(!found){
				LineItem tempItem = new LineItem(itemScanned, quantity, numberOfWarranties);
				lineItems.add(tempItem);
			}
			else {
				lineItems.get(i-1).setQuantity(lineItems.get(i-1).getQuantity() + quantity);
				lineItems.get(i-1).setNumberOfWarranties(lineItems.get(i - 1).getNumberofWarranties() +
						lineItems.get(i - 1).getNumberofWarranties());
			
			}
		}
	}
		
	/**
	 * This method generates a receipt based on the transaction.
	 * The format is:
	 *  [transaction type] Transaction: [transaction id]
		Register: [register id]
		Transaction Date: [transaction date]
		----------------------------------------
		[product1 name]
			 quantity: [quantity of product1 sold]
			 price: [product1 price]
			 warranties: [number of warranties for product1]
			 subtotal: [cost for product1, including warranties, no tax]
		...
		[productN name]
			 quantity: [quantity of productN sold]
			 price: [productN price]
			 warranties: [number of warranties for productN]
		 subtotal: [cost for productN, including warranties, no tax]
		=======================================
		Subtotal: [sum of subtotals of all items]
		Tax: [tax amount for subtotal]
		TOTAL: [total transaction cost]

	 * @throws FileNotFoundException If it can't make an output file abort
	 */
	public void generateReceipt() throws FileNotFoundException {
		double subtotal = 0;
		double totalSubtotal = 0;
		FileOutputProcessor outputFile = new FileOutputProcessor();
		outputFile.openOutputFile("recipt_" + transactionID + "_" + 
		registerID + ".txt"); // each receipt will be its own output file
		StringBuilder receipt = new StringBuilder();
		
		if(transactionType == TransactionTypeEnum.SALE){
			receipt.append("SALE ");
		}
		else{
			receipt.append("RETURN ");
		}
		
		receipt.append("Transaction: ");
		receipt.append(transactionID);
		receipt.append("\nRegister: ");
		receipt.append(Integer.toString(registerID));
		receipt.append("\nTransaction Date: ");
		receipt.append(transactionDate);
		receipt.append("\n----------------------------------------");
		
		/*
		 * loop iterates for each lineItem outputting the relavent information in the proper
		 * format as well as keeping track of the totalSubtotal for later calculations
		 */
		for(int i = 0; i < lineItems.size(); i++){
			receipt.append("\n");
			receipt.append(lineItems.get(i).getProduct().getProductName());
			receipt.append("\n\tquantity: ");
			receipt.append(Integer.toString(lineItems.get(i).getQuantity()));
			receipt.append("\n\tprice: ");
			receipt.append(getTotalAsFormattedString(lineItems.get(i).getProduct().getPrice()));
			receipt.append("\n\twarranties: ");
			receipt.append(Integer.toString(lineItems.get(i).getNumberofWarranties()));
			
			subtotal = lineItems.get(i).getProduct().getPrice() * lineItems.get(i).getQuantity() + 
					lineItems.get(i).getProduct().getPrice() * 
					lineItems.get(i).getNumberofWarranties() * warrantyRate;
			
			receipt.append("\n\tsubtotal: ");
			receipt.append(getTotalAsFormattedString(subtotal));
			
			totalSubtotal += subtotal;
		}
		// if it is a return, the amount must be a negative value
		totalSubtotal = (TransactionTypeEnum.RETURN == transactionType) ? -subtotal : subtotal;

		/*
		 * After the loop, given totalSubtotal, it is possible to calculate taxes and 
		 * the total of the transaction without calling calculateTransactionTotal()
		 * saving on memory and time.
		 */
		receipt.append("\n========================================");
		receipt.append("\nSubtotal: ");
		receipt.append(getTotalAsFormattedString(totalSubtotal));
		receipt.append("\nTax: ");
		receipt.append(getTotalAsFormattedString(totalSubtotal * taxRate));
		receipt.append("\nTOTAL: ");
		receipt.append(getTotalAsFormattedString(totalSubtotal * taxMultiplier));
		receipt.append("\n");
		
		System.out.print(receipt + "\n"); 	// output to the console
		outputFile.printLine(receipt); 		// output to the output file
		
		outputFile.closeOutputFile();
	}
		
		/**
		 * This method calculates the dollar amount of how much the items cost in a transaction
		 * through iterating through lineItems and calculating through the price, quantities, 
		 * warranties, and if it is a sale or a return.
		 * @return Returns the total amount of the transaction after tax negative if return
		 */
	public double calculateTransactionTotal(){
		double total = 0;
		
		/*
		 * For loop iterates as long as there are lineItems to iteraate through.
		 * While in the loop it calculates the price by the following forumula:
		 * total += price * quantity + price * numberOfWarranties * warrantyRate
		 */
		for(int i = 0; i < lineItems.size(); i++){
			total += lineItems.get(i).getProduct().getPrice() * lineItems.get(i).getQuantity() + 
					lineItems.get(i).getProduct().getPrice() * 
					lineItems.get(i).getNumberofWarranties() * warrantyRate;
		}
		// If it is a return total must be negative
		total = (TransactionTypeEnum.RETURN == transactionType) ? -total : total;

		return total * taxMultiplier;
	}

	/**
	 * This method returns the number of products sold in a transaction by iterating through
	 * lineItems and summing up the quantities of each of the products.
	 * @return The total number of products purchased/returned.
	 */
	public int getNumberOfProductsSold(){
		int quantity = 0;
		
		for(int i = 0; i < lineItems.size(); i++){
			quantity += lineItems.get(i).getQuantity();
		}
		
		return quantity;		
	}
	
	/**
	 * This method formats a string summarizing the transaction in the following format:
	 * Transaction [transactionID]: [totalQuantity] items totaling [total] on register [registerID]
	 * @return The string with the above format followed and if negative put () around dollar amt.
	 */
	public String getTransactionSummary(){
		
		return String.format("Transaction %s: %s items totaling %s on register %s",
                getTransactionID(),
                getNumberOfProductsSold(),
                getTotalAsFormattedString(calculateTransactionTotal()),
                getRegisterID());
	}
	
	/**
	 * Displays to the console the contents of lineItems mainly for testing purposes.
	 */
	public void displayRetailTransaction(){
		for(int i = 0; i < lineItems.size(); i++){
			System.out.println(lineItems.get(i).getProduct().getProductID());
			System.out.println(lineItems.get(i).getQuantity());
			System.out.println(lineItems.get(i).getNumberofWarranties());
		}
	}
}