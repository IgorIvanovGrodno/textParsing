package componentParsingText;

import java.util.Objects;

public class PunctuationMark implements ComponentParsingText {
    private String punctuationMark;

    public PunctuationMark(String punctuationMark) {
        this.punctuationMark = punctuationMark;
    }

    @Override
    public TypeComponentParseText getType() {
        return TypeComponentParseText.PUNCTUATION_MARK;
    }

    @Override
    public String getText() {
        return punctuationMark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PunctuationMark that = (PunctuationMark) o;
        return Objects.equals(punctuationMark, that.punctuationMark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(punctuationMark);
    }

    @Override
    public String toString() {
        return "PunctuationMark{" +
                "punctuationMark='" + punctuationMark + '\'' +
                '}';
    }
}
