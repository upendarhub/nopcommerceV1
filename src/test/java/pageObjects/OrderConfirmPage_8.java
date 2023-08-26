package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderConfirmPage_8 extends BasePage{

    public OrderConfirmPage_8(WebDriver driver){
        super(driver);
    }

    @FindBy (css = "div[class='section order-completed'] div[class='title'] strong")
    WebElement orderConfirmMessage;

    @FindBy (css = "div[class='order-number'] Strong")
    WebElement orderNumber;

    //Actions
    public String getorderConfirmMessage(){
        return orderConfirmMessage.getText();
    }

    public String getorderNumber(){
        return orderNumber.getText();
    }
}
