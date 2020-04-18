
/**
 * 
 * @modelName 
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.ListIterator;

public class Library implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int APPLIANCE_NOT_FOUND = 1;
	public static final int APPLIANCE_NOT_PURCHASED = 2;
	public static final int APPLIANCE_HAS_BACKORDER = 3;
	public static final int APPLIANCE_PURCHASED = 4;
	public static final int BACKORDER_PLACED = 5;
	public static final int NO_BACKORDER_FOUND = 6;
	public static final int OPERATION_COMPLETED = 7;
	public static final int OPERATION_FAILED = 8;
	public static final int NO_SUCH_CUSTOMER = 9;
	public static final int INVALID_APPLIANCE = 10;
	// private static final String  = null;
	public Catalog catalog;
	private CustomerList customerList;
	private RepairPlanList repairPlanList; 
	private static Library library;
	private double allSales = 0.0 ; 
	private double allSalesRepairPlans = 0.0; 

	/**
	 * Private for the singleton pattern Creates the catalog and customer collection
	 * objects
	 */
	private Library() {
		catalog = Catalog.instance();
		customerList = CustomerList.instance();
		repairPlanList = repairPlanList.instance();
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static Library instance() {
		if (library == null) {
			CustomerIdServer.instance(); // instantiate all singletons
			return (library = new Library());
		} else {
			return library;
		}
	}

	/**
	 * Organizes the operations for adding a appliance
	 * 
	 * @param brandName  appliance brandName
	 * @param modelName modelName name
	 * @param id     appliance id
	 * @return the Appliance object created
	 */
	public Appliance addModel( String typeOfAppliance, double price, String brandName, String modelName,
		double monthlyRepairPlanCost, double capacity, double heatOutput) {

		switch(typeOfAppliance) {
		
			case "1" : 
				Washer washer = new Washer(brandName, modelName, price, monthlyRepairPlanCost);
				washer.applianceType = "washerType";
				catalog.insertAppliance(washer); 
				break;
				
			case "2" : 
				Dryer dryer = new Dryer(brandName, modelName, price, monthlyRepairPlanCost);
				dryer.applianceType = "dryerType";
				catalog.insertAppliance(dryer); 
				break;
				
			// kitchen range and dish washers
			case "3" : 
				KitchenRange kitchenRange = new KitchenRange(brandName, modelName, price);
				kitchenRange.applianceType = "kitchenRangeType";
				catalog.insertAppliance(kitchenRange);
				break;
			
			case "4" :  
				DishWasher dishwasher = new DishWasher(brandName, modelName, price);
				dishwasher.applianceType = "dishwasherType";
				catalog.insertAppliance(dishwasher);
				break;
			
			// refrigerators
			case "5" : 
				Refrigerator refrigerator = new Refrigerator(brandName, modelName, price, capacity);
				refrigerator.applianceType = "refrigeratorType"; 
				catalog.insertAppliance(refrigerator);
				break;
			
			// furnaces 
			case "6" : 
				Furnace furnace = new Furnace(brandName, modelName, price, heatOutput);
				furnace.applianceType = "furnaceType";
				catalog.insertAppliance(furnace);
				break;
			
			default:
				System.out.println("An error has occurred");
		}
		
		// Not sure what this is doing, fix it later
		//if (catalog.insertAppliance(appliance)) {
		//return (appliance);
		//}
		return null;
	}

	/**
	 * Organizes the operations for adding a customer
	 * 
	 * @param name    customer name
	 * @param phone   customer phone
	 * @return the Customer object created
	 */
	public Customer addCustomer(String name, String phone) {
		Customer customer = new Customer(name, phone);
		if (customerList.insertCustomer(customer)) {
			return (customer);
		}
		return null;
	}
	
	/**
	 * Searches for a given customer
	 * 
	 * @param customerId id of the customer
	 * @return true iff the customer is in the customer list collection
	 */
	public Customer searchCustomer(String customerId) {
		return customerList.search(customerId);
	}
	
	
	public void addInventory(String applianceId, int quantity) {
		Appliance appliance; 
		Customer customer ; 
		
		
		appliance = catalog.search(applianceId);
		
		int j = quantity ; 
		for (int i = 0 ; i < j ; i++) {
			customer = processBackOrder(applianceId); 
			if (customer == null) {
				break; 
			}
			quantity--;
		}
		
		appliance.stock += quantity;
	}
	
	// type - "all" or refrigeratorType etc. 
	public void listAppliance(String type) {
		

	}
	
	/**
	 * Enroll Repair Plan 
	 * based on placeBackOrder
	 */
	public int enrollRepairPlan(String customerId, String applianceId) {
		Appliance appliance = catalog.search(applianceId);
		if (appliance == null) {
			return APPLIANCE_NOT_FOUND;
		}
		if (appliance.applianceType.equals("kitchenRangeType") || appliance.applianceType.equals("dishwasherType") 
				|| appliance.applianceType.equals("refrigeratorType") || appliance.applianceType.equals("furnaceType") ) {
			
			return INVALID_APPLIANCE;
		}
		if (appliance.getIssuer() == null) {
			return APPLIANCE_NOT_PURCHASED;
		}
		Customer customer = customerList.search(customerId);
		if (customer == null) {
			return NO_SUCH_CUSTOMER;
		}
		
		RepairPlan repairPlan = new RepairPlan(customer, appliance);
		repairPlanList.insertRepairPlan(repairPlan); 
		customer.placeRepairPlan(repairPlan);
		
		
		return OPERATION_COMPLETED;
		
	}
	
	/**
	 * based on removeBackOrder
	 */
	public int withdrawRepairPlan(String customerId, String applianceId) {
		Customer customer = customerList.search(customerId);
		if (customer == null) {
			return (NO_SUCH_CUSTOMER);
		}
		Appliance appliance = catalog.search(applianceId);
		if (appliance == null) {
			return (APPLIANCE_NOT_FOUND);
		}
		
		Customer customerBeingCheckedInloop ; 
		Appliance applianceBeingCheckedInloop ; 
		for (Iterator iterator =  repairPlanList.getRepairPlans(); iterator.hasNext();) {
			
			RepairPlan repairPlan = (RepairPlan) iterator.next();
			
			customerBeingCheckedInloop = repairPlan.getCustomer();
			applianceBeingCheckedInloop = repairPlan.getAppliance();
			
			// double check equal 	
			if ((customer.getId()).equals(customerBeingCheckedInloop.getId())) {
				if ((appliance.getId()).equals(applianceBeingCheckedInloop.getId())) {
					customer.removeRepairPlan(applianceId);
					repairPlanList.withdrawRepairPlan(repairPlan);
				}
			}
			
		}
		
		return OPERATION_COMPLETED ;
	}
	
	// 
	public void chargeRepairPlans() {
		Appliance appliance; 
		Customer customer; 
		Double repairPlanCostOfAppliance; 
		
		for (Iterator iterator =  repairPlanList.getRepairPlans(); iterator.hasNext();) {
			
			RepairPlan repairPlan = (RepairPlan) iterator.next();
			customer = repairPlan.getCustomer();
			appliance = repairPlan.getAppliance();
			
			repairPlanCostOfAppliance = appliance.monthlyRepairPlanCost ; 
			customer.addToAccountBalance(repairPlanCostOfAppliance);
			allSalesRepairPlans += repairPlanCostOfAppliance ;
			
			
	}
//			
//			
//			RepairPlan repairPlan = (RepairPlan) iterator.next();
//			appliance = repairPlan.getAppliance(); 
//			String id = repairPlan.getAppliance().getId();
//			if (id.equals(applianceId)) {
//				iterator.remove();
//				return true;
//			}
//		}
//		return false;
	}
		
	
	public double printAllSales() {
		return allSales  ; 
	}
	
	public double printAllSalesRepairPlans() {
		return allSalesRepairPlans ; 
	}
	
	/**
	 * Based on processBackOrder
	 */
	public Customer chargeRepairPlans(String applianceId) {
		Appliance appliance = catalog.search(applianceId);
		if (appliance == null) {
			return null;
		}
		BackOrder backOrder = appliance.getNextBackOrder();
		if (backOrder == null) {
			return null;
		}
		backOrder.getCustomer().removeBackOrder(applianceId);
		backOrder.getAppliance().removeBackOrder(backOrder.getCustomer().getId());
		return backOrder.getCustomer();
	}

	/**
	 * Organizes the placing of a backOrder
	 * 
	 * @param customerId customer's id
	 * @param applianceId   appliance's id
	 * @param duration for how long the backOrder should be valid in days
	 * @return indication on the outcome
	 */
	public int placeBackOrder(String customerId, String applianceId) {
		Appliance appliance = catalog.search(applianceId);
		if (appliance == null) {
			return APPLIANCE_NOT_FOUND;
		}
		if (appliance.getIssuer() == null) {
			return APPLIANCE_NOT_PURCHASED;
		}
		Customer customer = customerList.search(customerId);
		if (customer == null) {
			return NO_SUCH_CUSTOMER;
		}
		BackOrder backOrder = new BackOrder(customer, appliance);
		appliance.placeBackOrder(backOrder);
		customer.placeBackOrder(backOrder);
		return BACKORDER_PLACED;
	}
	



	/**
	 * Processes backOrders for a single appliance
	 * 
	 * @param applianceId id of the appliance
	 * @return the customer who should be notified
	 */
	public Customer processBackOrder(String applianceId) {
		Appliance appliance = catalog.search(applianceId);
		if (appliance == null) {
			return null;
		}
		BackOrder backOrder = appliance.getNextBackOrder();
		if (backOrder == null) {
			return null;
		}
		backOrder.getCustomer().removeBackOrder(applianceId);
		backOrder.getAppliance().removeBackOrder(backOrder.getCustomer().getId());
		return backOrder.getCustomer();
	}

	/**
	 * Removes a backOrder for a specific appliance and customer combincation
	 * 
	 * @param customerId id of the customer
	 * @param applianceId   appliance id
	 * @return result of the operation
	 */
	public int removeBackOrder(String customerId, String applianceId) {
		Customer customer = customerList.search(customerId);
		if (customer == null) {
			return (NO_SUCH_CUSTOMER);
		}
		Appliance appliance = catalog.search(applianceId);
		if (appliance == null) {
			return (APPLIANCE_NOT_FOUND);
		}
		return customer.removeBackOrder(applianceId) && appliance.removeBackOrder(customerId) ? OPERATION_COMPLETED : NO_BACKORDER_FOUND;
	}

//	/*
//	 * Removes all out-of-date backOrders
//	 */
//	private void removeInvalidBackOrders() {
//		for (Iterator catalogIterator = catalog.getAppliances(); catalogIterator.hasNext();) {
//			for (Iterator iterator = ((Appliance) catalogIterator.next()).getBackOrders(); iterator.hasNext();) {
//				BackOrder backOrder = (BackOrder) iterator.next();
//				if (!backOrder.isValid()) {
//					backOrder.getAppliance().removeBackOrder(backOrder.getCustomer().getId());
//					backOrder.getCustomer().removeBackOrder(backOrder.getAppliance().getId());
//				}
//			}
//		}
//	}

	/**
	 * Organizes the issuing of a appliance
	 * 
	 * @param customerId customer id
	 * @param applianceId   appliance id
	 * @return the appliance issued
	 */
//	public Appliance issueAppliance(String customerId, String applianceId) {
//		Appliance appliance = catalog.search(applianceId);
//		if (appliance == null) {
//			return (null);
//		}
//		if (appliance.getIssuer() != null) {
//			return (null);
//		}
//		Customer customer = customerList.search(customerId);
//		if (customer == null) {
//			return (null);
//		}
//		if (!(appliance.issue(customer) && customer.issue(appliance))) {
//			return null;
//		}
//		return (appliance);
//	}
//	
	/**
	 * Organizes the issuing of a appliance
	 * 
	 * @param customerId customer id
	 * @param applianceId   appliance id
	 * @return the appliance issued
	 */
	public Appliance purchaseAppliance(String customerId, String applianceId, int quantity) {
		Appliance appliance = catalog.search(applianceId);
		if (appliance == null) {
			return (null);
		}

		Customer customer = customerList.search(customerId);
		if (customer == null) {
			return (null);
		}
		
		if (quantity <= appliance.stock) {
			appliance.stock -= quantity; 
			if (customer.issue(appliance,quantity)) {
				allSales += appliance.price * quantity ; 
			}
		} else if ((appliance.applianceType).equals("furnaceType")) {
			System.out.print("There's no back order for furnace so could only purchase " + appliance.stock + " furnaces, which were in stock");
			appliance.stock = 0; 
			if (customer.issue(appliance,appliance.stock)) {
				allSales += appliance.price * appliance.stock ;
			}

		} else {
			int backOrderQuantity = quantity - appliance.stock ;
			
			 
			if(customer.issue(appliance,appliance.stock)) {
				for (int i = 0 ; i < backOrderQuantity ; i++) {
					placeBackOrder(customerId, applianceId);
				}
				
				// Do all sales include backorders too ? 
				allSales += appliance.price * quantity ;
			}
			
		}
		
		return (appliance);
	}


	/**
	 * Returns an iterator to the appliances issued to a customer
	 * 
	 * @param customerId customer id
	 * @return iterator to the collection
	 */
	public Iterator getAppliances(String customerId) {
		Customer customer = customerList.search(customerId);
		if (customer == null) {
			return (null);
		} else {
			return (customer.getAppliancesIssued());
		}
	}

	/**
	 * Removes a specific appliance from the catalog
	 * 
	 * @param applianceId id of the appliance
	 * @return a code representing the outcome
	 */
	public int removeAppliance(String applianceId) {
		Appliance appliance = catalog.search(applianceId);
		if (appliance == null) {
			return (APPLIANCE_NOT_FOUND);
		}
		if (appliance.hasBackOrder()) {
			return (APPLIANCE_HAS_BACKORDER);
		}
		if (appliance.getIssuer() != null) {
			return (APPLIANCE_PURCHASED);
		}
		if (catalog.removeAppliance(applianceId)) {
			return (OPERATION_COMPLETED);
		}
		return (OPERATION_FAILED);
	}



	/**
	 * Returns an iterator to the transactions for a specific customer on a certain
	 * date
	 * 
	 * @param customerId customer id
	 * @param date     date of issue
	 * @return iterator to the collection
	 */
	public Iterator getTransactions(String customerId, Calendar date) {
		Customer customer = customerList.search(customerId);
		if (customer == null) {
			return (null);
		}
		return customer.getTransactions(date);
	}

	/**
	 * Retrieves a deserialized version of the library from disk
	 * 
	 * @return a Library object
	 */
	public static Library retrieve() {
		try {
			FileInputStream file = new FileInputStream("LibraryData");
			ObjectInputStream input = new ObjectInputStream(file);
			library = (Library) input.readObject();
			CustomerIdServer.retrieve(input);
			return library;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}

	/**
	 * Serializes the Library object
	 * 
	 * @return true iff the data could be saved
	 */
	public static boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("LibraryData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(library);
			output.writeObject(CustomerIdServer.instance());
			file.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}

	/**
	 * String form of the library
	 * 
	 */
	@Override
	public String toString() {
		return catalog + "\n" + customerList;
	}
}