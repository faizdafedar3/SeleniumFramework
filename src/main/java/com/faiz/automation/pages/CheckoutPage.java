package com.faiz.automation.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {

        this.driver = driver;

        this.wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(30));

        PageFactory.initElements(
                driver,
                this);
    }

    @FindBy(id = "billing-address-select")
    private WebElement billingAddressSelect;

    @FindBy(id = "BillingNewAddress_FirstName")
    private WebElement billingFirstName;

    @FindBy(id = "BillingNewAddress_LastName")
    private WebElement billingLastName;

    @FindBy(id = "BillingNewAddress_Email")
    private WebElement billingEmail;

    @FindBy(id = "BillingNewAddress_CountryId")
    private WebElement billingCountry;

    @FindBy(id = "BillingNewAddress_City")
    private WebElement billingCity;

    @FindBy(id = "BillingNewAddress_Address1")
    private WebElement billingAddress1;

    @FindBy(id = "BillingNewAddress_ZipPostalCode")
    private WebElement billingZip;

    @FindBy(id = "BillingNewAddress_PhoneNumber")
    private WebElement billingPhone;

    @FindBy(css = "input.button-1.new-address-next-step-button")
    private WebElement billingContinue;

    @FindBy(css = "input.button-1.new-address-next-step-button[onclick='Shipping.save()']")
    private WebElement shippingAddressContinue;

    @FindBy(css = "input.button-1.shipping-method-next-step-button")
    private WebElement shippingMethodContinue;

    @FindBy(css = "input.button-1.payment-method-next-step-button")
    private WebElement paymentMethodContinue;

    @FindBy(css = "input.button-1.payment-info-next-step-button")
    private WebElement paymentInfoContinue;

    @FindBy(css = "input.button-1.confirm-order-next-step-button")
    private WebElement confirmOrderButton;

    @FindBy(xpath =
            "//*[contains(text(),'Your order has been successfully processed')]")
    private WebElement orderSuccessMessage;

    public void completeCheckout(String email) {

        enterBillingAddressIfRequired(email);

        continueShippingAddressIfRequired();

        continueShippingMethod();

        continuePaymentMethod();

        continuePaymentInformation();

        confirmOrder();
    }

    public boolean isOrderPlacedSuccessfully() {

        try {

            return wait.until(
                    ExpectedConditions.visibilityOf(
                            orderSuccessMessage))
                    .isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    private void enterBillingAddressIfRequired(
            String email) {

        try {

            if (billingAddressSelect.isDisplayed()) {

                Select addressSelect =
                        new Select(
                                billingAddressSelect);

                List<WebElement> options =
                        addressSelect.getOptions();

                if (options.size() > 1) {

                    addressSelect.selectByIndex(0);

                    clickElement(
                            billingContinue);

                    System.out.println(
                            "Existing Billing Address Selected");

                    return;
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Creating New Billing Address");
        }

        billingFirstName.clear();
        billingFirstName.sendKeys("Faiz");

        billingLastName.clear();
        billingLastName.sendKeys("Dafedar");

        billingEmail.clear();
        billingEmail.sendKeys(email);

        Select country =
                new Select(
                        billingCountry);

        country.selectByVisibleText(
                "India");

        billingCity.clear();
        billingCity.sendKeys("Bangalore");

        billingAddress1.clear();
        billingAddress1.sendKeys("MG Road");

        billingZip.clear();
        billingZip.sendKeys("560001");

        billingPhone.clear();
        billingPhone.sendKeys("9876543210");

        clickElement(
                billingContinue);

        System.out.println(
                "Billing Address Saved");
    }

    private void continueShippingAddressIfRequired() {

        try {

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            shippingAddressContinue));

            clickElement(
                    shippingAddressContinue);

            System.out.println(
                    "Shipping Address Continued");

        } catch (Exception e) {

            System.out.println(
                    "Shipping Address Step Skipped");
        }
    }

    private void continueShippingMethod() {

        try {

            wait.until(
                    ExpectedConditions.visibilityOf(
                            shippingMethodContinue));

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            shippingMethodContinue));

            clickElement(
                    shippingMethodContinue);

            System.out.println(
                    "Shipping Method Continued");

        } catch (Exception e) {

            System.out.println(
                    "Shipping Method Failed");

            throw e;
        }
    }

    private void continuePaymentMethod() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        paymentMethodContinue));

        clickElement(
                paymentMethodContinue);

        System.out.println(
                "Payment Method Continued");
    }

    private void continuePaymentInformation() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        paymentInfoContinue));

        clickElement(
                paymentInfoContinue);

        System.out.println(
                "Payment Information Continued");
    }

    private void confirmOrder() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        confirmOrderButton));

        clickElement(
                confirmOrderButton);

        System.out.println(
                "Order Confirmed");
    }

    private void clickElement(
            WebElement element) {

        try {

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            element));

            element.click();

        } catch (Exception e) {

            JavascriptExecutor js =
                    (JavascriptExecutor) driver;

            js.executeScript(
                    "arguments[0].click();",
                    element);
        }
    }
}