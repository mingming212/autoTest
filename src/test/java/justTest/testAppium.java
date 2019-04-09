package justTest;

import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        try {
            String text=driver.findElement(By.id("com.xueqiu.android:id/tab_name")).getAttribute("text");
            System.out.println("text: "+text);
//        }catch (Exception e){
//            System.out.println("未找到 1");
//        }

        try {
//            driver.findElement(By.id("com.xueqiu.android:id/tab_name")).getAttribute("id");
        }catch (Exception e){
            System.out.println("未找到 2");
        }

        try {
            String resourceId=driver.findElement(By.id("com.xueqiu.android:id/tab_name")).getAttribute("resourceId");
            System.out.println("1111111: "+resourceId);

        }catch (Exception e){
            System.out.println("未找到 3");
        }
        System.out.println("222222: ");
    }

    @AfterAll
    public static void tearDown(){
        driver.quit();
    }































}
