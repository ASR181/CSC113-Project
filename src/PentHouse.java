package Project_First;

public class PentHouse extends Suite {
	public PentHouse(int n, int d, Guest g) {
		super(n,d,g);
	}
	@Override
	public double calculatePrice() {
		return (days*500);
	}
}
