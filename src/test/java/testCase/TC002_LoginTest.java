package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage_1;
import pageObjects.LoginPage_3;
import testBase.BaseClass;
import utilities.Retry;


public class TC002_LoginTest extends BaseClass {

    @Test(retryAnalyzer = Retry.class)
    public void loginTest() throws InterruptedException {

        HomePage_1 hp = new HomePage_1(driver);
        hp.clickOnLogin();

        LoginPage_3 lp = new LoginPage_3(driver);
        String  welcomeSignInlbl = lp.getWelcomeSignInLbl();
        System.out.println(welcomeSignInlbl);
        Assert.assertEquals(welcomeSignInlbl, "Welcome, Please Sign In!");

        lp.setTxtEmail(rb.getString("email"));
        lp.setTxtPassword(rb.getString("password"));
        lp.clicktBtnLogin();
        String myAccountLbl = lp.getMyAccountLbl();
        System.out.println(myAccountLbl);
        Assert.assertEquals(myAccountLbl,"My account");
        lp.clickOnLogout();

    }
}
