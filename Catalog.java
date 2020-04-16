
/**
 * 
 * @modelName 
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
public class Catalog implements Serializable {
    private static final long serialVersionUID = 1L;
    private List appliances = new LinkedList();
    private static Catalog catalog;

    /*
     * Private constructor for singleton pattern
     * 
     */
    private Catalog() {
    }

    /**
     * Supports the singleton pattern
     * 
     * @return the singleton object
     */
    public static Catalog instance() {
        if (catalog == null) {
            return (catalog = new Catalog());
        } else {
            return catalog;
        }
    }

    /**
     * Checks whether a appliance with a given appliance id exists.
     * 
     * @param applianceId
     *            the id of the appliance
     * @return true iff the appliance exists
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
     * Removes a appliance from the catalog
     * 
     * @param applianceId
     *            appliance id
     * @return true iff appliance could be removed
     */
    public boolean removeAppliance(String applianceId) {
        Appliance appliance = search(applianceId);
        if (appliance == null) {
            return false;
        } else {
            return appliances.remove(appliance);
        }
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
     * Returns an iterator to all appliances
     * 
     * @return iterator to the collection
     */
    public Iterator getAppliances() {
        return appliances.iterator();
    }

    /**
     * String form of the collection
     * 
     */
    public String toString() {
        return appliances.toString();
    }
}
