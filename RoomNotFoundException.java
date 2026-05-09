// user defined exception
package application;

public class RoomNotFoundException extends Exception {
	
    public RoomNotFoundException(String message) {
        super(message);
        
    }
}