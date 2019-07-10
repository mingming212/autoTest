package justTest;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by whs on 2019/3/11.
 */
public class testXueXiQiangGuo {
    static AndroidDriver driver;

    @BeforeAll
    public static void setUp() throws IOException, InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println("当前时间："+df.format(new Date()));// new Date()为获取当前系统时间
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability("platform","android");
        capabilities.setCapability("deviceName","P4M7N15425007120");
//        capabilities.setCapability("udid","P4M7N15425007120");//写在这里没用，只能是启动server的时候设置参数

        //雪球APP
        capabilities.setCapability("appPackage","cn.xuexi.android");
        capabilities.setCapability("appActivity","com.alibaba.android.rimet.biz.SplashActivity");
        //api demo apk
//        capabilities.setCapability("appPackage","io.appium.android.apis");
//        capabilities.setCapability("appActivity",".ApiDemos");
        capabilities.setCapability("automationName","uiautomator2");
        capabilities.setCapability("noReset","true");
        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        driver=new AndroidDriver(remoteUrl,capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        System.out.println("是否黑屏："+isScreenLock() );
        if(isScreenLock()){//如果黑屏，去唤醒屏幕
            Runtime.getRuntime().exec("adb shell input keyevent 26");//模拟power键
            System.out.println("已唤醒屏幕" );
        }
//        System.out.println("-----"+driver.getPageSource());
//        System.out.println("---------"+driver.getPageSource().contains("com.android.keyguard:id/pinEntry"));
        if(driver.getPageSource().contains("com.android.keyguard:id/pinEntry")){//当前页面是唤醒后的背景图页
//        if(driver.getPageSource().contains("com.android.keyguard:id/magazinelockscreen")){//当前页面是唤醒后的背景图页
            System.out.println("当前activity： "+driver.currentActivity());
            unlock_secret();//输入密码解锁
//            unlock_gesture();//手势密码解锁
        }
    }

    //判断是否锁屏
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
            System.out.println("屏幕已唤醒，现在去输入密码解锁" );
            TouchAction touch = new TouchAction(driver);
            //唤醒后，滑动页面，使进入输入密码页
            touch.press(PointOption.point(233, 555)).moveTo(PointOption.point(800, 555)).release().perform();
            //输入密码0000
            for(int i=0;i<4;i++) {
                driver.findElement(By.id("com.android.keyguard:id/key0")).click();
            }
            sleep(5000);
    }

    //手势密码解锁，以华为MT7为例，这里的手势密码是一个"了"字
    public static void unlock_gesture() throws InterruptedException {
        System.out.println("屏幕已唤醒，现在去手势密码解锁" );
        int width=driver.manage().window().getSize().width;
        int height=driver.manage().window().getSize().height;
        System.out.println("屏幕大小："+width+" X "+height );

        TouchAction touch = new TouchAction(driver);
        //唤醒后，滑动页面，使进入输入密码页
        touch.press(PointOption.point(233, 455)).moveTo(PointOption.point(800, 455)).release().perform();
        PointOption point1=PointOption.point(269,1071);//坐标绝对值，不能用，要用偏移量
        PointOption point2=PointOption.point(809,1071);//坐标绝对值，不能用，要用偏移量
        PointOption point3=PointOption.point(539,1341);//坐标绝对值，不能用，要用偏移量
        PointOption point4=PointOption.point(539,1611);//坐标绝对值，不能用，要用偏移量
        PointOption point5=PointOption.point(269,1341);//坐标绝对值，不能用，要用偏移量

        sleep(1000);
        touch.press(PointOption.point(269, 1071))
                .moveTo(PointOption.point(133*4, 0))
                .moveTo(PointOption.point(-133*2,133*2))
                .moveTo(PointOption.point(0,133*2))
                .moveTo(PointOption.point(-133*2,-133*2))
                .release().perform();
        sleep(4000);

    }

    @Test
    //阅读文章
    public void testArticle() throws InterruptedException {
        sleep(3000);
        testXianshiWait("//*[@resource-id='cn.xuexi.android:id/home_bottom_tab_button_work' and @content-desc='学习']");//首页，即点击首页中间那个学习按钮
        TouchAction action=new TouchAction(driver);
        int count=0;
        while(true) {
            action.press(PointOption.point(507, 1400)).moveTo(PointOption.point(507, 400)).release().perform();
            action.press(PointOption.point(507, 1400)).moveTo(PointOption.point(507, 400)).release().perform();
            action.press(PointOption.point(507, 1400)).moveTo(PointOption.point(507, 400)).release().perform();
            System.out.println(""+count);
            sleep(2000);
            try{
                driver.findElement(By.xpath("//*[contains(@text,'2019-')]")).click();
//              -----翻到底，代表阅读完成
                while(true){
                    String oldpage=driver.getPageSource();
                    action.press(PointOption.point(507, 1300)).moveTo(PointOption.point(507, 400)).release().perform();
                    String newpage=driver.getPageSource();
                    if(newpage.equals(oldpage)){
                        break;
                    }
                }
//                sleep(300);
//              返回上层页面，即学习页，继续循环往下翻
                driver.navigate().back();
                count++;

            }catch (Exception e){
                e.printStackTrace();
            }
            if(count==12){//阅读结束
                break;
            }
        }
    }

