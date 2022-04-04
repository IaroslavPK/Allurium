package pk.tools.primitives;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.epam.jdi.tools.pairs.Pair;
import io.qameta.allure.Allure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pk.tools.AbstractWidget;
import pk.tools.StepTextProvider;
import pk.tools.UiSteps;
import pk.tools.interfaces.ListComponent;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.*;

@NoArgsConstructor
@Slf4j
public class UIElement implements ListComponent {

    @Getter
    protected SelenideElement root;

    @Setter
    protected String id = "";

    @Getter
    @Setter
    protected String type = "";

    @Getter
    @Setter
    protected String name = "";

    @Setter
    protected String assignNameMethod = "text";

    @Getter
    @Setter
    protected String description = "";

    @Getter
    @Setter
    protected AbstractWidget parent = null;

    @Getter
    protected String pageHost = "";

    protected boolean stepsConsoleLoggingEnabled = true;
    protected boolean stepsReportLoggingEnabled = true;

    private static String nameWrapStart = "";
    private static String nameWrapEnd = "";

    static {
        nameWrapStart = "[";
        nameWrapEnd = "]";
    }

    protected UIElement(String selenideLocator) {
        root = Selenide.$(selenideLocator);
    }

    protected UIElement(String selenideLocator, String name) {
        root = Selenide.$(selenideLocator);
        this.name = name;
    }

    protected UIElement(By locator) {
        root = Selenide.$(locator);
    }

    protected UIElement(By locator, String name) {
        root = Selenide.$(locator);
        this.name = name;
    }

    public UIElement(SelenideElement selenideElement) {
        root = selenideElement;
    }

    protected UIElement(SelenideElement selenideElement, String name) {
        root = selenideElement;
        this.name = name;
    }

    public static UIElement from(By locator) {
        return new UIElement(locator);
    }

    public static UIElement from(By locator, String name) {
        return new UIElement(locator, name);
    }

    public static UIElement from(SelenideElement selenideElement) {
        return new UIElement(selenideElement);
    }

    public static UIElement from(SelenideElement selenideElement, String name) {
        return new UIElement(selenideElement, name);
    }

    public static UIElement from(String selenideLocator) {
        return new UIElement(selenideLocator);
    }

    public static UIElement from(String selenideLocator, String name) {
        return new UIElement(selenideLocator, name);
    }

    @Override
    public String getId() {
        if (!id.equals("")) return id;
        return root.text();
    }

    public int getWidth() {
        return root.getWrappedElement().getRect().getWidth();
    }

    public int getHeight() {
        return root.getWrappedElement().getRect().getHeight();
    }

    protected void logStep(String stepText) {
        if (stepsConsoleLoggingEnabled) log.info(stepText);
        if (stepsReportLoggingEnabled) Allure.step(stepText);
    }

    public void logStepToReport(String stepName) {
        applyName();
        String parentName = "";
        if (parent != null) parentName = parent.getName();
        logStep(StepTextProvider.getStepText(stepName,
                Pair.$("{name}", wrappedName()),
                Pair.$("{element}", type),
                Pair.$("{parent}", parentName))
        );
    }

    public void logStepToReport(String stepName, Pair<String,String>... additionalPatterns) {
        applyName();
        String parentName = "";
        if (parent != null) parentName = parent.getName();
        List<Pair<String, String>> patterns = new ArrayList<>();
        patterns.add(Pair.$("{name}", wrappedName()));
        patterns.add(Pair.$("{element}", type));
        patterns.add(Pair.$("{parent}", parentName));
        patterns.addAll(Arrays.asList(additionalPatterns));
        logStep(StepTextProvider.getStepText(stepName, patterns));
    }

    @SuppressWarnings("unchecked")
//    @Deprecated
//    protected void logStep(StepText stepText) {
//        applyName();
//        String parentName = "";
//        if (parent != null) parentName = parent.getName();
//        logStep(stepText.getStepText(
//                Pair.$("{name}", wrappedName()),
//                Pair.$("{element}", type),
//                Pair.$("{parent}", parentName)));
//    }
//
//    @Deprecated
//    public void logStep(StepText stepText, Pair<String,String>... additionalPatterns) {
//        applyName();
//        String parentName = "";
//        if (parent != null) parentName = parent.getName();
//        List<Pair<String, String>> patterns = new ArrayList<>();
//        patterns.add(Pair.$("{name}", wrappedName()));
//        patterns.add(Pair.$("{element}", type));
//        patterns.add(Pair.$("{parent}", parentName));
//        patterns.addAll(Arrays.asList(additionalPatterns));
//        logStep(stepText.getStepText(patterns));
//    }

