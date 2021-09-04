package ru.bulkashmak.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import static org.junit.jupiter.api.Assertions.*;
import ru.bulkashmak.util.WebDriverWrapper;

public abstract class AbstractPage {

    public static Logger log = LogManager.getRootLogger();

    public WebDriverWrapper driver = WebDriverWrapper.getInstance();
    public String baseUrl;

    public AbstractPage(String baseUrl) {

        this.baseUrl = baseUrl;
    }

    public void open(){

        driver.get(baseUrl);
        log.info("Страница по адресу '{}' открыта.", baseUrl);
    }

    public void close(){

        driver.close();
        log.info("Страница по адресу '{}' была закрыта.", baseUrl);
    }

//    public void checkPage() {
//
//        log.info("Осуществляю проверку, что мы находимся на странице по адресу '{}'.", baseUrl);
//        assertTrue(driver.getCurrentUrl().contains(baseUrl));
//    }
}
