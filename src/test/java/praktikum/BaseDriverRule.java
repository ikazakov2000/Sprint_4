package praktikum;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

import java.io.File;
import java.time.Duration;

public class BaseDriverRule extends ExternalResource {
    WebDriver driver;

    @Override
    protected void before() throws Throwable {
        if ("firefox".equals(System.getProperty("browser")))
            setUpFirefox();
        else
            setUpChrome();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    private void setUpChrome() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        ChromeDriverService service = new ChromeDriverService.Builder().withLogOutput(System.out)
                .usingDriverExecutable
                        (new File("src/main/resources/chromedriver.exe"))
                .build();

        ChromeOptions options = new ChromeOptions()
                //.setBinary("src/main/resources/chrome.exe");
        /*Дорогой ревьюер, только один chrome.exe прикрепить не получается, браузер требует остальные конфигурации,
        а весят они много 350МБ, поэтому указываю путь на локальное расположение*/
                .setBinary("C:\\WebDriver\\bin\\chrome-win64\\chrome.exe");

        driver = new ChromeDriver(service, options);
    }

    public void setUpFirefox() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        var service = new GeckoDriverService.Builder().withLogOutput(System.out)
                .usingDriverExecutable(new File
                        ("src/main/resources/geckodriver.exe"))
                .build();

        var options = new FirefoxOptions()
                //.setBinary("src/main/resources/firefox.exe");
        // Так же и с лисой)
                .setBinary("C:\\WebDriver\\bin\\Mozilla Firefox\\firefox.exe");

        driver = new FirefoxDriver(service, options);

    }
    @Override
    protected void after() {
        driver.quit();
    }


    public WebDriver getDriver() {
        return driver;
    }
}