    protected void applyName() {
        if (name.equals(""))
            applyName(assignNameMethod);
    }

    public void applyName(String method) {
        switch (method) {
            case "text":
                name = root.getText();
                break;
            case "href":
                name = root.getAttribute("href");
                break;
            case "id":
                name = root.getAttribute("id");
                break;
        }
    }

    protected String wrappedName() {
        return nameWrapStart + name + nameWrapEnd;
    }

    /**
     * @return native selenide element
     */
    public SelenideElement get() {
        return root;
    }

    public String text() {
        applyName();
        return get().text();
    }

    public boolean isDisplayed() {
        applyName();
        return root.isDisplayed();
    }

    public String getText() {
        applyName();
        return getText(true);
    }

    public String getText(String uniqueStepText) {
        applyName();
        logStep(uniqueStepText);
        return getText(true);
    }

    public String getText(boolean logAsStepOrNot) {
        applyName();
        if (logAsStepOrNot) {
            logStepToReport("get_text");
            return root.text();
        } else
            return root.text();
    }

    public void click() {
        logStepToReport("click");
        root.scrollIntoView("{behavior: \"auto\", block: \"center\", inline: \"center\"}");
        click(ClickOptions.usingDefaultMethod(), false);
    }

    public void click(String uniqueStepText) {
        logStepToReport(uniqueStepText);
        root.scrollIntoView("{behavior: \"auto\", block: \"center\", inline: \"center\"}");
        root.hover().click();
    }

    public void click(boolean logAsStepOrNot) {
        if (logAsStepOrNot) {
            logStepToReport("click");
            click();
        } else
            click();
    }

    @SuppressWarnings("unchecked")
    public void click(ClickOptions clickOptions) {
        logStepToReport("click");
        root.scrollIntoView("{behavior: \"auto\", block: \"center\", inline: \"center\"}");
        root.hover().click(clickOptions);
    }

    public void click(ClickOptions clickOptions, boolean logAsStepOrNot) {
        if (logAsStepOrNot) {
            logStepToReport("click");
            root.hover().click(clickOptions);
        } else
            root.hover().click(clickOptions);
    }

    /**
     * Полезно при работе с автоскроллом. Иначе не всегда успевает проскроллиться и кликает мимо элемента
     */
    public void clickWithDelay(int seconds) {
        UiSteps.waiting(seconds, "перед кликом");
        root.scrollIntoView("{behavior: \"auto\", block: \"start\", inline: \"start\"}");
        click(ClickOptions.usingDefaultMethod());
    }

    @SuppressWarnings("unchecked")
    public void assertHasText(String text) {
        logStepToReport("assert_text", Pair.$("{text}", text));
        root.shouldHave(Condition.text(text));
    }

    public void assertHasText(String text, String uniqueStepText) {
        logStep(uniqueStepText);
        root.shouldHave(Condition.text(text));
    }

    public void assertHasText(String text, boolean logAsStepOrNot) {
        if (logAsStepOrNot)
            assertHasText(text);
        else
            root.shouldHave(Condition.text(text));
    }

    @SuppressWarnings("unchecked")
    public void assertValue(String text) {
        logStepToReport("assert_value", Pair.$("{text}", text));
        root.shouldHave(Condition.value(text));
    }

    public void assertValue(String text,  String uniqueStepText) {
        logStep(uniqueStepText);
        assertValue(text);
    }

    public void assertValue(String text,  boolean logAsStepOrNot) {
        if (logAsStepOrNot)
            assertValue(text);
        else
            root.shouldHave(Condition.value(text));
    }

    @SuppressWarnings("unchecked")
    public void scrollIntoView() {
        logStepToReport("scroll");
        root.scrollIntoView("{behavior: \"auto\", block: \"center\", inline: \"center\"}");
    }

