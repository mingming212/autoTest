import driver.Driver;
//import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.testng.annotations.Test;
import page.LoginPage;
import page.MainPage;
import page.ProfilePage;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.equalTo;

/**
 * Created by helena.liu on 2019/3/11.
 */
public class LoginTest {
    @ParameterizedTest
    @CsvSource({
            "18001367612","111111","手机号码填写错误",
            "1800136761","111111","手机号码填写错误"
    })
    public void 密码登录(String username,String password,String expected){
//        Driver.start();
        MainPage mainPage=MainPage.start();
        ProfilePage profilePage=mainPage.gotoProfile();
        LoginPage loginPage=profilePage.gotoLogin();
        loginPage.passwordFail(username,password);
        String msg=loginPage.getMessage();

//        assertThat(msg,equalTo("手机号码填写错误"));//hamcrest执行失败
//        Assert.assertEquals(expected,msg);


    }
































}
