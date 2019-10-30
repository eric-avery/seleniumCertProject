package pages;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.RemoteWebDriver;

//TestAppPages Class acts as page manager and DATA PROVIDER class
public abstract class TestAppPages {
	RemoteWebDriver driver;
    String url = "https://www.google.com/finance";
    
    public String getStockOneData (int choice) {
    	if (choice==1) {
    		//DataSet1
    		String stockOneChoice = "NYSE: C";
    		return stockOneChoice;
    	}
    	else if (choice==2) {
    		//DataSet2
        	String stockOneChoice = "NYSE: X";
        	return stockOneChoice;
    	}
    	else {
    		//DataSet3
        	String stockOneChoice = "NYSE: XOM";
        	return stockOneChoice;
    	}	
    }
    	
    public String getStockTwoData (int choice) {
    	if (choice==1) {
    		//DataSet1
    		String stockTwoChoice = "NYSE: TGT";
    		return stockTwoChoice;
    	}
    	else if (choice==2) {
    		//DataSet2
    		String stockTwoChoice = "NYSE: V";
    		return stockTwoChoice;
    	}
    	else {
    		//DataSet3
    		String stockTwoChoice = "NYSE: VZ";
    		return stockTwoChoice;
    	}
    }
    
	
    public TestAppPages(RemoteWebDriver driver){
        this.driver = driver;
    }
    
    public TestAppPages init(){
        return this.isLoggedIn();
    }
    
	public abstract void goBack();
	
	public TestAppPages isLoggedIn() {
		this.driver.manage().timeouts()
        .implicitlyWait(20, TimeUnit.SECONDS);
        this.driver.manage().timeouts()
        .pageLoadTimeout(20, TimeUnit.SECONDS);
        this.driver.get(this.url);
        return this;
	}
	
	public HomePage begin(int choice){
	    return new HomePage(driver, this.getStockOneData(choice));
	}
}


