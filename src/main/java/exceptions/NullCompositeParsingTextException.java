package exceptions;

public class NullCompositeParsingTextException extends Exception {
    private static String message = "Component shouldn't be null!";

    public NullCompositeParsingTextException() {
        super(message);
    }
}
