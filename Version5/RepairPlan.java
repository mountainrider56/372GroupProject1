public class RepairPlan {

	private Customer customer;
	private Appliance appliance;
//	public double balance ; 
	
	public RepairPlan(Customer customer, Appliance appliance) {

		this.customer = customer;
		this.appliance = appliance;
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
	
}
	
