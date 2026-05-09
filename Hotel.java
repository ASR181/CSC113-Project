
package application;
import java.io.*;

public class Hotel implements Serializable {

    private Node head;    // first room 
    private Node tail;    // last room 
    private String name;
    private int count;
    private double pastRevenue; // to save revenue after guest's check out

    public Hotel(String name, int size) {
        setName(name);
        head = tail = null;              
        count = 0;
        pastRevenue = 0.0;
    }
    // loading data into file
    public void saveToFile(String name) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(name))) {
            out.writeObject(head);
            out.writeInt(count);
            out.writeDouble(pastRevenue);
            System.out.println("Data successfully saved to " + name);
        } catch (IOException e) {
            System.err.println("Error saving file: " + e.getMessage());
        }
    }
    //extracting data out of file
    public void loadFromFile(String name) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(name))) {
            head = (Node) in.readObject();
            count = in.readInt();
            pastRevenue = in.readDouble();
            
            
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
            System.err.println("Error loading file. Data might be from an older version.");
        }
    }

   

    public boolean addReservation(int num, int days, Guest guest, String type) {
        try {  // handling exception in addReservation
            if (findRoom(num, 0) != -1) {
                throw new DuplicateRoomException("Room " + num + " is already booked!");
            }
        } catch (DuplicateRoomException e) {
            System.err.println(e.getMessage());
            return false; 
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

    public boolean removeReservation(int RoomNumber) throws RoomNotFoundException {
        int i = findRoom(RoomNumber, 0);
        
        if (i == -1) {
            throw new RoomNotFoundException("Cannot remove: Room number " + RoomNumber + " was not found!");
        }

        // saving room revenue to avoid losing it
        Room roomToRemove = (Room) getNodeAt(i).getData();
        pastRevenue += roomToRemove.getPaymentAmount();

        // removing the room from the linked list
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

    private Node getNodeAt(int index) {
        Node current = head;
        for (int i = 0; i < index; i++)
            current = current.getNext();
        return current;
    }

    public int findRoom(int RoomNumber, int index) {
        if (index >= count)
            return -1;

        Room room = (Room) getNodeAt(index).getData();
        if (room.getRoomnumber() == RoomNumber)
            return index;

        return findRoom(RoomNumber, index + 1);
    }

    public double getRoomRevnue(int RoomNumber, int index) {
        if (index >= count)
            return 0;

        Room room = (Room) getNodeAt(index).getData();

        if (room.getRoomnumber() == RoomNumber)
            return room.getPaymentAmount();

        return getRoomRevnue(RoomNumber, index + 1);
    }

    public double getTotalRevnue() {
    	
        double sum = pastRevenue; 
        
        Node current = head;              
        while (current != null) {         
            // Add the money from all currently staying guests will be added to the pastRevenue
            Room room = (Room) current.getData();
            sum += room.getPaymentAmount();
            current = current.getNext();  
        }
        return sum;
    }
    // setters and getters
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