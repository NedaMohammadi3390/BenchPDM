package exception;


public class ClassFileCreationException extends RuntimeException {
    public ClassFileCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
