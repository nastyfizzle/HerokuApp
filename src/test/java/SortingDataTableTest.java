import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SortingDataTableTest {

    public WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/tables");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    //Check getting all values from cells
    @Test
    public void cellValues() {
        //Check every value from row for "Last Name" column
        String cell_11 = driver.findElement(By.xpath("//*[@id='table1']//tr[1]/td[1]")).getText();
        Assert.assertEquals(cell_11, "Smith", "Last name for row 1 is wrong");
        String cell_12 = driver.findElement(By.xpath("//*[@id='table1']//tr[2]/td[1]")).getText();
        Assert.assertEquals(cell_12, "Bach", "Last name for row 2 is wrong");
        String cell_13 = driver.findElement(By.xpath("//*[@id='table1']//tr[3]/td[1]")).getText();
        Assert.assertEquals(cell_13, "Doe", "Last name for row 3 is wrong");
        String cell_14 = driver.findElement(By.xpath("//*[@id='table1']//tr[4]/td[1]")).getText();
        Assert.assertEquals(cell_14, "Conway", "Last name for row 4 is wrong");

        //Check every value from row for "First Name" column
        String cell_21 = driver.findElement(By.xpath("//*[@id='table1']//tr[1]/td[2]")).getText();
        Assert.assertEquals(cell_21, "John", "First name for row 1 is wrong");
        String cell_22 = driver.findElement(By.xpath("//*[@id='table1']//tr[2]/td[2]")).getText();
        Assert.assertEquals(cell_22, "Frank", "First name for row 2 is wrong");
        String cell_23 = driver.findElement(By.xpath("//*[@id='table1']//tr[3]/td[2]")).getText();
        Assert.assertEquals(cell_23, "Jason", "First name for row 3 is wrong");
        String cell_24 = driver.findElement(By.xpath("//*[@id='table1']//tr[4]/td[2]")).getText();
        Assert.assertEquals(cell_24, "Tim", "First name for row 4 is wrong");

        //Check every value from row for "Email" column
        String cell_31 = driver.findElement(By.xpath("//*[@id='table1']//tr[1]/td[3]")).getText();
        Assert.assertEquals(cell_31, "jsmith@gmail.com", "Email for row 1 is wrong");
        String cell_32 = driver.findElement(By.xpath("//*[@id='table1']//tr[2]/td[3]")).getText();
        Assert.assertEquals(cell_32, "fbach@yahoo.com", "Email for row 2 is wrong");
        String cell_33 = driver.findElement(By.xpath("//*[@id='table1']//tr[3]/td[3]")).getText();
        Assert.assertEquals(cell_33, "jdoe@hotmail.com", "Email for row 3 is wrong");
        String cell_34 = driver.findElement(By.xpath("//*[@id='table1']//tr[4]/td[3]")).getText();
        Assert.assertEquals(cell_34, "tconway@earthlink.net", "Email for row 4 is wrong");

        //Check every value from row for "Due" column
        String cell_41 = driver.findElement(By.xpath("//*[@id='table1']//tr[1]/td[4]")).getText();
        Assert.assertEquals(cell_41, "$50.00", "Due for row 1 is wrong");
        String cell_42 = driver.findElement(By.xpath("//*[@id='table1']//tr[2]/td[4]")).getText();
        Assert.assertEquals(cell_42, "$51.00", "Due for row 2 is wrong");
        String cell_43 = driver.findElement(By.xpath("//*[@id='table1']//tr[3]/td[4]")).getText();
        Assert.assertEquals(cell_43, "$100.00", "Due for row 3 is wrong");
        String cell_44 = driver.findElement(By.xpath("//*[@id='table1']//tr[4]/td[4]")).getText();
        Assert.assertEquals(cell_44, "$50.00", "Due for row 4 is wrong");

        //Check every value from row for "Web Site" column
        String cell_51 = driver.findElement(By.xpath("//*[@id='table1']//tr[1]/td[5]")).getText();
        Assert.assertEquals(cell_51, "http://www.jsmith.com", "Web Site for row 1 is wrong");
        String cell_52 = driver.findElement(By.xpath("//*[@id='table1']//tr[2]/td[5]")).getText();
        Assert.assertEquals(cell_52, "http://www.frank.com", "Web Site for row 2 is wrong");
        String cell_53 = driver.findElement(By.xpath("//*[@id='table1']//tr[3]/td[5]")).getText();
        Assert.assertEquals(cell_53, "http://www.jdoe.com", "Web Site for row 3 is wrong");
        String cell_54 = driver.findElement(By.xpath("//*[@id='table1']//tr[4]/td[5]")).getText();
        Assert.assertEquals(cell_54, "http://www.timconway.com", "Web Site for row 4 is wrong");
    }

    //Check sorting order for column "First Name"
    @Test
    public void sortDataTable() {
        WebElement tableOne = driver.findElement(By.id("table1"));
        WebElement headerFirstName = driver.findElement(By.xpath("//thead/tr/th[2]"));

        List<WebElement> firstNameOriginalList = driver.findElements(By.xpath("//table[@id='table1']//tr/td[2]"));

        ArrayList<String> firstNameOriginalArray = new ArrayList<>();
        for (WebElement webElement : firstNameOriginalList) {
            firstNameOriginalArray.add(webElement.getText());
        }

        //If the First Name column is clicked firstly, then it will be sorted in ASC order
        headerFirstName.click();
        List<WebElement> firstNameAscList = driver.findElements(By.xpath("//table[@id='table1']//tr/td[2]"));

        ArrayList<String> firstNameAscArray = new ArrayList<>();
        for (WebElement webElement : firstNameAscList) {
            firstNameAscArray.add(webElement.getText());
        }
        Collections.sort(firstNameOriginalArray);
        Assert.assertEquals(firstNameOriginalArray, firstNameAscArray);

        //If the First Name column is clicked twice, then it will be sorted in DESC order
        headerFirstName.click();
        List<WebElement> firstNameDescList = driver.findElements(By.xpath("//table[@id='table1']//tr/td[2]"));

        ArrayList<String> firstNameDescArray = new ArrayList<>();
        for (WebElement webElement : firstNameDescList) {
            firstNameDescArray.add(webElement.getText());
        }
        Collections.reverse(firstNameOriginalArray);
        Assert.assertEquals(firstNameOriginalArray, firstNameDescArray);
    }


    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
