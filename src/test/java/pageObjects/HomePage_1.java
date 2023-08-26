package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage_1 extends BasePage{

    public HomePage_1(WebDriver driver) {

        super(driver);
    }

    //Elements
    @FindBy (css = ".ico-register")
    WebElement lnkRegister;

    @FindBy (linkText = "Log in")
    WebElement lnkLogin;

    @FindBy (xpath = "//span[@class='wishlist-label']" )
    WebElement lnkWishlist;

    @FindBy (xpath = "//span[text()='Shopping cart']")
    WebElement lnkShoppingCart;

    @FindBy (id = "small-searchterms")
    WebElement searchBox;

    @FindBy (xpath = "//button[contains(@class, 'search-box')]")
    WebElement btnSearch;


    //Actions

    public void clickOnRegister() {

        lnkRegister.click();
    }

    public void clickOnLogin() {
        lnkLogin.click();
    }

    public void clickOnWishlist() {

        lnkWishlist.click();
    }

    public void clickOnShoppingcar() {

        lnkShoppingCart.click();
    }

    public void searchProduct(String productName) {

        searchBox.sendKeys(productName);
        btnSearch.click();
    }

}
