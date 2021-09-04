package ru.bulkashmak.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class WebDriverWrapper {

    private static RemoteWebDriver driver;
    private static WebDriverWrapper wrap;

    private static final Logger LOG = LogManager.getRootLogger();

    private WebDriverWait wait;

    private WebDriverWrapper(){
        LOG.debug("Инициализирую экземпляр обертки над веб драйвером.");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        this.wait = new WebDriverWait(driver, 5, 100);

        LOG.debug("Инициализация обертки завершена.");
    }

    public static WebDriverWrapper getInstance() {

        LOG.debug("Запрошен экземпляр драйвера.");
        if (wrap == null) {
            wrap = new WebDriverWrapper();
        }
        return wrap;
    }

    public void get(String baseUrl) {

        LOG.debug(String.format("Открываю страницу по адресу '%s'", baseUrl));
        driver.get(baseUrl);
        LOG.debug(String.format("Открыл страницу по адресу '%s'", baseUrl));
    }

    public String getCurrentUrl() {

        LOG.debug("Получаю адрес текущей страницы...");
        return driver.getCurrentUrl();
    }

    public void close() {

        LOG.debug("Закрываю текущую страницу");
        driver.close();
    }


    public String getWindowHandle() {

        return driver.getWindowHandle();
    }

    public Set<String> getWindowHandles() {

        return driver.getWindowHandles();
    }

    public WebDriver.TargetLocator switchTo() {

        return driver.switchTo();
    }

    public void clickElement(String xpath){

        WebElement e;
        //даем 5 попыток нахождения элемента с небольшими скроллами
        //5 - просто как пример, вы можете вообще модифицировать цикл на while
        //и условием прекращения его выполнения считать дохождение до конца страницы
        for (int i = 0; i < 5; i++) {
            try {
                e = this.findElement(By.xpath(xpath));
                if (e.isDisplayed() && e.isEnabled()) {
                    e.click();
                    LOG.debug("Кликнут элемент по локатору '%s'", xpath);
                    return;
                }
            } catch (ElementNotInteractableException exc) {
                LOG.warn("Элемент некликабелен. Делаю дополнительный скрол вниз и повторяю попытку клика...");
                driver.executeScript(String.format("scroll(0, %d)", 50));
            } catch (StaleElementReferenceException exc) {
                LOG.warn("Элемент устарел/обновил свое состояние. Обновляю информацию об элементе и повторяю поиск...");
            } catch (Exception exc){
                exc.printStackTrace();
            }
        }
        LOG.error(String.format("Не удалось кликнуть по элементу с локатором '%s'.", xpath));
        throw new ElementNotInteractableException("Ошибка клика по элементу.");
    }

    public WebElement findElement(By xpath) {

        try {
            LOG.debug("Ищу элемент по локатору '{}'", xpath);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(xpath));
            ((JavascriptExecutor)driver).executeScript("arguments[0]['style']['backgroundColor']='darksalmon';", element);

            driver.executeScript("arguments[0].scrollIntoView(true);", element);

            LOG.debug("Элемент по локатору '{}' найден.", xpath);
            return element;
        } catch (RuntimeException e){
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String newFileName = String.format(
                    "%s\\screenshots\\%s.png", System.getProperty("user.dir"), LocalDateTime.now().toString().replace(":", "-"));
            File destination = new File(newFileName);

            try {
                FileUtils.copyFile(source, destination);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            driver.quit();
            throw e;
        }
    }

    public List<WebElement> findElements(By xpath) {

        LOG.debug("Ищу элемент по локатору '{}'", xpath);
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xpath));
        LOG.debug("Элемент по локатору '{}' найден.", xpath);
        return elements;
    }

    public void scrollDown(int scrollDownValue){

        driver.executeScript(String.format("scroll(0, %d)", scrollDownValue));
    }
}
