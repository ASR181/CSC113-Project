
public class LaundryService implements Payable {
	
	private int numOfItem;
	private double cost;
	
	private int pickCost(String type) {
		switch(type) {
		
		case "shirt":
			return 30;
		case "pants":
			return 20;
		case "jacket":
			return 50;
		case "shoes":
			return 10;
			
		}
		return 0;
	}
	
	public LaundryService(int numofItem, String type) {
		
		this.numOfItem = numOfItem;
		this.cost = pickCost(type);
		
	}
	
	public double getPaymentAmount() {
		
		return cost * numOfItem;
	}
}


