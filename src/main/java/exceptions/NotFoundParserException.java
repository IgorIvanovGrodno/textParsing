package exceptions;

public class NotFoundParserException extends Exception {
    private static String message = "Couldn't found parser!";

    public NotFoundParserException() {
        super(message);
    }
}
