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
	}

	/**
	 * Tests Member creation.
	 */
	public void test() {
		String[] names = { "n1", "n2", "n3" };
		String[] addresses = { "a1", "a2", "a3" };
		String[] phones = { "p1", "p2", "p3" };
		Member[] members = new Member[3];
		for (int count = 0; count < names.length; count++) {
			members[count] = library.addMember(names[count], addresses[count], phones[count]);
			assert members[count].getName().equals(names[count]);
			assert members[count].getAddress().equals(addresses[count]);
			assert members[count].getPhone().equals(phones[count]);
		}
	}
}
