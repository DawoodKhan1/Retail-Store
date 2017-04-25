/**
 * Class: CSCI 1125-30
 * Semester: Spring 2017
 * @author Dawood Khan
 * Homework Assignment #3
 * 
 * Description: This class defines LineItem which contains a Product object, how much of the 
 * product is sold, and how many warranties for RetailTransaciton class.
 * 
 */
public class LineItem {

	private Product product; 			// the physical Product object being sold
	private int quantity; 				// how much they are buying
	private int numberOfWarranties; 	// how much warranties are being bought
	
	/**
	 * Explicit value constructor for lineItem
	 * @param product 	What product is being purchased
	 * @param quantity  How much is being purchased
	 * @param numberOfWarranties How many warranties are being sold
	 */
	public LineItem(Product product, int quantity, int numberOfWarranties){
		this.product = product;
		this.quantity = quantity;
		this.numberOfWarranties = numberOfWarranties;
	}
	
	public Product getProduct(){
		return product;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public int getNumberofWarranties(){
		return numberOfWarranties;
	}
	
	public void setProduct(Product product){
		this.product = product;
	}
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	
	public void setNumberOfWarranties(int numberofWarranties){
		this.numberOfWarranties = numberofWarranties;
	}
}