package com.faiz.automation.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By billingAddressSelect =
            By.id("billing-address-select");

    private final By billingFirstName =
            By.id("BillingNewAddress_FirstName");

    private final By billingLastName =
            By.id("BillingNewAddress_LastName");

    private final By billingEmail =
            By.id("BillingNewAddress_Email");

    private final By billingCountry =
            By.id("BillingNewAddress_CountryId");

    private final By billingCity =
            By.id("BillingNewAddress_City");

    private final By billingAddress1 =
            By.id("BillingNewAddress_Address1");

    private final By billingZip =
            By.id("BillingNewAddress_ZipPostalCode");

    private final By billingPhone =
            By.id("BillingNewAddress_PhoneNumber");

    private final By billingContinue =
            By.cssSelector("input.button-1.new-address-next-step-button");

    private final By shippingAddressContinue =
            By.cssSelector("input.button-1.new-address-next-step-button[onclick='Shipping.save()']");

    private final By shippingMethodContinue =
            By.cssSelector("input.button-1.shipping-method-next-step-button");

    private final By paymentMethodContinue =
            By.cssSelector("input.button-1.payment-method-next-step-button");

    private final By paymentInfoContinue =
            By.cssSelector("input.button-1.payment-info-next-step-button");

    private final By confirmOrderButton =
            By.cssSelector("input.button-1.confirm-order-next-step-button");

    private final By orderSuccessMessage =
            By.xpath("//*[contains(text(),'Your order has been successfully processed')]");

    public CheckoutPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    public void completeCheckout(String email) {

        enterBillingAddressIfRequired(email);
        continueShippingAddressIfRequired();
        continueShippingMethod();
        continuePaymentMethod();
        continuePaymentInformation();
        confirmOrder();
    }

    public boolean isOrderPlacedSuccessfully() {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderSuccessMessage))
                .isDisplayed();
    }

    private void enterBillingAddressIfRequired(String email) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout-steps")));

        if (isElementPresent(billingAddressSelect)) {

            Select addressSelect =
                    new Select(driver.findElement(billingAddressSelect));

            List<WebElement> options =
                    addressSelect.getOptions();

            if (options.size() > 1) {
                addressSelect.selectByIndex(0);
                click(billingContinue);
                return;
            }
        }

        type(billingFirstName, "Faiz");
        type(billingLastName, "Dafedar");
        type(billingEmail, email);
        new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(billingCountry)))
                .selectByVisibleText("India");
        type(billingCity, "Bangalore");
        type(billingAddress1, "MG Road");
        type(billingZip, "560001");
        type(billingPhone, "9876543210");
        click(billingContinue);
    }

    private void continueShippingAddressIfRequired() {

        if (isElementPresent(shippingAddressContinue)) {
            click(shippingAddressContinue);
        }
    }

    private void continueShippingMethod() {

        click(shippingMethodContinue);
    }

    private void continuePaymentMethod() {

        click(paymentMethodContinue);
    }

    private void continuePaymentInformation() {

        click(paymentInfoContinue);
    }

    private void confirmOrder() {

        click(confirmOrderButton);
    }

    private void type(By locator,
                      String value) {

        WebElement element =
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        element.clear();
        element.sendKeys(value);
    }

    private void click(By locator) {

        wait.until(ExpectedConditions.elementToBeClickable(locator))
                .click();
    }

    private boolean isElementPresent(By locator) {

        return !driver.findElements(locator)
                .isEmpty();
    }
}
