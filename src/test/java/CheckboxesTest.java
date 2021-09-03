import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CheckboxesTest {

    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/checkboxes");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    //Check that first checkbox not isSelected and check
    @Test
    public void firstIsSelected () {
        WebElement checkbox = driver.findElement(By.xpath("//*[@id='checkboxes']/input[1]"));
        boolean isSelected = checkbox.isSelected();
        if(!isSelected) {
            checkbox.click();
        }
        else {
            System.out.println("Checkbox 1 is selected");
        }
    }

    //Check that both checkboxes are selected
    @Test
    public void bothAreSelected () {
        //WebElement checkbox1 = driver.findElement(By.xpath("//*[@id='checkboxes']/input[1]"));
        WebElement checkbox1 = driver.findElement(By.cssSelector("[type=checkbox]"));
        checkbox1.click();
        WebElement checkbox2 = driver.findElement(By.xpath("//*[@id='checkboxes']/input[2]"));
        boolean isSelected1 = checkbox1.isSelected();
        boolean isSelected2 = checkbox2.isSelected();
        if (isSelected1) {
            if (!isSelected2) {
                System.out.println("Both or one of the checkboxes are deselected");
            }
        } else {
            System.out.println("Both or one of the checkboxes are deselected");
        }
    }

    //Check that second checkbox isSelected and uncheck
    @Test
    public void secondIsDeselected () {
        WebElement checkbox2 = driver.findElement(By.xpath("//*[@id='checkboxes']/input[2]"));
        boolean isSelected = checkbox2.isSelected();
        if(isSelected) {
            checkbox2.click();
        }
        else {
            System.out.println("Checkbox 2 is deselected");
        }
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
