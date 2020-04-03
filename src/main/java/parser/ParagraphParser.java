package parser;

import componentParsingText.ComponentParsingText;
import componentParsingText.CompositeParsingText;
import componentParsingText.TypeComponentParseText;
import exceptions.NotFoundParserException;
import exceptions.NullOrEmptyTextForParsingException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ParagraphParser extends AbstractParser {
    final static Logger LOGGER = Logger.getLogger(ParagraphParser.class);
    private static final String REGEX_FOR_SENTENCES = "[^\\.\\?!]+[\\.\\?!]+\\n*";

    static {
        PropertyConfigurator.configure("./src/main/resources/log4j.properties");
    }

    @Override
    public ComponentParsingText parse(String text) throws NotFoundParserException, NullOrEmptyTextForParsingException {
        LOGGER.debug("Start execution method with params: text=" + text);
        checkNullEmptyText(text, LOGGER);
        CompositeParsingText paragraphComposite = new CompositeParsingText(TypeComponentParseText.PARAGRAPH);
        Pattern patternSentences = Pattern.compile(REGEX_FOR_SENTENCES);
        Matcher matcherSentences = patternSentences.matcher(text);
        while (matcherSentences.find()) {
            paragraphComposite.addComponent(getNextParser(LOGGER).parse(matcherSentences.group()));
        }
        LOGGER.debug("End execution method with result: " + paragraphComposite);
        return paragraphComposite;
    }

}
