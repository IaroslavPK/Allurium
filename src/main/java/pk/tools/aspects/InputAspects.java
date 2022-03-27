package pk.tools.aspects;

import com.epam.jdi.tools.pairs.Pair;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import pk.tools.StepText;
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
            throw new StepInjectionException(inputField, StepText.Write);
        }
    }

    @Around("execution (* pk.tools.inputs.InputField.clear(..))")
    @SuppressWarnings("unchecked")
    public void stepClearInjection(ProceedingJoinPoint invocation) throws StepInjectionException {
        InputField inputField = (InputField) invocation.getThis();
        try {
            inputField.logStep(StepText.Clear_text_field);
            invocation.proceed();
        } catch (Throwable tex) {
            throw new StepInjectionException(inputField, StepText.Clear_text_field);
        }
    }

    @Around("execution (* pk.tools.inputs.InputField.assertEmpty(..))")
    @SuppressWarnings("unchecked")
    public void stepAssertEmptyInjection(ProceedingJoinPoint invocation) throws StepInjectionException {
        InputField inputField = (InputField) invocation.getThis();
        try {
            inputField.logStep(StepText.Verify_text_field_blank);
            invocation.proceed();
        } catch (Throwable tex) {
            throw new StepInjectionException(inputField, StepText.Verify_text_field_blank);
        }
    }
}
