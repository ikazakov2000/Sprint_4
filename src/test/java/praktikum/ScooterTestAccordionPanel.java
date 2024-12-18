package praktikum;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static praktikum.EnvConfiguration.DEFAULT_TIMEOUT;

@RunWith(Parameterized.class)
public class ScooterTestAccordionPanel {
    private final String expectedTextImportantAnswers; //Текст ответа
    private final int indexQuestionAnswer;  //Номер вопроса-ответа
    public ScooterTestAccordionPanel(String expectedTextImportantAnswers, int indexQuestionAnswer) {
        this.expectedTextImportantAnswers = expectedTextImportantAnswers;
        this.indexQuestionAnswer = indexQuestionAnswer;
    }

    @Parameterized.Parameters(name = "numIndex{1} - expectText{0}")
    public static Object[][] testDateGen() {
        return new Object[][]{
                {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.", 0},
                {"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями," +
                        " можете просто сделать несколько заказов — один за другим.", 1},
                {"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня." +
                        " Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру." +
                        " Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", 2},
                {"Только начиная с завтрашнего дня. Но скоро станем расторопнее.", 3},
                {"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому" +
                        " номеру 1010.", 4},
                {"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете" +
                        " кататься без передышек и во сне. Зарядка не понадобится.", 5},
                {"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим." +
                        " Все же свои.", 6},
                {"Да, обязательно. Всем самокатов! И Москве, и Московской области.", 7}
        };
    }


    @Rule
    public BaseDriverRule driverRule = new BaseDriverRule();

    @Test
    public void testAccordionPanel (){
        HomePage homePage = new HomePage(driverRule.getDriver());
        WebDriver driver = driverRule.getDriver();
        //URL
        homePage.openHomePage()//Открыть страницу
                .clickButtonCookie();//клик по кнопке куки

        //тестовый метод для раздела "Вопросы о важном"
        //Скролл до первого вопроса
        WebElement importantQuestions = homePage.getImportantQuestion().get(indexQuestionAnswer);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", importantQuestions);
        //клик по первому вопросу:
        homePage.getImportantQuestion().get(indexQuestionAnswer).click();
        // ждун появления элемента:
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT)).
                until(ExpectedConditions.presenceOfAllElementsLocatedBy(homePage.byClassImportantAnswer()));
        WebElement importantAnswers = homePage.getImportantAnswer().get(indexQuestionAnswer);

        //Текст ответа из элемента:
        String actualTextImportantAnswers = importantAnswers.getText();
        //Сравнение текста:
        Assert.assertEquals(expectedTextImportantAnswers, actualTextImportantAnswers);
    }
}
