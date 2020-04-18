public class RepairPlan {

<<<<<<< HEAD
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
	
=======
	private int cost;
	private Customer c;
	private Appliance a;
	
	public RepairPlan(int cost, Customer c, Appliance a) {
		super();
		this.cost = cost;
		this.c = c;
		this.a = a;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public Customer getC() {
		return c;
	}
	public void setC(Customer c) {
		this.c = c;
	}
	public Appliance getA() {
		return a;
	}
	public void setA(Appliance a) {
		this.a = a;
	}
	

>>>>>>> a3030ab5b639b7ac5bf63d41a71ed148d3387f7a
}
	
