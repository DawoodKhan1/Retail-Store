# Retail-Store
This program simulates the POS system at a retail store.
This program uses OOP in order to take in information from an input file and then process transaction generating their own seperate receipts as output files as well as transaction summaries and total sales. 

A more detailed explanation is that first the RetailStoreDriver opens the input file using FileInputProcessor and the output file using FileOutputProcessor. It then opens a ProductCatalog object to store all inventory items. 
Next it processes the input file through a loop until it reaches the EOF marker at the end of the file. It processes the file by splitting a line by a comma which the method processLine() does. Next is a switch depending on if it is a product or transaction. If it is a product add it to the ProductCatalog object using RetailTransactionParser to get the information from the string array. Both Headphones and CellPhone inherit of the Product Class.
If it is a transaction call readTransaction in order to process it. readTransaction also uses RetailTransactionParser to initialize the transaction.
The while loop runs until it reaches the end of the transaction denoted by "End Transaction". Otherwise it makes sure that the product being added to the transaction is a valid product by checking if it is in the catalog or not. If it isn't the method findProductbyID returns a null Product object and then it checks if it is not null, scan the item in with the correct quantity, number of warranties, and catalog. 
Back in main after the transaction is done being read it generates a receipt in the form of an output file by the method generateReceipt. It also uses a string builder to keep track of the transaction summaries and running total.
After the input file has been read, it then outputs to the console and output file the transaction summaries and total amount sold.
