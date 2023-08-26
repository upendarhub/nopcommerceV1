package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductCataloguePage_4 extends BasePage{

    public ProductCataloguePage_4(WebDriver driver) {
       super(driver);
    }

    @FindBy (css = ".ico-account ")
    WebElement lblMyaccount;
    @FindBy (xpath = "//a[text()='Jewelry ']")
    WebElement selectProduct;
    @FindBy (tagName = "h1")
    WebElement lblProduct;
    @FindBy (xpath = "(//div[@class='product-grid'])//h2/a")
    List<WebElement> listOfJewelleries;

    //Actions
    public String getLblMyaccount() {
        return lblMyaccount.getText();
    }
    public void setSelectProduct() {
        selectProduct.click();
    }
    public String getProductLbl() {
        return lblProduct.getText();
    }
    public void setSelectItem(String itemName) {

        for(WebElement item :listOfJewelleries){

            if(item.getText().equalsIgnoreCase(itemName)){

                item.click();
                break;
            }
        }
    }
}
