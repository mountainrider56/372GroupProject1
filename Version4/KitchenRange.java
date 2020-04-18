import java.io.Serializable;

public class KitchenRange extends Appliance implements Serializable {
	private static final long serialVersionUID = 1L;
  
  public KitchenRange(String brandName, String modelName, double price){
    super(brandName, modelName, price); 

  }
  
  }
