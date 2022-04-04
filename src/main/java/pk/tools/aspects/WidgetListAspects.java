package pk.tools.aspects;

import io.qameta.allure.Allure;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class WidgetListAspects {

    @Around("execution (* pk.tools.ListWC.get(..))")
    public void getEvent(ProceedingJoinPoint invocation) throws Throwable {
        try {
            invocation.proceed();
        } catch (Throwable e) {
            Allure.getLifecycle().stopStep();
            throw new Throwable();
        }
    }

    @Around("execution (* pk.tools.ListWC.assertSize(..))")
    public void stepAssertSize() {}

    @Around("execution (* pk.tools.ListWC.assertSize(..))")
    public void stepAssertSizeGreatedThen() {}

    @Around("execution (* pk.tools.ListWC.assertSizeGreaterThenOrEqual(..))")
    public void stepAssertSizeEqualOrGreateThen() {}

    @Around("execution (* pk.tools.ListWC.assertSize(..))")
    public void stepAssertSizeLowerThen() {}
}
