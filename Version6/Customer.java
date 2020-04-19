
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
	
	private List appliancesOnRepairPlanList = new LinkedList(); 
	
	private double accountBalance ; 
	
	private boolean enrolledInRepairPlanStatus ; 

	/**
	 * Represents a single customer
	 * 
	 * @param name    name of the customer
	 * @param phone   phone number of the customer
	 */
	public Customer(String name, String phone) {
		this.name = name;
		this.phone = phone;
		accountBalance = 0.0; 
		id = CUSTOMER_STRING + (CustomerIdServer.instance()).getId();
	}

	/**
	 * Stores the appliance as issued to the customer
	 * 
	 * @param appliance the appliance to be issued
	 * @return true iff the appliance could be marked as issued. always true currently
	 */
	public boolean issue(Appliance appliance, double quantity) {
		if (appliancesIssued.add(appliance)) {
			transactions.add(new Transaction("Appliance issued ", appliance.getBrandName()));
			return true;
		}
		return false;
	}
	
	/**
	 * add appliance to repair plan list 
	 */
	public boolean addApplianceToRepairPlanList(RepairPlan repairPlan) {
		if (appliancesOnRepairPlanList.add(repairPlan)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Places a backOrder for the appliance
	 * 
	 * @param backOrder the appliance to be placed a backOrder
	 */
	public void placeRepairPlan(RepairPlan repairPlan) {
		appliancesOnRepairPlanList.add(repairPlan);
	}
	
	/**
	 * Removes a RepairPlan

	 */
	public boolean removeRepairPlan(String applianceId) {
		for (ListIterator iterator = appliancesOnRepairPlanList.listIterator(); iterator.hasNext();) {
			RepairPlan repairPlan = (RepairPlan) iterator.next();
			String id = repairPlan.getAppliance().getId();
			if (id.equals(applianceId)) {
				iterator.remove();
				return true;
			}
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
	 * Gets an iterator to the issued appliances
	 * 
	 * @return Iterator to the collection of issued appliances
	 */
	public Iterator getAppliancesOnRepairPlanList() {
		return (appliancesOnRepairPlanList.listIterator());
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
	
	public double getAccountBalance() {
		return accountBalance; 
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
	
	public void addToAccountBalance(double amount) {
		accountBalance+= amount;
	}
	
	public boolean checkEnrollInServicePlan() {
		enrolledInRepairPlanStatus = appliancesOnRepairPlanList.isEmpty();
		 return enrolledInRepairPlanStatus; 
	}
	
	public void setEnrolledInRepairPlanStatus(boolean status) {
		enrolledInRepairPlanStatus = status; 
	}
	



	/**
	 * String form of the customer
	 * 
	 */
	@Override
	public String toString() {
		String string = "Customer name : " + name + " -- id : " + id + " -- phone# " + phone;
		string += "\n";
		string += "purchaseIssued(Brand Name : Model Name (applianceId)): [";
		for (Iterator iterator = appliancesIssued.iterator(); iterator.hasNext();) {
			Appliance appliance = (Appliance) iterator.next();
			string += " " + appliance.getBrandName();
			string += " : " + appliance.getModelName();
			string += " ( " + appliance.getId() + " ) ";
			string += " , ";
		}
		string += "]\n";
		string += "backOrders(Brand Name : Model Name (applianceId)): [";
		for (Iterator iterator = appliancesOnBackOrder.iterator(); iterator.hasNext();) {
			BackOrder backOrder = (BackOrder) iterator.next();
			string += " " + backOrder.getAppliance().getBrandName();
			string += " : " + backOrder.getAppliance().getModelName();
			string += " ( " + backOrder.getAppliance().getId() + " ) ";;
			string += " , ";
		}
		string += "]\n";
		string += "Account Balance : " + accountBalance;
		string += "\n";
		string += "Enrolled in a service plan Status : " + enrolledInRepairPlanStatus;
		string += "\n";	
		// Loop through all enrollment plans a customer has.
		string += "Current Repair Plans : ";
		for (Iterator iterator = appliancesOnRepairPlanList.iterator(); iterator.hasNext();) {
			RepairPlan repairPlan = (RepairPlan) iterator.next();
			string += " " + repairPlan.getAppliance();
			string += " , ";
		}
		string += "\n";
		string += "\n";

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
