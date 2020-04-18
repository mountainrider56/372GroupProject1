
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The collection class for Repair Plan objects
 * 
 *
 */
public class repairPlanList implements Serializable {
    private static final long serialVersionUID = 1L;
    private List repair_plans = new LinkedList();
    private static repairPlanList repairPlanList;

    public static repairPlanList getRepairList() {
		return repairPlanList;
	}

	/*
     * Private constructor for singleton pattern
     * 
     */
    private repairPlanList() {
    }

    /**
     * Supports the singleton pattern
     * 
     * @return the singleton object
     */
    public static repairPlanList instance() {
        if (repairPlanList == null) {
            return (repairPlanList = new repairPlanList());
        } else {
            return repairPlanList;
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
    public repairPlanList search(String applianceId) {
        for (Iterator iterator = repair_plans.iterator(); iterator.hasNext();) {
            Appliance appliance = (Appliance) iterator.next();
            if (appliance.getId().equals(applianceId)) {
                return repairPlanList;
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
    public boolean insertRepairPlan(RepairPlan repair_plan) {
    	repair_plans.add(repair_plan);
        return true;
    }

    /**
     * String form of the collection
     * 
     */
    @Override
    public String toString() {
        return repair_plans.toString();
    }
}
