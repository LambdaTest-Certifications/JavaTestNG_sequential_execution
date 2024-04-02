import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

class Parent {

    public String username = System.getenv("LT_USERNAME");
    public String accesskey = System.getenv("LT_ACCESS_KEY");
    public RemoteWebDriver driver;
    public String gridURL = "mobile-hub.lambdatest.com";
    String status;
    String hub;
    SessionId sessionId;

    @org.testng.annotations.Parameters(value = {"browser", "platformVersion", "platform", "deviceName"})
    @BeforeClass
    public void setUp(String browser, String platformVersion, String platform, String deviceName) throws Exception {
        try {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("name", "Mobile");
            capabilities.setCapability("build", "MobileDemo");
            capabilities.setCapability("deviceName", deviceName);
//            capabilities.setCapability("udid", "RZCX101S1DE");
            capabilities.setCapability("platformVersion",platformVersion );
            capabilities.setCapability("platform", platform);
            capabilities.setCapability("network", true);
            capabilities.setCapability("autoGrantPermissions", true);
            capabilities.setCapability("autoAcceptAlerts", true);
            capabilities.setCapability("timezone", "UTC+13:00");
//            capabilities.setCapability("app", "lt://APP1016020161711527430715639");
            capabilities.setCapability("isRealMobile", true);
            capabilities.setCapability("console", true);
            capabilities.setCapability("visual", true);

            StopWatch driverStart = new StopWatch();
            driverStart.start();

            hub = "https://" + username + ":" + accesskey + "@" + gridURL + "/wd/hub";
            driver = new RemoteWebDriver(new URL(hub), capabilities);
            System.out.println("driver created");

            sessionId = driver.getSessionId();
            System.out.println(sessionId);
            driverStart.stop();
            float timeElapsed = driverStart.getTime() / 1000f;
            System.out.println("Driver initiate time" + "   " + timeElapsed);
            ArrayList<Float> TotalTimeDriverSetup = new ArrayList<Float>();
            TotalTimeDriverSetup.add(timeElapsed);
            System.out.println(TotalTimeDriverSetup);

        } catch (
                MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception f) {
            System.out.println(f);

        }

    }

    @AfterClass
    public void tearDown() throws Exception {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}
