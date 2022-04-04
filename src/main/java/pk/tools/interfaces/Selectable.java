package pk.tools.interfaces;

import org.openqa.selenium.InvalidArgumentException;

/**
 * Interface for pk.tools.examples.widgets which serves to selecting options like radio buttons ore any
 * other elements instead.
 */
public interface Selectable extends WebComponent {

    void select(String value) throws InvalidArgumentException;

    void selectFirst();

    void selectLast();

    void selectAny();

    void selectNot(String value);
}
