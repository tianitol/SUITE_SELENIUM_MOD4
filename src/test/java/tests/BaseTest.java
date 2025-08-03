package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;
import utils.ReporteManager;

import java.lang.reflect.Method;

public class BaseTest {

    protected WebDriver driver;
    protected String urlBase = "https://magento.softwaretestingboard.com/";
    protected String navegador;

    public BaseTest(String navegador)   {
        this.navegador = navegador;
    }

    @BeforeClass(alwaysRun = true)
    public void setupReporte()  {
        ReporteManager.iniciarReporte();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) {
        ReporteManager.crearTest(method.getName() + "-" + navegador);

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
        driver.manage().deleteAllCookies();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown()  {
        if (driver != null) {
            driver.quit();
        }
        ReporteManager.finalizarReporte();
    }

}
