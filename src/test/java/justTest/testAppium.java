package justTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.TapOptions;
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
 * Created by whs on 2019/3/11.
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void test_getAttribute() throws InterruptedException {
//        Thread.sleep(3000);
        try {
            String resourceId=driver.findElement(By.id("com.xueqiu.android:id/tab_name")).getAttribute("resourceId");
            System.out.println("1111111: "+resourceId);
        }catch (Exception e){
            System.out.println("未找到 3");
        }
        System.out.println("222222: ");
    }

    @Test
    public void test_longPress(){//长按
        WebElement e=  driver.findElement(By.id("portfolio_stockName"));
        TouchAction touch=new TouchAction(driver);
        touch.longPress(ElementOption.element(e));
        touch.release();
        touch.perform();
    }

    @Test
    public void test_getLocation() throws InterruptedException {
        WebElement e=  driver.findElement(By.id("com.xueqiu.android:id/tv_search"));
        Point point=e.getLocation();
        System.out.println("1----"+point.toString());
        e.click();
        driver.navigate().back();
        driver.navigate().back();

        TouchAction touch =new TouchAction(driver);
        touch.press(PointOption.point(353,105)).release().perform();//可点击
        driver.navigate().back();
        driver.navigate().back();

        touch.tap(PointOption.point(353,105)).perform();//可点击
        Thread.sleep(10000);
        driver.navigate().back();
        driver.navigate().back();

        Thread.sleep(10000);
    }

    @Test
    //点击clickable=false的元素-》结果：可以点击，并跳转页面
    public void testClickUnclickable() throws InterruptedException {
        WebElement e=  driver.findElement(By.xpath("//*[@text='沪深' and @resource-id='com.xueqiu.android:id/button_text']"));
        System.out.println("是否可被点击："+e.getAttribute("clickable"));
        e.click();
        Thread.sleep(10000);

    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }































}
