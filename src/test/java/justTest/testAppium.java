package justTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by helena.liu on 2019/3/11.
 */
public class testAppium {
    static AppiumDriver driver;

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability("platform","android");
        capabilities.setCapability("deviceName","P4M7N15425007120");
        capabilities.setCapability("appPackage","com.xueqiu.android");
        capabilities.setCapability("appActivity","com.xueqiu.android.view.WelcomeActivityAlias");
        capabilities.setCapability("noReset","true");
        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        driver=new AppiumDriver(remoteUrl,capabilities);
    }

    @Test
    public void findEle() throws InterruptedException {
//        Thread.sleep(3000);
        try {
            String resourceId=driver.findElement(By.id("com.xueqiu.android:id/tab_name")).getAttribute("resourceId");
            System.out.println("1111111: "+resourceId);
        }catch (Exception e){
            System.out.println("未找到 3");
        }
        System.out.println("222222: ");
    }

    public void longPress(){//长按
        WebElement e=  driver.findElement(By.id("portfolio_stockName"));
        TouchAction touch=new TouchAction(driver);
        touch.longPress(ElementOption.element(e));
        touch.release();
        touch.perform();
    }

    @Test
    public void getLocat(){

        WebElement e=  driver.findElement(By.id("com.xueqiu.android:id/tv_search"));
        Point point=e.getLocation();
        System.out.println("1----"+point.toString());
        TouchAction touch =new TouchAction(driver);
        PointOption pointOption=new PointOption();
        touch.press(PointOption.point(353,105));
//        touch.tap(1,2);
    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }































}
