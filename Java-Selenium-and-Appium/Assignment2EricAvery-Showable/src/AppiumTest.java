import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.html5.*;
import org.openqa.selenium.logging.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Cookie.Builder;

import io.appium.java_client.*;
import io.appium.java_client.android.*;
import pages.*;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResult;
import com.perfecto.reportium.test.result.TestResultFactory;

/**
 * This template is for users that use DigitalZoom Reporting (ReportiumClient).
 * For any other use cases please see the basic template at https://github.com/PerfectoCode/Templates.
 * For more programming samples and updated templates refer to the Perfecto Documentation at: http://developers.perfectomobile.com/
 */
public class AppiumTest {
    
    @SuppressWarnings({ "rawtypes", "deprecation", "static-access" })
	public static void main(String[] args) throws MalformedURLException, IOException {
        System.out.println("Run started");
        
        String browserName = "mobileOS";
        DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
        String host = "";
        capabilities.setCapability("user", "");
        capabilities.setCapability("password", ""); //Password removed for submission
        
        capabilities.setCapability("deviceName", "");
        //capabilities.setCapability("platformName", "Android");
        capabilities.setCapability(WindTunnelUtils.WIND_TUNNEL_PERSONA_CAPABILITY, WindTunnelUtils.EMPTY);
        capabilities.setCapability("model", "Galaxy S7");
        
        final String mapsPkg = "com.google.android.apps.maps";
        final String mapsAct = "com.google.android.maps.MapsActivity";
        capabilities.setCapability("appPackage", mapsPkg);
        capabilities.setCapability("appActivity", mapsAct);
        
        capabilities.setCapability("automationName", "Appium");
        
        PerfectoLabUtils.setExecutionIdCapability(capabilities, host);
        
        ///capabilities.setCapability("scriptName", "PerfectoEngineeringAssigment2EricAvery");

        // Proxy Settings for AndroidDriver
        System.getProperties().put("");
     	System.getProperties().put("");
        
        AndroidDriver driver = new AndroidDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        
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
        		String name = "" + "Assigment 2 Test: Map Distances and Times" + " - Data Set " + choice;
	        	reportiumClient.testStart(name, new TestContext("Perfecto", "Assignment"));
	        	reportiumClient.testStep("Set Location and Open Google Maps Home Page");
	        	MapHome home = new MapHome(driver);
	        	home.closeMaps(driver);
	        	reportiumClient.testStep("Set Up NA Map");
	        	home.setUpFirstMap(choice);
	        	home.enterNAAddress(choice);
	        	home.clickSearch();
	        	reportiumClient.testStep("Get NA Route and Output Time to Restaurant");
	        	NATestCase na = new NATestCase(driver);
	        	na.setStartingPoint();
	        	na.displayRouteTime(choice);
	        	reportiumClient.testStep("Close NA Map");
	        	home.closeMaps(driver);
	        	reportiumClient.testStep("Set Up EU Map");
	        	home.setUpSecondMap(choice);
	        	home.enterEUAddress(choice);
	        	home.clickSearch();
	        	reportiumClient.testStep("Get EU Route and Output Time to Destination");
	        	EUTestCase eu = new EUTestCase(driver);
	        	eu.setStartingPoint();
	        	eu.displayRouteTime(choice);
	        	reportiumClient.testStep("Close EU Map");
	        	home.closeMaps(driver);
	        	reportiumClient.testStop(TestResultFactory.createSuccess());
	            choice++;
	            String reportPdfUrl = (String)(driver.getCapabilities().getCapability("reportPdfUrl"));
                System.out.println(reportPdfUrl);
                PerfectoLabUtils.downloadReport(driver, "pdf", "C:\\test\\report");
        	}
        } catch (Exception e) {
        	try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
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
