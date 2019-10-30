package pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class StockOnePage extends TestAppPages {
	
	public float stockOnePrice;
	public float stockOneHigh;
	public float stockOneLow;
	public float stockOneEPS;
	
	public By stockOnePriceField = By.xpath("/html[1]/body[1]/div[8]/div[1]/div[5]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/sticky-header[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/g-card-section[1]/div[1]/g-card-section[1]/div[1]/span[1]/span[1]/span[1]");
	public By stockOneAnnualHighField = By.xpath("/html[1]/body[1]/div[8]/div[1]/div[5]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/sticky-header[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/g-card-section[2]/div[1]/g-flippy-carousel[1]/div[1]/div[1]/ol[1]/li[2]/div[1]/div[1]/table[1]/tbody[1]/tr[2]/td[2]");
	public By stockOneAnnualLowField = By.xpath("/html[1]/body[1]/div[8]/div[1]/div[5]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/sticky-header[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/g-card-section[2]/div[1]/g-flippy-carousel[1]/div[1]/div[1]/ol[1]/li[2]/div[1]/div[1]/table[1]/tbody[1]/tr[3]/td[2]");
	public By stockOneEPSField = By.xpath("/html[1]/body[1]/div[8]/div[1]/div[5]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/sticky-header[1]/div[2]/g-flippy-carousel[1]/div[1]/div[1]/ol[1]/li[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/g-card-section[1]/table[2]/tbody[1]/tr[3]/td[2]");
	WebDriverWait wait = new WebDriverWait(driver, 20);
	
	public StockOnePage(RemoteWebDriver driver) {
		super(driver);
	}

	public float getCurrentStockPrice() {
		return this.stockOnePrice;
	}
	
	public void setCurrentStockPrice() {
		//Pop-up handler
		new PopUpUtils(driver).addWebViewPopupBtns(
		        By.xpath("//*[contains(@text,'x')]"),
		        By.xpath("//*[contains(@text,'Do Not Show Me This Message Again')]"))
		        .clickMulitplePopUps(2);
		//Set first stock price for summary and comparison later
		wait.until(ExpectedConditions.visibilityOfElementLocated(this.stockOnePriceField));
		this.stockOnePrice = Float.parseFloat(driver.findElement(this.stockOnePriceField).getText());
	}

	public float getStockOneAnnualHighField() {
		return this.stockOneHigh;
	}

	public void setStockOneAnnualHighField() {
		//Wait and then scroll to the right of stock stats view
		this.driver.manage().timeouts()
        .implicitlyWait(20, TimeUnit.SECONDS);
		Map<String, Object> down = new HashMap<String, Object>();
		down.put("location", "1498,1305");
		down.put("operation", "down");
		driver.executeScript("mobile:touch:tap", down);
		
		Map<String, Object> scroll = new HashMap<String, Object>();
		List<String> coordinates = new ArrayList<String>();
		coordinates.add("1140,1305");
		coordinates.add("1079,1305");
		scroll.put("location", coordinates);
		scroll.put("auxiliary", "notap");
		driver.executeScript("mobile:touch:drag", scroll);
		
		Map<String, Object> up = new HashMap<String, Object>();
		up.put("location", "1079,1305");
		up.put("operation", "up");
		driver.executeScript("mobile:touch:tap", up);
		
		//Set first stock 52-week high for summary and comparison later
		wait.until(ExpectedConditions.visibilityOfElementLocated(this.stockOneAnnualHighField));
		this.stockOneHigh = Float.parseFloat(driver.findElement(this.stockOneAnnualHighField).getText());
	}

	public float getStockOneAnnualLowField() {
		return this.stockOneLow;
	}

	public void setStockOneAnnualLowField() {
		//Set first stock 52-week low for summary and comparison later
		wait.until(ExpectedConditions.visibilityOfElementLocated(this.stockOneAnnualLowField));
		this.stockOneLow = Float.parseFloat(driver.findElement(this.stockOneAnnualLowField).getText());
	}
	
	public float getStockOneEPSField() {
		return this.stockOneEPS;
	}
	
	public void setStockOneEPSField() {
		//Wait and then scroll down to the EPS view
		this.driver.manage().timeouts()
        .implicitlyWait(20, TimeUnit.SECONDS);
		
		Map<String, Object> params1 = new HashMap<String, Object>();
		params1.put("location", "175,1515");
		params1.put("operation", "down");
		driver.executeScript("mobile:touch:tap", params1);
		
		Map<String, Object> params2 = new HashMap<String, Object>();
		List<String> coordinates2 = new ArrayList<String>();
		coordinates2.add("169,1465");
		coordinates2.add("155,1393");
		params2.put("location", coordinates2);
		params2.put("auxiliary", "notap");
		driver.executeScript("mobile:touch:drag", params2);
		
		Map<String, Object> params3 = new HashMap<String, Object>();
		List<String> coordinates3 = new ArrayList<String>();
		coordinates3.add("146,1203");
		coordinates3.add("146,1087");
		params3.put("location", coordinates3);
		params3.put("auxiliary", "notap");
		driver.executeScript("mobile:touch:drag", params3);
		
		Map<String, Object> params4 = new HashMap<String, Object>();
		List<String> coordinates4 = new ArrayList<String>();
		coordinates4.add("146,1283");
		params4.put("location", coordinates4);
		params4.put("auxiliary", "notap");
		driver.executeScript("mobile:touch:drag", params4);
		
		Map<String, Object> params6 = new HashMap<String, Object>();
		List<String> coordinates6 = new ArrayList<String>();
		coordinates6.add("140,847");
		coordinates6.add("140,764");
		params6.put("location", coordinates6);
		params6.put("auxiliary", "notap");
		driver.executeScript("mobile:touch:drag", params6);
		
		Map<String, Object> params5 = new HashMap<String, Object>();
		List<String> coordinates5 = new ArrayList<String>();
		coordinates5.add("143,983");
		coordinates5.add("140,906");
		params5.put("location", coordinates5);
		params5.put("auxiliary", "notap");
		driver.executeScript("mobile:touch:drag", params5);
		
		Map<String, Object> params8 = new HashMap<String, Object>();
		List<String> coordinates8 = new ArrayList<String>();
		coordinates8.add("163,309");
		params8.put("location", coordinates8);
		params8.put("auxiliary", "notap");
		driver.executeScript("mobile:touch:drag", params8);
		
		Map<String, Object> params7 = new HashMap<String, Object>();
		List<String> coordinates7 = new ArrayList<String>();
		coordinates7.add("146,671");
		params7.put("location", coordinates7);
		params7.put("auxiliary", "notap");
		driver.executeScript("mobile:touch:drag", params7);
		
		Map<String, Object> params9 = new HashMap<String, Object>();
		List<String> coordinates9 = new ArrayList<String>();
		coordinates9.add("152,591");
		coordinates9.add("158,505");
		params9.put("location", coordinates9);
		params9.put("auxiliary", "notap");
		driver.executeScript("mobile:touch:drag", params9);
		
		Map<String, Object> params10 = new HashMap<String, Object>();
		List<String> coordinates10 = new ArrayList<String>();
		coordinates10.add("158,431");
		coordinates10.add("163,359");
		params10.put("location", coordinates10);
		params10.put("auxiliary", "notap");
		driver.executeScript("mobile:touch:drag", params10);
		
		Map<String, Object> params11 = new HashMap<String, Object>();
		List<String> coordinates11 = new ArrayList<String>();
		coordinates11.add("163,276");
		coordinates11.add("163,244");
		coordinates11.add("163,214");
		params11.put("location", coordinates11);
		params11.put("auxiliary", "notap");
		driver.executeScript("mobile:touch:drag", params11);
		
		Map<String, Object> params12 = new HashMap<String, Object>();
		List<String> coordinates12 = new ArrayList<String>();
		coordinates12.add("163,193");
		coordinates12.add("163,181");
		params12.put("location", coordinates12);
		params12.put("auxiliary", "notap");
		driver.executeScript("mobile:touch:drag", params12);
		
		Map<String, Object> params13 = new HashMap<String, Object>();
		List<String> coordinates13 = new ArrayList<String>();
		coordinates13.add("163,169");
		coordinates13.add("163,166");
		params13.put("location", coordinates13);
		params13.put("auxiliary", "notap");
		driver.executeScript("mobile:touch:drag", params13);
		
		Map<String, Object> params14 = new HashMap<String, Object>();
		List<String> coordinates14 = new ArrayList<String>();
		coordinates14.add("163,163");
		params14.put("location", coordinates14);
		params14.put("auxiliary", "notap");
		driver.executeScript("mobile:touch:drag", params14);
		
		Map<String, Object> params15 = new HashMap<String, Object>();
		List<String> coordinates15 = new ArrayList<String>();
		coordinates15.add("163,160");
		params15.put("location", coordinates15);
		params15.put("auxiliary", "notap");
		driver.executeScript("mobile:touch:drag", params15);
		
		Map<String, Object> params16 = new HashMap<String, Object>();
		params16.put("location", "163,160");
		params16.put("operation", "up");
		driver.executeScript("mobile:touch:tap", params16);
		
		this.driver.manage().timeouts()
        .implicitlyWait(20, TimeUnit.SECONDS);
		Map<String, Object> params17 = new HashMap<String, Object>();
		params17.put("location", "187,1506");
		params17.put("operation", "down");
		driver.executeScript("mobile:touch:tap", params17);
		
		Map<String, Object> params18 = new HashMap<String, Object>();
		List<String> coordinates18 = new ArrayList<String>();
		coordinates18.add("187,1420");
		coordinates18.add("178,1301");
		params18.put("location", coordinates18);
		params18.put("auxiliary", "notap");
		driver.executeScript("mobile:touch:drag", params18);
		
		Map<String, Object> params19 = new HashMap<String, Object>();
		List<String> coordinates19 = new ArrayList<String>();
		coordinates19.add("166,1224");
		params19.put("location", coordinates19);
		params19.put("auxiliary", "notap");
		driver.executeScript("mobile:touch:drag", params19);
		
		Map<String, Object> params20 = new HashMap<String, Object>();
		List<String> coordinates20 = new ArrayList<String>();
		coordinates20.add("140,564");
		coordinates20.add("140,446");
		params20.put("location", coordinates20);
		params20.put("auxiliary", "notap");
		driver.executeScript("mobile:touch:drag", params20);
		
		Map<String, Object> params21 = new HashMap<String, Object>();
		List<String> coordinates21 = new ArrayList<String>();
		coordinates21.add("158,1093");
		coordinates21.add("146,974");
		params21.put("location", coordinates21);
		params21.put("auxiliary", "notap");
		driver.executeScript("mobile:touch:drag", params21);
		
		Map<String, Object> params25 = new HashMap<String, Object>();
		List<String> coordinates25 = new ArrayList<String>();
		coordinates25.add("140,157");
		coordinates25.add("140,131");
		params25.put("location", coordinates25);
		params25.put("auxiliary", "notap");
		driver.executeScript("mobile:touch:drag", params25);
		
		Map<String, Object> params22 = new HashMap<String, Object>();
		List<String> coordinates22 = new ArrayList<String>();
		coordinates22.add("143,820");
		coordinates22.add("140,680");
		params22.put("location", coordinates22);
		params22.put("auxiliary", "notap");
		driver.executeScript("mobile:touch:drag", params22);
		
		Map<String, Object> params26 = new HashMap<String, Object>();
		List<String> coordinates26 = new ArrayList<String>();
		coordinates26.add("137,110");
		params26.put("location", coordinates26);
		params26.put("auxiliary", "notap");
		driver.executeScript("mobile:touch:drag", params26);
		
		Map<String, Object> params23 = new HashMap<String, Object>();
		List<String> coordinates23 = new ArrayList<String>();
		coordinates23.add("140,333");
		params23.put("location", coordinates23);
		params23.put("auxiliary", "notap");
		driver.executeScript("mobile:touch:drag", params23);
		
		Map<String, Object> params24 = new HashMap<String, Object>();
		List<String> coordinates24 = new ArrayList<String>();
		coordinates24.add("140,256");
		coordinates24.add("140,199");
		params24.put("location", coordinates24);
		params24.put("auxiliary", "notap");
		driver.executeScript("mobile:touch:drag", params24);
		
		Map<String, Object> params27 = new HashMap<String, Object>();
		List<String> coordinates27 = new ArrayList<String>();
		coordinates27.add("137,101");
		params27.put("location", coordinates27);
		params27.put("auxiliary", "notap");
		driver.executeScript("mobile:touch:drag", params27);
		
		Map<String, Object> params28 = new HashMap<String, Object>();
		params28.put("location", "137,101");
		params28.put("operation", "up");
		driver.executeScript("mobile:touch:tap", params28);
		
		//Set first stock EPS for summary and comparison later
		wait.until(ExpectedConditions.visibilityOfElementLocated(this.stockOneEPSField));
		this.stockOneEPS = Float.parseFloat(driver.findElement(this.stockOneEPSField).getText());
	}

	//Output stock one summary
	public void summary(){
		System.out.println("Today's price of " + this.getCurrentStockPrice() + "is " + Math.round(((1-(this.getCurrentStockPrice()/this.getStockOneAnnualHighField()))*100)) + "% lower than the 52 week high and " + Math.round(((this.getCurrentStockPrice()/this.getStockOneAnnualLowField())-1)*100) + "% higher than the 52 week low." + System.lineSeparator());
	}
	
	//goBack function
	public void goBack() {
		driver.navigate().back();
	}
}
