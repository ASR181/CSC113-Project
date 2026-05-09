package application;
public class Suite extends Room { // inherent from class Room
	
// Attributes
	protected double pricePNight = 250;
	protected double VIPService = 500;

// Constructor
	public Suite(int num, int days, Guest guest) {
		super(num,days,guest);
	}
	
// Overriding Polymorphic method
	public double calculatePrice() {
		return (days*pricePNight + VIPService);
	}
	
// Setters and Getters
	public void setPricePNight(double price) {
		pricePNight = price;
	}
	
	public double getPricePNight() {
		return pricePNight;
	}
}
