package automation;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

public class DemoQATests extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(DemoQATests.class);

    @Test
    public void keyboard1Test() {
        Logs.info("Navegando a la pagina");
        driver.get("https://demoqa.com/text-box");

        final var faker = new Faker();
        final var fullName = faker.name().fullName();
        Logs.debug("fullname %s", fullName);

        final var userNameInput = driver.findElement(By.id("userName"));

        Logs.info("Haciendo click para dar focus");
        userNameInput.click();

        Logs.info("Presionando SHYFT y escribiendo texto");
        new Actions(driver)
                .keyDown(Keys.SHIFT) // presionando shift
                .sendKeys(fullName) // escribiendo el nombre generado con faker
                .keyUp(Keys.SHIFT) // dejando de presionar keys
                .perform();

        Logs.info("Verificando que el texto este en mayusculas");
        Assert.assertEquals(userNameInput.getAttribute("value"), fullName.toUpperCase());

    }

    @Test
    public void keyboard2Test() {
        Logs.info("Navegando a la pagina");
        driver.get("https://demoqa.com/text-box");

        final var faker = new Faker();
        final var address = faker.address().fullAddress();
        Logs.debug("Direccion creada %s", address);

        final var currentAddressInput = driver.findElement(By.id("currentAddress"));

        Logs.info("Escribiendo direccion y dando click para focus");
        currentAddressInput.click();
        currentAddressInput.sendKeys(address);

        Logs.info("Seleccionando y copiando contenido");
        new Actions(driver)
                .keyDown(Keys.CONTROL) // presionado control
                .sendKeys("a") // presionado a
                .sendKeys("c") // presionado c
                .keyUp(Keys.CONTROL) //soltando control
                .perform();

        final var permanentAddressInput = driver.findElement(By.id("permanentAddress"));
        Logs.info("Click para dar focus");
        permanentAddressInput.click();

        Logs.info("Pegando texto");
        new Actions(driver)
                .keyDown(Keys.CONTROL)
                .sendKeys("v")
                .keyUp(Keys.CONTROL)
                .perform();

        Logs.info("Verificando que ambos inputs tengan el mismo texto");
        Assert.assertEquals(permanentAddressInput.getAttribute("value"), currentAddressInput.getAttribute("value"));
    }

    @Test
    public void keybordClickTest() {
        Logs.info("Navegando a la pagina");
        driver.get("https://demoqa.com/text-box");

        final var faker = new Faker();
        final var fullName = faker.name().fullName();
        Logs.debug("fullname %s", fullName);

        final var userNameInput = driver.findElement(By.id("userName"));


        Logs.info("Presionando SHYFT y escribiendo texto");
        new Actions(driver)
                .click(userNameInput) // haciendo click para el focus
                .keyDown(Keys.SHIFT) // presionando shift
                .sendKeys(fullName) // escribiendo el nombre generado con faker
                .keyUp(Keys.SHIFT) // dejando de presionar keys
                .perform();

        Logs.info("Verificando que el texto este en mayusculas");
        Assert.assertEquals(userNameInput.getAttribute("value"), fullName.toUpperCase());

    }

    @Test
    public void keyboardCick2Test() {
        Logs.info("Navegando a la pagina");
        driver.get("https://demoqa.com/text-box");

        final var faker = new Faker();
        final var address = faker.address().fullAddress();
        Logs.debug("Direccion creada %s", address);

        final var currentAddressInput = driver.findElement(By.id("currentAddress"));

        Logs.info("Escribiendo direccion y dando click para focus");

        currentAddressInput.sendKeys(address);

        Logs.info("Seleccionando y copiando contenido");
        new Actions(driver)
                .click(currentAddressInput)
                .keyDown(Keys.CONTROL) // presionado control
                .sendKeys("a") // presionado a
                .sendKeys("c") // presionado c
                .keyUp(Keys.CONTROL) //soltando control
                .perform();

        final var permanentAddressInput = driver.findElement(By.id("permanentAddress"));
        Logs.info("Click para dar focus");


        Logs.info("Pegando texto");
        new Actions(driver)
                .click(permanentAddressInput)
                .keyDown(Keys.CONTROL)
                .sendKeys("v")
                .keyUp(Keys.CONTROL)
                .perform();

        Logs.info("Verificando que ambos inputs tengan el mismo texto");
        Assert.assertEquals(permanentAddressInput.getAttribute("value"), currentAddressInput.getAttribute("value"));
    }

    @Test
    public void mouseTest() {
        Logs.info("Navegando a la pagina");
        driver.get("https://demoqa.com/droppable");

        final var figuraOrigen = driver.findElement(By.id("draggable"));
        final var figuraDestino = driver.findElement(By.id("draggable"));

        Logs.info("Arrastro la figura origen a la destino");
        new Actions(driver)
                .dragAndDrop(figuraOrigen, figuraDestino) //arrastro las imagenes
                .perform();

        Logs.info("Verifico que el texto dropped sea visible");
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Dropped!']")).isDisplayed());
    }

    @Test
    public void mouse2Test() {
        driver.get("https://demoqa.com/tool-tips");
        final var botonVerde = driver.findElement(By.id("toolTipButton"));
        new Actions(driver)
                .moveToElement(botonVerde)
                .pause(1500)
                .perform();

        Assert.assertEquals(botonVerde.getAttribute("aria-describedby"), "buttonToolTip");
    }

    
}
