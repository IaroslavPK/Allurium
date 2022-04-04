package example.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import pk.tools.ListWC;
import pk.tools.annotations.Name;
import pk.tools.annotations.PageObject;
import pk.tools.inputs.InputField;
import pk.tools.primitives.Link;

@PageObject
@Getter
public class SearchResultPage extends BasePage {

    @Name("search")
    protected InputField searchField = InputField.from(By.xpath("//input[@title='Search']"));

    @Name("search groups")
    protected ListWC<Link> searchGroups = new ListWC<>(By.xpath("//div[@role='navigation']/div/div/div/div/div"));

    @Name("search results")
    protected ListWC<Link> searchResults = new ListWC<>(By.xpath("//a//h3"));

}
