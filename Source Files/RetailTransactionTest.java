/**
 * Class: CSCI 1125-30
 * Semester: Spring 2017
 * @author Dawood Khan
 * Homework Assignment #3
 * 
 * Description: This class tests 3 methods of the Retail Transaction class.
 * Those three methods are scanItem(), calculateTransactionTotal(), getNumberOfProductsSold().
 * It tests to make sure products are not added to a transaction when it shouldn't be, make sure
 * it works for sale, returns, large transactions, empty ones, as well as transactions with
 * invalid items. Also no need for try catch because that is no what we are testing here at 
 * least for most methods.
 * 
 */
import static org.junit.Assert.*;
import org.junit.Test;

public class RetailTransactionTest {

	// ----------- TESTING scanItem() -----------
	@Test
	public void test_scanItem_CellPhone() throws InvalidScannedItemException {
		// GIVEN //
		RetailTransaction sut = new RetailTransaction(1, 100, "2017-01-01 08:01:59", 
				TransactionTypeEnum.SALE);
		
		CellPhone cellphone = new CellPhone();
		cellphone.setPrice(100.0);
		cellphone.setWarrantyEligible(true);
		cellphone.setProductID("111");
		ProductCatalog catalog = new ProductCatalog();
		catalog.addProduct(cellphone);
		
		// WHEN //
		sut.scanItem(cellphone, 1, 1, catalog);

		double total = sut.calculateTransactionTotal();
		
		// THEN //
		double cost = (100 * 1 + 100 * 1 * .1) * 1.0725;
		
		assertTrue(cost == total);
	}
	
	@Test
	public void test_scanItem_HeadPhones() throws InvalidScannedItemException {
		// GIVEN //
		RetailTransaction sut = new RetailTransaction(1, 100, "2017-01-01 08:01:59", 
				TransactionTypeEnum.SALE);
		
		Headphones headphones = new Headphones();
		headphones.setPrice(50.0);
		headphones.setWarrantyEligible(false);
		headphones.setProductID("1111");
		ProductCatalog catalog = new ProductCatalog();
		catalog.addProduct(headphones);
		
		// WHEN //
		sut.scanItem(headphones, 1, 0, catalog);
		
		double total = sut.calculateTransactionTotal();
		
		// THEN //
		double cost = (50.0 * 1 + 50.0 * 0 * .1) * 1.0725;
		
		assertTrue(cost == total);
	}
	
	/**
	 * This test function tests all types of invalid products:
	 * 1. the product is not in the product catalog.
	 * 2. the quantity is invalid.
	 * 3. the number of warranties is greater than the quantity
	 * 4. there are warranties for an item that is ineligible for warranties
	 * We are surrounding it with try catch blocks to make sure errors are being
	 * thrown and RetailTransaction object is correct.
	 * @throws InvalidScannedItemException 
	 */
	@Test
	public void test_scanItem_InvalidProductTypes() {
		// GIVEN //
		RetailTransaction sut = new RetailTransaction(1, 100, "2017-01-01 08:01:59", 
				TransactionTypeEnum.SALE);
		
		CellPhone cellphone = new CellPhone();
		cellphone.setPrice(100.0);
		cellphone.setWarrantyEligible(true);
		cellphone.setProductID("111");
		
		CellPhone cellphone2 = new CellPhone();
		cellphone2.setPrice(50.0);
		cellphone2.setWarrantyEligible(false);
		cellphone2.setProductID("1");
		
		Headphones headphones = new Headphones();
		headphones.setPrice(50.0);
		headphones.setWarrantyEligible(false);
		headphones.setProductID("1111");
		
		ProductCatalog catalog = new ProductCatalog();
		catalog.addProduct(cellphone); 		// adding cellphone object
		catalog.addProduct(cellphone2);     // adding cellphone object 2

		// WHEN //

		try {
			sut.scanItem(headphones, 1, 0, catalog);// 1. did not add to catalog
		} catch (InvalidScannedItemException e) {}
		
		try {	
			sut.scanItem(cellphone, -9, 2, catalog);// 2. invalid quantity
		} catch (InvalidScannedItemException e) {}
		try {
			sut.scanItem(cellphone, 0, 2, catalog); // 2. invalid quantity
		} catch (InvalidScannedItemException e) {}
		try {
			sut.scanItem(cellphone, -99999, 2, catalog);// 2. invalid quantity
		} catch (InvalidScannedItemException e) {}
		
		try {
			sut.scanItem(cellphone, 1, 2, catalog); // 3. numberOfWarranties > quantity
		} catch (InvalidScannedItemException e) {}
		try {
			sut.scanItem(cellphone, 50, 51, catalog); // 3. numberOfWarranties > quantity
		} catch (InvalidScannedItemException e) {}
			
		try {
			sut.scanItem(cellphone2, 1, 1, catalog); // 4. not warranty eligible
		} catch (InvalidScannedItemException e) {}

		double total = sut.calculateTransactionTotal();
		
		// THEN //
		double cost = 0 * 1.0725;

		assertTrue(cost == total);
	}
	
