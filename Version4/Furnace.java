import java.io.Serializable;

public class Furnace extends Appliance implements Serializable {
	private static final long serialVersionUID = 1L;
	private double heatOutput ;
  
  public Furnace(String brandName, String modelName, String price, String heatOutput){
    super(brandName, modelName, Double.parseDouble(price)); 
    this.heatOutput = Double.parseDouble(heatOutput); 
 
  }
  
 

}
