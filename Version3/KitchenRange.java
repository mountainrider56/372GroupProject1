import java.io.Serializable;

public class KitchenRange extends Appliance implements Serializable {
	private static final long serialVersionUID = 1L;
  
  public KitchenRange(String brandName, String modelName, String price){
    super(brandName, modelName, Double.parseDouble(price)); 

  }
  
  }
