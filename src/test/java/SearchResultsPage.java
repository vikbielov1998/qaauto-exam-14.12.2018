import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultsPage {

    public WebDriver webDriver;

    @FindBy (xpath = "//input[@name='q']")
    private WebElement searchField;

    @FindBy (xpath = "//div[@class='srg']/div[@class='g']")
    private List<WebElement> resultsList;

    @FindBy (xpath = "//a[@aria-label='Page 2']")
    private WebElement linkToNextTab;

    public SearchResultsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void search(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);
    }

    public int getSizeOfList(){
        return resultsList.size();
    }

    public boolean searchOnTabOne(String searchTerm){

        for (WebElement result : resultsList){
            if (!result.getText().toLowerCase().contains(searchTerm.toLowerCase())) return false;
        }

        return true;
    }

    public void goToNextTab(){
        linkToNextTab.click();
    }

    public boolean searchOnTabTwo(String searchTerm){

        for (WebElement result : resultsList){
            if (!result.getText().toLowerCase().contains(searchTerm.toLowerCase())) return false;
        }

        return true;
    }
}
