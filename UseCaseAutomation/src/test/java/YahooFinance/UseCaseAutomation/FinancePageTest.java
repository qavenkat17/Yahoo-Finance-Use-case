package YahooFinance.UseCaseAutomation;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;

public class FinancePageTest {
	
	private static final String URL = "https://finance.yahoo.com/";
    private static final String SEARCH_QUERY = "TSLA";
    private static final double MIN_STOCK_PRICE = 200.0;
    private static WebDriver driver;
    private static Logger logger = Logger.getLogger(FinancePageTest.class.getName());
    YahooFinancePage yfp;
    
  @Test
  public void f() {
	  
	  try {
          yfp.searchStock(SEARCH_QUERY);
          double stockPrice = yfp.getStockPrice();
          if (stockPrice > MIN_STOCK_PRICE) {
              logger.info("Stock price is valid: " + stockPrice);
              System.out.println("Stock price is valid: " + stockPrice);
          } else {
              throw new AssertionError("Stock price is below expected: " + stockPrice);
          }
          yfp.captureAdditionalData();
      } catch (Exception e) {
          logger.severe("Test failed: " + e.getMessage());
          System.out.println("Test failed: " + e.getMessage());
      } 
	  
  }
  @BeforeTest
  public void beforeTest() {
	  
	  driver = new ChromeDriver();
      driver.manage().window().maximize();
      driver.get(URL);
     yfp = new YahooFinancePage(driver);

  }

  @AfterTest
  public void afterTest() {
	             driver.quit();
        }



}
