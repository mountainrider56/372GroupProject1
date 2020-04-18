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
		String[] names = { "n1", "n2", "n3" , "n4" , "n5" };
		String[] phones = { "p1", "p2", "p3" , "p4", "p5" };		
		
		Customer[] customers = new Customer[5];
		for (int count = 0; count < names.length; count++) {
			customers[count] = library.addCustomer(names[count], phones[count]);
			assert customers[count].getName().equals(names[count]);
			assert customers[count].getPhone().equals(phones[count]);
		}
		
		// Test Add Model
		String[] brandnames = {"B1", "B2", "B3", "B4", "B5" , "B6",};
		String[] modelnames = {"M1" , "M2" , "M3" , "M4" , "M5"};
		String[] prices = {"5.0","10.0","15.0", "20.0", "25.0"};
		
		
		
		// Test Add Inventory
		
		
		
		// Test purchase 
		
		
		
		// Test enroll repair plan 
		
		
		// test withdraw repair plan 
		
		
		
		// test charge repair plan 
		
		
		
		// test print revenue 
		
		
		
		// test list appliances
		
		// test list all users in repair plans
		
		
		// test list customers
		
		
		// test list all backorders. 
		
//		Appliance[] appliances = new Appliance[5];
//		for (int count = 0; count < names.length; count++) {
//			customers[count] = library.addCustomer(names[count], phones[count]);
//			assert customers[count].getName().equals(names[count]);
//			assert customers[count].getPhone().equals(phones[count]);
//		}
				
	}
}
