import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResultFactory;
import pages.*;

public class RemoteWebDriverTest {

    @SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
        System.out.println("Run started");
        
        String browserName = "mobileOS";
        DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
        String host = "";
        capabilities.setCapability("user", "");
        capabilities.setCapability("password", "");

        //capabilities.setCapability("deviceName", "");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability(WindTunnelUtils.WIND_TUNNEL_PERSONA_CAPABILITY, WindTunnelUtils.EMPTY);
        capabilities.setCapability("model", "iPad Pro 9.7");

        capabilities.setCapability("automationName", "PerfectoMobile");

        PerfectoLabUtils.setExecutionIdCapability(capabilities, host);

        //capabilities.setCapability("scriptName", "PerfectoEngineeringAssigment1EricAvery");

        // Proxy Settings for RemoteWebDriver
        System.getProperties().put("");
     	System.getProperties().put("");
     	
        RemoteWebDriver driver = new RemoteWebDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        
        WebDriverWait wait = new WebDriverWait(driver, 20);
        int choice = 1;
     			
        // Reporting client. For more details, see http://developers.perfectomobile.com/display/PD/Reporting
        PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
                .withProject(new Project("My Project", "1.0"))
                .withJob(new Job("My Job", 45))
                .withContextTags("tag1")
                .withWebDriver(driver)
                .build();
        ReportiumClient reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);

        try {
        	while (choice<4) {
        		String name = "" + "Assigment 1 Test: Stock Comparison" + " - Data Set " + choice;
	        	reportiumClient.testStart(name, new TestContext("Perfecto", "Assignment"));
	            reportiumClient.testStep("Home Screen");
	            HomePage data = new HomePage(driver);
	            HomePage home = new HomePage(driver, data.getStockOneData(choice));
	            home.isLoggedIn();
	            wait.until(ExpectedConditions.visibilityOfElementLocated(home.searchTextField));
	            home.begin(choice);
	            wait.until(ExpectedConditions.visibilityOfElementLocated(home.searchTextField));
	    		home.putStock(home.getStock());
	    		home.submitStockOne();
	    		reportiumClient.testStep("Stock Option One Screen");
	    		StockOnePage stockOne = new StockOnePage(driver);
	    		stockOne.setCurrentStockPrice();
	    		stockOne.setStockOneAnnualHighField();
	    		stockOne.setStockOneAnnualLowField();
	    		stockOne.setStockOneEPSField();
	    		reportiumClient.testStep("Stock Option One Summary");
	    		stockOne.summary();
	    		reportiumClient.testStep("Stock Option Two Screen");
	    		home.isLoggedIn();
	    		home = new HomePage(driver, data.getStockTwoData(choice));
	    		wait.until(ExpectedConditions.visibilityOfElementLocated(home.searchTextField));
	    		home.setStockTwo(choice);
	    		home.putStock(home.getStock());
	    		home.submitStockTwo();
	    		StockTwoPage stockTwo = new StockTwoPage(driver);
	    		stockTwo.setCurrentStockPrice();
	    		stockTwo.setStockTwoAnnualHighField();
	    		stockTwo.setStockTwoAnnualLowField();
	    		stockTwo.setStockTwoEPSField();
	    		reportiumClient.testStep("Stock Option Two Summary & Stock Comparison");
	    		stockTwo.compare(choice);
	            reportiumClient.testStop(TestResultFactory.createSuccess());
	            choice++;
	            
	            String reportPdfUrl = (String)(driver.getCapabilities().getCapability("reportPdfUrl"));
                System.out.println(reportPdfUrl);
                PerfectoLabUtils.downloadReport(driver, "pdf", "C:\\test\\report");
        	}
        } catch (Exception e) {
            reportiumClient.testStop(TestResultFactory.createFailure(e.getMessage(), e));
            e.printStackTrace();
        } finally {
            try {
                driver.quit();

                // Retrieve the URL to the DigitalZoom Report (= Reportium Application) for an aggregated view over the execution
                String reportURL = reportiumClient.getReportUrl();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Run ended");
    }

    private static void switchToContext(RemoteWebDriver driver, String context) {
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
        Map<String,String> params = new HashMap<String,String>();
        params.put("name", context);
        executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
    }

    private static String getCurrentContextHandle(RemoteWebDriver driver) {
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
        String context =  (String) executeMethod.execute(DriverCommand.GET_CURRENT_CONTEXT_HANDLE, null);
        return context;
    }

    private static List<String> getContextHandles(RemoteWebDriver driver) {
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
        List<String> contexts =  (List<String>) executeMethod.execute(DriverCommand.GET_CONTEXT_HANDLES, null);
        return contexts;
    }
}
