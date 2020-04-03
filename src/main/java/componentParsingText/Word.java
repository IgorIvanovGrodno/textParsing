package componentParsingText;

import java.util.Objects;

public class Word implements ComponentParsingText {
    private String word;

    public Word(String word) {
        this.word = word;
    }

    @Override
    public TypeComponentParseText getType() {
        return TypeComponentParseText.WORD;
    }

    @Override
    public String getText() {
        return word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return Objects.equals(word, word1.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                '}';
    }
}
