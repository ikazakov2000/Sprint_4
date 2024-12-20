package praktikum;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ScooterTestButtonOrder {

    @Rule
    public BaseDriverRule driverRule = new BaseDriverRule();


    @Test
    public void upButtonOrderScooter() {

        HomePage homePage = new HomePage(driverRule.getDriver());
        homePage.openHomePage()
                .upButtonOrder(); //Верхняя кнопка "Заказать"

        OrderPage orderPage = new OrderPage(driverRule.getDriver());
        orderPage.sectionForWhomScooter() // Раздел - Для кого самокат
                .sectionAboutRentScooter();   //Раздел - Про аренду
                //.successfulOrderNotification(); // всплывающее окно с сообщением об успешном создании заказа
        assertTrue(orderPage.orderIsProcessedTextIsDisplayed());
    }


    @Test
    public void downButtonOrderScooter() {

        HomePage homePage = new HomePage(driverRule.getDriver());
        homePage.openHomePage()
                .scrollDownButtonOrder() // скролл до нижней кнопки "Заказать"
                .downButtonOrder(); //Нижняя кнопка "Заказать"

        OrderPage orderPage = new OrderPage(driverRule.getDriver());
        orderPage.sectionForWhomScooter() //Раздел - Для кого самокат
                .sectionAboutRentScooter(); //Раздел - Про аренду
                //.successfulOrderNotification();// всплывающее окно с сообщением об успешном создании заказа
        assertTrue(orderPage.orderIsProcessedTextIsDisplayed());
    }


}