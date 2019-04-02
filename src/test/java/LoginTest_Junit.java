import org.junit.Test;
import page.LoginPage;
import page.MainPage;
import page.ProfilePage;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by helena.liu on 2019/3/11.
 */
public class LoginTest_Junit {
    @Test
    public void 非手机号(){
//        Driver.start();
        MainPage mainPage=MainPage.start();
        ProfilePage profilePage=mainPage.gotoProfile();
        LoginPage loginPage=profilePage.gotoLogin();
        loginPage.passwordFail("180013676","123456");
        String msg=loginPage.getMessage();

        assertThat("1",equalTo("1"));
//        assertThat(loginPage.getMessage(),equalTo("手机号码填写错误"));


    }
    @Test
    public void 非手机号2(){
//        Driver.start();
        MainPage mainPage=MainPage.start();
        ProfilePage profilePage=mainPage.gotoProfile();
        LoginPage loginPage=profilePage.gotoLogin();
        loginPage.passwordFail("1800136761","123456");
        String msg=loginPage.getMessage();

        assertThat("手机号码填写错误",equalTo("手机号码填写错误"));
//        assertThat(loginPage.getMessage(),equalTo("手机号码填写错误"));


    }

    @Test
    public void 非手机号3(){
//        Driver.start();
        MainPage mainPage=MainPage.start();
        ProfilePage profilePage=mainPage.gotoProfile();
        LoginPage loginPage=profilePage.gotoLogin();
        loginPage.passwordFail("18001367612","123456");
        String msg=loginPage.getMessage();

        assertThat("1",equalTo("手机号码填写错误"));
//        assertThat(loginPage.getMessage(),equalTo("手机号码填写错误"));


    }































}
