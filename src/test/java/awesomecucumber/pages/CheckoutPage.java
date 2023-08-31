package awesomecucumber.pages;

import awesomecucumber.domainobjects.BillingDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage{

    @FindBy(id = "billing_first_name") private WebElement billingFirstnameFld;
    @FindBy(id = "billing_last_name") private WebElement billingLastnameFld;
    @FindBy(id = "billing_address_1") private WebElement billingAddressOneFld;
    @FindBy(id = "billing_city") private WebElement billingCityFld;
    @FindBy(id = "billing_state") private WebElement billingStateDropdown;
    @FindBy(id = "select2-billing_state-container")private WebElement alternateBillingStateDropdown;
    //private final By alternateStateDropdown = By.id("select2-billing_state-container");
    @FindBy(id = "billing_postcode") private WebElement billingZipFld;
    @FindBy(id = "billing_email") private WebElement billingEmailFld;
    @FindBy(id = "place_order") private WebElement placeOrderBtn;
    @FindBy(css = ".woocommerce-notice") private WebElement noticeTxt;
    private final By overlay = By.cssSelector(".blockUI.blockOverlay");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterBillingFirstName(String billingFirstName){
       WebElement e = wait.until(ExpectedConditions.visibilityOf(billingFirstnameFld));
       e.clear();
       e.sendKeys(billingFirstName);
        return this;
    }

    public CheckoutPage enterBillingLastName(String billingLastName){
        WebElement e = wait.until(ExpectedConditions.visibilityOf(billingLastnameFld));
        e.clear();
        e.sendKeys(billingLastName);
        return this;
    }

    public CheckoutPage enterBillingAddressLineOne(String billingAddressLineOne){
        WebElement e = wait.until(ExpectedConditions.visibilityOf(billingAddressOneFld));
        e.clear();
        e.sendKeys(billingAddressLineOne);
        return this;
    }

    public CheckoutPage enterBillingCity(String billingCity){
        WebElement e = wait.until(ExpectedConditions.visibilityOf(billingCityFld));
        e.clear();
        e.sendKeys(billingCity);
        return this;
    }

    public CheckoutPage enterBillingState(String billingStateName){
        //this has to be done to run test in firefox as firefox gives error if we use "select" for dropdown
        //org.openqa.selenium.ElementNotInteractableException:
        //Element <option> could not be scrolled into view
        wait.until(ExpectedConditions.elementToBeClickable(alternateBillingStateDropdown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[text()='" + billingStateName + "']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();

        //for chrome below statement works
//        Select select = new Select(wait.until(ExpectedConditions.visibilityOf(billingStateDropdown)));
//        select.selectByVisibleText(billingStateName);
        return this;
    }

    public CheckoutPage enterBillingZip(String billingZip){
        WebElement e = wait.until(ExpectedConditions.visibilityOf(billingZipFld));
        e.clear();
        e.sendKeys(billingZip);
        return this;
    }

    public CheckoutPage enterBillingEmail(String billingEmail){
        WebElement e = wait.until(ExpectedConditions.visibilityOf(billingEmailFld));
        e.clear();
        e.sendKeys(billingEmail);
        return this;
    }

//    public CheckoutPage setBillingDetails(String billingFirstName,String billingLastName,
//                                          String billingAddressOne, String billingCity,
//                                          String billingStateName, String billingZip,
//                                          String billingEmail){
//        return enterBillingFirstName(billingFirstName)
//                .enterBillingLastName(billingLastName)
//                .enterBillingAddressLineOne(billingAddressOne)
//                .enterBillingCity(billingCity)
//                .enterBillingState(billingStateName)
//                .enterBillingZip(billingZip)
//                .enterBillingEmail(billingEmail);
//    }


    //use of datatabletype concept and domain object
    public CheckoutPage setBillingDetails(BillingDetails billingDetails){
        return enterBillingFirstName(billingDetails.getBillingFirstName())
                .enterBillingLastName(billingDetails.getBillingLastName())
                .enterBillingAddressLineOne(billingDetails.getBillingAddressOne())
                .enterBillingCity(billingDetails.getBillingCity())
                .enterBillingState(billingDetails.getBillingStateName())
                .enterBillingZip(billingDetails.getBillingZip())
                .enterBillingEmail(billingDetails.getBillingEmail());
    }

    public CheckoutPage placeOrder(){
        waitForOverlaysToDisappear(overlay);
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();
        return this;
    }

    public String getNotice(){
        return wait.until(ExpectedConditions.visibilityOf(noticeTxt)).getText();
    }

}
