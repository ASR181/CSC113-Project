public class StandardRoom extends Room { // inherent from class Room
	
// Attributes
	private double pricePNight = 100;
	
// Constructor
	public StandardRoom(int num, int days, Guest guest) {
		super(num,days,guest);
	}

	// Overriding Polymorphic method
	public double calculatePrice() {
		return (days*pricePNight);
	}
	
// Setters & Getters
	public void setPricePNight(double price) {
		pricePNight = price;
	}
	
	public double getPricePNight() {
		return pricePNight;
	}
}
