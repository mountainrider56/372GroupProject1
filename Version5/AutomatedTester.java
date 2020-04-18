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
		test();
	}

	/**
	 * Tests Customer creation.
	 */
	public void test() {
		String[] names = { "Sam", "Jane", "Belle","Frank","Joe","John","Jill","Francis","Roma" };
		String[] phones = { "651-555-4444", "547-888-1558", "155-555-5555","651-222-4222","651-785-7508","651-758-5888",
				  "594-112-2310","542-432-3333","651-888-0010"};
		Customer[] customers = new Customer[9];
		for (int count = 0; count < names.length; count++) {
			customers[count] = library.addCustomer(names[count], phones[count]);
			assert customers[count].getName().equals(names[count]);
			assert customers[count].getPhone().equals(phones[count]);
		}
		
		// Appliances
		Appliance[] appliances = new Appliance[3];
		String[] brandnames = {"Samsung", "LG", "MayTag"};
		String[] modelnames = {"S1","S2","S3"};
		double[] prices = {555.55,333.33,223.22};
		double[] repairPlanCosts = {44.99,33.4,22.33};
		for (int count = 0; count < brandnames.length; count++) {
			appliances[count] = library.addModel("washerType", prices[count], brandnames[count],modelnames[count],repairPlanCosts[count],0,0);}
		}
}
