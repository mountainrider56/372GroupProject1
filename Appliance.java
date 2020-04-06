package ics372GroupProject1;

public class Appliance {
	
	protected String brandName;
	protected String model;
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	protected int stock;
	protected double price;
	
	
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}



	public Appliance(String brandName, String model, int stock, double price) {
		super();
		this.brandName = brandName;
		this.model = model;
		this.stock = stock;
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Appliance [brandName=" + brandName + ", model=" + model + ", stock=" + stock + ", price=" + price + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brandName == null) ? 0 : brandName.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + stock;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appliance other = (Appliance) obj;
		if (brandName == null) {
			if (other.brandName != null)
				return false;
		} else if (!brandName.equals(other.brandName))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (stock != other.stock)
			return false;
		return true;
	}


	
	
}
