import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World!");
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://qaexercise.envalfresco.com/settings");
        Thread.sleep(2000);

        WebElement providerDropDown = driver.findElement(By.id("adf-provider-selector"));
        providerDropDown.click();

        //WebElement ecmOption = driver.findElements(By.cssContaingtext(|))
    }
}
