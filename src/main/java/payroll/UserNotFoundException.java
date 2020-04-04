package payroll;

public class UserNotFoundException extends RuntimeException  {
    public UserNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
