/**
 * Class: CSCI 1125-30
 * Semester: Spring 2017
 * @author Dawood Khan
 * Homework Assignment #3
 * 
 * Description: This class defines ProductCatalog class which contains all the products 
 * currently being sold and stores it in an ArrayList of Products.
 * 
 */
import java.util.ArrayList;

public class ProductCatalog {
	// simple ArrayList of all products being sold
	private ArrayList<Product> products = new ArrayList<Product>(); 
	
	ProductCatalog(){
		products = new ArrayList<Product>();
	}
	
	/**
	 * This method finds a product by it's productID through a linear search through the ArrayList
	 * of Products.
	 * @param ID The productID of the product you are searching for
	 * @return  Returns the correct product if it exists or a null object if it does not
	 */
	public Product findProductbyID(String ID){
		Product returnProduct;  // the product we are going to return
		int index = 0; 			// index of product into arrayList we are interested in
		boolean found = false;
		// linear search through ArrayList of Products
		for(int i = 0; i < products.size() && !found; i ++){
			if(products.get(i).getProductID().trim().equals(ID.trim())){
				found = true;
				index = i;  	// assign the index the location of the product
			}
		}

		// if not found make a new Product initialized to null as a sentinel value
		if(!found){
			returnProduct = null; // if not found initialize it to null
		}
		else {
			returnProduct = products.get(index);
		}
		
		return returnProduct; // return the product that was searched for by id
	}
	
	/**
	 * This method adds a product to the list of products being sold by calling a method
	 * of ArrayList to add it in.
	 * @param product the product you are looking to add
	 */
	public void addProduct(Product product){
		products.add(product);
	}
	
	/**
	 * Displays the contents of all the products currently being sold except for carrier
	 * for phones and hasBluetooth for headphones for testing using polymorphism.
	 */
	public void displayProductCatalog(){
		for(int i = 0; i < products.size(); i++){
			System.out.println(products.get(i).getProductInfo());
		}
	}	
}

