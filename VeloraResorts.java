import java.util.Scanner;

public class VeloraResorts {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Hotel Velora = new Hotel("Velora Heights", 500);
        
        while(true) {
            System.out.println("\n\t-- Velora Heights Resorts --");
            System.out.println("1- Management\t2- Laundry\t3- Files\t4- Exit");
            int choice = console.nextInt();
            
            if(choice == 4) System.exit(0);
            
            if(choice == 3) {
                System.out.println("1- Save Data\t2- Load Data");
                int fChoice = console.nextInt();
                if(fChoice == 1) Velora.saveToBinaryFile("HotelData.ser");
                else Velora.loadFromBinaryFile("HotelData.ser");
            }
            else if(choice == 1) {
                System.out.println("1- Add\t2- Remove\t3- Total Revenue");
                int op = console.nextInt();
                
                if(op == 1) {
                    try { // التعامل مع الـ Unchecked Exception
                        System.out.print("Room Type (Standard, Suite, PentHouse): ");
                        String type = console.next();
                        System.out.print("Room Num: "); int num = console.nextInt();
                        System.out.print("Days: "); int days = console.nextInt();
                        Velora.addReservation(num, days, new Guest("User", 20), type);
                        System.out.println("Done!");
                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    }
                }
                else if(op == 2) {
                    try { // التعامل مع الـ User-defined Exception
                        System.out.print("Enter Room Number to remove: ");
                        Velora.removeReservation(console.nextInt());
                        System.out.println("Removed successfully.");
                    } catch (RoomNotFoundException e) {
                        System.err.println(e.getMessage());
                    }
                }
                else if(op == 3) {
                    System.out.println("Total Revenue (with 15% Tax): " + Velora.getTotalRevnue());
                }
            }
        }
    }
}