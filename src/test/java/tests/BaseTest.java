package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class BaseTest {

    protected WebDriver driver;
    protected String urlBase = "https://bless.cl/";

    @DataProvider(name = "navegadores")
    public Object[][] navegadores() {
        return new Object[][]   {
                {"chrome"},
                {"edge"}
        };
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("navegador")
    public void setUp(@Optional("chrome") String navegador) {
        if  (navegador.equalsIgnoreCase("chrome"))    {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }   else if (navegador.equalsIgnoreCase("edge")) {
            //Descomentar sigte línea para utilizar Edge sin necesidad de la configuración local del webdriver
            //WebDriverManager.edgedriver().setup();
            System.setProperty("webdriver.edge.driver", "msedgedriver"); //comentar para utilizar webdriver para Edge
            driver = new EdgeDriver();
        }   else    {
            throw new IllegalArgumentException("Navegador no soportado: " + navegador);
        }

        driver.manage().window().maximize();
        driver.get(urlBase);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown()  {
        if (driver != null) {
            driver.quit();
        }
    }

}
