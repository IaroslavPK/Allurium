package pk.tools.aspects;

import com.epam.jdi.tools.pairs.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import pk.tools.exceptions.StepInjectionException;
import pk.tools.inputs.InputField;

@Aspect
public class InputAspects {

    @Around("execution (* pk.tools.inputs.InputField.write(..))")
    @SuppressWarnings("unchecked")
    public void stepWriteInjection(ProceedingJoinPoint invocation) throws StepInjectionException {
        InputField inputField = (InputField) invocation.getThis();
        String typingText = (String) invocation.getArgs()[0];
        try {
            inputField.logStepToReport("write", Pair.$("{text}", typingText));
            invocation.proceed();
        } catch (Throwable tex) {
            throw new StepInjectionException(inputField, "write");
        }
    }

    @Around("execution (* pk.tools.inputs.InputField.clear(..))")
    @SuppressWarnings("unchecked")
    public void stepClearInjection(ProceedingJoinPoint invocation) throws StepInjectionException {
        InputField inputField = (InputField) invocation.getThis();
        try {
            inputField.logStepToReport("clear");
            invocation.proceed();
        } catch (Throwable tex) {
            throw new StepInjectionException(inputField, "clear");
        }
    }

    @Around("execution (* pk.tools.inputs.InputField.pressEnter(..))")
    @SuppressWarnings("unchecked")
    public void stepPressEnter(ProceedingJoinPoint invocation) throws StepInjectionException {
        InputField inputField = (InputField) invocation.getThis();
        try {
            inputField.logStepToReport("text_field_press_enter");
            invocation.proceed();
        } catch (Throwable tex) {
            throw new StepInjectionException(inputField, "text_field_press_enter");
        }
    }

    @Around("execution (* pk.tools.inputs.InputField.assertEmpty(..))")
    @SuppressWarnings("unchecked")
    public void stepAssertEmptyInjection(ProceedingJoinPoint invocation) throws StepInjectionException {
        InputField inputField = (InputField) invocation.getThis();
        try {
            inputField.logStepToReport("text_field_assert_blank");
            invocation.proceed();
        } catch (Throwable tex) {
            throw new StepInjectionException(inputField, "text_field_assert_blank");
        }
    }
}
