package parser;

import componentParsingText.ComponentParsingText;
import exceptions.NotFoundParserException;
import exceptions.NullOrEmptyTextForParsingException;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class AbstractParser {

    private AbstractParser nextParser;

    public void setNextParser(AbstractParser nextParser) {
        this.nextParser = nextParser;
    }

    abstract public ComponentParsingText parse(String text) throws NotFoundParserException, NullOrEmptyTextForParsingException;

    public AbstractParser getNextParser(Logger LOGGER) throws NotFoundParserException {
        checkNextParser(LOGGER);
        return nextParser;
    }

    protected void checkNullEmptyText(String text, Logger LOGGER) throws NullOrEmptyTextForParsingException {
        if (text == null || text.isEmpty()) {
            LOGGER.error("Text for parsing is null or empty. StackTrace: " + Arrays.stream(Thread.currentThread().getStackTrace()).collect(Collectors.toList()));
            throw new NullOrEmptyTextForParsingException();
        }
    }

    private void checkNextParser(Logger LOGGER) throws NotFoundParserException {
        if (nextParser == null) {
            LOGGER.error("Next parser is not found" + Arrays.stream(Thread.currentThread().getStackTrace()).collect(Collectors.toList()));
            throw new NotFoundParserException();
        }
    }
}
