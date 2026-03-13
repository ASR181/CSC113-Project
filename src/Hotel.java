
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
	
	public int findRoom(Room target, int lower, int higher) {
		
		if(lower > higher)
			return -1;
		else {
			
			int mid = (lower + higher) / 2;
			
			if(rooms[mid] == target)
				return mid;
			else if (rooms[mid] < target)
				return findRoom(target, mid + 1, higher);
			else
				return findRoom(target, lower, mid - 1);
			
		}
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
