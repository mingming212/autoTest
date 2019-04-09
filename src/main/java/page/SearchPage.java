package page;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

/**
 * Created by whs on 2019/4/2.
 */
public class SearchPage extends BasePage{
    By search=By.id("home_search");//首页上的搜索框，写在MainPage里的gotoSearch()方法里
    By searchInSearchPage=By.id("search_input_text");
    By cancelBtn=By.id("action_close");
    By searchResult=text("拼多多");
    By addBtn=locate("follow_btn");//搜索结果右边的加号按钮，点击后添加到自选
    By moveBtn=locate("followed_btn");//从自选中删除

    public SearchPage search(String keyword){
        find(searchInSearchPage).sendKeys(keyword);

        /*find(searchResult);
        find(addBtn);
        find(moveBtn);*/
        return this;
    }

    public MainPage cancel(){
        find(cancelBtn).click();
        return new MainPage();
    }

    public ArrayList<String> getAll(){
        ArrayList<String> list=new ArrayList<String>();
//        WebElement webe= (WebElement) Driver.getCurrentDriver().findElements(By.id("stockName")).get(0);
//        WebElement webe2=  Driver.getCurrentDriver().findElement(By.id("stockName"));

        for(WebElement e: Driver.getCurrentDriver().findElements(By.id("stockName"))){
            list.add(e.getText());
        }
        return list;
    }

    public SearchPage getByStock(){

        return this;
    }

    public ArrayList<String> addSelected(){
        ArrayList<String> arrayList=new ArrayList<String>();
        WebElement select=find(By.xpath("//*[contains(@resource-id,'follow') and contains(@resource-id,'_btn')]"));
        String selectID=select.getAttribute("resourceId");
        arrayList.add(selectID);
        select.click();
        WebElement select2=find(By.xpath("//*[contains(@resource-id,'follow') and contains(@resource-id,'_btn')]"));
        String selectID2=select2.getAttribute("resourceId");
        arrayList.add(selectID2);
//        System.out.println("2222: "+arrayList.get(0)+","+arrayList.get(1));
        select2.click();
        return arrayList;
    }

    public SearchPage removeSelected(){
        return this;
    }



}
