package E2ETest_nopcommerce;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class nopcommercee2e {

    public static void main(String[] args) throws IOException, InterruptedException {

// ***** Define Variables *****

//        String email = "test003@email.com";
        String passWord = "Test@123";
        String email = RandomStringUtils.randomAlphabetic(6)+"@email.com";
        System.out.println(email);
        String productName = "Jewelry";
        String  itemName = "Vintage Style Engagement Ring";

// ***** Driver Initialization *****

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://demo.nopcommerce.com/");

// ***** Registration *****

        driver.findElement(By.cssSelector(".ico-register")).click();
        driver.findElement(By.id("gender-male")).click();
        driver.findElement(By.id("FirstName")).sendKeys("Test002");
        driver.findElement(By.id("LastName")).sendKeys("Test002");
        WebElement date =  driver.findElement(By.name("DateOfBirthDay"));
        Select selectDate = new Select(date);
        selectDate.selectByValue("5");

        WebElement month =  driver.findElement(By.name("DateOfBirthMonth"));
        Select selectMonth = new Select(month);
        selectMonth.selectByValue("7");

        WebElement year =  driver.findElement(By.name("DateOfBirthYear"));
        Select selectYear = new Select(year);
        selectYear.selectByValue("1982");

        driver.findElement(By.cssSelector("#Email")).sendKeys(email);

        driver.findElement(By.cssSelector("#Company")).sendKeys("Start Solutions");

        driver.findElement(By.name("Password")).sendKeys(passWord);
        driver.findElement(By.name("ConfirmPassword")).sendKeys(passWord);

        driver.findElement(By.xpath("//button[contains(@class, 'register')]")).click();

        String msgRegister = driver.findElement(By.cssSelector(".result")).getText();

        Assert.assertEquals(msgRegister, "Your registration completed");

        driver.findElement(By.xpath("//a[contains(@class, 'continue')]")).click();

// ***** Login *****

        driver.findElement(By.className("ico-login")).click();

        driver.findElement(By.id("Email")).sendKeys(email);

        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(passWord);

        driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();

// ***** Product Selection(Product Catalogue Page) *****

        String msgMyAccount = driver.findElement(By.cssSelector(".ico-account ")).getText();

        Assert.assertEquals(msgMyAccount, "My account");

        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File(".//screenShots//login.png"));

//        List<WebElement> products = driver.findElements(By.cssSelector(".top-menu notmobile"));
//
//        for(WebElement product : products){
//            System.out.println(product);
//            if(product.getText().equalsIgnoreCase(productName)){
//                product.click();
//                break;
//            }
//        }

        driver.findElement(By.xpath("//a[text()='Jewelry ']")).click();

        String itemLbl = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(itemLbl, productName);

        List<WebElement> listOfJewelley = driver.findElements(By.xpath("(//div[@class='product-grid'])//h2/a"));

        for(WebElement item :listOfJewelley){

            if(item.getText().equalsIgnoreCase(itemName)){

                item.click();
                break;
            }
//            System.out.println(item.getText());
        }

// ***** Product Page *****


        String itemLble= driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(itemLble, itemName);

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scroll(0,500)");

//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("//input[@class='qty-input valid']"))));
        driver.findElement(By.xpath("//input[contains(@class,'qty-input')]")).clear();

        driver.findElement(By.xpath("//input[contains(@class,'qty-input')]")).sendKeys("2");

        driver.findElement(By.xpath("//button[@class='button-1 add-to-cart-button']")).click();

        File itemFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(itemFile, new File(".//screenShots//item.png"));

            driver.findElement(By.cssSelector(".close")).click();

        Actions actions = new Actions(driver);

        actions.moveToElement(driver.findElement(By.cssSelector(".cart-label"))).build().perform();

        driver.findElement(By.cssSelector(".button-1.cart-button")).click();

        File itemInCart = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(itemInCart, new File(".//screenShots//itemInCart.png"));

// ***** Shopping Cart Page *****

        String shopCart = driver.findElement(By.tagName("h1")).getText();

        Assert.assertEquals(shopCart, "Shopping cart");

        String iteminCart = driver.findElement(By.cssSelector(".product-name")).getText();

        Assert.assertEquals(iteminCart, itemName);

        WebElement gift = driver.findElement(By.id("checkout_attribute_1"));
        Select giftWrapper = new Select(gift);
        giftWrapper.selectByValue("2");

//        WebElement giftWrapperMsg = driver.findElement(By.cssSelector(".selected-checkout-attributes"));

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        boolean result = wait.until(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(".selected-checkout-attributes"), "Gift wrapping: No"));
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='selected-checkout-attributes']"))));

        String giftWrapperMsgText = driver.findElement(By.cssSelector(".selected-checkout-attributes")).getText();

        System.out.println(giftWrapperMsgText);

        Assert.assertEquals(giftWrapperMsgText.contains("Yes"), true);

        if(result){
            driver.findElement(By.id("termsofservice")).click();
        }



        driver.findElement(By.id("checkout")).click();

// ***** Checkout Page *****

        String lblCheckout = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(lblCheckout, "Checkout");

        WebElement country = driver.findElement(By.id("BillingNewAddress_CountryId"));

        Select selectCountry = new Select(country);

        selectCountry.selectByValue("1");

        WebElement state = driver.findElement(By.id("BillingNewAddress_StateProvinceId"));

        Select selectState = new Select(state);

        selectState.selectByValue("20");

        driver.findElement(By.name("BillingNewAddress.City")).sendKeys("Chesapeake");

        driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("Main Street");

        driver.findElement(By.cssSelector("#BillingNewAddress_ZipPostalCode")).sendKeys("22244");

        driver.findElement(By.cssSelector("#BillingNewAddress_PhoneNumber")).sendKeys("1234567891");

        driver.findElement(By.xpath("//button[@onclick='Billing.save()']")).click();

//        driver.findElement(By.id("BillingNewAddress_City")).sendKeys("Texas");

        driver.findElement(By.id("shippingoption_1")).click();
        driver.findElement(By.xpath("//button[@onclick='ShippingMethod.save()']")).click();

//        driver.findElement(By.id("paymentmethod_1")).click();
        driver.findElement(By.xpath("//button[@onclick='PaymentMethod.save()']")).click();

        List<WebElement> address = driver.findElements(By.tagName("b"));
        boolean flag = address.stream().anyMatch(s->s.getText().contains("New York"));

        if(flag){
            driver.findElement(By.xpath("//button[@onclick='PaymentInfo.save()']")).click();
        }

//        js.executeScript("window.scroll(0,1500)");

        driver.findElement(By.xpath("//button[@onclick='ConfirmOrder.save()']")).click();

// ***** Order Confirm Page *****

        String confirmMsg = driver.findElement(By.cssSelector("div[class='section order-completed'] div[class='title'] strong")).getText();

        Assert.assertEquals(confirmMsg, "Your order has been successfully processed!");

        String orderNo = driver.findElement(By.cssSelector("div[class='order-number'] Strong")).getText();

        System.out.println(orderNo);

        File confirmFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile (confirmFile, new File(".//screenShots//orderConfirmed.png"));

// ***** Logout Page *****

    driver.findElement(By.linkText("Log out")).click();

// ***** Tear Down *****

        driver.close();

    }
}
