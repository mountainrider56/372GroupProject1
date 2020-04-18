import java.io.Serializable;

public class Furnace extends Appliance implements Serializable {
	private static final long serialVersionUID = 1L;
	private double heatOutput ;
  
  public Furnace(String brandName, String modelName, double price, double heatOutput){
    super(brandName, modelName, price); 
    this.heatOutput = heatOutput; 
 
  }
  
 

}
