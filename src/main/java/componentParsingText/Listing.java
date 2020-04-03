package componentParsingText;

import java.util.Objects;

public class Listing implements ComponentParsingText {
    private String text;

    public Listing(String text) {
        this.text = text;
    }

    @Override
    public TypeComponentParseText getType() {
        return TypeComponentParseText.LISTING;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Listing listing = (Listing) o;
        return Objects.equals(text, listing.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "Listing{" +
                "text='" + text + '\'' +
                '}';
    }

}
