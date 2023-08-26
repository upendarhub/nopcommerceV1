package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage_3 extends BasePage{

    public LoginPage_3(WebDriver driver) {
        super(driver);

    }

    @FindBy (xpath = "//h1")
    WebElement lblWelcomeSignin;
    @FindBy (id = "Email")
    WebElement txtEmail;
    @FindBy (name = "Password")
    WebElement txtPassword;
    @FindBy(xpath = "//button[text()='Log in']")
    WebElement btnLogin;
    @FindBy(xpath = "//a[@class='ico-account']")
    WebElement lblMeAccount;
    @FindBy (linkText = "Log out")
    WebElement lnkLogout;

    //Actions
    public String getWelcomeSignInLbl() {

        String lbl = lblWelcomeSignin.getText();
        return lbl;
    }

    public void setTxtEmail(String email) {

        txtEmail.sendKeys(email);
    }

    public void setTxtPassword(String password) {

        txtPassword.sendKeys(password);
    }

    public void clicktBtnLogin() {

        btnLogin.click();
    }

    public String getMyAccountLbl() {

        return lblMeAccount.getText();
    }
    public boolean cnfLblMyAccount(){
        try {
            return lblMeAccount.isDisplayed();
        } catch (Exception e){
            return (false);
        }
    }
    public void clickOnLogout() {
        lnkLogout.click();
    }



}
