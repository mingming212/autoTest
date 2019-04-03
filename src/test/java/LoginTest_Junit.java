import org.junit.Assert;
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
    public void 密码登录(){
//        Driver.start();
        MainPage mainPage=MainPage.start();
        ProfilePage profilePage=mainPage.gotoProfile();
        LoginPage loginPage=profilePage.gotoLogin();
        loginPage.passwordFail("18001367612","123456");
        String msg=loginPage.getMessage();

        assertThat(msg,equalTo("手机号码填写错误"));//hamcrest执行失败
//        Assert.assertEquals(msg,"手机号码填写错误");

    }

































}
