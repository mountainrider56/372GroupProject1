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
	 * Private for the singleton pattern Creates the catalog and member collection
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
	 * Organizes the operations for adding a model
	 *
	 */
	public Appliance addModel(____) {
		Appliance appliance = new Appliance(____);
		if (catalog.insertModel(appliance)) {
			return (appliance);
		}
		return null;
	}

	/**
	 * Organizes the operations for adding a customer
	 * 
	 * @param name    member name
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
	 * Organizes the placing of a backorder
	 * 
	 * @param customerId customer's id
	 * @param applianceId   appliance's id
	 * @return indication on the outcome
	 */
	public int placeBackorder(String customerId, String applianceId) {
		Appliance appliance = catalog.search(applianceId);
		if (appliance == null) {
			return APPLIANCE_NOT_FOUND;
		}
		if (appliance.getCustomer() == null) { // this was getBorrower
			return APPLIANCE_NOT_ISSUED;
		}
		Customer customer = customerList.search(customerId);
		if (customer == null) {
			return NO_SUCH_CUSTOMER;
		}
		Backorder backorder = new Backorder(customer, appliance);
		appliance.placeBackorder(backorder);
		customer.placeBackorder(backorder);
		return BACKORDER_PLACED;
	}

	/**
	 * Searches for a given customer
	 * 
	 * @param customerId id of the customer
	 * @return true iff the customer is in the customer list collection
	 */
	public Customer searchCustomerList(String customerId) { //was searchMembership(_)
		return customerList.search(customerId);
	}

	public boolean save() {
		// TODO Auto-generated method stub
		return false;
	}

	public Appliance addAppliance(Appliance[] appliances) {
		// TODO Auto-generated method stub
		return null;
	}
