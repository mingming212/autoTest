package page;

import driver.Driver;
import org.openqa.selenium.By;
import sun.java2d.cmm.Profile;

/**
 * Created by helena.liu on 2019/3/25.
 */
public class LoginPage extends BasePage{
    By other=text("手机及其他登录");
    By passwordLogin=text("邮箱手机号密码登录");
    By username=By.id("com.xueqiu.android:id/login_account");
//    By username=By.id("login_account");
    By password=By.xpath("//*[@password='true']");
    By login=By.id("com.xueqiu.android:id/button_next");
    By msg=By.id("md_content");

    String message;

    public LoginPage passwordFail(String username,String password){
        find(other).click();
        find(passwordLogin).click();
        find(this.username).sendKeys(username);
        find(this.password).sendKeys(password);
        find(login).click();
        message=find(msg).getText();
        find(By.id("md_buttonDefaultPositive")).click();
        return this;
    }

    public String getMessage(){
        return message;
    }

    public ProfilePage gotoProfile(){
        find(By.id("iv_close")).click();
        return new ProfilePage();
    }











































































}
