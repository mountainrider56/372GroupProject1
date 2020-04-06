package ics372GroupProject1;

public class ClothDryer extends Appliance {
	private Repairplan repairPlan;
	private double monthlyPayment;
	
	// I don't think repair plan or monthly payment need to be inlcuded in the constructor.  Aaron
	public ClothDryer(String brandName, String model, int stock, double price) {
		super(brandName, model, stock, price);
		// TODO Auto-generated constructor stub
	}
	
	public double getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}
	
	
}
