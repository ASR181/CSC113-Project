public abstract class Room implements Payable {
	
	protected int Roomnumber;
	protected int days;
	protected Guest guest;
	
	public Room(int num, int days, Guest guest) {
		setRoomnumber(num);
		setDays(days);
		this.guest = guest;
	}
	
	public abstract double calculatePrice();
	
	public double getPaymentAmount(){
		return calculatePrice();
	}

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