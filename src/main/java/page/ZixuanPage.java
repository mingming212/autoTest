package page;

import driver.Driver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whs on 2019/4/2.
 */
public class  ZixuanPage extends BasePage{
    By search=By.id("action_create_cube");//自选首页上的搜索按钮
    By searchEditText=By.id("search_input_text");//自选首页上的搜索框
    By add=By.id("follow_btn");//搜索页上的添加自选按钮
//    By firstInSearch=By.id("stockName");//搜索结果中的第一个股票
    By cancel=By.id("action_close");//搜索页面上的取消按钮
    By firstStock=By.id("portfolio_stockName");//自选列表中的第一个股票

    By recommendStock=By.id("com.xueqiu.android:id/recommend_stock_one");//自选首页，推荐的第一个股票
    By stockNameInDetails=By.id("com.xueqiu.android:id/action_bar_stock_name");//股票详情页，最上面的股票名字
    By addZixuanBtn=By.xpath("//*[@text='加自选']");//右下角的加自选按钮
    By setZixuanBtn=By.xpath("//*[@text='设自选']");
    By deleteZixuanBtn=By.xpath("//*[@text='删除自选']");
    By addAllCommend=By.xpath("//*[@text='加入自选股']");

    public ZixuanPage searchInZixuan(String keyword){
        find(search).click();
        find(searchEditText).sendKeys(keyword);
        return this;
    }

    public String addFirstSelected(){//搜索页
        WebElement selectBtn=find(add);
        //根据选择按钮，获取他的前一个兄弟节点的name，即是股票名字
        //获取股票名字的代码不能单独放在一个方法中：因为这个方法里有click按钮，这个方法执行后，页面就变了
        String selectedName=find(By.xpath("//*[@resource-id='com.xueqiu.android:id/follow_btn']/../preceding-sibling::*/*[@resource-id='com.xueqiu.android:id/stockName']")).getText();
        System.out.println("根据兄弟节点计算出的添加的股票名字："+selectedName);
        selectBtn.click();//添加股票
        return selectedName;
    }


    public String getFirstInZixuan(){
        WebElement e=find(firstStock);
        String firstStockName=e.getText();
        return firstStockName;//第一个股票的名字
    }

    public void cancelSearch(){
        find(cancel).click();//点击取消按钮，回到自选
    }

    public void removeAllSelected(){
        List<WebElement> list= Driver.getCurrentDriver().findElements(By.id("portfolio_stockName"));
        System.out.println("需要删除的自选个数："+list.size());
        for(int i=0;i<list.size();i++){
            removeFirstStock();
        }
    }

    public void removeFirstStock(){
        TouchAction touch=new TouchAction(Driver.getCurrentDriver());
        touch.longPress(ElementOption.element(find(firstStock)));
        touch.release();
        touch.perform();
        find(text("删除")).click();
    }

    public String enterFirstRecommend() {
        sleep(2000);
        WebElement e=find(recommendStock);
        String recommend_stock_name=e.getText();
        e.click();
        return recommend_stock_name;
    }

    public String getStockNameInDetails() {
        String stockName=find(stockNameInDetails).getText();
        return stockName;
    }

    public void addZixuan() {
        find(addZixuanBtn).click();

    }

    public void back() {
        Driver.getCurrentDriver().navigate().back();
    }

    public void enterStock(String stockName) {
        find(text(stockName)).click();
    }

    public void deleteZixuan() {
        find(setZixuanBtn).click();
        find(deleteZixuanBtn).click();
    }

    public Boolean addBtnExist() {
        try{
            find(addAllCommend);
            return true;//查找成功，返回true
        }catch (Exception e){
            return false;
        }
    }
}
