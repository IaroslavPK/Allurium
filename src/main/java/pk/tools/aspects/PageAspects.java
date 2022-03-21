package pk.tools.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import pk.tools.AbstractWidget;
import pk.tools.composers.ListWCTypeBuilder;
import pk.tools.composers.WebElementMetaDateBuilder;

import java.lang.reflect.Field;

@Aspect
public class PageAspects {

    @After("within(@pk.tools.annotations.PageObject *) && execution(*.new(..))")
    public void initializePageElements(JoinPoint joinPoint) {
        Iterable<Field> fields = WebElementMetaDateBuilder
                .getFieldsUpTo(joinPoint.getThis().getClass(), AbstractWidget.class);

        ListWCTypeBuilder.buildType(joinPoint, fields);
    }
}
