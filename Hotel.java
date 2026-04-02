public class Hotel {

// Attributes
	private Room [] rooms;
	private String name;
	private int count;

	// Constructor
	public Hotel(String name, int size) {
		
		setName(name);
		rooms = new Room[size];
		this.count = 0;
		
	}
	
// Method for adding guest to room
	public boolean addReservation(int num, int days, Guest guest, String type) {
		
		if (count < rooms.length) {
			
			if(type.equalsIgnoreCase("suite"))
				rooms[count++] = new Suite(num, days, guest);
			else if(type.equalsIgnoreCase("PentHouse"))
				rooms[count++] = new PentHouse(num, days, guest);
			else
				rooms[count++] = new StandardRoom(num, days, guest);
			return true;
				}
		return false;
		
	}
	
// finding room using recursion
	public int findRoom(int RoomNumber, int index) {
		
		if (index >= count) return -1;
		if(rooms[index].getRoomnumber() == RoomNumber) {
			return index;
		}
		return findRoom(RoomNumber, index + 1);
	}
	
// end current reservation
	public boolean removeReservation(int RoomNumber) {
		
		int i = this.findRoom(RoomNumber, 0);
		
		if(i == -1)
			return false;
		
		for(int j = i; j < count - 1; j++) {
			rooms[j] = rooms[j + 1];
				}
			rooms[--count] = null;
			return true;
		
		
	}
	
// gets total payment for specific room using it's number
	public double getRoomRevnue(int RoomNumber, int index) {
		if(index >= count)
			return 0;
		if(rooms[index].getRoomnumber() == RoomNumber) {
			return rooms[index].getPaymentAmount();		
		}
		return getRoomRevnue(RoomNumber, index+1);
	}
	
	public double getTotalRevnue() { // to get total revenue using for loop
		double sum = 0;
		for(int i = 0; i < count; i++) {
			sum += rooms[i].getPaymentAmount();
		}
		return sum;
	}
	
// Setters and Getters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}