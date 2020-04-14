/**
 * This class generates sample automated tests for the library system using
 * asserts.
 * 
 * @author Brahma Dathan
 *
 */
public class AutomatedTester {
	private Library library;

	/**
	 * Stores the Library object and invokes the test() method to test Member
	 * creation.
	 * 
	 * @param library the Library object
	 */
	public AutomatedTester(Library library) {
		this.library = library;
		test();
		addAppliances();
	}

	/**
	 * Tests Member creation.
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
	}
	
	// Method populates the library with starting appliance data. 
	public void addAppliances(){
		String[] brandName = { "Samsung", "MayTag", "KitchenAide","LG" };
		Double[] price = {599.55,699.99,499.99,555.55};
		Appliance[] appliances = new Appliance[5];
		for (int count = 0; count < brandName.length; count++) {
			appliances[count] = library.addAppliance(appliances);
			assert appliances[count].getBrandName().equals(appliances[count]);
			//assert appliances[count].getPrice().equals(appliances[count]);
		}
		
	}
}
