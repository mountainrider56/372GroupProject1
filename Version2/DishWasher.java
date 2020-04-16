import java.io.Serializable;

public class DishWasher extends Appliance implements Serializable {
	private static final long serialVersionUID = 1L;
  
  public DishWasher(String brandName, String modelName, String price){
    super(brandName, modelName, Double.parseDouble(price)); 

  }
  
  }
