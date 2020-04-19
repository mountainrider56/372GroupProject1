
import java.io.Serializable;

public class RepairPlan implements Serializable   {

	private Customer customer;
	private Appliance appliance;
	private static final long serialVersionUID = 1L;
	
	public RepairPlan(Customer customer, Appliance appliance) {

		this.customer = customer;
		this.appliance = appliance;
		customer.setEnrolledInRepairPlanStatus(true); 
	}

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Appliance getAppliance() {
		return appliance;
	}
	public void setAppliance(Appliance appliance) {
		this.appliance = appliance;
	}
	
	
	/**
	 * String form of the appliance
	 * 
	 */
	public String toString() {
		return "Customer Name :" + customer.getName() +  ", phone : " + customer.getPhone() + ", Id : " + customer.getId() + "\n"
				+ ", Account Balance : " + customer.getAccountBalance() 
				+ "-----" + " Appliance Brand : " + appliance.getBrandName() + ", Model :" + appliance.getModelName() + "\n" + "\n";
	}
	
}
	
