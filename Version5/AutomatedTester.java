
import java.util.Random; 
/**
 * This class generates sample automated tests for the library system using
 * asserts.
 * 
 * @modelName Brahma Dathan
 *
 */
public class AutomatedTester {
	private Library library;

	/**
	 * Stores the Library object and invokes the test() method to test Customer
	 * creation.
	 * 
	 * @param library the Library object
	 */
	public AutomatedTester(Library library) {
		this.library = library;
		addCustomers();
		addWashers();
		addDryers();
		addFridges();
		addFurnaces();
		addStartingInventory();
	}

	/**
	 * Tests Customer creation.
	 */
	public void addCustomers() {
		
		// Generate a set of customers to add to the program.
		String[] names = { "Sam", "Jane", "Belle","Frank","Joe","John","Jill","Francis","Roma" };
		String[] phones = { "651-555-4444", "547-888-1558", "155-555-5555","651-222-4222","651-785-7508","651-758-5888",
				  "594-112-2310","542-432-3333","651-888-0010"};
		Customer[] customers = new Customer[9];
		for (int count = 0; count < names.length; count++) {
			customers[count] = library.addCustomer(names[count], phones[count]);
			assert customers[count].getName().equals(names[count]);
			assert customers[count].getPhone().equals(phones[count]);
		}
		
		
			
		}
	// Generate a set of Washerss to load into the program.
	public void addWashers()
	{
		// washers
		Appliance[] appliances = new Appliance[3];
		String[] brandnames = {"Samsung", "LG", "MayTag"};
		String[] modelnames = {"S1","S2","S3"};
		double[] prices = {555.55,333.33,223.22};
		double[] repairPlanCosts = {44.99,33.4,22.33};
		for (int count = 0; count < brandnames.length; count++) {
			appliances[count] = library.addModel("1", prices[count], brandnames[count],modelnames[count],repairPlanCosts[count],0,0);
		}
	}
	
	// Generate a set of Dryers to load into the program.
	public void addDryers() {
		// Appliances
		Appliance[] appliances = new Appliance[3];
		String[] brandnames = {"Samsung", "LG", "MayTag"};
		String[] modelnames = {"D1","D2","D3"};
		double[] prices = {555.55,333.33,223.22};
		double[] repairPlanCosts = {44.99,33.4,22.33};
		for (int count = 0; count < brandnames.length; count++) {
			appliances[count] = library.addModel("2", prices[count], brandnames[count],modelnames[count],repairPlanCosts[count],0,0);
			}
			
	}
	
	// Generate a set of Fridges to load into the program.
	public void addFridges() {
		// Appliances
		Appliance[] appliances = new Appliance[3];
		String[] brandnames = {"Samsung", "LG", "KitchenAid"};
		String[] modelnames = {"S2019A3412","LG32020A2","K20102_23A"};
		double[] prices = {555.55,333.33,223.22};
		int[] capacity = {1200,1000,900};
		for (int count = 0; count < brandnames.length; count++) {
			appliances[count] = library.addModel("5", prices[count], brandnames[count],modelnames[count],0,capacity[count],0);
			}
			
	}
	
	
	
	// Generate a set of Furnaces to load into the program.
	public void addFurnaces() {
		// Appliances
		Appliance[] appliances = new Appliance[3];
		String[] brandnames = {"Bryant", "Train", "Frank Heater"};
		String[] modelnames = {"B123A","T2019A1","F2019F1B"};
		double[] prices = {5505.55,3303.33,9223.22};
		double[] repairPlanCosts = {44.99,33.4,22.33};
		double[] heatOutputs = {10000,20000,12000};
		for (int count = 0; count < brandnames.length; count++) {
			appliances[count] = library.addModel("6", prices[count], brandnames[count],modelnames[count],repairPlanCosts[count],0,heatOutputs[count]);
			}
			
	}
	
	public void addStartingInventory() {
		
		Random r = new Random();
		
		for(int i=1; i < 13; i++) {
			
			// Generate Appliance Ids
			String id = "A"+i;
			// Set random quantity
			int qty = r.nextInt(5);
			// Update inventory
			
			library.addInventory(id, qty);	
		}
	}
	
	
	
}