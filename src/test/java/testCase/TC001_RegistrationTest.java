package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage_1;
import pageObjects.RegistrationPage_2;
import testBase.BaseClass;
import utilities.Retry;

public class TC001_RegistrationTest extends BaseClass {

    @Test(retryAnalyzer = Retry.class)
    public void registrationTest() {
        HomePage_1 hp = new HomePage_1(driver);
        hp.clickOnRegister();

        RegistrationPage_2 rp = new RegistrationPage_2(driver);

        String LabelReg = rp.getLblRegister();
        System.out.println(LabelReg);
        Assert.assertEquals(LabelReg, "Register");

        rp.selectGender();
        rp.setTxtFirstName(getRandomString(4));
        rp.setTxtLastName(getRandomString(4));
        rp.selectDate("4");
        rp.selectMonth("4");
        rp.selectYear("1981");
        rp.enterEmail(rb.getString("email"));
        rp.enterCompanyName(getRandomString(8));
        rp.enterPassword("Test@123");
        rp.enterCnfPassword("Test@123");
        rp.clickOnRegister();
        String LblRegComp = rp.lblRegComplete();
        System.out.println(LblRegComp);
        Assert.assertEquals(LblRegComp, "Your registration completed");
        rp.clickOnContinue();

    }
}
