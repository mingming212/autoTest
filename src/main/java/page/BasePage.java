package page;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by helena.liu on 2019/3/26.
 */
public class BasePage {
    static WebElement find(By locator){
        try{
            System.out.println(locator+"---------"+Driver.getCurrentDriver().getPageSource());
            return Driver.getCurrentDriver().findElement(locator);
        }catch (Exception e){
            System.out.println("没找到 "+locator+"---------"+Driver.getCurrentDriver().getPageSource());
            Driver.getCurrentDriver().findElement(By.xpath("//*[contains(@text,'下次再说') or contains(@text,'允许')]")).click();
            sleep(1000);
            System.out.println("下次再说/允许"+"---------"+Driver.getCurrentDriver().getPageSource());
            return Driver.getCurrentDriver().findElement(locator);
        }
    }

    static By locate(String locator){
        if(locator.matches("/.*")){
            return By.xpath(locator);
        }else {
            return By.id(locator);
        }
    }

    static By text(String content){
        return By.xpath("//*[@text='"+content+"']");

    }

    static void sleep(int sleepTime_millis){
        try {
            Thread.sleep(sleepTime_millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
































}
