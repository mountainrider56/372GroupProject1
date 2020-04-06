package ics372GroupProject1;

public class ClothWasher extends Appliance {
	private Repairplan repairPlan; // Need Repair Class 
	private double monthlyPayment;
	
	



	public double getMonthlyPayment() {
		return monthlyPayment;
	}

	// I don't think repair plan or monthly payment need to be inlcuded in the constructor.  Aaron
	public ClothWasher(String brandName, String model, int stock, double price) {
		super(brandName, model, stock, price);
		// TODO Auto-generated constructor stub
	}


	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}
	
	
}
