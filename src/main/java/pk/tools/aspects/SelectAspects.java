package pk.tools.aspects;

import com.epam.jdi.tools.pairs.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import pk.tools.exceptions.StepInjectionException;
import pk.tools.inputs.DropdownWidget;

@Aspect
public class SelectAspects {

//    @Around("execution (* pk.tools.inputs.Select.extend(..))")
//    public void stepExtendInjection(ProceedingJoinPoint invocation) throws StepInjectionException {
//        DropdownWidget dropdownWidget = (DropdownWidget) invocation.getThis();
//        dropdownWidget.logStepToReport("dropdown_extend");
//        try {
//            invocation.proceed();
//        } catch (Throwable tex) {
//            throw new StepInjectionException(dropdownWidget, "dropdown_select");
//        }
//    }

    @Around("execution (* pk.tools.inputs.Select.select(..))")
    @SuppressWarnings("unchecked")
    public void stepSelectInjection(ProceedingJoinPoint invocation) throws StepInjectionException {
        DropdownWidget dropdownWidget = (DropdownWidget) invocation.getThis();
        String option = (String) invocation.getArgs()[0]; //todo: сделать чтобы шаги не падали если нет аргумента
        try {
            dropdownWidget.logStepToReport("dropdown_select", Pair.$("{option}", option));
            invocation.proceed();
        } catch (Throwable tex) {
            throw new StepInjectionException(dropdownWidget, "dropdown_select");
        }
    }

    @Around("execution (* pk.tools.inputs.Select.selectFirst(..))")
    @SuppressWarnings("unchecked")
    public void stepSelectFirstInjection(ProceedingJoinPoint invocation) throws StepInjectionException {
        DropdownWidget dropdownWidget = (DropdownWidget) invocation.getThis();
        dropdownWidget.logStepToReport("dropdown_select_first");
        try {
            invocation.proceed();
        } catch (Throwable tex) {
            throw new StepInjectionException(dropdownWidget, "dropdown_select_first");
        }
    }

    @Around("execution (* pk.tools.inputs.Select.selectLast(..))")
    @SuppressWarnings("unchecked")
    public void stepSelectLastInjection(ProceedingJoinPoint invocation) throws StepInjectionException {
        DropdownWidget dropdownWidget = (DropdownWidget) invocation.getThis();
        dropdownWidget.logStepToReport("dropdown_select_last");
        try {
            invocation.proceed();
        } catch (Throwable tex) {
            throw new StepInjectionException(dropdownWidget, "dropdown_select_last");
        }
    }

    @Around("execution (* pk.tools.inputs.Select.selectAny(..))")
    @SuppressWarnings("unchecked")
    public void stepSelectAnyInjection(ProceedingJoinPoint invocation) throws StepInjectionException {
        DropdownWidget dropdownWidget = (DropdownWidget) invocation.getThis();
        dropdownWidget.logStepToReport("dropdown_select_any");
        try {
            invocation.proceed();
        } catch (Throwable tex) {
            throw new StepInjectionException(dropdownWidget, "dropdown_select_any");
        }
    }

    @Around("execution (* pk.tools.inputs.Select.assertOptionExist(..))")
    @SuppressWarnings("unchecked")
    public void stepAssertOptionExist(ProceedingJoinPoint invocation) throws StepInjectionException {
        DropdownWidget dropdownWidget = (DropdownWidget) invocation.getThis();
        String option = (String) invocation.getArgs()[0];
        dropdownWidget.logStepToReport("dropdown_assert_option_exist", Pair.$("{option}", option));
        try {
            invocation.proceed();
        } catch (Throwable tex) {
            throw new StepInjectionException(dropdownWidget, "dropdown_assert_option_exist");
        }
    }
}
