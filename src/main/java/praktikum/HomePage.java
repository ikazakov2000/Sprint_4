package praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;



public class HomePage {

    final WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //Открыть страницу
    public HomePage openHomePage() {
        driver.get(EnvConfiguration.URL_BASE);
        return this;
    }


    //Клик по кнопке "Cookie"
    public void clickButtonCookie() {
        driver.findElement(byClassCookie()).click();
    }
    private static By byClassCookie() {
        return By.className("App_CookieButton__3cvqF");
    }


    //Лист вопросов
    public List<WebElement> getImportantQuestion() {
        return driver.findElements(byClassImportantQuestion());
    }
    private static By byClassImportantQuestion() {
        return By.className("accordion__button");
    }

    //Лист ответов
    public List<WebElement> getImportantAnswer() {
        return driver.findElements(byClassImportantAnswer());
    }
    public By byClassImportantAnswer() {
        return By.className("accordion__panel");
    }


    // OrderPage
    //Верхняя кнопка "Заказать"
    public static final By byUpButtonOrder =
            By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div[2]/button[1]");
    public HomePage upButtonOrder() {
        driver.findElement(byUpButtonOrder).click(); //Верхняя кнопка Заказать
        return this;
    }

    //Нижняя кнопка "Заказать"
    public static final By byDownButtonOrder =
            By.xpath("//*[@id=\"root\"]/div/div[1]/div[4]/div[2]/div[5]/button");
    //Скролл до нижней кнопки
    public HomePage scrollDownButtonOrder() {
        WebElement downButtonOrder = driver.findElement(byDownButtonOrder); // Нижняя кнопка Заказать
        // Прокрути страницу до Нижней кнопка Заказать
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", downButtonOrder);
        return this;
    }
    public HomePage downButtonOrder() {
        driver.findElement(byDownButtonOrder).click();//Нижняя кнопка Заказать
        return this;
    }

}
