package testCase;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;
import utilities.Retry;

import java.time.Duration;



//import static org.asynchttpclient.util.ProxyUtils.logger;

public class EndToEndTest extends BaseClass {

    @Test(retryAnalyzer = Retry.class)
    public void endToEndTest() {
    logger.info("***** Starting EndToEnd Test *****");
        String firstName = getRandomString(5);
        String lastName = getRandomString(5);
        String email = getEmail(3);
        String password = rb.getString("password");
        String company = getRandomString(8);
        String productName = "Jewelry";
        String  itemName = "Vintage Style Engagement Ring";
        String stateName = "Oklahoma";
        String cityName = "Oklahoma";
        System.out.println(email);
        System.out.println(password);
        System.out.println(company);

    logger.info("---- Starting Home Page ----");
        HomePage_1 hp = new HomePage_1(driver);
        hp.clickOnRegister();
    logger.info("---- Completed Home Page ----");

    logger.info("---- Starting Registration Page ----");
        RegistrationPage_2 rp = new RegistrationPage_2(driver);
        String LabelReg = rp.getLblRegister();
        System.out.println(LabelReg);
        Assert.assertEquals(LabelReg, "Register");
        rp.selectGender();
        rp.setTxtFirstName(firstName);
        rp.setTxtLastName(lastName);
        rp.selectDate("4");
        rp.selectMonth("4");
        rp.selectYear("1981");
        rp.enterEmail(email);
        rp.enterCompanyName(company);
        rp.enterPassword(password);
        rp.enterCnfPassword(password);
        rp.clickOnRegister();
        String LblRegComp = rp.lblRegComplete();
        System.out.println(LblRegComp);
        Assert.assertEquals(LblRegComp, "Your registration completed","Issue in registration");
        rp.clickOnContinue();
        hp.clickOnLogin();
    logger.info("---- Completed Registration Page ----");

    logger.info("---- Starting Login Page ----");
        LoginPage_3 lp = new LoginPage_3(driver);
        String  welcomeSignInlbl = lp.getWelcomeSignInLbl();
        System.out.println(welcomeSignInlbl);
        Assert.assertEquals(welcomeSignInlbl, "Welcome, Please Sign In!","Issue in to get login page");
        lp.setTxtEmail(email);
        lp.setTxtPassword(password);
        lp.clicktBtnLogin();
//        String myAccountLbl = lp.getMyAccountLbl();
//        System.out.println(myAccountLbl);
//        Assert.assertEquals(myAccountLbl,"My account");
    logger.info("---- Completed Login Page ----");

    logger.info("---- Starting Product Catalogue Page ----");
        ProductCataloguePage_4 pcp = new ProductCataloguePage_4(driver);
        String lblMyaccount = pcp.getLblMyaccount();
        Assert.assertEquals(lblMyaccount,"My account","Issue in login");
        pcp.setSelectProduct();
        String lblProduct = pcp.getProductLbl();
        Assert.assertEquals(lblProduct,productName,"Product not matched");
        pcp.setSelectItem(itemName);
    logger.info("---- Completed Product Catalogue Page ----");

    logger.info("---- Starting Product Page ----");
        ProductPage_5 pp = new ProductPage_5(driver);
        String itename = pp.getLblItem();
        Assert.assertEquals(itename, itemName,"Item not matched in Product Page");
        pp.setProductQty("2");
        pp.setBtnAddToCart();
        pp.setBtnClose();
        // ***** Actions Class *****
//        Actions actions = new Actions(driver);
//        actions.moveToElement(pp.provideShoppingCartWebElement()).build().perform();
        pp.moveToShoppingCart();
        pp.setBtnGoTOCart();
    logger.info("---- Completed Product Page ----");

    logger.info("---- Starting Shopping Cart Page ----");
        ShoppingCartPage_6 scp = new ShoppingCartPage_6(driver);
        String cartName = scp.getLblShoppingCart();
        Assert.assertEquals(cartName,"Shopping cart","Issue in navigate to Shopping Cart");
        String itemname = scp.getLblProductName();
        Assert.assertEquals(itename,itemname,"Item not matched in Shopping Cart");
        scp.setGiftWrapper("2");
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        boolean flag = wait.until(ExpectedConditions.textToBePresentInElement(scp.getLocator(),"Gift wrapping: Yes [+$10.00]"));
        Boolean flag = scp.getLocator("Gift wrapping: Yes [+$10.00]");
        String giftWrapperMsgText = scp.getGiftWrapperStatus();
        Assert.assertEquals(giftWrapperMsgText.contains("Yes"), true);
//       Gift wrapping: Yes [+$10.00]
        if (flag){
            scp.setCbTerms();
        }

        scp.setBtnCheckout();
    logger.info("---- Completed Shopping Cart Page ----");

    logger.info("---- Starting Checkout Page ----");
        CheckOutPage_7 cop = new CheckOutPage_7(driver);
        String lblCheckout = cop.getLblCheckout();
        Assert.assertEquals(lblCheckout, "Checkout");
        cop.setCountries("1");
        cop.setStates(stateName);
        cop.setCity(cityName);
        takeScreenShot("EndToEnd_CityName");
        cop.setAddress1("Main Street");
        cop.setZipCode("10010");
        cop.setPhoneNumber("2354145878");
        cop.setBtnContinueBilling();
        cop.setCbShippingOption();
        cop.setBtnContinueShipiing();
        cop.setBtnContinuePayment();
        Assert.assertTrue(cop.confirmCity(cityName),"City Name not matched");
        cop.setBtnContinuePaymentInfo();
        cop.setBtnContinueConfirmOrder();
    logger.info("---- Completed Checkout Page ----");

    logger.info("---- Starting Order Confirm Page ----");
        OrderConfirmPage_8 ocp = new OrderConfirmPage_8(driver);
        String ordercnfmsg = ocp.getorderConfirmMessage();
        Assert.assertEquals(ordercnfmsg, "Your order has been successfully processed!");
        System.out.println(ocp.getorderNumber());

        takeScreenShot("EndToEnd_Order");

        LogOutPage_9 lop = new LogOutPage_9(driver);
        lop.clickOnLogout();
    logger.info("---- Completed Order Confirm Page ----");

    logger.info("***** Finished EndToEnd Test Successfully *****");
    }
}
