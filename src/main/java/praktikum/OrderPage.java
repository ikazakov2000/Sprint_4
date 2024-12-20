package praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {

    // Секция для кого самокат

    //Поле Имя
    public static final By byFieldName =
            By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/input");

    //Поле Фамилия
    public static final By byFieldLastName =
            By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/input");

    //Поле адрес доставки
    public static final By byFieldAdressDelivery =
            By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[3]/input");

    //Поле станция метро
    public static final By byFieldMetroStation =
            By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/div/div/input");

    //Выпадающий список станций метро
    public static final By byListMetroStation =
            By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/div/div[2]/ul/li");

    //Поле номер телефона
    public static final By byFieldPhone =
            By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[5]/input");

    //Кнопка Далее
    public static final By byButtonNext =
            By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/button");

    //Секция Про Аренду

    //Поле Календаря
    public static final By byFieldCalendar =
            By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/div[1]/div/input");
    //Кнопка следующий месяц в календаре
    public static final By byButtonNextMonth =
            By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/div[2]/div[2]/div/div/button[2]");
    //Дата
    public static final By byDate =
            By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/div[2]/div[2]/div/div/div[2]/div[2]" +
                    "/div[4]/div[3]");

    //Поле срок аренды
    public static final By byFieldRentalPeriod = By.className("Dropdown-placeholder");
    //Выпадающий список срок аренды
    public static final By byListRentalPeriod = By.className("Dropdown-option");

    //Чек-бокс цвет самоката
    public static final By byCheckboxColorScooter = By.className("Checkbox_Input__14A2w");

    //Комментарий для доставки
    public static final By byFieldCommentForDelivery =
            By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/input");

    // Кнопка Заказать
    public static final By byButtonOrder = By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/button[2]");
    //Кнопка подтверждения заказа -"Да"
    public static final By byButtonConfirmOrder =
            By.xpath("//*[@id=\"root\"]/div/div[2]/div[5]/div[2]/button[2]");
    //Уведомление об успешном заказе
    public static final By byNoticeSuccessfulOrder = By.className("Order_Modal__YZ-d3");

    final WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public OrderPage sectionForWhomScooter() {
        driver.findElement(byFieldName).sendKeys(TestDate.testTextName); //поле Имя
        driver.findElement(byFieldLastName).sendKeys(TestDate.testTextLastName); // поле Фамилия
        driver.findElement(byFieldAdressDelivery).sendKeys(TestDate.testTextAdressDelivery); // Поле Адрес

        driver.findElement(byFieldMetroStation).click(); //поле Станция метро
        driver.findElements(byListMetroStation).get(3).click(); //поле Станция метро - выпадающий список

        driver.findElement(byFieldPhone).sendKeys(TestDate.testNumberPhone); // поле Телефон

        driver.findElement(byButtonNext).click(); //кнопка Далее
        return this;
    }

    public OrderPage sectionAboutRentScooter() {
        driver.findElement(byFieldCalendar).click(); // поле Календарь
        driver.findElement(byButtonNextMonth).click(); // кнопка следующий месяц
        driver.findElement(byDate).click(); // клик по дате

        driver.findElement(byFieldRentalPeriod).click(); // поле срок аренды
        driver.findElements(byListRentalPeriod).get(5).click(); //поле срок аренды - выпадающий список

        driver.findElements(byCheckboxColorScooter).get(1).click(); // чек-бокс цвет самоката

        driver.findElement(byFieldCommentForDelivery).sendKeys(TestDate.testTextCommentForDelivery); // поле комментарий для курьера

        driver.findElement(byButtonOrder).click(); //кнопка Заказать
        driver.findElement(byButtonConfirmOrder).click(); //кнопка подтверждения заказа
        return this;
    }

    public OrderPage successfulOrderNotification() {
        driver.findElement(byNoticeSuccessfulOrder).isEnabled(); // Уведомление об успешном заказе
        return this;
    }
    //Локатор поля Заказ оформлен
    private final By orderIsProcessed = By.xpath("//div[text()='Заказ оформлен']");

    //метод возвращает истину, если поле Заказ оформлен отображено
    public boolean orderIsProcessedTextIsDisplayed() {

        return driver.findElement(orderIsProcessed).isDisplayed();
    }
}
