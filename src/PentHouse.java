public class PentHouse extends Suite { // inherent from class Room
	
// Attributes
	protected double pricePNight = 500;
	
// Constructor
	public PentHouse(int num, int days, Guest guest) {
		super(num, days, guest);
	}
	
// Overriding Polymorphic method
	public double calculatePrice() {
		return (days*pricePNight);
	}
}