package ics372GroupProject1;

public class Refridgerator extends Appliance {
	private int capacity;



	public Refridgerator(String brandName, String model, int stock, double price) {
		super(brandName, model, stock, price);
		this.capacity = capacity;
		// TODO Auto-generated constructor stub
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
	
}
