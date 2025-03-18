package utilities;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class FileManager {
    private static final String screenshotPath = "src/test/resources/screenshots";

    public static void getScreenshot(String screenshotName) {
        Logs.debug("Tomando un screenshot");

        final var screenshotFile = ((TakesScreenshot) new WebDriverProvider().get()).getScreenshotAs(OutputType.FILE);

        final var path = String.format("%s/%s.png", screenshotPath, screenshotName);

        try {
            FileUtils.copyFile(screenshotFile, new File(path));
        } catch (IOException ioException) {
            Logs.error("error tomar screenshot %s", ioException.getLocalizedMessage());
        }


    }

    public static void deletePreviousEvidence() {
        try {
            Logs.debug("Borando las carpetas de evidencia");
            FileUtils.deleteDirectory(new File(screenshotPath));
        } catch (IOException ioException) {
            Logs.error("error al borrar la evidencia anterior %s", ioException.getLocalizedMessage());
        }
    }

    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] getScreenshot() {
        return ((TakesScreenshot) new WebDriverProvider().get()).getScreenshotAs(OutputType.BYTES);
    }
}
