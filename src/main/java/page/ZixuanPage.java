package page;

import driver.Driver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whs on 2019/4/2.
 */
public class ZixuanPage extends BasePage{
    By search=By.id("action_create_cube");//首页上的自选菜单
    By searchEditText=By.id("search_input_text");
    By add=By.id("follow_btn");//搜索页上的添加自选按钮
    By cancel=By.id("action_close");//搜索页面上的取消按钮

    public ZixuanPage searchInZixuan(String keyword){
        find(search).click();
        find(searchEditText).sendKeys(keyword);
        return this;
    }

    public void addFirstSelected(){//搜索页
        find(add).click();//添加股票
        find(cancel).click();//点击取消按钮，回到自选
    }

    public void removeAllSelected(){
        List<WebElement> list= Driver.getCurrentDriver().findElements(By.id("portfolio_stockName"));
        for(WebElement e: list){
            TouchAction touch=new TouchAction(Driver.getCurrentDriver());
            touch.longPress(ElementOption.element(e));
            touch.release();
            touch.perform();
            find(By.id("md_title")).click();
        }
    }

    public void removeOne_longPress(){
        TouchAction touch=new TouchAction(Driver.getCurrentDriver());
//        touch.longPress().release().perform();
    }
}
