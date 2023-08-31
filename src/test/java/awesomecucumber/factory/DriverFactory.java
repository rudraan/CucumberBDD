package awesomecucumber.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverFactory {
   /* 1) making the driver as static as we are going to use it in multiple classes
    and if we donot use static here,then we cannot use static in the below methods as well
    bcz non static field cannot be referenced in static methods
    2) suppose we are not using static in the methods as well as in the variable craetion, then whenever
    we use this methods in other classes we have to create object of this DriverFactory class and then call the
     method. As soon as we create new object, the driver instance is set to null, bcz driver variable
     will be bind to the instance of the class and not to the class itself
    with static keyword, driver variable is bind to the class itself.
    3) If we only keep driver initialization as static, and create non static methods, then also driver
    will not be null when this driver methods are called from different classes

    */

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initializeDriver(String browser){
        WebDriver driver;
        switch(browser){
            case "chrome":
                // Set up the WebDriverManager for chrome driver
                WebDriverManager.chromedriver().setup();
                // Create the driver object
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalStateException("Invalid Browser" + browser);
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        DriverFactory.driver.set(driver);
        return driver;
    }

    public static WebDriver getDriver(){
        return driver.get();
    }

}
