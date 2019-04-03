import driver.Driver;
//import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
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
    static MainPage mainPage;
    static ProfilePage profilePage;
    @BeforeAll
    static void beforeAll(){
        mainPage=MainPage.start();
         profilePage=mainPage.gotoProfile();

    }

    @ParameterizedTest
    @CsvSource({
            "18001367612,111111,手机号码填写错误",
            "1800136761,dddddd,手机号码填写错误"
    })
    public void 密码登录(String username,String password,String expected){
        LoginPage loginPage=profilePage.gotoLogin();
        loginPage.passwordFail(username,password);
        String msg=loginPage.getMessage();

        assertThat(msg,equalTo(expected));
        profilePage=loginPage.gotoProfile();

    }
































}
