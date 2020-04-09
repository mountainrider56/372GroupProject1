package GroupProjectOne;

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
	private static final String Customer_STRING = "C";
	private List transactions = new LinkedList();

	public Customer(String name, String phone) {
		this.name = name;
		this.phone = phone;
		id = Customer_STRING + (CustomerIdServer.instance()).getId();
	}
/**
	public Iterator getTransactions(Calendar dateStart, Calendar dateEnd) {
		List result = new LinkedList();
		for (Iterator iterator = transactions.iterator(); iterator.hasNext();) {
			Transaction transaction = (Transaction) iterator.next();
			if (transaction.onDateStart(dateStart) && transaction.onDateEnd(dateEnd)) {
				result.add(transaction);
			}
		}
		return (result.iterator());
	}
**/

	public String getName() {
		return name;
	}
	
	public String getPhone() {
		return phone;
	}

	public String getCustomerID() {
		return id;
	}

	public void setName(String newName) {
		name = newName;
	}

	public void setPhone(String newPhone) {
		phone = newPhone;
	}

	public boolean equals(String id) {
		return this.id.equals(id);
	}

	@Override
	public String toString() {
		String string = "Customer Name: " + name + ", id: " + id + ", Phone number: " + phone;
		return string;
	}

}
