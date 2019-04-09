//import org.junit.Test;

import driver.Driver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import page.MainPage;
import page.SearchPage;
import page.ZixuanPage;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;

public class ZixuanTest {
    static MainPage mainPage;
    static ZixuanPage zixuanPage;
    @BeforeAll
    static void beforeAll(){
        Driver.getCurrentDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        mainPage=MainPage.start();
        zixuanPage=mainPage.gotoZixuan();
    }

//    点搜索，搜索后点击加入自选股，点返回，返回到自选首页，显示股票，长按删除，把页面上的所有股票都删了
//    添加其他股票，搜索，点进去，详情页，添加自选，删除自选

    @Test
    public void 搜索添加自选(){
        zixuanPage.searchInZixuan("a");//搜索股票a
        zixuanPage.addFirstSelected();//添加搜索出来的第一个股票
        zixuanPage.removeAllSelected();//移除所有自选股票

    }






























}
