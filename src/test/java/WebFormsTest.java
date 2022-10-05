import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class WebFormsTest {
    WebDriver driver;
    @BeforeMethod
    public void open() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void task1(){
        String baseUrl = "http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html";
        driver.get(baseUrl);
        List<WebElement> dropdowns = driver.findElements(By.cssSelector("#dropdowm-menu-1 > option"));
        for (WebElement e : dropdowns) {
            if(!e.isSelected()){
                e.click();
                System.out.println("Element is selected - "+e.isSelected());
                break;
            }
        }
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type=checkbox]") );
        for (WebElement e : checkboxes) {
            if(!e.isSelected()) {
                e.click();
            }
        }
        WebElement yellowRadio = driver.findElement(By.cssSelector("input[type=radio][value=yellow]"));
        yellowRadio.click();
        WebElement orangeOption = driver.findElement(By.cssSelector("#fruit-selects > option[value=orange]"));
        System.out.println("Orange option is disabled - " + !orangeOption.isEnabled());
    }
    @Test
    public void task2(){
        String baseUrl = "https://demoqa.com/progress-bar";
        driver.get(baseUrl);
        driver.findElement(By.id("startStopButton")).click();
        WebElement progressBar = driver.findElement(By.cssSelector(".progress-bar.bg-info"));
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(10));
        wait.until(ExpectedConditions.attributeToBe(progressBar,"style","width: 50%;"));
    }
    @AfterMethod
    public void close() {
        driver.quit();
    }
}
