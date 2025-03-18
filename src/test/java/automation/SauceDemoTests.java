package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

import java.time.Duration;

public class SauceDemoTests extends BaseTest {

    @Test(groups = {regression})
    public void usuarioInvalidoTest() {

        rellenarFormularioLogin("invalid_test", "invalid_pass");
        final var errorLabel = driver.findElement(By.xpath("//h3[@data-test='error']"));
        softAssert.assertTrue(errorLabel.isDisplayed());
        softAssert.assertEquals(errorLabel.getText(), "Epic sadface: Username and password do not match any user in this service");
        softAssert.assertAll();
    }

    @Test(groups = {regression, smoke})
    public void usuarioPassCorrectosTest() {
        rellenarFormularioLogin("standard_user", "secret_sauce");
        final var inventoryList = driver.findElement(By.className("inventory_list"));
        Logs.info("Verificando que el inventario sea visible");
        Assert.assertTrue(inventoryList.isDisplayed());
    }

    @Test(groups = {regression, smoke})
    public void informacionProductoTest() {
        rellenarFormularioLogin("standard_user", "secret_sauce");
        final var inventoryList = driver.findElement(By.className("inventory_list"));
        Logs.info("Verificando que el inventario sea visible");
        Assert.assertTrue(inventoryList.isDisplayed());

        final var imageList = driver.findElements(By.cssSelector("img[class='inventory_item_img']"));
        Logs.info("haciendo click en el primer elemento de la lista");
        imageList.get(0).click();

        Logs.info("verificamos detalles del producto");
        softAssert.assertTrue(driver.findElement(By.className("inventory_details_name")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.className("inventory_details_desc")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.className("inventory_details_price")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.xpath("//button[text()='Add to cart']")).isDisplayed());

        softAssert.assertAll();
    }

    @Test(groups = {regression})
    public void selectDropdownTest() {

        rellenarFormularioLogin("standard_user", "secret_sauce");
        final var selectDropdown = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select"));

        //cast a select
        final var select = new Select(selectDropdown);

        Logs.info("Seleccionamos Z to A");
        select.selectByValue("za");
        final var itemList = driver.findElements(By.className("inventory_item_name"));

        Logs.info("Primer elemento");
        final var primerElemento = itemList.get(0).getText();

        Logs.info("Ultimo elemento");
        final var ultimoElemento = itemList.get(itemList.size() - 1).getText();

        Logs.info("Verificando nombres");
        softAssert.assertEquals(primerElemento, "Test.allTheThings() T-Shirt (Red)");
        softAssert.assertEquals(ultimoElemento, "Sauce Labs Backpack");
        softAssert.assertAll();

    }

    @Test(groups = {regression})
    public void menorMayorPrecioTest() {

        rellenarFormularioLogin("standard_user", "secret_sauce");
        final var selectDropdown = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select"));

        //cast a select
        final var select = new Select(selectDropdown);

        Logs.info("Seleccionamos precio menor a mayor ");
        select.selectByValue("lohi");
        final var priceList = driver.findElements(By.className("inventory_item_price"));

        Logs.info("Primer elemento");
        final var primerPrecio = Double.parseDouble(priceList.get(0).getText().replace("$", ""));


        Logs.info("Ultimo elemento");
        final var ultimoPrecio = Double.parseDouble(priceList.get(priceList.size() - 1).getText().replace("$", ""));

        Logs.info("Verificando Precios");
        softAssert.assertEquals(primerPrecio, 7.99);
        softAssert.assertEquals(ultimoPrecio, 49.99);

        softAssert.assertAll();
    }

    @Test(groups = {regression})
    public void link1Test() {
        rellenarFormularioLogin("standard_user", "secret_sauce");
        final var facebookLabel = driver.findElement(By.xpath("//a[text()='Facebook']"));
        Logs.info("Verificando el hpervinculo");
        softAssert.assertTrue(facebookLabel.isDisplayed());
        softAssert.assertTrue(facebookLabel.isEnabled());
        softAssert.assertEquals(facebookLabel.getAttribute("href"), "https://www.facebook.com/saucelabs");
        softAssert.assertAll();

    }

    @Test(groups = {regression})
    public void link2Test() {
        rellenarFormularioLogin("standard_user", "secret_sauce");
        final var linkedinLabel = driver.findElement(By.xpath("//a[text()='LinkedIn']"));
        Logs.info("Verificando el hpervinculo");
        softAssert.assertTrue(linkedinLabel.isDisplayed());
        softAssert.assertTrue(linkedinLabel.isEnabled());
        softAssert.assertEquals(linkedinLabel.getAttribute("href"), "https://www.linkedin.com/company/sauce-labs/");
        softAssert.assertAll();

    }

    @Test(groups = {regression})
    public void link3Test() {
        rellenarFormularioLogin("standard_user", "secret_sauce");
        driver.findElement(By.id("react-burger-menu-btn")).click();
        sleep(2000);
        final var aboutIcon = driver.findElement(By.id("about_sidebar_link"));
        softAssert.assertTrue(aboutIcon.isDisplayed());
        softAssert.assertTrue(aboutIcon.isEnabled());
        softAssert.assertEquals(aboutIcon.getAttribute("href"), "https://saucelabs.com/");
        softAssert.assertAll();

    }

    @Test(groups = {regression, smoke})
    public void link4Test() {
        rellenarFormularioLogin("standard_user", "secret_sauce");
        driver.findElement(By.id("react-burger-menu-btn")).click();
        sleep(2000);
        driver.findElement(By.id("logout_sidebar_link")).click();
        //sleep(2000);

        final var urlActual = driver.getCurrentUrl();
        Assert.assertEquals(urlActual, "https://www.saucedemo.com/");

    }


    public void rellenarFormularioLogin(String username, String password) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Logs.info("Navegando a la pagina");
        driver.get("https://www.saucedemo.com/");
        //sleep(3000);

        Logs.info("Ingresando usuario");
        driver.findElement(By.id("user-name")).sendKeys(username);

        Logs.info("Ingresando password");
        driver.findElement(By.id("password")).sendKeys(password);

        Logs.info("Haciendo clik en boton login");
        driver.findElement(By.id("login-button")).click();

        // sleep(2000);
    }


}

