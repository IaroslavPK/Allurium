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

    //also working
//    @Before("execution (* pk.tools.inputs.InputField.write(..))")
//    public void stepWrite(JoinPoint joinPoint) {
//        InputField inputField = (InputField) joinPoint.getThis();
//        String typingText = (String) joinPoint.getArgs()[0];
//        inputField.logStep(StepText.Write, Pair.$("{text}", typingText));
//    }

    @Around("execution (* pk.tools.inputs.InputField.write(..))")
    public void stepWrite(ProceedingJoinPoint invocation) throws StepInjectionException {
        InputField inputField = (InputField) invocation.getThis();
        String typingText = (String) invocation.getArgs()[0];
        try {
            inputField.logStep(StepText.Write, Pair.$("{text}", typingText));
            invocation.proceed();
        } catch (Throwable e) {
            throw new StepInjectionException(inputField, StepText.Write);
        }
    }
}
