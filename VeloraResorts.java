import java.util.Scanner;

public class VeloraResorts {

	public static void main(String[] args) {
		
		boolean running = true;
		
		Scanner console = new Scanner(System.in); // using Scanner
		
		Hotel Velora = new Hotel("Velora Resorts", 500);
		
		while(running) { // System is running
			
			System.out.println("\t--Velora Hights resorts--\n");
			System.out.println("1- Hotel rooms\t 2- Laundry Service\t 3- Shut Down");
			System.out.print("Please select number: ");
			int choice = console.nextInt();
			
			while(choice !=1 && choice != 2 && choice != 3) {
				System.err.print("Please select a correct number: ");
				choice = console.nextInt();
			}
			
			if(choice == 3) // Shut down
				System.exit(0);
			
			if(choice == 2) {
				System.out.println("-- Laundry Service --");
				System.out.println("Number of Items: ");
				int numOfItem = console.nextInt();
				System.out.println("which type of item(shirt, pants, jacket, shoes): ");
				String typeOfItem = console.next();
				
				LaundryService Laundry = new LaundryService(numOfItem, typeOfItem);
				System.out.println("Cost of Laundry(in r.s): " + Laundry.getPaymentAmount());
				
			}
			// room management and reservation
			else {
				System.out.println("-- Booking management --\n");
				System.out.println("1- Add Reservation\t 2- remove Reservation");
				System.out.println("3- Calculate Revnue\t 4- Calculate Total Revnue");
				int bookingChoice = console.nextInt();
				
				if(bookingChoice == 1) { // adding new reservation
					System.out.println("Please select room type:\n1- Standard Room\t2- Suite\t3- PentHouse");
					int roomType = console.nextInt();
					System.out.println("enter the Room number: ");
					int roomNum = console.nextInt();
					System.out.println("enter the Guest name: ");
					String guestName = console.next();
					System.out.println("enter the Guest age: ");
					int guestAge = console.nextInt();
					System.out.println("enter the Number of days: ");
					int stayingDays = console.nextInt();
					
					String roomType1;
					
					switch(roomType) { // transforming user input to suitable values for method
						case 2:
							roomType1 = "Suite";
							break;
						case 3:
							roomType1 = "PentHouse";
							break;
						default:
							roomType1 = "StandardRoom";
					}
					
					Guest Guest = new Guest(guestName, guestAge); // making object for the guest
					if(Velora.addReservation(roomNum, stayingDays, Guest, roomType1)) // validating and making the reservation
						System.out.println("Reservation successfully done!");
					else
						System.out.println("Sorry, there are no rooms left!");
				}
				
				else if(bookingChoice == 2) { // removing reservation
					System.out.print("please enter room number:");
					int roomNum = console.nextInt();
					
					if(Velora.removeReservation(roomNum))
						System.out.println("Reservation removed succesfully!");
					else
						System.out.println("Room is empty!");
				}
				
				else if(bookingChoice == 3) { // revenue for one room
					System.out.print("please enter room number on which revnue to be shown: ");
					int roomNum = console.nextInt();
					System.out.println("The room revenue is (in r.s):" + Velora.getRoomRevnue(roomNum, 0));
				}
				
				else if(bookingChoice == 4) { // the total revenue of the hotel
					System.out.print("The Total Revnue (in r.s): ");
					System.out.println(Velora.getTotalRevnue());
				}
				
				else
					System.err.println("incorrect number!\nPlease try again");
			}
		}
		
	}

}
