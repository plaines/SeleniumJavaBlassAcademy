package automation;

import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import utilities.BaseTestNew;

public class LoginTestPmo extends BaseTestNew {
    private final LoginPage loginPage = new LoginPage();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver.get("http://www.saucedemo.com/");
        loginPage.waitPageToLoad();

    }

    @Override
    public void waitPageToLoad() {

    }

    @Override
    public void verifyPage() {

    }
}
