
public class Hotel {
	
	private Room [] rooms;
	private String name;
	private int count;
	
	public Hotel(String name, int size) {
		
		setName(name);
		rooms = new Room[size];
		
	}
	
	public boolean addRoom(Room room) {
		
		if (count < rooms.length)
			rooms[count++] = new Room(room);
		
	}
	
	public int findRoom(Room room) {
		
		for(int i = 0; i < count; i++) {
			
			if (room.getGuest().equals(rooms[i].getGuest())) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean removeRoom(Room room) {
		
		int i = this.findRoom(room);
		
		if(i == -1)
			return false;
		
		for(int j = i; j < count - 1; j++) {
			rooms[j] = rooms[j + 1];
			rooms[--count] = null;
			return true;
		}
		
	}
	
	public double getTotalRevnue(int roomNumber) {
		
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
