import java.io.Serializable;

public class DishWasher extends Appliance implements Serializable {
	private static final long serialVersionUID = 1L;
  
  public DishWasher(String brandName, String modelName, double price){
    super(brandName, modelName, price); 

  }
  
  }
