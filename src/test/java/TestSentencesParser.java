import componentParsingText.*;
import exceptions.NullOrEmptyTextForParsingException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import parser.SentencesParser;
import util.UtilTest;

import static util.UtilTest.getExpectedSentence;


public class TestSentencesParser {
    private static CompositeParsingText expectedComponent;

    @BeforeClass
    public static void setUp() {
        expectedComponent = getExpectedSentence();

    }

    @Test
    public void shouldReturnExpectedComponentParsingText_whenPassTextToParse() throws NullOrEmptyTextForParsingException {
        SentencesParser sentencesParser = new SentencesParser();
        ComponentParsingText actualComponentParsingText = sentencesParser.parse("    Java is very cool!");
        Assert.assertEquals(expectedComponent, actualComponentParsingText);
    }

    @Test(expected = NullOrEmptyTextForParsingException.class)
    public void shouldThrowNullOrEmptyTextForParsingException_whenPassNullToParse() throws NullOrEmptyTextForParsingException {
        SentencesParser sentencesParser = new SentencesParser();
        sentencesParser.parse(null);
    }

    @Test(expected = NullOrEmptyTextForParsingException.class)
    public void shouldThrowNullOrEmptyTextForParsingException_whenPassEmptyTextToParse() throws NullOrEmptyTextForParsingException {
        SentencesParser sentencesParser = new SentencesParser();
        sentencesParser.parse("");
    }
}
