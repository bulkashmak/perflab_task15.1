package ru.bulkashmak.pages;

import org.openqa.selenium.By;

import java.util.List;

public class ProductPage extends AbstractPage {

    String addToCartButtonXpath = "//div[@class='bcontent lot']//a[@title='Добавить в корзину']";
    String goToCartButtonXpath = "//div[@class='bcontent lot']//a[@title='Перейти в корзину']";
    String itemNameHeaderXpath = "//div[@class='goods_header']/span";

    public ProductPage() {
        super("https://www.regard.ru/catalog/tovar");
    }

    public void addProductToShoppingCart(List<String> shoppingList){
        driver.clickElement(addToCartButtonXpath);
        shoppingList.add(driver.findElement(By.xpath(itemNameHeaderXpath)).getAttribute("content"));
    }

    public void clickGoToShoppingCartButton(){
        driver.clickElement(goToCartButtonXpath);
    }
}
