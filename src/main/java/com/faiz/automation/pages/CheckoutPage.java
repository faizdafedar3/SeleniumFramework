package com.faiz.automation.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.faiz.automation.base.BasePage;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);
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

    @FindBy(xpath = "//*[contains(text(),'Your order has been successfully processed')]")
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

            return isDisplayed(
                    orderSuccessMessage);

        } catch (Exception e) {

            return false;
        }
    }

    private void enterBillingAddressIfRequired(
            String email) {

        try {

            if (isDisplayed(
                    billingAddressSelect)) {

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

        type(
                billingFirstName,
                "Faiz");

        type(
                billingLastName,
                "Dafedar");

        type(
                billingEmail,
                email);

        Select country =
                new Select(
                        billingCountry);

        country.selectByVisibleText(
                "India");

        type(
                billingCity,
                "Bangalore");

        type(
                billingAddress1,
                "MG Road");

        type(
                billingZip,
                "560001");

        type(
                billingPhone,
                "9876543210");

        clickElement(
                billingContinue);

        System.out.println(
                "Billing Address Saved");
    }

    private void continueShippingAddressIfRequired() {

        try {

            waitForClickable(
                    shippingAddressContinue);

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

            waitForVisibility(
                    shippingMethodContinue);

            waitForClickable(
                    shippingMethodContinue);

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

        waitForClickable(
                paymentMethodContinue);

        clickElement(
                paymentMethodContinue);

        System.out.println(
                "Payment Method Continued");
    }

    private void continuePaymentInformation() {

        waitForClickable(
                paymentInfoContinue);

        clickElement(
                paymentInfoContinue);

        System.out.println(
                "Payment Information Continued");
    }

    private void confirmOrder() {

        waitForClickable(
                confirmOrderButton);

        clickElement(
                confirmOrderButton);

        System.out.println(
                "Order Confirmed");
    }

    private void clickElement(
            WebElement element) {

        try {

            click(
                    element);

        } catch (Exception e) {

            jsClick(
                    element);
        }
    }
}