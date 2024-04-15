package kaem0n.u5w3d1.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("Element with ID '" + id + "' not found.");
    }

    public NotFoundException(String msg) {
        super(msg);
    }
}
