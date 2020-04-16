
/**
 * 
 * @modelName   
 */
import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String phone;
	private String id;
	private static final String CUSTOMER_STRING = "M";
	private List appliancesIssued = new LinkedList();
	private List appliancesOnBackOrder = new LinkedList();
	private List transactions = new LinkedList();

	/**
	 * Represents a single customer
	 * 
	 * @param name    name of the customer
	 * @param phone   phone number of the customer
	 */
	public Customer(String name, String phone) {
		this.name = name;
		this.phone = phone;
		id = CUSTOMER_STRING + (CustomerIdServer.instance()).getId();
	}

	/**
	 * Stores the appliance as issued to the customer
	 * 
	 * @param appliance the appliance to be issued
	 * @return true iff the appliance could be marked as issued. always true currently
	 */
	public boolean issue(Appliance appliance) {
		if (appliancesIssued.add(appliance)) {
			transactions.add(new Transaction("Appliance issued ", appliance.getBrandName()));
			return true;
		}
		return false;
	}

	/**
	 * Marks the appliance as not issued to the customer
	 * 
	 * @param appliance the appliance to be returned
	 * @return true iff the appliance could be marked as marked as returned
	 */
	public boolean returnAppliance(Appliance appliance) {
		if (appliancesIssued.remove(appliance)) {
			transactions.add(new Transaction("Appliance returned ", appliance.getBrandName()));
			return true;
		}
		return false;
	}


	/**
	 * Gets an iterator to the issued appliances
	 * 
	 * @return Iterator to the collection of issued appliances
	 */
	public Iterator getAppliancesIssued() {
		return (appliancesIssued.listIterator());
	}

	/**
	 * Places a backOrder for the appliance
	 * 
	 * @param backOrder the appliance to be placed a backOrder
	 */
	public void placeBackOrder(BackOrder backOrder) {
		transactions.add(new Transaction("BackOrder Placed ", backOrder.getAppliance().getBrandName()));
		appliancesOnBackOrder.add(backOrder);
	}

	/**
	 * Removes a backOrder
	 * 
	 * @param applianceId the appliance id for removing a backOrder
	 * @return true iff the backOrder could be removed
	 */
	public boolean removeBackOrder(String applianceId) {
		for (ListIterator iterator = appliancesOnBackOrder.listIterator(); iterator.hasNext();) {
			BackOrder backOrder = (BackOrder) iterator.next();
			String id = backOrder.getAppliance().getId();
			if (id.equals(applianceId)) {
				transactions.add(new Transaction("BackOrder Removed ", backOrder.getAppliance().getBrandName()));
				iterator.remove();
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets an iterator to a collection of selected transactions
	 * 
	 * @param date the date for which the transactions have to be retrieved
	 * @return the iterator to the collection
	 */
	public Iterator getTransactions(Calendar date) {
		List result = new LinkedList();
		for (Iterator iterator = transactions.iterator(); iterator.hasNext();) {
			Transaction transaction = (Transaction) iterator.next();
			if (transaction.onDate(date)) {
				result.add(transaction);
			}
		}
		return (result.iterator());
	}

	/**
	 * Getter for name
	 * 
	 * @return customer name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for phone number
	 * 
	 * @return phone number
	 */
	public String getPhone() {
		return phone;
	}


	/**
	 * Getter for id
	 * 
	 * @return customer id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Setter for name
	 * 
	 * @param newName customer's new name
	 */
	public void setName(String newName) {
		name = newName;
	}



	/**
	 * Setter for phone
	 * 
	 * @param newName customer's new phone
	 */
	public void setPhone(String newPhone) {
		phone = newPhone;
	}

	/**
	 * String form of the customer
	 * 
	 */
	@Override
	public String toString() {
		String string = "Customer name " + name + " id " + id + "phone " + phone;
		string += " borrowed: [";
		for (Iterator iterator = appliancesIssued.iterator(); iterator.hasNext();) {
			Appliance appliance = (Appliance) iterator.next();
			string += " " + appliance.getBrandName();
		}
		string += "] backOrders: [";
		for (Iterator iterator = appliancesOnBackOrder.iterator(); iterator.hasNext();) {
			BackOrder backOrder = (BackOrder) iterator.next();
			string += " " + backOrder.getAppliance().getBrandName();
		}
		string += "] transactions: [";
		for (Iterator iterator = transactions.iterator(); iterator.hasNext();) {
			string += (Transaction) iterator.next();
		}
		string += "]";
		return string;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * Checks whether the customer is equal to the one supplied
	 * 
	 * @param object the customer who should be compared
	 * @return true iff the customer ids match
	 */

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}
		Customer other = (Customer) object;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}
