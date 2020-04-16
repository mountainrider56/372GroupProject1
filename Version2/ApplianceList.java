
/**
 * 

 */
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The collection class for Appliance objects
 * 
 *
 */
public class ApplianceList implements Serializable {
    private static final long serialVersionUID = 1L;
    private List appliances = new LinkedList();
    private static ApplianceList applianceList;

    public static ApplianceList getApplianceList() {
		return applianceList;
	}

	/*
     * Private constructor for singleton pattern
     * 
     */
    private ApplianceList() {
    }

    /**
     * Supports the singleton pattern
     * 
     * @return the singleton object
     */
    public static ApplianceList instance() {
        if (applianceList == null) {
            return (applianceList = new ApplianceList());
        } else {
            return applianceList;
        }
    }

    /**
     * Checks whether a appliance with a given appliance id exists.
     * 
     * @param applianceId
     *            the id of the appliance
     * @return true iff appliance exists
     * 
     */
    public Appliance search(String applianceId) {
        for (Iterator iterator = appliances.iterator(); iterator.hasNext();) {
            Appliance appliance = (Appliance) iterator.next();
            if (appliance.getId().equals(applianceId)) {
                return appliance;
            }
        }
        return null;
    }

    /**
     * Inserts a appliance into the collection
     * 
     * @param appliance
     *            the appliance to be inserted
     * @return true iff the appliance could be inserted. Currently always true
     */
    public boolean insertAppliance(Appliance appliance) {
        appliances.add(appliance);
        return true;
    }

    /**
     * String form of the collection
     * 
     */
    @Override
    public String toString() {
        return appliances.toString();
    }
}
