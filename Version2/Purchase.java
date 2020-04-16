import java.io.Serializable;
import java.util.Calendar;

/**
 * Represents a single Transaction (purchase, renew, etc.)
 * 
 * @modelName Brahma Dathan
 *
 */
public class Purchase implements Serializable {
    private static final long serialVersionUID = 1L;
    private double price ;
	private String customerId;
	private String applianceId; 


    public Purchase(String applianceId, String customerId, int quantity) {
      this.applianceId = applianceId; 
      this.customerId = customerId; 
      
      this.price = quantity; // something goes here with multiplication - price for given applianceId
    }




    /**
     * String form of the transaction
     * 
     */
 
}
