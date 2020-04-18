/**
 * 
 * @modelName 
 *   
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * 
 * This class implements the user interface for the Library project. The
 * commands are encoded as integers using a number of static final variables. A
 * number of utility methods exist to make it easier to parse the input.
 *
 */
public class UserInterface {
	private static UserInterface userInterface;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Library library;
	private static final int EXIT = 0;
	private static final int ADD_APPLIANCE_MODEL = 1;
	private static final int ADD_CUSTOMER = 2;
	private static final int ADD_INVENTORY = 3;
	private static final int PURCHASE_ORDER = 4;
	private static final int ENROLL_REPAIR_PLAN = 5;
	private static final int WITHDRAW_REPAIR_PLAN = 6;
	private static final int CHARGE_REPAIR_PLAN = 7;
	private static final int PRINT_REVENUE = 8;
	private static final int LIST_APPLIANCES = 9;
	private static final int LIST_CUSTOMERS_WITH_REPAIR_PLANS = 10;
	private static final int LIST_CUSTOMERS = 11;
	private static final int LIST_BACKORDERS = 12;
	private static final int SAVE = 13;
	private static final int HELP = 14;
	
	/**
	 * Made private for singleton pattern. Conditionally looks for any saved data.
	 * Otherwise, it gets a singleton Library object.
	 */
	private UserInterface() {
		if (yesOrNo("Look for saved data and  use it?")) {
			retrieve();
		} else {
			library = Library.instance();
			if (yesOrNo("Do you want to generate a test bed and invoke the functionality using asserts?")) {
				new AutomatedTester(library);
			}
		}

	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static UserInterface instance() {
		if (userInterface == null) {
			return userInterface = new UserInterface();
		} else {
			return userInterface;
		}
	}

	/**
	 * Gets a token after prompting
	 * 
	 * @param prompt - whatever the user wants as prompt
	 * @return - the token from the keyboard
	 * 
	 */
	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}

