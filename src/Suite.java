package Project_First;

public class Suite extends Room {
	public Suite(int n, int d, Guest g) {
		super(n,d,g);
	}
	@Override
	public double calculatePrice() {
		return (days*250);
	}
}
