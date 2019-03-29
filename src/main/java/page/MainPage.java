package page;

import driver.Driver;
import org.openqa.selenium.By;

/**
 * Created by whs on 2019/3/11.
 */
public class MainPage {
    By profile = By.id("user_profile_icon");
    public static MainPage start(){
        Driver.start();
        return new MainPage();
    }

    public ProfilePage gotoProfile(){
        Driver.getCurrentDriver().findElement(profile).click();
        return new ProfilePage();

    }
}






















