package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistroPage {
    private WebDriver driver;

    public RegistroPage(WebDriver driver)   {
        this.driver = driver;
    }

    //Localizadores

    private By linkCrearCuenta = By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[3]/a");
    private By campoNombre = By.id("firstname");
    private By campoApellido = By.id("lastname");
    private By campoEmail = By.id("email_address");
    private By campoPassword = By.id("password");
    private By campoConfirmarPassword = By.id("password-confirmation");
    private By botonFormCrearCuenta = By.cssSelector("#form-validate > div > div.primary > button");
    private By vistaCuentaUsuario = By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[1]/h1/span");
    private By mensajeErrorCorreoYaRegistrado = By.cssSelector("#maincontent > div.page.messages > div:nth-child(2) > div > div > div");

    //Métodos

    public void ingresarTextoLento(By localizador, String textto)    {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement campo = wait.until(ExpectedConditions.visibilityOfElementLocated(localizador));
        Actions actions = new Actions(driver);
        for (char letra : textto.toCharArray()) {
            actions.moveToElement(campo).click().sendKeys(Character.toString(letra)).pause(java.time.Duration.ofMillis(150)).build().perform();
        }
    }

    public void clickAElemento(By localizador)    {
        driver.findElement(localizador).click();
    }
    public void ingresarNombre(String nombre)   {
        ingresarTextoLento(campoNombre, nombre);
    }

    public void ingresarApellido(String apellido)   {
        ingresarTextoLento(campoApellido, apellido);

    }

    public void ingresarEmail(String email) {
        ingresarTextoLento(campoEmail, email);
    }

    public void ingresarPassword(String password)   {
        ingresarTextoLento(campoPassword, password);

    }

    public void ingresarConfirmarPassword(String password)   {
        ingresarTextoLento(campoConfirmarPassword, password);

    }

    public void hacerClickEnCrearCuenta()   {
        driver.findElement(botonFormCrearCuenta).click();
    }


    public boolean esperarYValidarVistaCuentaDeUsuario()    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(vistaCuentaUsuario));
                return driver.findElement(vistaCuentaUsuario).isDisplayed();
        }   catch   (Exception e)   {
                return false;
        }
    }

    public void registrarUsuario(String nombre, String apellido, String email, String password) {
        ingresarNombre(nombre);
        ingresarApellido(apellido);
        ingresarEmail(email);
        ingresarPassword(password);
        ingresarConfirmarPassword(password);
        hacerClickEnCrearCuenta();
    }

    public void iniciarRegistroYCompletarFormulario(String nombre, String apellido, String email, String password )   {
        clickAElemento(linkCrearCuenta);
        registrarUsuario(nombre, apellido, email, password);
    }

    public boolean validarError(By locator, String mensaje)    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            WebElement mensajeError = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return mensajeError.getText().contains(mensaje);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validarErrorEmailYaRegistrado()  {
        return validarError(mensajeErrorCorreoYaRegistrado, "There is already an account with this email address. If you are sure that it is your email address, click here to get your password and access your account.");
    }
}
