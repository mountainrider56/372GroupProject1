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
		String[] addresses = { "a1", "a2", "a3" ,"a4","a5","a6","a7","a8","a9"};
		String[] phones = { "651-555-4444", "547-888-1558", "155-555-5555","651-222-4222","651-785-7508","651-758-5888",
				  "594-112-2310","542-432-3333","651-888-0010"};
		Member[] members = new Member[9];
		for (int count = 0; count < names.length; count++) {
			members[count] = library.addMember(names[count], addresses[count], phones[count]);
			assert members[count].getName().equals(names[count]);
			assert members[count].getAddress().equals(addresses[count]);
			assert members[count].getPhone().equals(phones[count]);
		}
	}
	
	// Method populates the library with starting appliance data. 
	public void addAppliances(){
		String[] brand = { "Sam", "Jane", "Belle","Frank","Joe","John","Jill","Francis","Roma" };
	}
}
