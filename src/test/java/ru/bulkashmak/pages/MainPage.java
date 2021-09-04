package ru.bulkashmak.pages;

public class MainPage {

    public MainPage() {
        super("https://regard.ru");
    }

    public void openMenu(ProductCategory productCategory) {
        WebElement motherboardsMenuItem =
                driver.findElement(By.xpath(String.format("//a[text()='%s']", productCategory.sectionName)));
        motherboardsMenuItem.click();
    }

    public void openMenuWithSubCategory(ProductCategory productCategory, String subCategoryName) {
        openMenu(productCategory);
        WebElement subCategory = driver.findElement(By.xpath(String.format(
                "//a[text()='%s']/following-sibling::ul/li/a[text()='%s']", productCategory.sectionName, subCategoryName)));
        subCategory.click();
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
