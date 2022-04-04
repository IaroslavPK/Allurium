package pk.tools.inputs;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;
import groovy.transform.InheritConstructors;
import pk.tools.primitives.UIElement;

@InheritConstructors
public class CheckBox extends UIElement {

    public CheckBox(SelenideElement selenideElement) {
        super(selenideElement);
    }

    public void check() {
        if (!root.isSelected())
            root.click(ClickOptions.usingJavaScript());
        else
            logStep("checkbox is already ON");
    }

    public void uncheck() {
        if (root.isSelected())
           root.click(ClickOptions.usingJavaScript());
        else
           logStep("checkbox is already OFF");
    }
}
