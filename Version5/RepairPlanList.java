import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * The collection class for RepairPlan objects
 * 
 *
 */
public class RepairPlanList implements Serializable {
    private static final long serialVersionUID = 1L;
    private static List repairPlans = new LinkedList();
    private static RepairPlanList repairPlanList;

    /*
     * Private constructor for singleton pattern
     * 
     */
    private RepairPlanList() {
    }

    /**
     * Supports the singleton pattern
     * 
     * @return the singleton object
     */
    public static RepairPlanList instance() {
        if (repairPlanList == null) {
            return (repairPlanList = new RepairPlanList());
        } else {
            return repairPlanList;
        }
    }

    /**
     * Checks whether a repairPlan with a given repairPlan id exists.
     * 
     * @param repairPlanId
     *            the id of the repairPlan
     * @return true iff repairPlan exists
     * 
     */
//    public RepairPlan search(RepairPlan repairPlan) {
//        for (Iterator iterator = repairPlans.iterator(); iterator.hasNext();) {
//            RepairPlan repairPlan = (RepairPlan) iterator.next();
//            if (repairPlan.getId().equals(repairPlanId)) {
//                return repairPlan;
//            }
//        }
//        return null;
//    }

    /**
     * Inserts a repairPlan into the collection
     * 
     * @param repairPlan
     *            the repairPlan to be inserted
     * @return true iff the repairPlan could be inserted. Currently always true
     */
    public boolean insertRepairPlan(RepairPlan repairPlan) {
        repairPlans.add(repairPlan);
        return true;
    }
    
    /**
     * Removes a appliance from the catalog
     * 
     * @param applianceId
     *            appliance id
     * @return true iff appliance could be removed
     */
    public boolean withdrawRepairPlan(RepairPlan repairPlan) {
        	return repairPlans.remove(repairPlan) ; 

    }
    

    
    /**
     * Returns an iterator to all appliances
     * 
     * @return iterator to the collection
     */
    public Iterator getRepairPlans() {
        return repairPlans.iterator();
    }
    

    /**
     * String form of the collection
     * 
     */
    @Override
    public String toString() {
        return repairPlans.toString();
    }


}