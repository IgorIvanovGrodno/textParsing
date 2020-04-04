package util;

import componentParsingText.ComponentParsingText;
import componentParsingText.CompositeParsingText;
import componentParsingText.TypeComponentParseText;
import exceptions.NullCompositeParsingTextException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UtilSortSentences {
    final static Logger LOGGER = Logger.getLogger(UtilSortSentences.class);

    static {
        PropertyConfigurator.configure("./src/main/resources/log4j.properties");
    }

    public static List<ComponentParsingText> sortSentencesByCountWords(CompositeParsingText compositeParsingText) throws NullCompositeParsingTextException {
        LOGGER.debug("Start execution method with params: compositeParsingText=" + compositeParsingText);
        if (compositeParsingText == null) {
            LOGGER.error("Component for sort sentences is null " + Arrays.stream(Thread.currentThread().getStackTrace()).collect(Collectors.toList()));
            throw new NullCompositeParsingTextException();
        }

        List<ComponentParsingText> resultList = searchListCompositesByType(compositeParsingText, TypeComponentParseText.SENTENCES);
        resultList = sortListComponentsByCountChild(resultList);
        LOGGER.debug("End execution method with result: " + resultList);
        return resultList;
    }

    private static List<ComponentParsingText> searchListCompositesByType(CompositeParsingText sourceCompositeParsingText, TypeComponentParseText typeForSearching) throws NullCompositeParsingTextException {
        LOGGER.debug("Start execution method with params: sourceCompositeParsingText=" + sourceCompositeParsingText + " typeForSearching=" + typeForSearching);
        List<ComponentParsingText> resultList = new ArrayList<>();
        TypeComponentParseText typeComponent = sourceCompositeParsingText.getType();
        if (typeComponent.equals(typeForSearching)) {
            resultList.add(sourceCompositeParsingText);
        } else {

            for (ComponentParsingText childComponent : (sourceCompositeParsingText.getListComponents())) {
                if (childComponent == null) {
                    LOGGER.error("Child component is null" + Arrays.stream(Thread.currentThread().getStackTrace()).collect(Collectors.toList()));
                    throw new NullCompositeParsingTextException();
                }
                if (!(childComponent.getType().equals(TypeComponentParseText.LISTING)
                        || childComponent.getType().equals(TypeComponentParseText.WORD)
                        || childComponent.getType().equals(TypeComponentParseText.PUNCTUATION_MARK))) {
                    resultList.addAll(searchListCompositesByType((CompositeParsingText) childComponent, typeForSearching));
                }
            }

        }
        LOGGER.debug("End execution method with result: " + resultList);
        return resultList;
    }

    private static List<ComponentParsingText> sortListComponentsByCountChild(List<ComponentParsingText> listComponents) {
        LOGGER.debug("Start execution method with params: listComponents=" + listComponents);
        List<ComponentParsingText> resultList = listComponents.stream().sorted((o1, o2) -> getCountWordsInSentences((CompositeParsingText) o1) - getCountWordsInSentences((CompositeParsingText) o2)).collect(Collectors.toList());
        LOGGER.debug("End execution method with result: " + resultList);
        return resultList;
    }

    private static int getCountWordsInSentences(CompositeParsingText sentences) {
        LOGGER.debug("Start execution method with params: sentences=" + sentences);
        int result = (int) sentences.getListComponents().stream().filter((o) -> o.getType().equals(TypeComponentParseText.WORD)).count();
        LOGGER.debug("End execution method with result: " + result);
        return result;
    }
}
