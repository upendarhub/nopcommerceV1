package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CheckOutPage_7 extends BasePage{

    public CheckOutPage_7(WebDriver driver) {
        super(driver);
    }

    //Elements
    @FindBy(tagName = "h1")
    WebElement lblCheckout;
    @FindBy (id = "BillingNewAddress_CountryId")
    WebElement countries;
    @FindBy (id = "BillingNewAddress_StateProvinceId")
    WebElement states;
    @FindBy (xpath = "//select[@id='BillingNewAddress_StateProvinceId']")
    WebElement stateName;
    @FindBy (name = "BillingNewAddress.City")
    WebElement city;
    @FindBy (id = "BillingNewAddress_Address1")
    WebElement addres1;
    @FindBy (css = "#BillingNewAddress_ZipPostalCode")
    WebElement zipCode;
    @FindBy (css = "#BillingNewAddress_PhoneNumber")
    WebElement phoneNumber;
    @FindBy (xpath = "//button[@onclick='Billing.save()']")
    WebElement btnContinueBilling;
    @FindBy (id = "shippingoption_1")
    WebElement cbShippingOption;
    @FindBy (xpath = "//button[@onclick='ShippingMethod.save()']")
    WebElement btnContinueShipiing;
    @FindBy (xpath = "//button[@onclick='PaymentMethod.save()']")
    WebElement btnContinuePayment;
    @FindBy (tagName = "b")
    List<WebElement> addressCityName;
    @FindBy (xpath = "//button[@onclick='PaymentInfo.save()']")
    WebElement btnContinuePaymentInfo;
    @FindBy (xpath = "//button[@onclick='ConfirmOrder.save()']")
    WebElement btnContinueConfirmOrder;

    //Actions

    public String getLblCheckout(){
        return lblCheckout.getText();
    }
    public void setCountries(String num){
        Select country = new Select(countries);
        country.selectByValue(num);
    }
    public void setStates(String statename){
        Select state = new Select(states);
        state.selectByVisibleText(statename);
    }

    public void setCity(String cityName){
        city.sendKeys(cityName);
    }
    public void setAddress1(String address1){
        addres1.sendKeys(address1);
    }

    public void setZipCode(String zipcode) {
        zipCode.sendKeys(zipcode);
    }

    public void setPhoneNumber(String phoneNum) {
        phoneNumber.sendKeys(phoneNum);
    }

    public void setBtnContinueBilling(){
        btnContinueBilling.click();
    }
    public void setCbShippingOption(){
        cbShippingOption.click();
    }
    public void setBtnContinueShipiing(){
        btnContinueShipiing.click();
    }
    public void setBtnContinuePayment(){
        btnContinuePayment.click();
    }
    public boolean confirmCity (String cityName){
        return addressCityName.stream().anyMatch(s->s.getText().contains("New York"));
    }
    public void setBtnContinuePaymentInfo(){
        btnContinuePaymentInfo.click();
    }
    public void setBtnContinueConfirmOrder(){
        btnContinueConfirmOrder.click();
    }

}
