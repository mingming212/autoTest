import driver.Driver;
import org.testng.annotations.Test;
import page.LoginPage;
import page.MainPage;
import page.ProfilePage;

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
        loginPage.passwordFail("12345678","1234");
//        loginPage.password("xxxx","xxx");
//        assertThat(toast,equalTo("手机号码填写错误"));

        //testBranch
修改了本地，pull远端
                合并以后有冲突，在本分支上显示冲突，但不上传到github，本地push后就是merge后的本地分支
    }































}