    /**
     * Проверка, что элемент отображается на текущем экране, т.е. в видимой части страницы. Полезно при проверке автоскролла.
     */
//    public void assertVisibleInViewport() {
//        logStep(StepText.Assert_visibleInViewPort);
//        Assertions.assertThat()
//        Assert.assertEquals(executeJavaScript(GlobalVariable.isVisibleInViewport, root), Boolean.TRUE);
//    }

    public void assertVisible() {
        logStepToReport("assert_visible");
        root.shouldBe(visible);
    }

    public void assertVisible(String uniqueStepText) {
        logStep(uniqueStepText);
        root.shouldBe(visible);
    }

    public void assertVisible(boolean logAsStepOrNot) {
        if (logAsStepOrNot)
            assertVisible();
        else
            root.shouldBe(visible);
    }

    public void assertNotVisible() {
        logStepToReport("assert_not_visible");
        root.shouldNotBe(visible);
    }

    public void assertNotVisible(String uniqueStepText) {
        logStep(uniqueStepText);
        root.shouldNotBe(visible);
    }

    public void assertNotVisible(boolean logAsStepOrNot) {
        if (logAsStepOrNot)
            assertNotVisible();
        else
            root.shouldNotBe(visible);
    }

    @SuppressWarnings("unchecked")
    public void assertVisibleWithDuration(int seconds) {
        logStepToReport("assert_visible_with_duration", Pair.$("{sec}", String.valueOf(seconds)));
        root.shouldBe(visible, Duration.ofSeconds(seconds));
    }

    public void assertExists() {
        logStepToReport("assert_exist");
        root.should(exist);
    }

    public void assertExists(String uniqueStepText) {
        logStep(uniqueStepText);
        root.should(exist);
    }

    public void assertExists(boolean logAsStepOrNot) {
        if (logAsStepOrNot)
            assertExists();
        else
            root.should(exist);
    }

    public void assertNotExists() {
        logStepToReport("assert_not_exist");
        root.shouldNot(exist);
    }

    public void assertNotExists(String uniqueStepText) {
        logStep(uniqueStepText);
        root.shouldNot(exist);
    }

    public void assertNotExists(boolean logAsStepOrNot) {
        if (logAsStepOrNot)
            assertNotExists();
        else
            root.shouldNot(exist);
    }

    public void hover() {
        logStepToReport("hover");
        root.hover();
    }

    public void hover(String uniqueStepText) {
        logStepToReport(uniqueStepText);
    }

    public void hover(boolean logAsStepOrNot) {
        if (logAsStepOrNot)
            hover();
        else
            root.hover();
    }

    @SuppressWarnings("unchecked")
    public void assertHasCssClass(String clazz) {
        logStepToReport("assert_has_css_class", Pair.$("{clazz}", clazz));
        root.shouldHave(cssClass(clazz));
    }

    public void assertHasCssClass(String clazz, boolean logAsStepOrNot) {
        if (logAsStepOrNot)
            assertHasCssClass(clazz);
        else
            root.shouldHave(cssClass(clazz));
    }

    @SuppressWarnings("unchecked")
    public void assertHasNotCssClass(String clazz) {
        logStepToReport("assert_has_not_css_class", Pair.$("{clazz}", clazz));
        root.shouldNotHave(cssClass(clazz));
    }

    public void assertHasNotCssClass(String clazz, boolean logAsStepOrNot) {
        if (logAsStepOrNot)
            assertHasNotCssClass(clazz);
        else
            root.shouldNotHave(cssClass(clazz));
    }

    @SuppressWarnings("unchecked")
    public void assertHasCssClass(String clazz, int duringSeconds) {
        logStepToReport("assert_has_css_class_during_time",
                Pair.$("{clazz}", clazz),
                Pair.$("{seconds}", String.valueOf(duringSeconds)));
        root.shouldBe(cssClass(clazz), Duration.ofSeconds(duringSeconds));
    }

    @SuppressWarnings("unchecked")
    public void shouldNotHaveCssClass(String clazz, int duringSeconds) {
        logStepToReport("assert_has_not_css_class_during_time",
                Pair.$("{clazz}", clazz),
                Pair.$("{seconds}", String.valueOf(duringSeconds)));
        root.shouldNotBe(cssClass(clazz), Duration.ofSeconds(30));
    }

}