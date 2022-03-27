package pk.tools.exceptions;

import pk.tools.StepText;
import pk.tools.primitives.UIElement;

public class StepInjectionException extends Throwable {

    public StepInjectionException(UIElement uiElement, StepText stepText) {
        super("failed trying to integrate step='" + stepText.name() +"' into " + uiElement.getName() + " method");
    }
}