//    @Test
    public void testTouchAction() {
        sleep(10000);
        TouchAction action = new TouchAction(driver);
        int count = 0;
        while (true) {
            action.press(PointOption.point(507, 1400)).moveTo(PointOption.point(507, 400)).release().perform();

//            sleep(300);
            count++;
            if(count>3){
                break;
            }
        }

    }



    @Test
    public void testVideo(){//视频
        System.out.println("调节音量~");
        driver.pressKeyCode(25);
        driver.pressKeyCode(25);
        sleep(3000);
//        driver.findElementByAccessibilityId("视听学习").click();
        testXianshiWait("//*[@resource-id='cn.xuexi.android:id/home_bottom_tab_text' and @text='视听学习']");

        TouchAction action=new TouchAction(driver);
        int count=0;
        while(true) {
            action.press(PointOption.point(507, 1400)).moveTo(PointOption.point(507, 400)).release().perform();
            action.press(PointOption.point(507, 1400)).moveTo(PointOption.point(507, 400)).release().perform();
//            action.press(PointOption.point(507, 1400)).moveTo(PointOption.point(507, 400)).release().perform();
//            action.press(PointOption.point(507, 1400)).moveTo(PointOption.point(507, 400)).release().perform();
//            action.press(PointOption.point(507, 1400)).moveTo(PointOption.point(507, 400)).release().perform();
//            action.press(PointOption.point(507, 1400)).moveTo(PointOption.point(507, 400)).release().perform();
//            action.press(PointOption.point(507, 1400)).moveTo(PointOption.point(507, 400)).release().perform();
            System.out.println(""+count);
            try{
                driver.findElement(By.xpath("//*[contains(@text,'2019-')]")).click();
                sleep(50*1000);//50秒
                action.press(PointOption.point(507, 1500)).moveTo(PointOption.point(507, 1100)).release().perform();
                sleep(50*1000);
                action.press(PointOption.point(507, 1500)).moveTo(PointOption.point(507, 1100)).release().perform();
                sleep(30*1000);
                action.press(PointOption.point(507, 1500)).moveTo(PointOption.point(507, 1100)).release().perform();

//              返回上层页面，继续循环往下翻
                driver.navigate().back();
                count++;

            }catch (Exception e){
                e.printStackTrace();
            }
            if(count==8){//结束
                break;
            }
        }
    }

