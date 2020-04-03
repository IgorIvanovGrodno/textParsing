import componentParsingText.ComponentParsingText;
import componentParsingText.CompositeParsingText;
import componentParsingText.TypeComponentParseText;
import exceptions.NullCompositeParsingTextException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import util.UtilParsingText;

import java.util.List;

import static util.UtilTest.getListSentences;
import static util.UtilTest.getListWithNullSentence;

public class TestUtilParsingText {
    private static CompositeParsingText paragraphCompositeParsingTextSpy;
    private static CompositeParsingText paragraphCompositeParsingTextWithNullSentenceSpy;

    @BeforeClass
    public static void setUp() {
        CompositeParsingText paragraphCompositeParsingText = new CompositeParsingText(TypeComponentParseText.PARAGRAPH);
        paragraphCompositeParsingTextSpy = Mockito.spy(paragraphCompositeParsingText);
        paragraphCompositeParsingTextWithNullSentenceSpy = Mockito.spy(paragraphCompositeParsingText);
        Mockito.when(paragraphCompositeParsingTextSpy.getListComponents()).thenReturn(getListSentences());
        Mockito.when(paragraphCompositeParsingTextWithNullSentenceSpy.getListComponents()).thenReturn( getListWithNullSentence());
    }

    @Test(expected = NullCompositeParsingTextException.class)
    public void shouldThrowNullCompositeParsingText_whenPassNullToSortSentencesByCountWords() throws NullCompositeParsingTextException {
        UtilParsingText.sortSentencesByCountWords(null);
    }

    @Test(expected = NullCompositeParsingTextException.class)
    public void shouldThrowNullCompositeParsingText_whenPassComponentWhichContainsNullChildComponentToSortSentencesByCountWords() throws NullCompositeParsingTextException {
        UtilParsingText.sortSentencesByCountWords(paragraphCompositeParsingTextWithNullSentenceSpy);
    }

    @Test
    public void shouldReturnSortedListSentencesByCountWords_whenPassParagraph() throws NullCompositeParsingTextException {
        List<ComponentParsingText> actualList =UtilParsingText.sortSentencesByCountWords(paragraphCompositeParsingTextSpy);
        Assert.assertEquals("    Java is very cool!", actualList.get(0).getText());
        Assert.assertEquals(" Spring is very cool too!\n", actualList.get(1).getText());
        Assert.assertEquals("    Hibernate is very useful tool for job.\n", actualList.get(2).getText());
    }

}
