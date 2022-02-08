package api;

public class RequestFailedException extends Exception {
    public RequestFailedException(int code) {
        super("HTTP Code error: " + code);
    }
}
