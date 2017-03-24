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
}
