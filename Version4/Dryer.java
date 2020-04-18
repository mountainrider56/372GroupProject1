import java.io.Serializable;

public class Dryer extends Appliance implements Serializable {
	private static final long serialVersionUID = 1L;

  
  public Dryer(String brandName, String modelName, double price, double monthlyRepairPlanCost){
    super(brandName, modelName, price); 
    super.monthlyRepairPlanCost = monthlyRepairPlanCost; 
 
  } 

}
