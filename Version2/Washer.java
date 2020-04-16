import java.io.Serializable;

public class Washer extends Appliance implements Serializable {
	private static final long serialVersionUID = 1L;
	private double monthlyRepairPlanCost ;
  
  public Washer(brandName, modelName, price, monthlyRepairPlanCost){
    super(brandName, modelName, Double.parseDouble(price)); 
    this.monthlyRepairPlanCost = Double.parseDouble(monthlyRepairPlanCost); 
 
  }
  
  public double getMonthlyRepairPlanCost() {
  
    return monthlyRepairPlanCost; 
  }
 
 

}
