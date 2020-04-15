
/**
 * 
 * @author 
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;

public class Library implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int APPLIANCE_NOT_FOUND = 1;
	public static final int APPLIANCE_NOT_ISSUED = 2;
	public static final int APPLIANCE_HAS_BACKORDER = 3;
	public static final int APPLIANCE_ISSUED = 4;
	public static final int BACKORDER_PLACED = 5;
	public static final int NO_BACKORDER_FOUND = 6;
	public static final int OPERATION_COMPLETED = 7;
	public static final int OPERATION_FAILED = 8;
	public static final int NO_SUCH_CUSTOMER = 9;
	private Catalog catalog;
	private CustomerList customerList;
	private static Library library;

	/**
	 * Private for the singleton pattern Creates the catalog and customer collection
	 * objects
	 */
	private Library() {
		catalog = Catalog.instance();
		customerList = CustomerList.instance();
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
	 * @param title  appliance title
	 * @param author author name
	 * @param id     appliance id
	 * @return the Appliance object created
	 */
	public Appliance addModel(String title, String author, String id) {
		Appliance appliance = new Appliance(title, author, id);
		if (catalog.insertAppliance(appliance)) {
			return (appliance);
		}
		return null;
	}

	/**
	 * Organizes the operations for adding a customer
	 * 
	 * @param name    customer name
	 * @param address customer address
	 * @param phone   customer phone
	 * @return the Customer object created
	 */
	public Customer addCustomer(String name, String address, String phone) {
		Customer customer = new Customer(name, address, phone);
		if (customerList.insertCustomer(customer)) {
			return (customer);
		}
		return null;
	}

	/**
	 * Organizes the placing of a backOrder
	 * 
	 * @param memberId customer's id
	 * @param applianceId   appliance's id
	 * @param duration for how long the backOrder should be valid in days
	 * @return indication on the outcome
	 */
	public int placeBackOrder(String memberId, String applianceId, int duration) {
		Appliance appliance = catalog.search(applianceId);
		if (appliance == null) {
			return APPLIANCE_NOT_FOUND;
		}
		if (appliance.getBorrower() == null) {
			return APPLIANCE_NOT_ISSUED;
		}
		Customer customer = customerList.search(memberId);
		if (customer == null) {
			return NO_SUCH_CUSTOMER;
		}
		BackOrder backOrder = new BackOrder(customer, appliance, duration);
		appliance.placeBackOrder(backOrder);
		customer.placeBackOrder(backOrder);
		return BACKORDER_PLACED;
	}

	/**
	 * Searches for a given customer
	 * 
	 * @param memberId id of the customer
	 * @return true iff the customer is in the customer list collection
	 */
	public Customer searchCustomer(String memberId) {
		return customerList.search(memberId);
	}

	/**
	 * Processes holds for a single appliance
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
	 * @param memberId id of the customer
	 * @param applianceId   appliance id
	 * @return result of the operation
	 */
	public int removeBackOrder(String memberId, String applianceId) {
		Customer customer = customerList.search(memberId);
		if (customer == null) {
			return (NO_SUCH_CUSTOMER);
		}
		Appliance appliance = catalog.search(applianceId);
		if (appliance == null) {
			return (APPLIANCE_NOT_FOUND);
		}
		return customer.removeBackOrder(applianceId) && appliance.removeBackOrder(memberId) ? OPERATION_COMPLETED : NO_BACKORDER_FOUND;
	}

	/*
	 * Removes all out-of-date holds
	 */
	private void removeInvalidBackOrders() {
		for (Iterator catalogIterator = catalog.getAppliances(); catalogIterator.hasNext();) {
			for (Iterator iterator = ((Appliance) catalogIterator.next()).getBackOrders(); iterator.hasNext();) {
				BackOrder backOrder = (BackOrder) iterator.next();
				if (!backOrder.isValid()) {
					backOrder.getAppliance().removeBackOrder(backOrder.getCustomer().getId());
					backOrder.getCustomer().removeBackOrder(backOrder.getAppliance().getId());
				}
			}
		}
	}

	/**
	 * Organizes the issuing of a appliance
	 * 
	 * @param memberId customer id
	 * @param applianceId   appliance id
	 * @return the appliance issued
	 */
	public Appliance issueAppliance(String memberId, String applianceId) {
		Appliance appliance = catalog.search(applianceId);
		if (appliance == null) {
			return (null);
		}
		if (appliance.getBorrower() != null) {
			return (null);
		}
		Customer customer = customerList.search(memberId);
		if (customer == null) {
			return (null);
		}
		if (!(appliance.issue(customer) && customer.issue(appliance))) {
			return null;
		}
		return (appliance);
	}


	/**
	 * Returns an iterator to the appliances issued to a customer
	 * 
	 * @param memberId customer id
	 * @return iterator to the collection
	 */
	public Iterator getAppliances(String memberId) {
		Customer customer = customerList.search(memberId);
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
		if (appliance.getBorrower() != null) {
			return (APPLIANCE_ISSUED);
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
	 * @param memberId customer id
	 * @param date     date of issue
	 * @return iterator to the collection
	 */
	public Iterator getTransactions(String memberId, Calendar date) {
		Customer customer = customerList.search(memberId);
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
