package learning.springboot.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("User not found with id: " + id);
    }

    // You can also create a constructor with a custom message
    public UserNotFoundException(String message) {
        super(message);
    }
}