	@Test
	public void test_scanItem_Return() throws InvalidScannedItemException {
		// GIVEN //
		RetailTransaction sut = new RetailTransaction(1, 100, "2017-01-01 08:01:59", 
				TransactionTypeEnum.RETURN);
		
		CellPhone cellphone = new CellPhone();
		cellphone.setPrice(100.0);
		cellphone.setWarrantyEligible(true);
		cellphone.setProductID("111");
		ProductCatalog catalog = new ProductCatalog();
		catalog.addProduct(cellphone);
		
		// WHEN //
		sut.scanItem(cellphone, 2, 2, catalog);

		
		double total = sut.calculateTransactionTotal();
		
		// THEN //
		// cost is the cost of 2 products, 2 warranties, tax rate and -1 for return
		double cost = (100 * 2 + 100 * 2 * .1) * 1.0725 * - 1;
		
		assertTrue(cost == total);
	}
	
	// ----------- TESTING calculateTransactionTotal() -----------
	@Test
	public void test_calculateTransactionTotal_Return() throws InvalidScannedItemException {
		// GIVEN //
		RetailTransaction sut = new RetailTransaction(1, 100, "2017-01-01 08:01:59", 
				TransactionTypeEnum.RETURN);
		
		CellPhone cellphone = new CellPhone();
		cellphone.setPrice(200.0);
		cellphone.setWarrantyEligible(true);
		cellphone.setProductID("000");
		ProductCatalog catalog = new ProductCatalog();
		catalog.addProduct(cellphone);
		
		// WHEN //

		sut.scanItem(cellphone, 5, 3, catalog);

		double total = sut.calculateTransactionTotal();
		
		// THEN //
		// cost is the cost of 5 products, 3 warranties, tax rate and -1 for return
		double cost = (200 * 5 + 200 * 3 * .1) * 1.0725 * - 1;
		
		assertTrue(cost == total);
	}

	@Test
	public void test_calculateTransactionTotal_Sale() throws InvalidScannedItemException {
		// GIVEN //
		RetailTransaction sut = new RetailTransaction(1, 100, "2017-01-01 08:01:59", 
				TransactionTypeEnum.SALE);
		
		CellPhone cellphone = new CellPhone();
		cellphone.setPrice(200.0);
		cellphone.setWarrantyEligible(true);
		cellphone.setProductID("000");
		
		Headphones headphones = new Headphones();
		headphones.setPrice(50.0);
		headphones.setWarrantyEligible(true);
		headphones.setProductID("1111");
		
		// invaild product to make sure it doesn't get added to transaction
		Headphones headphones2 = new Headphones();
		headphones2.setPrice(50.0);
		headphones2.setWarrantyEligible(true);
		headphones2.setProductID("100");
		
		ProductCatalog catalog = new ProductCatalog();
		catalog.addProduct(cellphone);
	 	catalog.addProduct(headphones);
	 	
		// WHEN //

		sut.scanItem(cellphone, 5, 3, catalog); 
	 	sut.scanItem(headphones, 4, 2, catalog);
		sut.scanItem(headphones2, 10, 10, catalog); // shoudn't affect transaction at all

		double total = sut.calculateTransactionTotal();
		
		// THEN //
		// cost is the cost of 5 products, 3 warranties, tax rate and -1 for return
		// as well as 4 headphones, 2 warranties
		double cost = ( (200 * 5 + 200 * 3 * .1) + (50 * 4 + 50 * 2 * .1) ) * 1.0725;

		assertTrue(cost == total);
	}
	
