package ru.bulkashmak;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ru.bulkashmak.pages.MainPage;
import ru.bulkashmak.pages.ProductPage;
import ru.bulkashmak.util.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class RegardTest {

    //Список, в котором будут храниться наименования покупок.
    List<String> shoppingList = new ArrayList<>();
    MainPage mainPage = new MainPage();

    @Given("user is on main page")
    public void user_is_on_main_page() {
        mainPage.open();
    }

    @When("user click motherboards in left menu")
    public void user_click_motherboards_in_left_menu() {
        mainPage.openMenu(ProductCategory.MOTHERBOARDS);
    }
    @And("user click {string} in motherboards submenu")
    public void userClickIntelSocketInMotherboardsSubmenu(String subCategoryName) {
        mainPage.openMenuSubCategory(ProductCategory.MOTHERBOARDS, subCategoryName);
    }
    @Then("browser open page with {string} motherboards")
    public void browserOpenPageWithIntelSocketMotherboards(String subCategoryName) {
        mainPage.checkMenuSubCategoryOpened(subCategoryName);
    }
    @And("user click add to cart button of {int}th motherboard on page")
    public void userClickAddToCartButtonOfThMotherboardOnPage(int arg) {
        mainPage.buyMarketItem(arg, shoppingList);
    }

    @When("user click cases in left menu")
    public void userClickCasesInLeftMenu() {
        mainPage.openMenu(ProductCategory.COMPUTER_CASES);
    }
    @And("click {string} in cases submenu")
    public void clickAerocoolInCasesSubmenu(String subCategoryName) {
        mainPage.openMenuSubCategory(ProductCategory.COMPUTER_CASES, subCategoryName);
    }
    @Then("browser open page with {string} cases")
    public void browserOpenPageWithAerocoolCases(String subCategoryName) {
        mainPage.checkMenuSubCategoryOpened(subCategoryName);
    }

    @And("user click add to cart button of {int}th case on page")
    public void userClickAddToCartButtonOfThCaseOnPage(int arg0) {
        mainPage.buyMarketItem(arg0, shoppingList);
    }
    @And("user click name of {int}th case on page")
    public void userClickNameOfThCaseOnPage(int arg0) {
        mainPage.openMarketItem(arg0);
    }

    ProductPage productPage = new ProductPage();

    @Given("user is on product page")
    public void userIsOnProductPage() {
        productPage.open();
    }
    @When("user click add to cart button on product page")
    public void userClickAddToCartButtonOnProductPage() {
        productPage.addProductToShoppingCart(shoppingList);
    }
    @And("user click blue button open cart")
    public void userClickBlueButtonOpenCart() {
        productPage.clickGoToShoppingCartButton();
    }
    @Then("browser open cart page")
    public void browserOpenCartPage() {
        mainPage.checkMenuSubCategoryOpened("Корзина");
    }
}
