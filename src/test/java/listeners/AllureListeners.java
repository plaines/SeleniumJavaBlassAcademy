package listeners;

import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.TestResult;
import utilities.FileManager;
import utilities.Logs;

public class AllureListeners implements TestLifecycleListener {

    public void beforeTestStops(TestResult result) {
        Logs.debug("beforeTestStops");
        final var status = result.getStatus();

        switch (status) {
            case BROKEN, FAILED -> FileManager.getScreenshot();
        }
    }
}
