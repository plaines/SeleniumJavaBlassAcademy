package automation;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

public class NavegacionTests extends BaseTest {

    @Test
    public void ejercicio1Test() {
        final var url = "https://www.saucedemo.com/";
        Logs.info("Navegando a: %s%n", url);
        driver.get(url);

        Logs.info("URL actul");
        final var currentUrl = driver.getCurrentUrl();

        Logs.info("Verificando que las URLs sean iguales");
        Assert.assertEquals(currentUrl, url);
    }

    @Test
    public void ejercicio2Test() {
        final var urlHeroku = "https://the-internet.herokuapp.com/";
        final var urlGithub = "https://github.com";

        Logs.info("Navegando a %s%n", urlHeroku);
        driver.get(urlHeroku);

        Logs.info("Navegando a %s%n", urlGithub);
        driver.get(urlGithub);

        Logs.info("regresando a la pagina anterior");
        driver.navigate().back();

        Logs.info("URL actual");
        final var currentUrl = driver.getCurrentUrl();

        Logs.info("Verificando que ambas URL sean iguales");
        Assert.assertEquals(currentUrl, urlHeroku);
    }

    @Test
    final void faildTest() {
        final var url = "https://the-internet.herokuapp.com/";
        Logs.info("Navegando a %s%n", url);
        driver.get(url);

        Logs.info("Obteniendo url actual");
        final var currentUrl = driver.getCurrentUrl();

        Logs.info("Verificando URL");
        Assert.assertEquals(currentUrl, "hola mundo");

    }

    @Test
    public void ejercicio4Test() {
        final var url = "https://www.saucedemo.com/";
        Logs.info("Navegando a: %s%n", url);
        driver.get(url);
        sleep(2000);
        Logs.info("URL actul");
        final var currentUrl = driver.getCurrentUrl();

        Logs.info("Verificando que las URLs sean iguales");
        Assert.assertEquals(currentUrl, url);
    }

    @Test
    public void ejercicio5Test() {
        final var urlHeroku = "https://the-internet.herokuapp.com/";
        final var urlGithub = "https://github.com";

        Logs.info("Navegando a %s%n", urlHeroku);
        driver.get(urlHeroku);
        sleep(2000);

        Logs.info("Navegando a %s%n", urlGithub);
        driver.get(urlGithub);
        sleep(3000);

        Logs.info("regresando a la pagina anterior");
        driver.navigate().back();
        sleep(1500);

        Logs.info("URL actual");
        final var currentUrl = driver.getCurrentUrl();

        Logs.info("Verificando que ambas URL sean iguales");
        Assert.assertEquals(currentUrl, urlHeroku);
    }


}
