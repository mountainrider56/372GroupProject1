
/**
 * 
 * @modelName 
 * @Copyright (c) 2010
 
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The modelNames do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The collection class for Customer objects
 * 
 *
 */
public class CustomerList implements Serializable {
    private static final long serialVersionUID = 1L;
    private List customers = new LinkedList();
    private static CustomerList customerList;

    /*
     * Private constructor for singleton pattern
     * 
     */
    private CustomerList() {
    }

    /**
     * Supports the singleton pattern
     * 
     * @return the singleton object
     */
    public static CustomerList instance() {
        if (customerList == null) {
            return (customerList = new CustomerList());
        } else {
            return customerList;
        }
    }

    /**
     * Checks whether a customer with a given customer id exists.
     * 
     * @param customerId
     *            the id of the customer
     * @return true iff customer exists
     * 
     */
    public Customer search(String customerId) {
        for (Iterator iterator = customers.iterator(); iterator.hasNext();) {
            Customer customer = (Customer) iterator.next();
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    /**
     * Inserts a customer into the collection
     * 
     * @param customer
     *            the customer to be inserted
     * @return true iff the customer could be inserted. Currently always true
     */
    public boolean insertCustomer(Customer customer) {
        customers.add(customer);
        return true;
    }

 

    /**
     * String form of the collection
     * 
     */
    @Override
    public String toString() {
        return customers.toString();
    }
}
