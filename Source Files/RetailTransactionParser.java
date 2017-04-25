/**
 * Class: CSCI 1125-30
 * Semester: Spring 2017
 * @author z001cnc
 * Homework Assignment #3
 * 
 * Description: This class defines a RetailTransactionParser class that parses an array
 * of strings and initializes CellPhone, Headphones, and RetailTransaction objects using the 
 * setters of the class and parsing.
 * 
 */
public class RetailTransactionParser {
	
	/**
	 * This parses an array of strings to initialize a CellPhone object with.
	 * Format:
	 * cell phone,[product id],[price],[name],[warranty eligible], [brand],[carrier]
	 * @param cellPhoneFields the string array containing info about the CellPhone
	 * @return 	returns the CellPhone object correctly initialized
	 */
    public static CellPhone getCellPhoneFromFields(String[] cellPhoneFields) {
        CellPhone cellPhone = new CellPhone();
        
        cellPhone.setProductID(cellPhoneFields[1]);    
        cellPhone.setPrice(Double.parseDouble(cellPhoneFields[2]));
        cellPhone.setProductName(cellPhoneFields[3]);
        cellPhone.setWarrantyEligible(Boolean.parseBoolean(cellPhoneFields[4]));
        cellPhone.setBrand(cellPhoneFields[5]);
        cellPhone.setCarrier(cellPhoneFields[6]);

        return cellPhone;
    }

    /**
     * This parses an array of strings to initialize a Headphones object with.
     * Format: 
     * headphones,[product id],[price],[name],[warranty eligible],[brand],[Bluetooth capable]
     * @param headphonesFields   the string array containing info about the Headphones
     * @return  returns the Headphones object correctly initialized
     */
    public static Headphones getHeadphonesFromFields(String[] headphonesFields) {
        Headphones headphones = new Headphones();
        
        headphones.setProductID(headphonesFields[1]);
        headphones.setPrice(Double.parseDouble(headphonesFields[2]));
        headphones.setProductName(headphonesFields[3]);
        headphones.setWarrantyEligible(Boolean.parseBoolean(headphonesFields[4]));
        headphones.setBrand(headphonesFields[5]);
        headphones.setHasBluetooth(Boolean.parseBoolean(headphonesFields[6]));

        return headphones;
    }

    /**
     * This parses an array of strings to initialize a RetailTransaction object with.
     * Format: 
     * Transaction,[transaction id],[register id],[transaction date/time],[sale/return]
     * @param headphonesFields   the string array containing info about the RetailTransaction
     * @return  returns the RetailTransaction object correctly initialized
     */
    public static RetailTransaction getTransactionFromFields(String[] transactionFields) {
    	
    	return new RetailTransaction(
                Long.parseLong(transactionFields[1]),
                Integer.parseInt(transactionFields[2]),
                transactionFields[3],
                TransactionTypeEnum.valueOf(transactionFields[4].toUpperCase()));
    }
}
