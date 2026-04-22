package boutique;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

/**
 * Base class for all Selenium tests.
 * Handles browser setup, teardown, and automatic screenshots.
 *
 * Screenshots are saved to: project4/screenshots/
 */
public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected static final String BASE_URL = "http://localhost:8080";
    private static final Path SCREENSHOT_DIR = Paths.get("screenshots");

    private String currentTestName;

    @BeforeEach
    public void setUp(TestInfo testInfo) throws IOException {
        currentTestName = testInfo.getDisplayName()
                .replaceAll("[^a-zA-Z0-9_\\-]", "_");

        Files.createDirectories(SCREENSHOT_DIR);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-search-engine-choice-screen");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /**
     * Call this at any point in a test to save a labelled screenshot.
     * Example: screenshot("after_add_to_cart")
     */
    protected void screenshot(String label) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path dest = SCREENSHOT_DIR.resolve(currentTestName + "__" + label + ".png");
            Files.copy(src.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved: " + dest.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Could not save screenshot: " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        // Always capture a final screenshot before closing
        if (driver != null) {
            screenshot("FINAL");
            driver.quit();
        }
    }
}
