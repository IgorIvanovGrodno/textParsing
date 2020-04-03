package util;

import componentParsingText.*;

import java.util.ArrayList;
import java.util.List;

public class UtilTest {
    private static CompositeParsingText text;
    private static CompositeParsingText paragraphFirst;
    private static CompositeParsingText paragraphSecond;
    private static Listing listing;
    private static CompositeParsingText sentenceFirst;
    private static CompositeParsingText sentenceSecond;
    private static CompositeParsingText sentenceThird;
    private static String expectedString;

    static {
        expectedString = "    Java is very cool! Spring is very cool too!\n<code> Cat cat = new Cat(); </code>\n    Hibernate is very useful tool for job.\n";
        sentenceFirst = new CompositeParsingText(TypeComponentParseText.SENTENCES);
        sentenceFirst.addComponent(new Word("    Java"));
        sentenceFirst.addComponent(new Word(" is"));
        sentenceFirst.addComponent(new Word(" very"));
        sentenceFirst.addComponent(new Word(" cool"));
        sentenceFirst.addComponent(new PunctuationMark("!"));

        sentenceSecond = new CompositeParsingText(TypeComponentParseText.SENTENCES);
        sentenceSecond.addComponent(new Word(" Spring"));
        sentenceSecond.addComponent(new Word(" is"));
        sentenceSecond.addComponent(new Word(" very"));
        sentenceSecond.addComponent(new Word(" cool"));
        sentenceSecond.addComponent(new Word(" too"));
        sentenceSecond.addComponent(new PunctuationMark("!\n"));

        sentenceThird = new CompositeParsingText(TypeComponentParseText.SENTENCES);
        sentenceThird.addComponent(new Word("    Hibernate"));
        sentenceThird.addComponent(new Word(" is"));
        sentenceThird.addComponent(new Word(" very"));
        sentenceThird.addComponent(new Word(" useful"));
        sentenceThird.addComponent(new Word(" tool"));
        sentenceThird.addComponent(new Word(" for"));
        sentenceThird.addComponent(new Word(" job"));
        sentenceThird.addComponent(new PunctuationMark(".\n"));

        paragraphFirst=new CompositeParsingText(TypeComponentParseText.PARAGRAPH);
        paragraphFirst.addComponent(sentenceFirst);
        paragraphFirst.addComponent(sentenceSecond);

        paragraphSecond=new CompositeParsingText(TypeComponentParseText.PARAGRAPH);
        paragraphSecond.addComponent(sentenceThird);

        listing = new Listing("<code> Cat cat = new Cat(); </code>\n");

        text=new CompositeParsingText(TypeComponentParseText.TEXT);
        text.addComponent(paragraphFirst);
        text.addComponent(listing);
        text.addComponent(paragraphSecond);
    }

    public static List<ComponentParsingText> getListSentences(){
        List<ComponentParsingText> listSentences = new ArrayList<>();

        listSentences.add(sentenceSecond);
        listSentences.add(sentenceThird);
        listSentences.add(sentenceFirst);

        return listSentences;
    }

    public static List<ComponentParsingText> getListWithNullSentence(){
        List<ComponentParsingText> listWithNullSentence = new ArrayList<>();
        listWithNullSentence.add(null);
        return listWithNullSentence;
    }

    public static String getExpectedString() {
        return expectedString;
    }

    public static CompositeParsingText getExpectedParagraph() {
        return paragraphFirst;
    }

    public static CompositeParsingText getExpectedText(){
        return text;
    }

    public static CompositeParsingText getExpectedSentence() { return sentenceFirst;}
}
