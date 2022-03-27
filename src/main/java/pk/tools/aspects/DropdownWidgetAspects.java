package pk.tools.aspects;

import com.epam.jdi.tools.pairs.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import pk.tools.StepText;
import pk.tools.exceptions.StepInjectionException;
import pk.tools.inputs.DropdownWidget;

public class DropdownWidgetAspects {

//    @Around("execution (* pk.tools.inputs.DropdownWidget.select(..))")
//    @SuppressWarnings("unchecked")
//    @SuppressWarnings("unchecked")
//    public void stepWriteInjection(ProceedingJoinPoint invocation) throws StepInjectionException {
//        DropdownWidget dropdownWidget = (DropdownWidget) invocation.getThis();
//        String selectingOption = (String) invocation.getArgs()[0];
//        try {
//            dropdownWidget.logStep(StepText.Dropdown_select, Pair.$("{text}", ));
//            invocation.proceed();
//        } catch (Throwable tex) {
//            throw new StepInjectionException(dropdownWidget, StepText.Dropdown_select);
//        }
//    }
}
