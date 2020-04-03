package componentParsingText;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompositeParsingText implements ComponentParsingText {
    final static Logger LOGGER = Logger.getLogger(CompositeParsingText.class);
    private TypeComponentParseText typeComponentParseText;
    private List<ComponentParsingText> listComponents = new ArrayList<>();

    static {
        PropertyConfigurator.configure("./src/main/resources/log4j.properties");
    }

    public CompositeParsingText(TypeComponentParseText typeComponentParseText) {
        this.typeComponentParseText = typeComponentParseText;
    }

    public void addComponent(ComponentParsingText componentParsingText) {
        listComponents.add(componentParsingText);
    }

    public List<ComponentParsingText> getListComponents() {
        return listComponents;
    }

    @Override
    public TypeComponentParseText getType() {
        return typeComponentParseText;
    }

    @Override
    public String getText() {
        LOGGER.debug("Start execution method");
        StringBuilder stringBuilder = new StringBuilder();
        for (ComponentParsingText componentParsingText : listComponents) {
            stringBuilder.append(componentParsingText.getText());
        }
        LOGGER.debug("End execution method with result: " + stringBuilder);
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "CompositeParsingText{" +
                "typeComponentParseText=" + typeComponentParseText +
                ", listComponents=" + listComponents +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeParsingText that = (CompositeParsingText) o;
        return typeComponentParseText == that.typeComponentParseText &&
                Objects.equals(listComponents, that.listComponents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeComponentParseText, listComponents);
    }
}
