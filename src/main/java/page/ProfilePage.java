package page;

import driver.Driver;
import org.openqa.selenium.By;

/**
 * Created by helena.liu on 2019/3/20.
 */
public class ProfilePage {
    By login=By.xpath("//*[@text='点击登录']");
    public LoginPage gotoLogin(){
        Driver.getCurrentDriver().findElement(login).click();
        return new LoginPage();

    }

    public LoginPage goto密码登录(){
        return new LoginPage();
    }
}
