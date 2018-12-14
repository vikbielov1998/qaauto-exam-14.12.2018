import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.lang.Thread.sleep;

class SearchResultsPage {

    private WebDriver webDriver;

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchField;

    @FindBy(xpath = "//div[@class='srg']/div[@class='g']")
    private List<WebElement> resultsList1;

    @FindBy(xpath = "//div[@class='srg']/div[@class='g']")
    private List<WebElement> resultsList2;

    @FindBy(xpath = "//a[@aria-label='Page 2']")
    private WebElement linkToNextTab;

    /**
     * Constructor of SearchResultsPage class;
     *
     * @param webDriver - webDriver instance from SearchTest class.
     */
    SearchResultsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Method to enter search term to field and press "Enter" button;
     *
     * @param searchTerm - term we want to find.
     */
    void search(String searchTerm) {
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(ExpectedConditions.visibilityOf(searchField));
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to get size of result list of necessary elements on first page;
     *
     * @return returns size of result list.
     */
    int getSizeOfList1() {
        return resultsList1.size();
    }

    /**
     * Method to check if all result element on first page contains search term;
     *
     * @param searchTerm - term we want to find;
     * @return returns false if at least one result doesn't contain search term, otherwise returns true.
     */
    boolean searchOnTabOne(String searchTerm) {

        for (WebElement result : resultsList1) {
            if (!result.getText().toLowerCase().contains(searchTerm.toLowerCase())) return false;
        }

        return true;
    }

    /**
     * Method to switch to page 2.
     */
    void goToNextTab() {
        linkToNextTab.click();
    }

    /**
     * Method to get size of result list of necessary elements on second page;
     *
     * @return returns size of result list.
     */
    int getSizeOfList2() {
        return resultsList2.size();
    }

    /**
     * Method to check if all result element on second page contains search term;
     *
     * @param searchTerm - term we want to find;
     * @return returns false if at least one result doesn't contain search term, otherwise returns true.
     */
    boolean searchOnTabTwo(String searchTerm) {

        for (WebElement result : resultsList2) {
            if (!result.getText().toLowerCase().contains(searchTerm.toLowerCase())) return false;
        }

        return true;
    }
}
