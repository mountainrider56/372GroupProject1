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
		String[] names = { "n1", "n2", "n3" };
		String[] phones = { "p1", "p2", "p3" };
		Customer[] customers = new Customer[3];
		for (int count = 0; count < names.length; count++) {
			customers[count] = library.addCustomer(names[count], phones[count]);
			assert customers[count].getName().equals(names[count]);
			assert customers[count].getPhone().equals(phones[count]);
		}
		
		// Appliances
				Appliance[] appliances = new Appliance[3];
				String[] brandnames = {"Samsung", "LG", "MayTag"};
				String[] modelnames = {"S1","S2","S3"};
				String[] prices = {"555.55","333.33","223.22"};
				
	}
}
