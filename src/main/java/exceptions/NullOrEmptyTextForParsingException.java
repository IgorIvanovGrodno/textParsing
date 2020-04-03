package exceptions;

public class NullOrEmptyTextForParsingException extends Exception {
    private static String message = "Text for parsing shouldn't be null or empty!";

    public NullOrEmptyTextForParsingException() {
        super(message);
    }
}
