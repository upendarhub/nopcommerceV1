package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ProductPage_5 extends BasePage{

    public ProductPage_5(WebDriver driver) {
        super(driver);
    }

    @FindBy (tagName = "h1")
    WebElement lblItem;

    @FindBy (xpath = "//input[contains(@class,'qty-input')]")
    WebElement boxQty;

    @FindBy (xpath = "//button[@class='button-1 add-to-cart-button']")
    WebElement btnAddToCart;

    @FindBy (css = ".close")
    WebElement btnClose;

    @FindBy (css = ".cart-label")
    WebElement btnShoppingCart;

    @FindBy (css = ".button-1.cart-button")
    WebElement btnGoTOCart;

    //Actions

    public String getLblItem(){
        return lblItem.getText();
    }
    public void setProductQty(String num){
        boxQty.clear();
        boxQty.sendKeys(num);
    }
    public void setBtnAddToCart(){
        btnAddToCart.click();
    }
    public void setBtnClose(){
        btnClose.click();
    }
    public void moveToShoppingCart(){
           moveToElement(btnShoppingCart);
    }
    public void setBtnGoTOCart(){
        btnGoTOCart.click();
    }

}
