import java.io.Serializable;

public class Washer extends Appliance implements Serializable {
	private static final long serialVersionUID = 1L;

  
	  public Washer(String brandName, String modelName, double price, double monthlyRepairPlanCost){
		    super(brandName, modelName, price); 
		    super.monthlyRepairPlanCost = monthlyRepairPlanCost; 
		 
	  }

 
 

}
