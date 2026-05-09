// user defined exception
package application;

public class DuplicateRoomException extends RuntimeException {
	
    public DuplicateRoomException(String message) {
        super(message);
        
    }
}