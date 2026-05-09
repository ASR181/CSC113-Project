
import java.io.*;

public class Hotel implements Serializable {

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

 // write data to an Object File
    public void saveToFile(String name) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(name))) {
            out.writeObject(head);
            out.writeInt(count);
            System.out.println("Data successfully saved to " + name);
        } catch (IOException e) {
            System.err.println("Error saving file: " + e.getMessage());
        }
    }
    
    // read from an Object File
    public void loadFromFile(String name) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(name))) {
            head = (Node) in.readObject();
            count = in.readInt();
            
            // Re-establish the tail pointer
            if (head == null) {
                tail = null;
            } else {
                Node current = head;
                while (current.getNext() != null) {
                    current = current.getNext();
                }
                tail = current;
            }
            System.out.println("Data successfully loaded from " + name);
        } catch (FileNotFoundException e) {
            System.out.println("No previous save file found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading file: " + e.getMessage());
        }
    }


    // addReservation 
 public boolean addReservation(int num, int days, Guest guest, String type) {
        
        // Handling Exception locally in method
        try {
            if (findRoom(num, 0) != -1) {
                throw new DuplicateRoomException("Room " + num + " is already booked!");
            }
        } catch (DuplicateRoomException e) {
            System.err.println(e.getMessage());
            return false; // Exit method because room already exists
        }

        Room newRoom;
        if (type.equalsIgnoreCase("suite"))
            newRoom = new Suite(num, days, guest);
        else if (type.equalsIgnoreCase("PentHouse"))
            newRoom = new PentHouse(num, days, guest);
        else
            newRoom = new StandardRoom(num, days, guest);

        Node newNode = new Node(newRoom);

        if (head == null)
            head = tail = newNode;
        else {
            tail.setNext(newNode);   
            tail = newNode;          
        }
        count++;
        return true;
    }


    // Helper method
    private Node getNodeAt(int index) {
        Node current = head;
        for (int i = 0; i < index; i++)
            current = current.getNext();
        return current;
    }

    // findRoom 
    public int findRoom(int RoomNumber, int index) {
        if (index >= count)
        	return -1;

        // Get node at current index and cast data to Room
        Room room = (Room) getNodeAt(index).getData();

        if (room.getRoomnumber() == RoomNumber)
            return index;

        return findRoom(RoomNumber, index + 1);
    }

    // removeReservation 
    public boolean removeReservation(int RoomNumber) throws RoomNotFoundException {
        int i = findRoom(RoomNumber, 0);
        
        // Propagating Exception
        if (i == -1) {
            throw new RoomNotFoundException("Cannot remove: Room number " + RoomNumber + " was not found!");
        }

        if (i == 0) {
            if (head == tail)
                head = tail = null;      
            else
                head = head.getNext();    
        } else {
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
        if (index >= count)
        	return 0;

        Room room = (Room) getNodeAt(index).getData();

        if (room.getRoomnumber() == RoomNumber)
            return room.getPaymentAmount();

        return getRoomRevnue(RoomNumber, index + 1);
    }

    // getTotalRevnue  walks over all nodes
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