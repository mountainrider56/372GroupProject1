
public class RepairPlan {

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
	

}
