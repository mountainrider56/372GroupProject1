public class RepairPlan {

	private Customer customer;
	private Appliance appliance;
	
	public RepairPlan(Customer customer, Appliance appliance) {

		this.customer = customer;
		this.appliance = appliance;
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
	
}
	
