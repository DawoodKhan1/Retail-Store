/**
 * Class: CSCI 1125-30
 * Semester: Spring 2017
 * @author Dawood Khan
 * Homework Assignment #3
 * 
 * Description: This class defines Product class which has generic product information
 * such as productID, price, name, warrantyEligible, and brand. It is used as the super class
 * for Headphones and CellPhone.
 * 
 */
public class Product {

	private String productID; 			// unique identifying alphanum sequence
	private double price; 				// cost
	private String productName; 		// name ex. iPhone 4s
	private boolean warrantyEligible; 	// T/F
	private String brand; 				// ex. Apple
	
	public Product(){
		price = 0;
		productName = null;
		warrantyEligible = false;
		brand = null;
	}
	
	public double getPrice(){
		return price;
	}
	
	public String getProductName(){
		return productName;
	}
	
	public boolean getWarrantyEligible(){
		return warrantyEligible;
	}
	
	public String getBrand(){
		return brand;
	}
	
	public String getProductID(){
		return productID;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
	
	public void setProductName(String productName){
		this.productName = productName;
	}
	
	public void setWarrantyEligible(boolean warrantyEligible){
		this.warrantyEligible = warrantyEligible;
	}
	
	public void setBrand(String brand){
		this.brand = brand;
	}
	
	public void setProductID(String productID){
		this.productID = productID;
	}	
	
	/**
	 * This method returns a string containing a string with all the base
	 * product information contained within it.
	 * @return The string with all the basic product info on seperate lines
	 */
	public String getProductInfo(){
		// will hold basic product information
		StringBuilder productInfo = new StringBuilder();
		
		productInfo.append(this.getProductName());
		productInfo.append("\n");
		productInfo.append(this.getProductID());
		productInfo.append("\n");
		productInfo.append(this.getPrice());
		productInfo.append("\n");
		productInfo.append(this.getBrand());
		productInfo.append("\n");
		productInfo.append(this.getWarrantyEligible());
		productInfo.append("\n");


		return productInfo.toString();// convert to string
	}
}

