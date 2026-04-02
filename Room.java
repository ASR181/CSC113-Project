public abstract class Room implements Payable { // using Payable Interface
	
//Attributes
	protected int Roomnumber;
	protected int days;
	protected Guest guest;
	
// Constructor
	public Room(int num, int days, Guest guest) {
		setRoomnumber(num);
		setDays(days);
		this.guest = guest;
	}
	
// Abstract method
	public abstract double calculatePrice();
	
	public double getPaymentAmount(){ // price including taxes
		return calculatePrice() + (calculatePrice() * 0.15);
	}
// Setters and Getters
	public int getRoomnumber() {
		return Roomnumber;
	}

	public void setRoomnumber(int roomnumber) {
		Roomnumber = roomnumber;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	
}