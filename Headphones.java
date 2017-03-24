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
}
