package justTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by whs on 2019/3/11.
 */
public class testAppium {
    static AndroidDriver driver;

    @BeforeAll
    public static void setUp() throws IOException, InterruptedException {
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability("platform","android");
        capabilities.setCapability("deviceName","P4M7N15425007120");
        capabilities.setCapability("appPackage","com.xueqiu.android");
        capabilities.setCapability("appActivity","com.xueqiu.android.view.WelcomeActivityAlias");
        capabilities.setCapability("noReset","true");
        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        driver=new AndroidDriver(remoteUrl,capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("是否黑屏："+isScreenLock() );
        if(isScreenLock()){//如果黑屏，去唤醒屏幕
            Runtime.getRuntime().exec("adb shell input keyevent 26");//模拟power键
            System.out.println("已唤醒屏幕" );
        }
        System.out.println("-----"+driver.getPageSource());
        System.out.println("---------"+driver.getPageSource().contains("com.android.keyguard:id/pinEntry"));
//        if(driver.getPageSource().contains("com.android.keyguard:id/pinEntry")){//当前页面是锁屏
        if(driver.getPageSource().contains("com.android.keyguard:id/magazinelockscreen")){//当前页面是唤醒后的背景图页
            System.out.println("当前activity： "+driver.currentActivity());
//            unlock_secret();//输入密码解锁
            unlock_swip();//手势密码解锁
        }
    }

    public static boolean isScreenLock() throws IOException {
        Runtime runtime=Runtime.getRuntime();
        Process process=runtime.exec("adb shell dumpsys power |grep 'Display Power'");
        BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream()));
        Boolean flag=false;
        String line;
        String content = "";
        while((line=br.readLine())!=null){
            content=content+line;
        }
        if(content.contains("Display Power: state=OFF")){
            flag=true;
        }
        process.destroy();
        return flag;
    }

//    @Test
    //密码解锁
    public static void unlock_secret() throws IOException {
//        if (isScreenLock() == false) {
            System.out.println("屏幕已唤醒，现在去输入密码解锁" );
            TouchAction touch = new TouchAction(driver);
            touch.press(PointOption.point(233, 555)).moveTo(PointOption.point(800, 555)).release().perform();
            //输入密码0000
            for(int i=0;i<4;i++) {
                driver.findElement(By.id("com.android.keyguard:id/key0")).click();
            }
            sleep(5000);
//        }
    }

    //手势密码解锁
    public static void unlock_gesture() throws InterruptedException {
        System.out.println("屏幕已唤醒，现在去手势密码解锁" );
        TouchAction touch = new TouchAction(driver);
        touch.press(PointOption.point(233, 555)).moveTo(PointOption.point(800, 555)).release().perform();
//        TouchAction touch=new TouchAction(driver);
        PointOption point1=PointOption.point(269,1071);
        PointOption point2=PointOption.point(809,1071);
        PointOption point3=PointOption.point(539,1341);
        PointOption point4=PointOption.point(539,1611);
        PointOption point5=PointOption.point(269,1341);

//        touch.press(point1).wait(1000);
//        touch.moveTo(point2).wait(1000);
//        touch.moveTo(point3).wait(1000);
        sleep(2000);
        touch.press(point1).moveTo(point2).moveTo(point3).release().perform();
        touch.release().perform();
        sleep(4000);

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
//        System.out.println("1----"+point.toString());
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

    public static void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @AfterAll
    public static void tearDown(){
        driver.quit();
    }































}
