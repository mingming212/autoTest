package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {
    private static AppiumDriver driver;
    public static void start(){
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//        desiredCapabilities.setCapability("deviceName", "emulator-5554");
        desiredCapabilities.setCapability("deviceName", "P4M7N15425007120");
//            desiredCapabilities.setCapability("appPackage", "com.ijourney.conbow");
//            desiredCapabilities.setCapability("appActivity", "com.ijourney.conbow.MainActivity");
//            desiredCapabilities.setCapability("app","/Users/helena.liu/Downloads/robothousekeeper_v1.2.0.2041_mtest_201901141453.apk");
        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity", "com.xueqiu.android.view.WelcomeActivityAlias");
//        desiredCapabilities.setCapability("app","/Users/helena.liu/Downloads/com.xueqiu.android_11.15_200.apk");
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("noReset","true");
//            desiredCapabilities.setCapability("fullReset","true");
        desiredCapabilities.setCapability("autoGrantPermissions","true");


        URL remoteUrl = null;
        try {
            remoteUrl = new URL("http://localhost:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    }

    public static AppiumDriver getCurrentDriver(){
        return driver;
    }
}
