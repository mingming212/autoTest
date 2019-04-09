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
            Driver.getCurrentDriver().findElement(text("下次再说")).click();
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
































}
