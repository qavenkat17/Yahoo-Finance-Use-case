package YahooFinance.UseCaseAutomation;

import java.time.Duration;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YahooFinancePage {

	 private WebDriver driver;
	    private WebDriverWait wait;
	    private static final Logger logger = Logger.getLogger(YahooFinancePage.class.getName());

	    @FindBy(name = "p")
	    private WebElement searchBox;

	    @FindBy(xpath = "//li[@role='option' and @data-id='result-quotes-0']")
	    private WebElement firstSuggestion;

	    @FindBy(xpath = "//span[@data-testid='qsp-price']")
	    private WebElement stockPriceElement;

	    @FindBy(xpath = "//fin-streamer[@data-field='regularMarketPreviousClose']")
	    private WebElement previousClose;

	    @FindBy(xpath = "//span[@title='Volume']/following::span[1]")
	    private WebElement volume;

	    public YahooFinancePage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        PageFactory.initElements(driver, this);
	    }

	    public void searchStock(String stockSymbol) {
	        wait.until(ExpectedConditions.elementToBeClickable(searchBox)).sendKeys(stockSymbol);
	        wait.until(ExpectedConditions.elementToBeClickable(firstSuggestion)).click();
	    }

	    public double getStockPrice() {
	        wait.until(ExpectedConditions.visibilityOf(stockPriceElement));
	        return Double.parseDouble(stockPriceElement.getText().replaceAll(",", ""));
	    }

	    public void captureAdditionalData() {
	        wait.until(ExpectedConditions.visibilityOf(previousClose));
	        wait.until(ExpectedConditions.visibilityOf(volume));
	        logger.info("Previous Close: " + previousClose.getText());
	        logger.info("Volume: " + volume.getText());
	    }
}
