package automation;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;

import java.io.File;

public class TestPageTests extends BaseTest {

    @Test
    public void subirImagenTest() {

        driver.get("https://testpages.eviltester.com/styled/file-upload-test.html");
        final var file = new File("src/test/resources/imagen/online-exam-or-test.jpeg");
        driver.findElement(By.id("fileinput")).sendKeys(file.getAbsolutePath());
        driver.findElement(By.id("itsanimage")).click();
        driver.findElement(By.name("upload")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='You uploaded this image:']")).isDisplayed());
    }
}
