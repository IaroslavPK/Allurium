package pk.tools.interfaces;

import com.codeborne.selenide.SelenideElement;

/**
 * Interface for web components (example.widgets) which have behaviour as native select/option element
 * To use for making default and custom dropdown lists or example.widgets which have container with
 * selectable <li> elements that can be folded/unfolded or shown/hidden.
 */
public interface Dropdown extends WebComponent {

    void extend();

    SelenideElement select(String value);

    boolean isOptionExists(String value);
}
