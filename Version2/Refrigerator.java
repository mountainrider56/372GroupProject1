import java.io.Serializable;

public class Refrigerator extends Appliance implements Serializable {
	private static final long serialVersionUID = 1L;
	private double capacity ;
  
  public Refrigerator(String brandName, String modelName, String price, String capacity){
    super(brandName, modelName, Double.parseDouble(price)); 
    this.capacity = Double.parseDouble(capacity); 
 
  }
  
  public double getCapacity() {
  
    return capacity; 
  }
 
 

}
