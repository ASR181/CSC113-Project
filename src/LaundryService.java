
public class LaundryService implements Payable {
	
	private int numOfItem;
	private double cost;
	
	private int pickCost(String type) {
		
		if(type.equalsIgnoreCase("shirt"))
			return 30;
		else if (type.equalsIgnoreCase("pants"))
			return 20;
		else if (type.equalsIgnoreCase("jacket"))
			return 50;
		else if(type.equalsIgnoreCase("shoes"))
			return 10;
		else
			return 0;
	}
	
	public LaundryService(int numOfItem, String type) {
		
		this.numOfItem = numOfItem;
		this.cost = pickCost(type);
	}
	
	public double getPaymentAmount() {
		
		return cost * numOfItem;
	}
}
