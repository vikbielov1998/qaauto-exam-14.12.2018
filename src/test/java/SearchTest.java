import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class SearchTest {

    private WebDriver webDriver;


    /**
     * Method runs at the begining of all tests. Sets up browser we want to use and goes to Google.com;
     *
     * @param browserName - browser we want to use.
     */
    @Parameters("browserName")
    @BeforeMethod
    public void beforeMethod(@Optional("chrome") String browserName) {
        if (browserName.toLowerCase().equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        }

        if (browserName.toLowerCase().equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        } else {
            try {
                throw new Exception("browserName " + browserName + " is not supported.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        webDriver.get("https://www.google.com");
    }

    /**
     * Test to check if size of result list equals to 10 and all results contains search term on first two pages.
     */
    @Test
    public void searchTest() {
        String searchTerm = "Selenium";

        SearchResultsPage searchResultsPage = new SearchResultsPage(webDriver);

        searchResultsPage.search(searchTerm);
        Assert.assertEquals(searchResultsPage.getSizeOfList1(), 10, "Size of result list on first page is not equal to 10");

        Assert.assertTrue(searchResultsPage.searchOnTabOne(searchTerm), "Not all results on first page contains searchTerm");

        searchResultsPage.goToNextTab();
        Assert.assertEquals(searchResultsPage.getSizeOfList2(), 10, "Size of result list on second page is not equal to 10");

        Assert.assertTrue(searchResultsPage.searchOnTabTwo(searchTerm), "Not all results on second page contains searchTerm");
    }

    /**
     * Method runs at end of all tests. Closes the browser.
     */
    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }
}
