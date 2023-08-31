package awesomecucumber.hooks;

import awesomecucumber.factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

public class MyHooks {
    private WebDriver driver;

    @Before
    public void before(Scenario scenario){
        System.out.println("BEFORE: THREAD ID: "+Thread.currentThread().getId() + "," +
                "SCENARIO NAME: " +scenario.getName());
        //setting the default browser to chrome here
        //but we can also test in different browser through maven by writing
        //mvn clean test -Dbrowser=firefox

        driver = DriverFactory.initializeDriver(System.getProperty("browser","chrome"));
    }

    @After
    public void after(Scenario scenario)
    {
        System.out.println("AFTER: THREAD ID: "+Thread.currentThread().getId() + "," +
                "SCENARIO NAME: " +scenario.getName());
            driver.quit();
    }
}
