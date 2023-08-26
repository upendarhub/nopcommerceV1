package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage_2 extends BasePage{

    public RegistrationPage_2(WebDriver driver){
        super(driver);
    }
//Elements
    @FindBy (tagName = "h1")
    WebElement lblRegister;

    @FindBy (id = "gender-male")
    WebElement btnMale;

    @FindBy (name = "FirstName")
    WebElement txtFirstName;

    @FindBy(id = "LastName")
    WebElement txtLastName;

    @FindBy (xpath = "//select[@name='DateOfBirthDay']")
   WebElement dobDates;

    @FindBy (xpath = "//select[@name='DateOfBirthMonth']")
    WebElement  dobMonths;

    @FindBy (xpath = "//select[@name='DateOfBirthYear']")
    WebElement  dobyears;

    @FindBy (id = "Email")
    WebElement txtEmail;

    @FindBy (id = "Company")
    WebElement txtCompanyName;

    @FindBy (name = "Password")
    WebElement txtPassword;

    @FindBy (id = "ConfirmPassword")
    WebElement txtConfirmPassword;

    @FindBy (id = "register-button")
    WebElement btnRegister;

    @FindBy (css = ".result")
    WebElement lblRegComplete;

    @FindBy (css = ".buttons")
    WebElement btnContinue;

    //Actions

    public String getLblRegister(){

        return lblRegister.getText();
    }
    public void selectGender(){
        btnMale.click();
    }
    public void setTxtFirstName (String firstName){
        txtFirstName.sendKeys(firstName);
    }

    public void setTxtLastName(String lastName){
        txtLastName.sendKeys(lastName);
    }

    public void selectDate(String date){
        Select dbdate = new Select(dobDates);
        dbdate.selectByValue(date);
    }
    public void selectMonth(String month){
        Select dbmonth = new Select(dobMonths);
        dbmonth.selectByValue(month);
    }
    public void selectYear(String year){
        Select dbyear = new Select(dobyears);
        dbyear.selectByValue(year);
    }

    public void enterEmail(String email){
        txtEmail.sendKeys(email);
    }
    public void enterCompanyName(String company) {
        txtCompanyName.sendKeys(company);
    }

    public void enterPassword(String password) {
        txtPassword.sendKeys(password);
    }
    public void enterCnfPassword(String password) {
        txtConfirmPassword.sendKeys(password);
    }
    public void clickOnRegister() {
        btnRegister.click();
    }
    public String lblRegComplete(){
        return lblRegComplete.getText();
    }

    public void clickOnContinue(){
        btnContinue.click();
    }

}
