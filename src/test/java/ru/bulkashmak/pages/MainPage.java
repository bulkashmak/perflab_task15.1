package ru.bulkashmak.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.bulkashmak.util.ProductCategory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPage extends AbstractPage {

    public MainPage() {
        super("https://regard.ru");
    }

    public void openMenu(ProductCategory productCategory) {
        WebElement motherboardsMenuItem =
                driver.findElement(By.xpath(String.format("//a[text()='%s']", productCategory.sectionName)));
        motherboardsMenuItem.click();
    }

    public void openMenuSubCategory(ProductCategory productCategory, String subCategoryName) {
        WebElement subCategory = driver.findElement(By.xpath(String.format(
                "//a[text()='%s']/following-sibling::ul/li/a[text()='%s']", productCategory.sectionName, subCategoryName)));
        subCategory.click();
    }

    public void checkMenuSubCategoryOpened(String subCategoryName) {
        WebElement mainPageTitle = driver.findElement(By.xpath("//div[@id='hits']/div[@class='top']/h1"));
        assertTrue(
                mainPageTitle.getText().contains(subCategoryName)
        );
    }

    public void buyMarketItem(int itemIndex, List<String> shoppingList) {
        itemIndex++;
        driver.clickElement(String.format("//div[%d]//*[@class='price']/span/a", itemIndex));

        //сохраняю информацию о купленном товаре
        shoppingList.add(driver.findElement(
                By.xpath(String.format("//div[%d]//span[@data-category]/preceding-sibling::a", itemIndex))).getText());
    }

    public void openMarketItem(int itemIndex) {
        itemIndex++;
        driver.clickElement(String.format("//div[%d]//span[@data-category]/preceding-sibling::a", itemIndex));
    }
}
