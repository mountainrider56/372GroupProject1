public class RepairPlan {

	private Customer customer;
	private Appliance appliance;
//	public double balance ; 
	
	public RepairPlan(Customer customer, Appliance appliance) {

		this.customer = customer;
		this.appliance = appliance;
		customer.setEnrolledInRepairPlanStatus(true); 
//		this.balance = 0 ; 
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
	
//	public double getBalance() {
//		return balance;
//	}
//	public void setBalance(double balance) {
//		this.balance = balance;
//	}
	
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
	
