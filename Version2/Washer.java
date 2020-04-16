import java.io.Serializable;

public class Washer extends Appliance implements Serializable {
	private static final long serialVersionUID = 1L;
	private double monthlyRepairPlanCost ;
  
  public Washer(String brandName, String modelName, String price, String monthlyRepairPlanCost){
    super(brandName, modelName, Double.parseDouble(price)); 
    this.monthlyRepairPlanCost = Double.parseDouble(monthlyRepairPlanCost); 
 
  }
  
  public double getMonthlyRepairPlanCost() {
  
    return monthlyRepairPlanCost; 
  }
 
 

}
