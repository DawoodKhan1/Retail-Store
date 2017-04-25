/**
 * Class: CSCI 1125-30
 * Semester: Spring 2017
 * @author Dawood Khan
 * Homework Assignment #3
 * 
 * Description: This class defines CellPhone class which inherits off of Product
 * with just one additional data field being carrier.
 * 
 */
public class CellPhone extends Product {
	
	private String carrier; 		// what is the carrier ex. AT&T
	
	public String getCarrier(){
		return carrier;
	}
	
	public void setCarrier(String carrier){
		this.carrier = carrier;
	}
	
	/**
	 * This method returns a string containing a string with all the product
	 * information about it namely base product information with 
	 * Carrier.
	 * @return The string with all the basic product info on seperate lines
	 */
	@Override
	public String getProductInfo(){
		// will hold basic product information
		StringBuilder productInfo = new StringBuilder();
		
		productInfo.append(super.getProductInfo());// get base info on product
		productInfo.append(this.getCarrier());
		productInfo.append("\n");

		return productInfo.toString();// convert to string
	}
}
