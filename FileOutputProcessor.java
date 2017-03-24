/**
 * Class: CSCI 1125-30
 * Semester: Spring 2017
 * @author Dawood Khan
 * Homework Assignment #3
 * 
 * Description: This class defines a FileOutputProcessor class that handles
 * opening outputFile, printing strings and string builder objects to it, and 
 * closing it.
 * 
 */
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileOutputProcessor {
	String outputFileName = ""; 	// name of output file
	PrintWriter outputFile = null;  // print writer to actually output data
	
	/**
	 * Opens the output file with the given name by the programmer.
	 * @param fileName  name of output file
	 * @throws FileNotFoundException if computer cannot make output files stop immediately
	 */
	public void openOutputFile(String fileName) throws FileNotFoundException {
		outputFileName = fileName;
		outputFile = new PrintWriter(outputFileName);
	}// end openOutputFile
	
	public void printLine(String text){
		outputFile.print(text);
	}
	
	public void printLine(StringBuilder text){
		outputFile.print(text.toString());
	}
	
	public void closeOutputFile(){
		outputFile.close();
	}
}
