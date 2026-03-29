package Project_First;

public abstract class Room implements Payable {
	protected int Roomnumber;
	protected int days;
	protected Guest guest;
	
	public Room(int n, int d, Guest g) {
		setRoomnumber(n);
		setDays(d);
		this.guest = g;
	}
	public abstract double calculatePrice();
	
	@Override
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
