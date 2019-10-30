package pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class MapHome extends TestAppPages {

	NATestCase na = new NATestCase(driver);
	EUTestCase eu = new EUTestCase(driver);
	public By mapSearchBarField = By.xpath("//*[@resource-id='com.google.android.apps.maps:id/search_omnibox_text_box']");
	public By getDirectionsButton = By.xpath("//*[@resource-id='com.google.android.apps.maps:id/placepage_directions_button']//*[@class='android.widget.ImageView']");
	WebDriverWait wait = new WebDriverWait(driver, 20);
	
	@SuppressWarnings("rawtypes")
	public MapHome(AndroidDriver driver) {
		super(driver);
	}
	
	//Searches for the NA Restaurant Address
	public void enterNAAddress (int choice) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(this.mapSearchBarField));
		driver.findElement(this.mapSearchBarField).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(this.mapSearchBarField).sendKeys(na.getNAAddress(choice));
		HashMap<Object,Object> pressEnter = new HashMap<Object, Object>();
		pressEnter.put("keySequence", "KEYBOARD_ENTER");
        driver.executeScript("mobile:presskey", pressEnter);
	}
	
	public void enterEUAddress (int choice) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(this.mapSearchBarField));
		driver.findElement(this.mapSearchBarField).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(this.mapSearchBarField).sendKeys(eu.getEUAddress(choice));
		HashMap<Object,Object> pressEnter = new HashMap<Object, Object>();
		pressEnter.put("keySequence", "KEYBOARD_ENTER");
        driver.executeScript("mobile:presskey", pressEnter);
	}
	
	//Clicks the Get Directions Button
	public void clickSearch() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(this.getDirectionsButton));
		driver.findElement(this.getDirectionsButton).click();
	}
	
	public void goBack() {
		driver.navigate().back();
	}

}
