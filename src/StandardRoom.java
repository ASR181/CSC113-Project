public class StandardRoom extends Room {
	
	private double pricePNight = 100;
	
	public StandardRoom(int num, int days, Guest guest) {
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
