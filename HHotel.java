public class Hotel {

    // Attributes head & tail instead of array
    private Node head;    // first room 
    private Node tail;    // last room 
    private String name;
    private int count;

    // Constructor
    public Hotel(String name, int size) {
        setName(name);
        head = tail = null;              
        count = 0;
    }

   

    // addReservation 
    public boolean addReservation(int num, int days, Guest guest, String type) {

        // Create the  Room type
        Room newRoom;
        if (type.equalsIgnoreCase("suite"))
            newRoom = new Suite(num, days, guest);
        else if (type.equalsIgnoreCase("PentHouse"))
            newRoom = new PentHouse(num, days, guest);
        else
            newRoom = new StandardRoom(num, days, guest);

        Node newNode = new Node(newRoom);

        // insertAtBack 
        if (head == null)
            head = tail = newNode;
        else {
            tail.setNext(newNode);   
            tail = newNode;          
        }
        count++;
        return true;
    }

    // Helper: get the Node at a specific index 
    private Node getNodeAt(int index) {
        Node current = head;
        for (int i = 0; i < index; i++)
            current = current.getNext();
        return current;
    }

    // findRoom 
    public int findRoom(int RoomNumber, int index) {
        if (index >= count) return -1;

        // Get node at current index and cast data to Room
        Room room = (Room) getNodeAt(index).getData();

        if (room.getRoomnumber() == RoomNumber)
            return index;

        return findRoom(RoomNumber, index + 1);
    }

    // removeReservation 
    public boolean removeReservation(int RoomNumber) {
        int i = findRoom(RoomNumber, 0);
        if (i == -1) return false;

        if (i == 0) {
            // removeFromFront 
            if (head == tail)
                head = tail = null;      
            else
                head = head.getNext();    
        } else {
            // Remove from middle or back
            Node previous = getNodeAt(i - 1);
            Node toRemove = previous.getNext();

            if (toRemove == tail)
                tail = previous;          

            previous.setNext(toRemove.getNext()); 
        }
        count--;
        return true;
    }

    // getRoomRevnue
    public double getRoomRevnue(int RoomNumber, int index) {
        if (index >= count) return 0;

        Room room = (Room) getNodeAt(index).getData();

        if (room.getRoomnumber() == RoomNumber)
            return room.getPaymentAmount();

        return getRoomRevnue(RoomNumber, index + 1);
    }

    // getTotalRevnue  walks through all nodes
    public double getTotalRevnue() {
        double sum = 0;
        Node current = head;              
        while (current != null) {         
            Room room = (Room) current.getData();
            sum += room.getPaymentAmount();
            current = current.getNext();  
        }
        return sum;
    }

    public String getName(){
    	return name;
    	}
    public void setName(String name){
    	this.name = name; 
    }
    public int getCount(){
    	return count; 
    	}
    public void setCount(int count){
    	this.count = count;
    	}
}
