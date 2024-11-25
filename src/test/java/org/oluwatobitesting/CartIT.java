package org.oluwatobitesting;

import org.oluwatobitesting.Pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.oluwatobitesting.ProductsIT.numberOfProducts;

public class CartIT extends ProjectSetup{
    int numberOfProductsLeft = 3;
    int numberOfProductsAdded = 2;
    String removedProduct = "Test.allTheThings() T-Shirt";
    String reducedProduct = "Sauce Labs Bolt T-Shirt";
    String addedProduct = "Sauce Labs Onesie";
    String[] remainProductNames = {"Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie", "Sauce Labs Backpack"};

    @BeforeClass(alwaysRun = true)
    public void preCartPageTest() {
        cartPageObject = new CartPage(driver);
    }

    @Test(priority = 1)
    public void navigateToCartPage() {
        //Tap Cart Badge
        cartPageObject.tapCartBadge();

        //Assertion for Cart Page
        Assert.assertEquals(cartPageObject.getCartPageTitle(), "My Cart");
    }

    @Test(priority = 2)
    public void removeProductFromCart() {
        //Assertion For Removed Product
        Assert.assertEquals(cartPageObject.getRemovedProduct(removedProduct), removedProduct);

        //Tap Remove Button
        cartPageObject.removeProductFromCart(removedProduct);
    }

    @Test(priority = 3)
    public void reduceProductFromCart() {
        //Tap Counter Minus Button
        cartPageObject.reduceProductFromCart(reducedProduct, numberOfProductsLeft);

        //Assertion For Reduced Product
        Assert.assertEquals(cartPageObject.getNumberOfProduct(reducedProduct), numberOfProductsLeft);
    }

    @Test(priority = 4)
    public void addProductToCart() {
        //Tap Counter Plus Button
        cartPageObject.addProductToCart(addedProduct, numberOfProducts + numberOfProductsAdded);

        //Assertion For Increased Product
        Assert.assertEquals(cartPageObject.getNumberOfProduct(addedProduct), numberOfProducts + numberOfProductsAdded);
    }

    @Test(priority = 5)
    public void proceedToCheckout() {
        //Assertions for Product Number and Amount
        Assert.assertEquals(cartPageObject.totalProductNumber(remainProductNames), cartPageObject.getTotalProductNumber());
        Assert.assertEquals(cartPageObject.totalProductAmount(remainProductNames), cartPageObject.getTotalProductAmount());

        //Tap Proceed to Checkout
        cartPageObject.tapCheckoutButton();
    }
}
