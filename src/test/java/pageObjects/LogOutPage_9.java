package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogOutPage_9 extends BasePage{

 public LogOutPage_9(WebDriver driver){
     super(driver);
 }

    //Elements

    @FindBy(linkText = "Log out")
    WebElement lnkLogOut;

    @FindBy (className = "ico-login")
    WebElement lblLogin;


   //Actions
    public void clickOnLogout() {

        lnkLogOut.click();
    }

    public String lblLogin() {

        return lblLogin.getText();
    }
}
