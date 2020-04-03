package parser;

import componentParsingText.ComponentParsingText;
import exceptions.NotFoundParserException;
import exceptions.NullOrEmptyTextForParsingException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ChainParser {
    final static Logger LOGGER = Logger.getLogger(ChainParser.class);
    static private TextParser textParser = new TextParser();
    static private ParagraphParser paragraphParser = new ParagraphParser();
    static private SentencesParser sentencesParser = new SentencesParser();

    static {
        PropertyConfigurator.configure("./src/main/resources/log4j.properties");
        textParser.setNextParser(paragraphParser);
        paragraphParser.setNextParser(sentencesParser);
    }

    public ComponentParsingText parse(String textForParsing) throws NullOrEmptyTextForParsingException, NotFoundParserException {
        LOGGER.debug("Start execution method with params: textForParsing=" + textForParsing);
        if (textForParsing == null) {
            LOGGER.error("Text for parsing is null or empty. StackTrace: " + Arrays.stream(Thread.currentThread().getStackTrace()).collect(Collectors.toList()));
            throw new NullOrEmptyTextForParsingException();
        }
        ComponentParsingText resultParse = textParser.parse(textForParsing);
        LOGGER.debug("End execution method with result: " + resultParse);
        return resultParse;
    }
}
