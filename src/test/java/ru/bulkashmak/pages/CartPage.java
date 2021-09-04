package ru.bulkashmak.pages;

import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends AbstractPage {

    private String shoppingCartContentsXpath = "//table[@id='table-basket']//tr[@class='goods_line']/td[3]/a";

    public CartPage() {
        super("https://www.regard.ru/basket/");
    }

    public void checkShoppingCartContentsNotEmpty(){
        List<WebElement> shoppingCartContents = driver.findElements(By.xpath(shoppingCartContentsXpath));
        assertTrue(shoppingCartContents.size() > 0, "Корзина пуста!");
    }

    public void verifyShoppingCartHasValidItems(List<String> expectedItems){
        List<String> shoppingCartContents = driver.findElements(By.xpath(shoppingCartContentsXpath))
                .stream().map(e -> e.getText()).collect(Collectors.toList());

        log.info("Ожидаемое наполнение корзины: " + expectedItems);
        log.info("Реальное наполнение корзины: " + shoppingCartContents);

        assertEquals(expectedItems.size(), shoppingCartContents.size(),
                "Количество покупок в корзине не соответствует ожидаемому!");

        assertTrue(shoppingCartContents.containsAll(expectedItems),
                "Покупки в корзине не содержат все ожидаемые товары!");
    }
}
