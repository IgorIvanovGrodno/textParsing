import componentParsingText.ComponentParsingText;
import componentParsingText.CompositeParsingText;
import exceptions.NotFoundParserException;
import exceptions.NullOrEmptyTextForParsingException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import parser.ParagraphParser;
import parser.SentencesParser;
import parser.TextParser;

import static util.UtilTest.getExpectedText;

public class TestTextParser {

    private static CompositeParsingText expectedText;

    @BeforeClass
    public static void setUp() {
        expectedText = getExpectedText();
    }

    @Test
    public void shouldReturnExpectedComponentParsingText_whenPassTextToParse() throws NotFoundParserException, NullOrEmptyTextForParsingException {
        ParagraphParser paragraphParser = new ParagraphParser();
        paragraphParser.setNextParser(new SentencesParser());
        TextParser textParser = new TextParser();
        textParser.setNextParser(paragraphParser);
        ComponentParsingText actualText = textParser.parse("    Java is very cool! Spring is very cool too!\n<code> Cat cat = new Cat(); </code>\n    Hibernate is very useful tool for job.\n");
        Assert.assertEquals(expectedText, actualText);
    }

    @Test(expected = NotFoundParserException.class)
    public void shouldThrowNotFoundParserException_whenNotSetNextParser() throws NullOrEmptyTextForParsingException, NotFoundParserException {
        TextParser textParser = new TextParser();
        textParser.parse("    Java is very cool!\n");
    }

    @Test(expected = NullOrEmptyTextForParsingException.class)
    public void shouldThrowNullOrEmptyTextForParsingException_whenPassNullToParse() throws NullOrEmptyTextForParsingException, NotFoundParserException {
        TextParser textParser = new TextParser();
        textParser.parse(null);
    }

    @Test(expected = NullOrEmptyTextForParsingException.class)
    public void shouldThrowNullOrEmptyTextForParsingException_whenPassEmptyTextToParse() throws NullOrEmptyTextForParsingException, NotFoundParserException {
        TextParser textParser = new TextParser();
        textParser.parse("");
    }
}
