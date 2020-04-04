import componentParsingText.*;
import exceptions.NotFoundParserException;
import exceptions.NullOrEmptyTextForParsingException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import parser.ParagraphParser;
import parser.SentencesParser;

import static util.UtilTest.getExpectedParagraph;


public class TestParagraphParser {
    private static CompositeParsingText expectedParagraph;

    @BeforeClass
    public static void setUp() {
        expectedParagraph = getExpectedParagraph();
    }

    @Test
    public void shouldReturnExpectedComponentParsingText_whenPassTextToParse() throws NotFoundParserException, NullOrEmptyTextForParsingException {
        ParagraphParser paragraphParser = new ParagraphParser();
        paragraphParser.setNextParser(new SentencesParser());
        ComponentParsingText actualParagraph = paragraphParser.parse("    Java is very cool! Spring is very cool too!\n");
        Assert.assertEquals(expectedParagraph, actualParagraph);
    }

    @Test(expected = NotFoundParserException.class)
    public void shouldThrowNotFoundParserException_whenNotSetNextParser() throws NullOrEmptyTextForParsingException, NotFoundParserException {
        ParagraphParser paragraphParser = new ParagraphParser();
        paragraphParser.parse("Java is very cool!");
    }

    @Test(expected = NullOrEmptyTextForParsingException.class)
    public void shouldThrowNullOrEmptyTextForParsingException_whenPassNullToParse() throws NullOrEmptyTextForParsingException, NotFoundParserException {
        ParagraphParser paragraphParser = new ParagraphParser();
         paragraphParser.parse(null);
    }

    @Test(expected = NullOrEmptyTextForParsingException.class)
    public void shouldThrowNullOrEmptyTextForParsingException_whenPassEmptyTextToParse() throws NullOrEmptyTextForParsingException, NotFoundParserException {
        ParagraphParser paragraphParser = new ParagraphParser();
         paragraphParser.parse("");
    }
}
