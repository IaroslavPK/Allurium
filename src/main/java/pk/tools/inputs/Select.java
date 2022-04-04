package pk.tools.inputs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import pk.tools.interfaces.Selectable;
import pk.tools.primitives.UIElement;

import static com.codeborne.selenide.Selenide.$$;

public class Select extends UIElement implements Selectable {

     final ElementsCollection options = $$("option");

    public Select(By rootLocator) {
        super(rootLocator);
    }

    public Select(String selenideLocator) {
        super(selenideLocator);
    }

    public Select(SelenideElement selenideElement) {
        super(selenideElement);
    }

    @Override
    public void select(String value) throws InvalidArgumentException {
        options.filterBy(Condition.attribute("text",value)).first().click();
    }

    @Override
    public void selectFirst() {
        options.get(0).click();
    }

    @Override
    public void selectLast() {
        options.get(options.size()).click();
    }

    @Override
    public void selectAny() {
        options.get(0).click();
    }

    @Override
    public void selectNot(String value) {
        options.filterBy(Condition.not(Condition.text(value))).first().click();
    }
}
