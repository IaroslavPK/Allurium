package pk.tools.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import pk.tools.inputs.InputField;

@Aspect
public class InputAspects {

    @Before("execution (* pk.tools.inputs.InputField.write(..))")
    public void stepWrite(JoinPoint joinPoint) {
        InputField inputField = (InputField) joinPoint.getThis();
        System.out.println("WE_HERE!!!");
        System.out.println(inputField.getClass().getName()+"+++");
    }
}
