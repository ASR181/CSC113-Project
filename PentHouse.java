public class PentHouse extends Suite { // inherent from class Room
	
// Constructor
	public PentHouse(int num, int days, Guest guest) {
		super(num, days, guest);
		setPricePNight(500);
	}
}