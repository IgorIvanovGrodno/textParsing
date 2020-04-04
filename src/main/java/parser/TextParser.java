package parser;

import componentParsingText.ComponentParsingText;
import componentParsingText.CompositeParsingText;
import componentParsingText.Listing;
import componentParsingText.TypeComponentParseText;
import exceptions.NotFoundParserException;
import exceptions.NullOrEmptyTextForParsingException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TextParser extends AbstractParser {
    final static Logger LOGGER = Logger.getLogger(TextParser.class);
    private static final String REGEX_FOR_PARAGRAPH = "(^\\s\\s\\s\\s[^\\n]+\\n)";
    private static final String REGEX_FOR_PARAGRAPH_LISTING = "((^\\s\\s\\s\\s[^\\n]+\\n)|(^<code>.+</code>\\n))";

    static {
        PropertyConfigurator.configure("./src/main/resources/log4j.properties");
    }

    @Override
    public ComponentParsingText parse(String text) throws NotFoundParserException, NullOrEmptyTextForParsingException {
        LOGGER.debug("Start execution method with params: text=" + text);
        checkNullEmptyText(text, LOGGER);
        CompositeParsingText textComposite = new CompositeParsingText(TypeComponentParseText.TEXT);
        Pattern patternParagraph = Pattern.compile(REGEX_FOR_PARAGRAPH, Pattern.MULTILINE);
        Pattern patternParagraphListing = Pattern.compile(REGEX_FOR_PARAGRAPH_LISTING, Pattern.DOTALL | Pattern.MULTILINE);
        Matcher matcherParagraphListing = patternParagraphListing.matcher(text);
        Matcher matcherParagraph = patternParagraph.matcher(text);

        String buffer;

        while (matcherParagraphListing.find()) {
            buffer = matcherParagraphListing.group();
            matcherParagraph.reset(buffer);
            if (matcherParagraph.matches()) {
                textComposite.addComponent(getNextParser(LOGGER).parse(buffer));
            } else {
                textComposite.addComponent(new Listing(buffer));
            }
        }
        LOGGER.debug("End execution method with result: " + textComposite);
        return textComposite;
    }

}
