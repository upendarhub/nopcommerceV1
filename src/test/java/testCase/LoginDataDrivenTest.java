package testCase;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.HomePage_1;
import pageObjects.LoginPage_3;
import testBase.BaseClass;
import utilities.DataProviders;

public class LoginDataDrivenTest extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
    public void test_LoginDataDriven(String email, String password, String exp) {

        logger.info("***** Starting Login Data Driven Test *****");

        try {
            HomePage_1 hp = new HomePage_1(driver);
            hp.clickOnLogin();

            LoginPage_3 lp = new LoginPage_3(driver);
            Assert.assertEquals(lp.getWelcomeSignInLbl(), "Welcome, Please Sign In!");

            lp.setTxtEmail(email);
            lp.setTxtPassword(password);
            lp.clicktBtnLogin();

            boolean targetPage = lp.cnfLblMyAccount();

            if (exp.equals("Valid")) {
                if (targetPage == true) {
                    lp.clickOnLogout();
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }
            }

            if (exp.equals("Invalid")) {
                if (targetPage == true) {
                    lp.clickOnLogout();
                    Assert.assertTrue(false);
                } else {
                    Assert.assertTrue(true);
                }
            }

        } catch (Exception e){
            Assert.fail(e.getMessage());
        }
        logger.info("***** Finished Login Data Driven Test *****");
    }
}