	@Test
	public void test_calculateTransactionTotal_LargeTransaction() throws InvalidScannedItemException {
		// GIVEN //
		RetailTransaction sut = new RetailTransaction(1, 100, "2017-01-01 08:01:59", 
				TransactionTypeEnum.SALE);
		
		CellPhone cellphone = new CellPhone();
		cellphone.setPrice(200.0);
		cellphone.setWarrantyEligible(true);
		cellphone.setProductID("000");
		
		Headphones headphones = new Headphones();
		headphones.setPrice(50.0);
		headphones.setWarrantyEligible(true);
		headphones.setProductID("1111");
		
		// invaild product to make sure it doesn't get added to transaction
		Headphones headphones2 = new Headphones();
		headphones2.setPrice(50.0);
		headphones2.setWarrantyEligible(true);
		headphones2.setProductID("100");
		
		ProductCatalog catalog = new ProductCatalog();
		catalog.addProduct(cellphone);
	 	catalog.addProduct(headphones);
	 	
		// WHEN //

		sut.scanItem(cellphone, 50, 39, catalog);
		sut.scanItem(headphones, 45, 22, catalog);		

		double total = sut.calculateTransactionTotal();
		
		// THEN //
		// cost is the cost of 50 products, 39 warranties, tax rate and -1 for return
		// as well as 45 headphones, 22 warranties
		double cost = ( (200 * 50 + 200 * 39 * .1) + (50.0 * 45 + 50.0 * 22 * .1) ) * 1.0725;

		assertTrue(cost == total);
	}
	
	// ----------- TESTING getNumberOfProductsSold() -----------
	@Test
	public void test_getNumberOfProductsSold_0() {
		// GIVEN //
		RetailTransaction sut = new RetailTransaction(1, 100, "2017-01-01 08:01:59", 
				TransactionTypeEnum.RETURN);
		CellPhone cellphone = new CellPhone();
		
		cellphone.setPrice(200.0);
		cellphone.setWarrantyEligible(true);
		cellphone.setProductID("000");
		ProductCatalog catalog = new ProductCatalog();
		catalog.addProduct(cellphone);
		
		// WHEN //
		try {
			sut.scanItem(cellphone, 0, 0, catalog);
		} catch (InvalidScannedItemException e) {}
		
		int quantity = sut.getNumberOfProductsSold();
		
		// THEN //	
		assertTrue(quantity == 0);
	}
	
	@Test
	public void test_getNumberofProductsSold_13() throws InvalidScannedItemException {
		// GIVEN //
		RetailTransaction sut = new RetailTransaction(1, 100, "2017-01-01 08:01:59", 
				TransactionTypeEnum.RETURN);
		CellPhone cellphone = new CellPhone();
		
		cellphone.setPrice(200.0);
		cellphone.setWarrantyEligible(true);
		cellphone.setProductID("000");
		ProductCatalog catalog = new ProductCatalog();
		catalog.addProduct(cellphone);
		
		// WHEN //
		
		sut.scanItem(cellphone, 13, 0, catalog);

		
		int quantity = sut.getNumberOfProductsSold();
		
		// THEN //
		assertTrue(quantity == 13);
	}
	
	@Test
	public void test_getNumberofProductsSold_999() throws InvalidScannedItemException {
		// GIVEN //
		RetailTransaction sut = new RetailTransaction(1, 100, "2017-01-01 08:01:59", 
				TransactionTypeEnum.RETURN);
		CellPhone cellphone = new CellPhone();
		
		cellphone.setPrice(200.0);
		cellphone.setWarrantyEligible(true);
		cellphone.setProductID("000");
		ProductCatalog catalog = new ProductCatalog();
		catalog.addProduct(cellphone);
		
		Headphones headphones = new Headphones();
		headphones.setPrice(50.0);
		headphones.setWarrantyEligible(false);
		headphones.setProductID("1111");
		catalog.addProduct(headphones);
		// WHEN //

		sut.scanItem(cellphone, 500, 0, catalog);
		sut.scanItem(headphones, 499, 0, catalog);

		
		int quantity = sut.getNumberOfProductsSold();
		
		// THEN //	
		assertTrue(quantity == 999);
	}
	
}
