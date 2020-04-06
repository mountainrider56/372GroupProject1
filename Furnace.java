package ics372GroupProject1;

public class Furnace extends Appliance {
	private int maxHeatOutput;



	public Furnace(String brandName, String model, int stock, double price,int maxHeatOutput) {
		super(brandName, model, stock, price);
		this.maxHeatOutput = maxHeatOutput;
		// TODO Auto-generated constructor stub
	}

	public int getMaxHeatOutput() {
		return maxHeatOutput;
	}

	public void setMaxHeatOutput(int maxHeatOutput) {
		this.maxHeatOutput = maxHeatOutput;
	}

	@Override
	public String toString() {
		return "Furnace [maxHeatOutput=" + maxHeatOutput + ", brandName=" + brandName + ", model=" + model + ", stock="
				+ stock + ", price=" + price + "]";
	}





	
	
}
