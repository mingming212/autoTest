import driver.Driver;
import org.testng.annotations.Test;
import page.LoginPage;
import page.MainPage;
import page.ProfilePage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by helena.liu on 2019/3/11.
 */
public class LoginTest {
    @Test
    public void 非手机号(){
//        Driver.start();
        MainPage mainPage=MainPage.start();
        ProfilePage profilePage=mainPage.gotoProfile();
        LoginPage loginPage=profilePage.gotoLogin();
<<<<<<< HEAD

=======


        1
                2
//        loginPage.password?("xxxx","xxx");
//        assertThat(toast,equalTo("手机号码填写错误"));
>>>>>>> refs/remotes/origin/master





    }































}
