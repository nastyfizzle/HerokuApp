import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class DownloadTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.prompt_for_download", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void fileDownload() throws InterruptedException {

        driver.get("http://the-internet.herokuapp.com/download");
        Thread.sleep(2000);
        WebElement fileName = driver.findElement(By.xpath("//*[contains(text(), 'example.jpg')]"));
        fileName.click();
        if (System.getProperty("os.name").equals("windows")) {
            String downloadPath = "C:\\Users\\452Dell\\Downloads";
            assertTrue(isFileDownloaded(downloadPath, "example.jpg"), "Failed to download Expected document");
        }

        Thread.sleep(7000);
    }

    public boolean isFileDownloaded(String downloadPath, String fileName) {
        File dir = new File(downloadPath);
        File[] dir_contents = dir.listFiles();

        for (int i = 0; i < dir_contents.length; i++) {
            if (dir_contents[i].getName().equals(fileName)) {
                return true;
            }
        }
        return false;
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
