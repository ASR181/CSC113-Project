public class PentHouse extends Suite {
	
	protected double pricePNight = 500;
	
	public PentHouse(int num, int days, Guest guest) {
		super(num, days, guest);
	}
	
	public double calculatePrice() {
		return (days*pricePNight);
	}
}