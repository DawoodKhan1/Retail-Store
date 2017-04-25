/**
 * Class: CSCI 1125-30
 * Semester: Spring 2017
 * @author Dawood Khan
 * Homework Assignment #3
 * 
 * Description: This class defines an exception that is thrown if a item that
 * being scanned cannot be scanned. Meaning if the quantity is 0 or less or the
 * number of warranties exceeds the number of quantity sold. 
 * 
 */
public class InvalidScannedItemException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public InvalidScannedItemException(){}
	
	// For displaying a system message
	public InvalidScannedItemException(String message){
		super(message);
	}
}


