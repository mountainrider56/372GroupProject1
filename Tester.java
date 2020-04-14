package ics372GroupProject1;

import java.util.ArrayList;

// Not sure if we need this class as a standalone or if we just stick it in UserInterface or Library.

public class Tester {
	

	
	
	// use this method to generate the first 20 customers, 
	// and appliances to populate the program with starting data
	void generateStartingData()
	{
		// Array of 20 phone numbers
		String[] phoneNumberList = new String[20];
		String[] nameList = new String[20];
		
		phoneNumberList[0] = "615-555-1444";
		phoneNumberList[1] = "615-505-1444";
		phoneNumberList[2] = "615-555-1444";
		phoneNumberList[3] = "615-555-1444";
		phoneNumberList[4] = "615-555-1444";
		phoneNumberList[5] = "615-555-1444";
		phoneNumberList[6] = "615-555-1444";
		phoneNumberList[7] = "615-555-1444";
		phoneNumberList[8] = "615-555-1444";
		phoneNumberList[9] = "615-555-1444";
		phoneNumberList[10] = "615-555-1444";
		phoneNumberList[11] = "615-555-1444";
		phoneNumberList[12] = "615-555-1444";
		phoneNumberList[13] = "615-555-1444";
		phoneNumberList[14] = "615-555-1444";
		phoneNumberList[15] = "615-555-1444";
		phoneNumberList[16] = "615-555-1444";
		phoneNumberList[17] = "615-555-1444";
		phoneNumberList[18] = "615-555-1444";
		phoneNumberList[19] = "615-555-1444";
		
		nameList[0] = "Bob";
		nameList[1] = "John";
		nameList[2] = "Timmy";
		nameList[3] = "Samantha";
		nameList[4] = "Diamond";
		nameList[5] = "Oliver";
		nameList[6] = "Frank";
		nameList[7] = "Sam";
		nameList[8] = "Bob";
		nameList[9] = "John";
		nameList[10] = "Timmy";
		nameList[11] = "Samantha";
		nameList[12] = "Diamond";
		nameList[13] = "Oliver";
		nameList[14] = "Frank";
		nameList[15] = "Sam";
		nameList[16] = "Samantha";
		nameList[17] = "Suzet";
		nameList[18] = "Mary";
		nameList[19] = "Abdi";
		
		for(int i=0;i<20;i++) {
			Customer c = new Customer(nameList[i],phoneNumberList[i]);
			Library.customerList.add(c);
		}
		
		
		// Appliances
		String[] brandNameList = new String[5];
		brandNameList[0] = "Samsung";
		brandNameList[1] = "MayTag";
		brandNameList[2] = "Bryant";
		brandNameList[3] = "Bayer";
		brandNameList[4] = "KitchenAide";
		
	}
	
	
	

}
