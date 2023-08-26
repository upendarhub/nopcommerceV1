package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ShoppingCartPage_6 extends BasePage{

    public ShoppingCartPage_6(WebDriver driver) {
        super(driver);
    }

    //Elements
    @FindBy (tagName = "h1")
    WebElement lblShoppingCart;
    @FindBy (css = ".product-name")
    WebElement productName;
    @FindBy (id = "checkout_attribute_1")
    WebElement giftWrapper;
    @FindBy (css = ".selected-checkout-attributes")
    WebElement attribute;
    @FindBy (id = "termsofservice")
    WebElement cbTerms;
    @FindBy (css = ".selected-checkout-attributes")
    WebElement isGiftWrapperSelected;
    @FindBy (id = "checkout")
    WebElement btnCheckout;

    //Actions

    public String getLblShoppingCart(){
        return lblShoppingCart.getText();
    }
    public String getLblProductName(){
        return productName.getText();
    }
    public void setGiftWrapper(String num){
        Select gift = new Select(giftWrapper);
        gift.selectByValue(num);
    }
    public boolean getLocator (String text) {
//        return attribute;
        return waitUntilTextToBePresent(attribute,text);
    }
    public void setCbTerms (){
        cbTerms.click();
    }
    public String getGiftWrapperStatus(){
        return isGiftWrapperSelected.getText();
    }
    public void setBtnCheckout(){
        btnCheckout.click();
    }
}
