/**
 * Class: CSCI 1125-30
 * Semester: Spring 2017
 * @author Dawood Khan
 * Homework Assignment #3
 * 
 * Description: This class defines Headphones class which inherits off of Product with
 * just one extra data field of does it have bluetooth.
 * 
 */
public class Headphones extends Product {

	private boolean hasBluetooth; 		// is it bluetooth compatible or not
	
	public boolean getHasBluetooth(){
		return hasBluetooth;
	}

	public void setHasBluetooth(boolean hasBluetooth){
		this.hasBluetooth = hasBluetooth;
	}
	
	/**
	 * This method returns a string containing a string with all the product
	 * information about it namely base product information with 
	 * hasbluetooth.
	 * @return The string with all the basic product info on seperate lines
	 */
	@Override
	public String getProductInfo(){
		// will hold basic product information
		StringBuilder productInfo = new StringBuilder();
		
		productInfo.append(super.getProductInfo());// get base info on product
		productInfo.append(this.getHasBluetooth());
		productInfo.append("\n");

		return productInfo.toString();// convert to string
	}
}
