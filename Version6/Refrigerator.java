import java.io.Serializable;

public class Refrigerator extends Appliance implements Serializable {
	private static final long serialVersionUID = 1L;
	private double capacity ;
  
  public Refrigerator(String brandName, String modelName, double price, double capacity){
    super(brandName, modelName, price); 
    this.capacity = capacity; 
 
  }
  
 

}
