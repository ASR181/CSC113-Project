public class Suite extends Room {
	
	protected double pricePNight = 250;
	
	public Suite(int num, int days, Guest guest) {
		super(num,days,guest);
	}
	
	public double calculatePrice() {
		return (days*pricePNight);
	}
	

	public void setPricePNight(double price) {
		pricePNight = price;
	}
	
	public double getPricePNight() {
		return pricePNight;
	}
}
