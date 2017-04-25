/**
 * Class: CSCI 1125-30
 * Semester: Spring 2017
 * @author Dawood Khan
 * Homework Assignment #3
 * 
 * Description: This class defines a FileInputProcessor class that handles
 * taking lines from the input file, processing it, opening it, and closing it.
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileInputProcessor {
	
	String inputFileName = ""; 	// name of the file
	File iFile = null; 			// file object is initially null
	Scanner inputFile = null;   // scanner object to go through the input file
	
	/**
	 * glorified accessor to hasNextLine()
	 * @return T if has not reached EOF, F if reached EOF
	 */
	public boolean notEmpty(){
		return inputFile.hasNextLine();
	}// end notEmpty()
	
	/**
	 * This method opens the input file with the name the programmar provides.
	 * @param fileName What the name of the input file is
	 * @throws FileNotFoundException if can't find input file stop processing
	 */
	public void openInputFile(String fileName) {
		inputFileName = fileName;
		iFile = new File(inputFileName);
		try {
			inputFile = new Scanner(iFile);
		} catch (FileNotFoundException e) {
			System.out.println("Error. The input file " + fileName + " was not found.");
			System.exit(0);
		}
	}// end openInputFile()
	
	// this function takes in a line from input file and formats the first element
	// in line to lower case and no whitespace checks for room
	/**
	 * This program takes in a line from the input file, splits it by the ',', and then
	 * takes out leading white space and trailing white space
	 * @return the formatted array of strings
	 */
	public String[] processLine(){
		String[] fields = null; 		// the string array to hold the data fields
		
		if(inputFile.hasNextLine()){
			fields = inputFile.nextLine().split(",");// take in input into string array
		}// end if
		
		for(int i = 0; i < fields.length ; i++){
			fields[i] = fields[i].trim(); 		// remove extra white space
		}
		
		return fields; 							// return the array of strings
	}
	
	public void closeInputFile(){
		inputFile.close();
	}
}
