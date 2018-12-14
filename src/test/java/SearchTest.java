import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest {

    public WebDriver webDriver;

    private SearchResultsPage searchResultsPage;


    @BeforeMethod
    public void beforeMethod() {
        WebDriverManager.chromedriver().setup();
        webDriver = new FirefoxDriver();
        webDriver.get("https://www.google.com");
    }

    @Test
    public void searchTest() {
        String searchTerm = "Selenium";

        searchResultsPage = new SearchResultsPage(webDriver);

        searchResultsPage.search(searchTerm);
        Assert.assertEquals(searchResultsPage.getSizeOfList(), 10, "Size of result list on first page is not equal to 10");

        Assert.assertTrue(searchResultsPage.searchOnTabOne(searchTerm), "Not all results on first page contains searchTerm");

        searchResultsPage.goToNextTab();
        Assert.assertEquals(searchResultsPage.getSizeOfList(), 10, "Size of result list on second page is not equal to 10");

        Assert.assertTrue(searchResultsPage.searchOnTabTwo(searchTerm), "Not all results on second page contains searchTerm");
    }

    @AfterMethod
    public void afterMethod(){
        webDriver.quit();
    }
}
