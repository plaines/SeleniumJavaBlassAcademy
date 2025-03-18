package utilities;

import listeners.SuiteListeners;
import listeners.TestListeners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

@Listeners({TestListeners.class, SuiteListeners.class})
public class BaseTest {
    protected SoftAssert softAssert;
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final String regression = "regression";
    protected final String smoke = "smoke";

    @BeforeMethod(alwaysRun = true)
    public void masterSetUp() {
        softAssert = new SoftAssert();
        Logs.debug("Inicia el Driver");
        driver = new ChromeDriver();

        Logs.debug("Maximizando patanlla");
        driver.manage().window().maximize();

        Logs.debug("Borrando las cookies");
        driver.manage().deleteAllCookies();

        Logs.debug("Seteando implicit wait");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        Logs.debug("Asigando driver");
        new WebDriverProvider().set(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void masterTearDown() {

        Logs.debug("Termina el Driver");
        driver.quit();

    }

    protected void sleep(int timeMs) {
        try {
            Thread.sleep(timeMs);

        } catch (InterruptedException interruptedException) {
            Logs.error("InterruptedException: %s", interruptedException.getLocalizedMessage());
        }
    }
}