	/**
	 * Queries for a yes or no and returns true for yes and false for no
	 * 
	 * @param prompt The string to be prepended to the yes/no prompt
	 * @return true for yes and false for no
	 * 
	 */
	private boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}

	public double getDoubleNumber(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Double number = Double.valueOf(item);
				return number.doubleValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a number ");
			}
		} while (true);
	}
	
	/**
	 * Converts the string to a number
	 * 
	 * @param prompt the string for prompting
	 * @return the integer corresponding to the string
	 * 
	 */
	public int getIntNumber(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Integer number = Integer.valueOf(item);
				return number.intValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a number ");
			}
		} while (true);
	}

	/**
	 * Prompts for a date and gets a date object
	 * 
	 * @param prompt the prompt
	 * @return the data as a Calendar object
	 */
	public Calendar getDate(String prompt) {
		do {
			try {
				Calendar date = new GregorianCalendar();
				String item = getToken(prompt);
				DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
				date.setTime(dateFormat.parse(item));
				return date;
			} catch (Exception fe) {
				System.out.println("Please input a date as mm/dd/yy");
			}
		} while (true);
	}

	/**
	 * Prompts for a command from the keyboard
	 * 
	 * @return a valid command
	 * 
	 */
	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
				if (value >= EXIT && value <= HELP) {
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		} while (true);
	}

	/**
	 * Displays the help screen
	 * 
	 */
	public void help() {
		System.out.println("Enter a number between 0 and 13 as explained below:");
		System.out.println(EXIT + " to Exit\n");
		System.out.println(ADD_APPLIANCE_MODEL + " to  add Appliance Model");
		System.out.println(ADD_CUSTOMER + " to add a Customer");
		System.out.println(PURCHASE_ORDER + " to  purchase order to a  customer");
		System.out.println(ENROLL_REPAIR_PLAN + " to  enroll in a repair plan");
		System.out.println(WITHDRAW_REPAIR_PLAN + " to  withdraw from repair plan");
		System.out.println(CHARGE_REPAIR_PLAN + " to  charge repair plan");
		System.out.println(PRINT_REVENUE + " to  print revenue");
		System.out.println(LIST_APPLIANCES + " to  list appliances");
		System.out.println(LIST_CUSTOMERS_WITH_REPAIR_PLANS + " to print customers with a repair plan");
		System.out.println(LIST_CUSTOMERS + " to list customers");
		System.out.println(LIST_BACKORDERS + " to list Backorders");
		System.out.println(SAVE + " to  save data");
		System.out.println(HELP + " for help");
	}

	/**
	 * Method to be called for adding a customer. Prompts the user for the appropriate
	 * values and uses the appropriate Library method for adding the customer.
	 * 
	 */
	public void addCustomer() {
		String name = getToken("Enter customer name");
		String phone = getToken("Enter phone");
		Customer result;
		result = library.addCustomer(name, phone);
		if (result == null) {
			System.out.println("Could not add customer");
		}
		System.out.println(result);
	}

	/**
	 * Method to be called for adding a model. Prompts the user for the appropriate
	 * values and uses the appropriate Library method for adding the model.
	 * 
	 */
	public void addModel() {
		Appliance result;
		do {
			String typeOfAppliance = getToken("Enter number of corresponding type of appliance you want to add," + "\n"
					+ "1. Cloth Washer" + "\n"
					+ "2. Cloth Dryer" + "\n"
					+ "3. Kitchen Ranges" + "\n"
					+ "4. Dish Washers" + "\n"
					+ "5. Refrigerators" + "\n"
					+ "6. Furnances");
			
			String brandName = getToken("Enter the brand Name"); 
			String modelName = getToken("Enter the model Name"); 
			double price = getDoubleNumber("Enter the Price"); 

			
			switch (typeOfAppliance) {
			
			// washer and dryer
			case "1" : case "2" : 
				double monthlyRepairPlanCost = getDoubleNumber("Enter monthly repair plan cost");
				result = library.addModel(typeOfAppliance, price, brandName, modelName,  monthlyRepairPlanCost, 0.0 , 0.0);
				break;
			
			// kitchen range and dish washers
			case "3" : case "4" :  
				result = library.addModel(typeOfAppliance, price, brandName, modelName, 0.0  , 0.0  , 0.0 );
				break;
				
			// refrigerators
			case "5" : 
				double capacity = getDoubleNumber("Enter capacity");
				result = library.addModel(typeOfAppliance, price, brandName, modelName, 0.0 , capacity , 0.0 );
				break;
			
			// furnaces 
			case "6" : 
				double heatOutput = getDoubleNumber("Enter heat Output");
				result = library.addModel(typeOfAppliance, price, brandName, modelName, 0.0 , 0.0  , heatOutput);
				break;
			
			default:
				System.out.println("An error has occurred");
			}
			
		} while (yesOrNo("Add more Models?"));
	}

	/**
	 * Method to be called for issuing appliances. Prompts the user for the appropriate
	 * values and uses the appropriate Library method for issuing appliances.
	 * 
	 */
	public void purchaseAppliance() {
		Appliance result;
		String customerID = getToken("Enter customer id");
		if (library.searchCustomer(customerID) == null) {
			System.out.println("No such customer");
			return;
		}
		do {
			String applianceID = getToken("Enter appliance id");
			int quantity = getIntNumber("Enter quantity of appliance you would like to purchase");
			result = library.purchaseAppliance(customerID, applianceID, quantity);
			if (result != null) {
				System.out.println(result.getBrandName() + "   " + result.getModelName() + " " );
			} else {
				System.out.println("Appliance could not be purchased");
			}
		} while (yesOrNo("Issue more appliances?"));
	}

	// adding Inventory 	
	public void addInventory() {	


		do {	
			String applianceId = getToken("Enter the Appliance ID"); 	
			int quantity = getIntNumber("Enter the quantity being added to the appliance"); 	
			library.addInventory(applianceId, quantity); 	


		} while (yesOrNo("Add more stocks for other appliances?"));	
	}
	

	


	/**
	 * Method to be called for placing a backOrder. Prompts the user for the appropriate
	 * values and uses the appropriate Library method for placing a backOrder.
	 * 
	 */
	public void enrollRepairPlan() {
		String customerID = getToken("Enter customer id");
		String applianceID = getToken("Enter appliance id");
		int result = library.enrollRepairPlan(customerID, applianceID);
		switch (result) {
		case Library.APPLIANCE_NOT_FOUND:
			System.out.println("No such Appliance in Library");
			break;
		case Library.APPLIANCE_NOT_PURCHASED:
			System.out.println(" Appliance is not checked out");
			break;
		case Library.NO_SUCH_CUSTOMER:
			System.out.println("Not a valid customer ID");
			break;
		case Library.OPERATION_COMPLETED:
			System.out.println("Enrolled in repair plan");
			break;
		default:
			System.out.println("An error has occurred");
		}
	}

	/**
	 * Method to be called for removing a backOrders. Prompts the user for the
	 * appropriate values and uses the appropriate Library method for removing a
	 * backOrder.
	 * 
	 */
	public void withdrawRepairPlan() {
		String customerID = getToken("Enter customer id");
		String applianceID = getToken("Enter appliance id");
		int result = library.withdrawRepairPlan(customerID, applianceID);
		switch (result) {
		case Library.APPLIANCE_NOT_FOUND:
			System.out.println("No such Appliance in Library");
			break;
		case Library.NO_SUCH_CUSTOMER:
			System.out.println("Not a valid customer ID");
			break;
		case Library.OPERATION_COMPLETED:
			System.out.println("The Repair Plan has been removed");
			break;
		default:
			System.out.println("An error has occurred");
		}
	}

	/**
	 * Method to be called for processing appliances. Prompts the user for the
	 * appropriate values and uses the appropriate Library method for processing
	 * appliances.
	 * 
	 */
	public void chargeRepairPlans() {
		library.chargeRepairPlans();
	}
	
	
	
	public void printRevenue() {
		System.out.println("Revenue of all sales = " + library.printAllSales());
		System.out.println("Revenue of all repair plans = " + library.printAllSalesRepairPlans()); 
	}

	/**
	 * Method to be called for displaying transactions. Prompts the user for the
	 * appropriate values and uses the appropriate Library method for displaying
	 * transactions.
	 * 
	 */
	public void getTransactions() {
		String customerID = getToken("Enter customer id");
		Calendar date = getDate("Please enter the date for which you want records as mm/dd/yy");
		Iterator result = library.getTransactions(customerID, date);
		if (result == null) {
			System.out.println("Invalid Customer ID");
		} else {
			while (result.hasNext()) {
				Transaction transaction = (Transaction) result.next();
				System.out.println(transaction.getType() + "   " + transaction.getBrandName() + "\n");
			}
			System.out.println("\n  There are no more transactions \n");
		}
	}

	/**
	 * Method to be called for saving the Library object. Uses the appropriate
	 * Library method for saving.
	 * 
	 */
	private void save() {
		if (library.save()) {
			System.out.println(" The library has been successfully saved in the file LibraryData \n");
		} else {
			System.out.println(" There has been an error in saving \n");
		}
	}

	/**
	 * Method to be called for retrieving saved data. Uses the appropriate Library
	 * method for retrieval.
	 * 
	 */
	private void retrieve() {
		try {
			if (library == null) {
				library = Library.retrieve();
				if (library != null) {
					System.out.println(" The library has been successfully retrieved from the file LibraryData \n");
				} else {
					System.out.println("File doesnt exist; creating new library");
					library = Library.instance();
				}
			}
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

	/**
	 * Orchestrates the whole process. Calls the appropriate method for the
	 * different functionalities.
	 * 
	 */
	public void process() {
		int command;
		help();
		while ((command = getCommand()) != EXIT) {
			switch (command) {
			case ADD_CUSTOMER:
				addCustomer();
				break;
			case ADD_APPLIANCE_MODEL:
				addModel();
				break;
			case PURCHASE_ORDER:
				purchaseAppliance(); 
				break;
			case ADD_INVENTORY:
				addInventory();
				break;
			case ENROLL_REPAIR_PLAN:
				enrollRepairPlan();
				break;
			case WITHDRAW_REPAIR_PLAN:
				withdrawRepairPlan(); 
				break;
			case CHARGE_REPAIR_PLAN:
				chargeRepairPlans(); 
				break;
			case PRINT_REVENUE:
				printRevenue();
				break;
			case LIST_APPLIANCES:
				list_appliances();
				break;
			case LIST_CUSTOMERS_WITH_REPAIR_PLANS:
				list_Repair_Plans();
				break;
			case LIST_CUSTOMERS:
				list_Customers();
				break;
			case LIST_BACKORDERS:
				
				break;
			case SAVE:
				save();
				break;
			case HELP:
				help();
				break;
			
			
			}
		}
	}

	private void list_appliances() {
		// TODO Auto-generated method stub
		System.out.println("Appliance List\n-----------\n");
		System.out.println(library.catalog.toString());
		//System.out.println(library.genericApplinaceList.toString());
	}

	private void list_Repair_Plans() {
		// TODO Auto-generated method stub
		System.out.println("List of users in Repair Plans\n-----------\n");
	}

	private void list_Customers() {
        // print out customers
        System.out.println("Customer List\n-----------\n");
        System.out.println(library.getCustomerList().toString()); 

    }
	/**
	 * The method to start the application. Simply calls process().
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		UserInterface.instance().process();
	}
}