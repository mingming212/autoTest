//import org.junit.Test;

import driver.Driver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import page.MainPage;
import page.ZixuanPage;


public class ZixuanTest {
    static MainPage mainPage;
    static ZixuanPage zixuanPage;
    @BeforeAll
    static void beforeAll(){
        mainPage=MainPage.start();
        zixuanPage=mainPage.gotoZixuan();

    }

//   case步骤：在自选首页，点搜索，将第一个未加入自选的股票点击加入自选股，点返回，返回到自选首页，显示自选股票列表，长按删除，把页面上的所有自选股票都删了
    @Test
    public void 搜索添加自选(){
        zixuanPage.searchInZixuan("a");//搜索股票a
        String selectedName= zixuanPage.addFirstSelected();//添加搜索出来的第一个股票
        System.out.println("搜:"+selectedName);
        zixuanPage.cancelSearch();//点击搜索框后面的取消按钮，回到自选首页
        String firstInList=zixuanPage.getFirstInZixuan();//得到自选列表中的第一个自选股票
        System.out.println("已选的:"+firstInList);
        assertThat(firstInList,equalTo(selectedName));
        //清理数据
        zixuanPage.removeAllSelected();//移除所有自选股票

    }

    //   case步骤：自选首页，点击推荐的股票，进到股票详情页，点右下角的添加自选，再点有下角的设自选来删除自选
    @Test
    public void 详情页添加自选(){
        //前提：删除所有自选，这块没写~
        String recommend_stock_name=zixuanPage.enterFirstRecommend();
        String stockName=zixuanPage.getStockNameInDetails();
        zixuanPage.addZixuan();
        zixuanPage.back();//返回键，回到自选首页
        String firstInList=zixuanPage.getFirstInZixuan();//得到自选列表中的第一个自选股票
        System.out.println("已选的:"+firstInList);
        assertThat(firstInList,equalTo(stockName));
        zixuanPage.enterStock(stockName);
        zixuanPage.deleteZixuan();
        zixuanPage.back();//返回键，回到自选首页
        //最后来个验证是否删除成功
        Boolean isExist=zixuanPage.addBtnExist();//判断自选首页上的推荐按钮"加入自选股"按钮是否存在
        assertThat(true,equalTo(isExist));
    }


    @AfterAll
    static void tearDowm(){
        Driver.getCurrentDriver().quit();
    }

}