//    @Test
    public void testSubscribe(){//订阅----找不到减号和加号的区别，无法定位元素，放弃
        testXianshiWait("//android.widget.TextView[@text='我的']");
//        driver.findElement(By.xpath("//android.widget.TextView[@text='我的']")).click();
        driver.findElementByAccessibilityId("学习积分").click();
        TouchAction action =new TouchAction(driver);
        //点击订阅的去看看按钮
        while(true){
            action.press(PointOption.point(500,1500)).moveTo(PointOption.point(500,500)).release().perform();
            action.press(PointOption.point(500,1500)).moveTo(PointOption.point(500,500)).release().perform();
            try{
                WebElement element=driver.findElement(By.xpath("//*[@content-desc='订阅']/following-sibling::*[3]"));
                String desc=element.getAttribute("content-desc");
                System.out.println(desc);
                if(desc.equals("去看看")){
                    element.click();
                    break;
                }else if(desc.equals("已完成")){
                    break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
//        向下翻，查找有没有加号，有的话，点击加号，返回到首页
        while(true){
            action.press(PointOption.point(500,1500)).moveTo(PointOption.point(500,500)).release().perform();
            try{
                WebElement element=driver.findElement(By.xpath(""));//找不到对号和加号的区别，无法定位元素，放弃

            }catch (Exception e){
                e.printStackTrace();
            }
        }
//        返回到首页

    }

    @Test
    public void testCollect() {//收藏
//        testXianshiWait("(//*[@resource-id='cn.xuexi.android:id/home_bottom_tab_icon_group'])[3]");//首页，即点击首页中间那个学习按钮
        testXianshiWait("//*[@resource-id='cn.xuexi.android:id/home_bottom_tab_button_work' and @content-desc='学习']");//首页，即点击首页中间那个学习按钮
        TouchAction action = new TouchAction(driver);
        int count = 0;
        while (count < 3) {
            action.press(PointOption.point(500, 1500)).moveTo(PointOption.point(500, 500)).release().perform();
            action.press(PointOption.point(500, 1500)).moveTo(PointOption.point(500, 500)).release().perform();
            action.press(PointOption.point(500, 1500)).moveTo(PointOption.point(500, 500)).release().perform();
            driver.findElement(By.xpath("//*[contains(@text,'2019-')]")).click();
            sleep(2000);
            int deviceHeight=driver.manage().window().getSize().height;
            if(deviceHeight==1920){//三星手机
                action.press(PointOption.point(848, 1846)).release().perform();//网页上是webview，使用点击坐标的方式点击收藏按钮

            }else {
                action.press(PointOption.point(859, 1754)).release().perform();//网页上是webview，使用点击坐标的方式点击收藏按钮
            }
            driver.navigate().back();
            count++;
        }
    }


    @Test
    public void testShare(){//分享
        testXianshiWait("//*[@resource-id='cn.xuexi.android:id/home_bottom_tab_button_work' and @content-desc='学习']");//首页，即点击首页中间那个学习按钮
        driver.findElement(By.xpath("//*[contains(@text,'2019-')]")).click();
        for(int i=0;i<2;i++){
            System.out.println("i= "+i);
            driver.findElementById("cn.xuexi.android:id/my_news_avatar").click();//右上角的三个点

            /*
            //分享到微信圈
            driver.findElement(By.xpath("(//*[@resource-id='cn.xuexi.android:id/img_gv_item'])[4]")).click();//第4个分享按钮，即分享给微信朋友圈
            driver.findElement(By.id("com.tencent.mm:id/ki")).click();//发表按钮
*/

            /*//分享到微信好友
            driver.findElement(By.xpath("(//*[@resource-id='cn.xuexi.android:id/img_gv_item'])[3]")).click();//第3个分享按钮，即分享给微信好友
            sleep(3000);
            //之后没有选择好友的操作，所以这里是假装分享给好友
            driver.findElement(By.id("com.tencent.mm:id/kx")).click();//分享到微信后，在微信登录页，点击左上角的返回按钮
            */
            //分享到学习强国
            driver.findElementById("cn.xuexi.android:id/img_gv_item").click();//第一个分享按钮，即分享到学习强国
            sleep(3000);
            //之后没有选择分享到哪里的操作，所以这里是假装分享
            driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='返回']")).click();//分享到学习强国后，在分享页的选择联系人页面，点击左上角的返回

            System.out.println("    i= "+i);
        }
        System.out.println("out~ ");
        sleep(1000);
        driver.navigate().back();
    }

//    @Test
    public void testBack(){
        testXianshiWait("//*[@resource-id='cn.xuexi.android:id/home_bottom_tab_button_work' and @content-desc='学习']");//首页，即点击首页中间那个学习按钮
        driver.findElement(By.xpath("//*[contains(@text,'2019-')]")).click();
//        sleep(1000);
        driver.navigate().back();
        sleep(3000);
    }

//    @Test
    public void testVoice(){
        System.out.println("调节音量~");
        driver.pressKeyCode(25);//音量减小，在MT7上，安卓6.0系统，减小的是媒体音量
        driver.pressKeyCode(25);
        sleep(3000);
    }










//    @Test
    //显示等待
    public void testXianshiWait(String xpath){
//        By waitElement=By.xpath("//*[contains(@text,'自选') and contains(@resource-id, 'tab_name')]");

        By waitElement=By.xpath(xpath);
        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(waitElement)).click();
        sleep(3000);

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
//        driver.stopRecordingScreen();
        driver.quit();
    }

}
