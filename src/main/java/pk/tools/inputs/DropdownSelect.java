package pk.tools.inputs;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pk.tools.interfaces.Dropdown;

/**
 * Widget to working with custom drop down list which looks as native element <select><option/><</select>
 *
 * example of using:
 *
 *    <html>
 *        <ul>
 *            <li>option1</li>
 *            <li>option2</li>
 *            <li>option3</li>
 *        </ul>
 *    </html>
 *
 * ---------------------------------
 *
 *    <code>
 *        DropdownWC dropdown = new DropdownWC("ul");
 *        dropdown.selectItem("option2");
 *    </code>
 *
 *    Next commands Will be recognized as a @Step and added to the Allure report:
 *      - extend()
 *      - select(value)
 *      - selectFirst()
 *      - selectLast()
 *      - selectNot(value)
 *      - selectAny()
 *      - assertValue(value)
 */
public class DropdownSelect extends Select implements Dropdown {

    public DropdownSelect(By rootLocator) {
        super(rootLocator);
    }

    public DropdownSelect(String selenideLocator) {
        super(selenideLocator);
    }

    public DropdownSelect(SelenideElement selenideElement) {
        super(selenideElement);
    }

    @Override
    public void extend() {
        root.click();
    }

    @Override
    public void select(String value) {
        extend();
        super.select(value);
    }
}
