package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class EUTestCase extends TestAppPages {
	
	public By startingPointField = By.xpath("//*[@resource-id='com.google.android.apps.maps:id/directions_startpoint_textbox']");
	public By yourLocationButton = By.xpath("//*[@class='android.support.v7.widget.RecyclerView']/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]");
	public By routeTime = By.xpath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.LinearLayout[1]/android.view.View[1]/android.widget.FrameLayout[1]/android.support.v4.view.ViewPager[1]/android.view.View[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.view.View[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]");
	WebDriverWait wait = new WebDriverWait(driver, 20);
	
	@SuppressWarnings("rawtypes")
	public EUTestCase(AndroidDriver driver) {
		super(driver);
	}
	
	//Gets current data set's EU destination address
	public String getEUAddress (int choice) {
		return this.getEUAddressOne(choice);
	}
	
	//Sets directions to begin at your current location
	public void setStartingPoint() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(this.startingPointField));
		driver.findElement(this.startingPointField).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(this.yourLocationButton));
		driver.findElement(this.yourLocationButton).click();
	}
	
	//Outputs route time to the screen
	public void displayRouteTime (int choice) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(this.routeTime));
		String time = driver.findElement(this.routeTime).getText();
		System.out.println("The time from the current device location to " + this.getEUAddress(choice) + " is " + time + "." + System.lineSeparator());
	}
	
	public void goBack() {
		driver.navigate().back();
	}
	
}
