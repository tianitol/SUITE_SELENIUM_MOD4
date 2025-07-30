package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistroPage {
    private WebDriver driver;

    public RegistroPage(WebDriver driver)   {
        this.driver = driver;
    }

    //Localizadores

    private By iconoCuenta = By.cssSelector(".header__icon--account > #Capa_1");
    private By linkCrearCuenta = By.linkText("Crear cuenta");
    private By campoNombre = By.id("RegisterForm-FirstName");
    private By campoApellido = By.id("RegisterForm-LastName");
    private By campoEmail = By.id("RegisterForm-email");
    private By campoPassword = By.id("RegisterForm-password");
    private By botonFormCrearCuenta = By.cssSelector("button:nth-child(7)");
    private By vistaCuentaUsuario = By.xpath("//div[@class='customer account section-template--18814871109870__main-padding']/div[2]/div[2]/p");
    private By linkCerrarSesion = By.linkText("Cerrar sesión");
    //private By mensajeErrorForm = By.cssSelector(".form__message");
    //private By mensajeDettaleError = By.cssSelector("ul:nth-child(4) > li");
    private By getLinkCerrarSesion = By.xpath("//*[@id=\"shopify-section-template--18814871109870__main\"]/div/div[1]/a");
    private By mensajeErrorForm = By.xpath("//*[@id=\"create_customer\"]/h2/text()"); //Por favor, ajusta lo siguiente:
    private By mensajeErrorDetalle = By.xpath("//*[@id=\"create_customer\"]/ul/li/text()"); //Esta dirección de e‑mail ya ha sido asociada con una cuenta. Si la cuenta es tuya, puedes <a href="/account/login#recover">restablecer tu contraseña aquí</a>

    //Métodos

    public void clickAElemento(By localizador)    {
        driver.findElement(localizador).click();
    }
    public void ingresarNombre(String nombre)   {
        driver.findElement(campoNombre).sendKeys(nombre);
    }

    public void ingresarApellido(String apellido)   {
        driver.findElement(campoApellido).sendKeys(apellido);
    }

    public void ingresarEmail(String email) {
        driver.findElement(campoEmail).sendKeys(email);
    }

    public void ingresarPassword(String password)   {
        driver.findElement(campoPassword).sendKeys(password);
    }

    public void hacerClickEnCrearCuenta()   {
        driver.findElement(botonFormCrearCuenta).click();
    }

    public void registrarUsuario(String nombre, String apellido, String email, String password) {
        ingresarNombre(nombre);
        ingresarApellido(apellido);
        ingresarEmail(email);
        ingresarPassword(password);
        hacerClickEnCrearCuenta();
    }

    public void iniciarRegistroYCompletarFormulario(String nombre, String apellido, String email, String password )   {
        clickAElemento(iconoCuenta);
        clickAElemento(linkCrearCuenta);
        registrarUsuario(nombre, apellido, email, password);
    }

    public boolean estaVisibleLinkCerrarSesion()    {
        return driver.findElement(linkCerrarSesion).isDisplayed();
    }
}
