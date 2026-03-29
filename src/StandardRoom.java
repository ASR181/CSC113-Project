package Project_First;

public class StandardRoom extends Room {
	public StandardRoom(int n, int d, Guest g) {
		super(n,d,g);
	}
	@Override
	public double calculatePrice() {
		return (days*100);
	}
}
