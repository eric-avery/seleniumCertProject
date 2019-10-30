package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends TestAppPages {

	public By searchTextField = By.xpath("/html[1]/body[1]/div[5]/div[1]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/div[1]/input[1]");
	public By searchButton = By.xpath("/html[1]/body[1]/div[5]/div[1]/div[1]/form[1]/div[2]/div[1]/div[1]/button[1]");
	public By xButton = By.xpath("/html[1]/body[1]/div[5]/div[1]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/div[2]/button[1]");
	String stock;
	WebDriverWait wait = new WebDriverWait(driver, 20);
	
	public HomePage(RemoteWebDriver driver, String stockInput) {
		super(driver);
		this.stock = stockInput;
		new PopUpUtils(driver).addWebViewPopupBtns(
		        By.xpath("//*[contains(@text,'Share Location')]"),
		        By.xpath("//*[contains(@text,'Share Now')]"))
		        .clickMulitplePopUps(2);
	}
	
	public HomePage(RemoteWebDriver driver) {
		super(driver);
	}

	public void setStockTwo(int choice) {
		this.stock = this.getStockTwoData(choice);
	}
	
	public String getStock() {
		return this.stock;
	}
	
	public void putStock(String stock){
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.searchTextField));
		driver.findElement(this.searchTextField).click();
		driver.findElement(this.xButton).click();
	    driver.findElement(this.searchTextField).sendKeys(stock);
	}
	
	public StockOnePage submitStockOne(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(this.searchButton));
	    driver.findElement(this.searchButton).click();
	    return new StockOnePage(driver);
	}
	
	public StockTwoPage submitStockTwo(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(this.searchButton));
	    driver.findElement(searchButton).click();
	    return new StockTwoPage(driver);
	}

	public void goBack() {
		driver.navigate().back();
	}
}
