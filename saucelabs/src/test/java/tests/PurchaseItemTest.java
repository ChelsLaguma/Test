package com.changeit;

import com.changeit.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;
import java.lang.reflect.Method;
import static org.testng.Assert.assertTrue;

public class PurchaseItemTest extends BaseTest {

    @BeforeMethod
    @Override
    public void setup(Method method) throws IOException {
        super.setup(method);
    }

    @Test
    public void purchaseItemSuccessful(){
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login();
        dashboardPage.addItemToCart();
        CartPage cartPage = dashboardPage.clickCartLink();
        DashboardPage dashboardPage1 = cartPage.clickContinue();
        dashboardPage1.addNewItem();
        CartPage cartPage1 = dashboardPage1.clickCartLink();
        cartPage1.clickRemoveButton();
        CheckoutPage checkoutPage = cartPage1.clickCheckout();
        CheckoutOverviewPage checkoutOverviewPage = checkoutPage.checkoutCredentials();
        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.clickFinishButton();
        assertTrue(checkoutCompletePage.orderPlacedSuccessful());
    }
}