
/**
 *  
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
	
	private Catalog catalog;
	private MemberList customerList;
	private static Library library;

	/**
	 * Private for the singleton pattern Creates the catalog and member collection
	 * objects
	 */
	private Library() {
		
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static Library instance() {

	}


	public Appliance addAppliance(_) {
		
	}


	public Member addCustomer(String name, String phone) {
		Customer customer = new Customer(name, phone);
		if (customerList.insertCustomer(customer)) {
			return (customer);
		}
		return null;
	}




	public Customer searchCustomer(String customerId) {
		return customerList.search(customerId);
	}


	public Customer processBacklog(String _) {

	}


	public int removeBacklog(String _, String _) {
		
	}

	

	
	public Iterator getPurchase(_) {
		
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
			MemberIdServer.retrieve(input);
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
