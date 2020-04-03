import componentParsingText.CompositeParsingText;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static util.UtilTest.getExpectedString;
import static util.UtilTest.getExpectedText;

public class TestCompositeParsingText {
    private static String expectedString;

    @BeforeClass
    public static void setUp() {
        expectedString = getExpectedString();
    }

    @Test
    public void shouldReturnExpectedText_whenInvokeGetText(){
        CompositeParsingText compositeParsingText = getExpectedText();
        Assert.assertEquals(expectedString, compositeParsingText.getText());
    }
}
