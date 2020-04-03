package parser;

import componentParsingText.*;
import exceptions.NullOrEmptyTextForParsingException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentencesParser extends AbstractParser {
    final static Logger LOGGER = Logger.getLogger(SentencesParser.class);
    private static final String REGEX_FOR_WORDS = "\\s*[\\wА-Яа-я]+\\n*";
    private static final String REGEX_FOR_WORDS_AND_PUNCTUATION_MARK = "((\\s*[\\wА-Яа-я]+\\n*)|(\\s*\\p{Punct}+\\n*))";

    static {
        PropertyConfigurator.configure("./src/main/resources/log4j.properties");
    }

    @Override
    public ComponentParsingText parse(String text) throws NullOrEmptyTextForParsingException {
        LOGGER.debug("Start execution method with params: text=" + text);
        checkNullEmptyText(text, LOGGER);
        CompositeParsingText sentenceComposite = new CompositeParsingText(TypeComponentParseText.SENTENCES);
        Pattern patternWordsAndPunctuationMark = Pattern.compile(REGEX_FOR_WORDS_AND_PUNCTUATION_MARK);
        Pattern patternWords = Pattern.compile(REGEX_FOR_WORDS);
        Matcher matcherWord = patternWords.matcher(text);
        Matcher matcherWordsAndPunctuationMark = patternWordsAndPunctuationMark.matcher(text);

        String buffer;

        while (matcherWordsAndPunctuationMark.find()) {
            buffer = matcherWordsAndPunctuationMark.group();
            matcherWord.reset(buffer);
            if (matcherWord.matches()) {
                sentenceComposite.addComponent(new Word(buffer));
            } else {
                sentenceComposite.addComponent(new PunctuationMark(buffer));
            }
        }
        LOGGER.debug("End execution method with result: " + sentenceComposite);
        return sentenceComposite;
    }
}
