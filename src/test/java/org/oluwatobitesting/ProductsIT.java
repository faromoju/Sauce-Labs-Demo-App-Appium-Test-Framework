package org.oluwatobitesting;

import org.oluwatobitesting.Pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ProductsIT extends ProjectSetup{
    static String[] productName = {"Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt", "Sauce Labs Backpack"};
    static int numberOfProducts = 4;

    @BeforeClass(alwaysRun = true)
    public static void preProductsPageTest() {
        //Page Objects
        productsPageObject = new ProductsPage(driver);
    }

    @Test(priority = 1)
    public void sortProducts() {
        //Click Sort Button
        productsPageObject.clickSortButton();

        //Select Sort Type
        //Change Sort Type based on Accessibility ID
        productsPageObject.selectSortType("nameDesc");

        //Assertion for Selected Sort Type
        //Change Expected First Product based on selected Sort Type
        Assert.assertEquals(productsPageObject.firstProductName(), "Test.allTheThings() T-Shirt");
    }

    @Test(priority = 2, dataProvider = "testData")
    public void rateProduct(String[] productName) {
        //Get Product and Rate Product
        //Adjust Product Name and Star Number for different scenarios
        productsPageObject.rateProducts(productName, "5");

        //Assertion for Rate Products
        productsPageObject.rateProduct("2");
        Assert.assertFalse(productsPageObject.rateModalText.isEmpty());
        productsPageObject.closeRateModal();
    }

    @Test(priority = 3, dataProvider = "testData")
    public static void addProductToCart(String[] productName) {
        //Add Product To Cart
        //Adjust Product Name and Number of Products for different scenarios
        productsPageObject.addProductsToCart(productName, numberOfProducts);

        //Assertion for Product Counter
        productsPageObject.addProductToCart(3);
        Assert.assertEquals(productsPageObject.getCounterAmount(), "3");
        productsPageObject.tapAndroidBackButton();
    }

    @Test(priority = 4, dataProvider = "testData")
    public void addToCartWithoutProduct(String[] productName) {
        //Sort Products
        productsPageObject.clickSortButton();
        productsPageObject.selectSortType("nameAsc");

        //Product Counter To Zero
        productsPageObject.removeFromProductsCounter(productName[1]);

        //Assertion that Add To Cart Button is not clickable
        Assert.assertEquals(productsPageObject.getAddToCartAttribute(), "false");
        productsPageObject.tapAndroidBackButton();
    }

    @Test(priority = 5)
    public void rateProductIndividualPage() {
        //Add Product to Cart and Rate It
        productsPageObject.addProductToCart(5);
        productsPageObject.rateProduct("3");

        //Assertion for Rate Products
        Assert.assertFalse(productsPageObject.rateModalText.isEmpty());
        productsPageObject.closeRateModal();
    }

    @DataProvider
    public Object[][] testData() {

        return new Object[][] {
            {
                productName
            }
        };
    }
}
