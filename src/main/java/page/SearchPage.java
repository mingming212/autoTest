package page;

import driver.Driver;
import org.openqa.selenium.By;

/**
 * Created by whs on 2019/4/2.
 */
public class SearchPage extends BasePage{
    By search=By.id("home_search");
    By searchResult=text("拼多多");
    By addBtn=locate("follow_btn");//搜索结果右边的加号按钮，点击后添加到自选
    By moveBtn=locate("followed_btn");//点击后从自选中删除

    public SearchPage gotoSearchPage(){
        find(search).sendKeys("pdd");
        find(searchResult);
        find(addBtn);
        find(moveBtn);
        return new SearchPage();
    }



}
