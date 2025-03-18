package automation;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

public class TutorialsPointTests extends BaseTest {
    @Test
    public void pestanaTest() {
        Logs.info("Navegando a la pagina");
        driver.get("https://www.tutorialspoint.com/selenium/practice/browser-windows.php");

        Logs.info("Obteniendo el id de la pestana actual");
        final var tabId = driver.getWindowHandle(); // obtiene el id de la pestaña actual
        Logs.debug("tabIs %s", tabId);

        Logs.info("Haciendo click en el boton new tab");
        driver.findElement(By.xpath("//button[text()='New Tab']")).click();

        final var windowHandlesSet = driver.getWindowHandles();
        Logs.debug("Window handles set %s", windowHandlesSet);

        Logs.info("Posicionamos en nueca pestana");
        for (var windowHandle : windowHandlesSet) {
            //si no es el tab original es el nuevo
            if (!windowHandle.equals(tabId)) {
                //cambiamos de pestaña
                driver.switchTo().window(windowHandle);
            }
        }

        Logs.info("Verificando el texto");
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='New Tab']")).isDisplayed());

        Logs.info("Cerrando pestana actual");
        driver.close();

        Logs.info("Regresando a la pestana original");
        driver.switchTo().window(tabId);

        Logs.info("Verificando que se regreso a la ventana original");
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Browser Windows']")).isDisplayed());
    }

    @Test
    public void ventanaTest() {
        Logs.info("Navegando a la pagina");
        driver.get("https://www.tutorialspoint.com/selenium/practice/browser-windows.php");

        Logs.info("Obteniendo el id de la ventana actual");
        final var windowId = driver.getWindowHandle(); // obtiene el id de la pestaña actual
        Logs.debug("tabIs %s", windowId);

        Logs.info("Haciendo click en el boton nueva ventana");
        driver.findElement(By.xpath("//button[text()='New Window']")).click();
        final var windowHandlesSet = driver.getWindowHandles();
        Logs.debug("Window handles set %s", windowHandlesSet);

        Logs.info("Posicionamos en nueva ventana");
        for (var windowHandle : windowHandlesSet) {
            //si no es el tab original es el nuevo
            if (!windowHandle.equals(windowId)) {
                //cambiamos de ventana
                driver.switchTo().window(windowHandle);
            }
        }

        Logs.info("Verificando el texto");
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='New Window']")).isDisplayed());

        Logs.info("Cerrando ventana actual");
        driver.close();

        Logs.info("Regresando a la ventana original");
        driver.switchTo().window(windowId);

        Logs.info("Verificando que se regreso a la ventana original");
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Browser Windows']")).isDisplayed());
    }

    @Test
    public void iframeTest() {
        Logs.info("Navegando a la pagina indicada");
        driver.get("https://www.tutorialspoint.com/selenium/practice/nestedframes.php");

        Logs.debug("Cambiando de frame");
        driver.switchTo().frame("frame1"); //id nuevo frame

        Logs.info("Verificando titulo del iframe");
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='New Tab']")).isDisplayed());

        Logs.info("Regresando a la pagina original");
        driver.switchTo().defaultContent();

        Logs.info("Verificar titulo de pagina");
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Nested Frames']")).isDisplayed());

    }
}
