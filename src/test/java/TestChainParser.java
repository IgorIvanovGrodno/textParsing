import componentParsingText.ComponentParsingText;
import componentParsingText.CompositeParsingText;
import exceptions.NotFoundParserException;
import exceptions.NullOrEmptyTextForParsingException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import parser.ChainParser;

import static util.UtilTest.getExpectedText;

public class TestChainParser {
    private static CompositeParsingText expectedText;

    @BeforeClass
    public static void setUp() {
        expectedText = getExpectedText();
    }

    @Test
    public void shouldReturnExpectedComponentParsingText_whenPassTextToParse() throws NotFoundParserException, NullOrEmptyTextForParsingException {
        ChainParser chainParser = new ChainParser();
        ComponentParsingText actualText = chainParser.parse("    Java is very cool! Spring is very cool too!\n<code> Cat cat = new Cat(); </code>\n    Hibernate is very useful tool for job.\n");
        Assert.assertEquals(expectedText, actualText);
    }


    @Test(expected = NullOrEmptyTextForParsingException.class)
    public void shouldThrowNullOrEmptyTextForParsingException_whenPassNullToParse() throws NullOrEmptyTextForParsingException, NotFoundParserException {
        ChainParser chainParser = new ChainParser();
       chainParser.parse(null);
    }

    @Test(expected = NullOrEmptyTextForParsingException.class)
    public void shouldThrowNullOrEmptyTextForParsingException_whenPassEmptyTextToParse() throws NullOrEmptyTextForParsingException, NotFoundParserException {
        ChainParser chainParser = new ChainParser();
        chainParser.parse("");
    }
}
