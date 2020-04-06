package ics372GroupProject1;

public class driver {
	
	
	// just a test class at the moment
	
	public static void main(String[] args) {
		Appliance a1 = new Appliance("Maytag","MT343", 2, 599.99);
		
		Appliance a2 = new Appliance("Samsung","SuperM1",30,569.99);
		
		Furnace f1 = new Furnace("Bryant","B34",1,6000,3000);
		
		System.out.println(f1.toString());
	}
}
