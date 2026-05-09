import java.util.*;
import java.io.*;

public class Hotel {

	// Attributes head & tail instead of array
	private Node head; // first room
	private Node tail; // last room
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
		if (days <= 0 || num <= 0) {
			throw new IllegalArgumentException("Error, The number Must be positive!");
		}

		// Create the Room type
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
	public int findRoom(int RoomNumber,Node current, int index) {
		if (current == null)
			return -1;
		if (((Room) current.getData()).getRoomnumber() == RoomNumber)
			return index;
		return findRoom(RoomNumber, current.getNext(), index + 1);
	}

	// removeReservation
	public boolean removeReservation(int roomNumber) throws RoomNotFoundException {
        if (head == null) throw new RoomNotFoundException("لا توجد حجوزات في النظام حالياً!");

        if (((Room)head.getData()).getRoomnumber() == roomNumber) {
            if (head == tail) head = tail = null;
            else head = head.getNext();
            count--;
            return true;
        }

        Node prev = head;
        while (prev.getNext() != null && ((Room)prev.getNext().getData()).getRoomnumber() != roomNumber) {
            prev = prev.getNext();
        }

        if (prev.getNext() == null) throw new RoomNotFoundException("الغرفة رقم " + roomNumber + " غير موجودة!");

        if (prev.getNext() == tail) tail = prev;
        prev.setNext(prev.getNext().getNext());
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

	// getTotalRevnue walks through all nodes
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
	public void saveToBinaryFile(String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(this.head);
            System.out.println("The file is saved successfully");
        } catch (IOException e) {
            System.err.println("Failed th save File: " + e.getMessage());
        }
    }

    public void loadFromBinaryFile(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            this.head = (Node) in.readObject();
            
            Node curr = head;
            count = 0;
            while (curr != null) {
                if (curr.getNext() == null) tail = curr;
                count++;
                curr = curr.getNext();
            }
            System.out.println("The data is been restored");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load Data: " + e.getMessage());
        }
    }

    // Getters
    public Node getHead() { return head; }
    public int getCount() { return count; }


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setCount(int count) {
		this.count = count;
	}
}
