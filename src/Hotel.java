public class Hotel {
	private Room [] rooms;
	private String name;
	private int count;
	
	public Hotel(String name, int size) {
		
		setName(name);
		rooms = new Room[size];
		this.count = 0;
		
	}
	
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
	
	public int findRoom(int RoomNumber, int index) {
		
		if (index >= count) return -1;
		if(rooms[index].getRoomnumber() == RoomNumber) {
			return index;
		}
		return findRoom(RoomNumber, index + 1);
	}
	
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
	
	public double getTotalRevnue(int RoomNumber, int index) {
		if(index >= count) return 0;
		if(rooms[index].getRoomnumber() == RoomNumber) {
			return rooms[index].calculatePrice();		
		}
		return getTotalRevnue(RoomNumber, index+1);
	}
	
	public Room getRoom(int i) {
		return rooms[i];
	}
	
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