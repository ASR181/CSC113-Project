import java.util.Scanner;

public class VeloraResorts {

    public static void main(String[] args) {
        
        boolean running = true;
        Scanner console = new Scanner(System.in); 
        
        Hotel Velora = new Hotel("Velora Resorts", 500);
        
        // LOAD DATA FROM FILE AT START
        System.out.println("Loading hotel data...");
        Velora.loadFromFile("HotelData.dat");
        
        while(running) { 
            
            System.out.println("\n\t--Velora Hights resorts--\n");
            System.out.println("1- Hotel rooms\t 2- Laundry Service\t 3- Shut Down");
            System.out.print("Please select number: ");
            int choice = console.nextInt();
            
            while(choice != 1 && choice != 2 && choice != 3) {
                System.err.print("Please select a correct number: ");
                choice = console.nextInt();
            }
            
            // --- OPTION 3: SHUT DOWN ---
            if(choice == 3) { 
                Velora.saveToFile("HotelData.dat");
                System.out.println("See you later, Goodbye!");
                System.exit(0);
            }
            
            // --- OPTION 2: LAUNDRY ---
            else if(choice == 2) {
                System.out.println("-- Laundry Service --");
                System.out.print("Number of Items: ");
                int numOfItem = console.nextInt();
                System.out.print("which type of item (shirt, pants, jacket, shoes): ");
                String typeOfItem = console.next();
                
                LaundryService Laundry = new LaundryService(numOfItem, typeOfItem);
                System.out.println("Cost of Laundry(in r.s): " + Laundry.getPaymentAmount());
            }
            
            // --- OPTION 1: HOTEL ROOMS ---
            else if (choice == 1) {
                System.out.println("-- Booking management --\n");
                System.out.println("1- Add Reservation\t 2- remove Reservation");
                System.out.println("3- Calculate Revnue\t 4- Calculate Total Revnue");
                System.out.print("Enter your choice: ");
                int bookingChoice = console.nextInt();
                
                if(bookingChoice == 1) { 
                    System.out.println("Please select room type:\n1- Standard Room\t2- Suite\t3- PentHouse");
                    int roomType = console.nextInt();
                    System.out.print("enter the Room number: ");
                    int roomNum = console.nextInt();
                    System.out.print("enter the Guest name: ");
                    String guestName = console.next();
                    System.out.print("enter the Guest age: ");
                    int guestAge = console.nextInt();
                    System.out.print("enter the Number of days: ");
                    int stayingDays = console.nextInt();
                    
                    String roomType1;
                    switch(roomType) { 
                        case 2: roomType1 = "Suite"; break;
                        case 3: roomType1 = "PentHouse"; break;
                        default: roomType1 = "StandardRoom";
                    }
                    
                    Guest Guest = new Guest(guestName, guestAge); 
                    if(Velora.addReservation(roomNum, stayingDays, Guest, roomType1)) {
                        System.out.println("Reservation successfully done!");
                    }
                }
                
                else if(bookingChoice == 2) { 
                    System.out.print("please enter room number: ");
                    int roomNum = console.nextInt();
                    
                    // EXCEPTION HANDLING: Try/Catch for Checked Exception
                    try {
                        Velora.removeReservation(roomNum);
                        System.out.println("Reservation removed succesfully!");
                    } catch (RoomNotFoundException e) {
                        System.err.println(e.getMessage()); // Prints the error without crashing
                    }
                }
                
                else if(bookingChoice == 3) { 
                    System.out.print("please enter room number on which revnue to be shown: ");
                    int roomNum = console.nextInt();
                    System.out.println("The room revenue is (in r.s): " + Velora.getRoomRevnue(roomNum, 0));
                }
                
                else if(bookingChoice == 4) { 
                    System.out.print("The Total Revnue (in r.s): ");
                    System.out.println(Velora.getTotalRevnue());
                }
                
                else {
                    System.err.println("incorrect number!\nPlease try again");
                }
            }
        }
    }
}