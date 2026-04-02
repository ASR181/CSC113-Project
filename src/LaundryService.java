
public class LaundryService implements Payable { // using Payable interface
	
// Attributes
	private int numOfItem;
	private double cost;
	
// Helper method used within constructor to choose cost value
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
	
// Constructor
	public LaundryService(int numOfItem, String type) {
		
		this.numOfItem = numOfItem;
		this.cost = pickCost(type);
	}

// Overriding method from Interface Payable	
	public double getPaymentAmount() {
		
		return cost * numOfItem;
	}
}
