package tests;

import org.testng.annotations.Test;
import pk.tools.UiSteps;
import example.pages.GoogleSearchPage;
import example.pages.SearchResultPage;


public class AlluriumBuildTest extends TestBase {

    GoogleSearchPage googleSearchPage = new GoogleSearchPage();
    SearchResultPage searchResultPage = new SearchResultPage();

    @Test
    public void googleSearch() {
        UiSteps.open("https://www.google.com/");
        googleSearchPage.getSubmitCookiesPopup().submitButton.click();
        googleSearchPage.getSearchField().write("bounty view");
        googleSearchPage.getSearchField().clear();
        googleSearchPage.getSearchField().assertEmpty();
        googleSearchPage.getSearchField().write("mountain view");
        googleSearchPage.getSearchField().pressEnter();
        searchResultPage.getSearchResults().get(110).click();
    }

    @Test(enabled = false)
    public void searchImage() {
        UiSteps.open("https://www.google.com/");
        googleSearchPage.getSubmitCookiesPopup().submitButton.click();
        googleSearchPage.getSearchField().write("mountain view");
        googleSearchPage.getSearchField().pressEnter();
        searchResultPage.getSearchGroups().get("Images").click();
    }
}
