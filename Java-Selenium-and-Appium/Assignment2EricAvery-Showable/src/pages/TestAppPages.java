package pages;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.html5.Location;

import io.appium.java_client.android.AndroidDriver;

//TestAppPages Class acts as page manager and DATA PROVIDER class
public abstract class TestAppPages {
	@SuppressWarnings("rawtypes")
	AndroidDriver driver;
    Location location;
    //WebDriverWait wait = new WebDriverWait(driver, 20);
    
    public By MapApplicationIcon = By.xpath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.TextView[2]");
	
    public Location getNALocationOne (int choice) {
    	if (choice==1) {
    		//DataSet1 - 3532 Chatham Green Ln. Arlington, TX 76014
    		Location NALocationOne = new Location(32.6851073,-97.1111666,17.75);
    		return NALocationOne;
    	}
    	else if (choice==2) {
    		//DataSet2 - Beverly Hills Police Department / 464 N Rexford Dr, Beverly Hills, CA 90210
    		Location NALocationOne = new Location(34.0727708,-118.4004204,17.75);
    		return NALocationOne;
    	}
    	else {
    		//DataSet3 - The Manhatten at Times Square Hotel / 790 7th Ave New York, NY 10019
    		Location NALocationOne = new Location(40.7614272,-73.9832047,17.75);
    		return NALocationOne;
    	}	
    }
    	
    public String getNAAddressOne (int choice) {
    	if (choice==1) {
    		//DataSet1 - McAlister's Deli / 137 Merchant's Row #101 Arlington, TX 76018
    		String NAAddressOne = "137 Merchant's Row #101 Arlington, TX 76018";
    		return NAAddressOne;
    	}
    	else if (choice==2) {
    		//DataSet2 - La Scala Beverly Hills / 434 N Canon Dr, Beverly Hills, CA 90210
    		String NAAddressOne = "434 N Canon Dr, Beverly Hills, CA 90210";
    		return NAAddressOne;
    	}
    	else {
    		//DataSet3 - Ellen's Stardust Diner / 1650 Broadway New York, NY 10019
    		String NAAddressOne = "1650 Broadway New York, NY 10019";
    		return NAAddressOne;
    	}
    }
    
    public Location getEULocationOne (int choice) {
    	if (choice==1) {
    		//DataSet1 - Eiffel Tower / Champ de Mars, 5 Avenue Anatole France, 75007
    		Location EULocationOne = new Location(48.8548405,2.2972987,16.25);
    		return EULocationOne;
    	}
    	else if (choice==2) {
    		//DataSet2 - Parc au animaux du bois de la bâtie / Petit-Lancy, Chemin de la Bâtie 28, 1213 Petit-Lancy, Switzerland
    		Location EULocationOne = new Location(46.1985455,6.1163619,15);
    		return EULocationOne;
    	}
    	else {
    		//DataSet3 - Venice Marco Polo Airport / Viale Galileo Galilei, 30/1, 30173 Venezia VE, Italy
    		Location EULocationOne = new Location(45.4784058,12.3161552,13.21);
    		return EULocationOne;
    	}	
    }
    	
    public String getEUAddressOne (int choice) {
    	if (choice==1) {
    		//DataSet1 - Starbucks 90 Rue Saint-Dominique, 75007
    		String EUAddressOne = "90 Rue Saint-Dominique, 75007";
    		return EUAddressOne;
    	}
    	else if (choice==2) {
    		//DataSet2 - Manor Genève / Rue de Cornavin 6, 1211 Genève, Switzerland
    		String EUAddressOne = "Rue de Cornavin 6, 1211 Genève, Switzerland";
    		return EUAddressOne;
    	}
    	else {
    		//DataSet3 - Grand Canal / 30100 Venice, Metropolitan City of Venice, Italy
    		String EUAddressOne = "30100 Venice, Metropolitan City of Venice, Italy";
    		return EUAddressOne;
    	}
    }
    
	
    public TestAppPages(@SuppressWarnings("rawtypes") AndroidDriver driver){
        this.driver = driver;
    }
    
    public TestAppPages init(){
        return this;
    }
    
	public abstract void goBack();
	
	//Function sets up NA Map and checks for pop-ups
	public TestAppPages setUpFirstMap(int choice) {
		this.driver.manage().timeouts()
        .implicitlyWait(20, TimeUnit.SECONDS);
		TestAppPages.deviceHome(this.driver);
		this.driver.manage().timeouts()
        .implicitlyWait(20, TimeUnit.SECONDS);
		location = this.getNALocationOne(choice);
		this.driver.setLocation(location);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		TestAppPages.deviceHome(this.driver);
		this.openMaps();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new PopUpUtils(driver).addNativePopupBtns(
				By.xpath("//*[contains(@text,'OK')]"),
		        By.xpath("//*[contains(@text,'SKIP')]"),
		        By.xpath("//*[contains(@text,'Skip')]"),
		        By.xpath("//*[contains(@text,'Accept & Continue')]"),
		        By.xpath("//*[contains(@text,'ACCEPT & CONTINUE')]"),
		        By.xpath("//*[contains(@text,'Got It')]"),
		        By.xpath("//*[contains(@text,'GOT IT')]"))
				.clickMulitplePopUps(5);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        return this;
	}
	
	//Function sets up EU Map and checks for pop-ups
	public TestAppPages setUpSecondMap(int choice) {
		this.driver.manage().timeouts()
        .implicitlyWait(20, TimeUnit.SECONDS);
		TestAppPages.deviceHome(this.driver);
		this.driver.manage().timeouts()
        .implicitlyWait(20, TimeUnit.SECONDS);
		location = this.getEULocationOne(choice);
		this.driver.setLocation(location);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		TestAppPages.deviceHome(this.driver);
		this.openMaps();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new PopUpUtils(driver).addNativePopupBtns(
		        By.xpath("//*[contains(@text,'OK')]"),
		        By.xpath("//*[contains(@text,'SKIP')]"),
		        By.xpath("//*[contains(@text,'Skip')]"),
		        By.xpath("//*[contains(@text,'Accept & Continue')]"),
		        By.xpath("//*[contains(@text,'ACCEPT & CONTINUE')]"),
		        By.xpath("//*[contains(@text,'Got It')]"),
		        By.xpath("//*[contains(@text,'GOT IT')]"))
				.clickMulitplePopUps(5);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        return this;
	}
	
	//Launches the Google Maps Application
	public void openMaps() {
        driver.launchApp();
	}
	
	//Hits device HOME key
	public static void deviceHome(@SuppressWarnings("rawtypes") AndroidDriver driver) {
        HashMap<Object,Object> goToHome = new HashMap<Object, Object>();
        driver.executeScript("mobile:handset:ready", goToHome);
    }
	
	//Closes the Google Maps Application
	@SuppressWarnings("rawtypes")
	public static void closeMaps(AndroidDriver driver) {
        String command = "mobile:application:close";
        Map<String, Object> Parms = new HashMap<String, Object>();
        Parms.put("name", "com.google.android.apps.maps");
        driver.executeScript(command, Parms);
    }
	
	public MapHome begin(int choice){
	    return new MapHome(driver);
	}
}


