package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver)  {
        this.driver = driver;
    }

    //Localizadores
    private By linkSignIn = By.cssSelector("body > div.page-wrapper > header > div.panel.wrapper > div > ul > li.authorization-link > a");
    private By campoUsuario = By.id("email");
    private  By campoPassword = By.id("pass");
    private By botonSignIn = By.id("send2");
    private By mensajeErrorLoginIncorrecto = By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div/div/div");
    private By mensajeBienvenidaUsuario = By.cssSelector("body > div.page-wrapper > header > div.panel.wrapper > div > ul > li.greet.welcome > span");

    //Métodos

    public void esperarElementoVisibleYEscribir (By localizador, String campoTexto )   {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement campo = wait.until(ExpectedConditions.visibilityOfElementLocated(localizador));
        campo.sendKeys(campoTexto);
    }

    public void ingresarUsuario(String usuario) {
        esperarElementoVisibleYEscribir(campoUsuario, usuario);
    }

    public void ingresarPass(String pass)   {
        esperarElementoVisibleYEscribir(campoPassword, pass);
    }

    public void clickAElementoVisible(By localizador)    {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement campo = wait.until(ExpectedConditions.visibilityOfElementLocated(localizador));
        campo.click();
    }

    public void logearUsuario(String usuario, String pass)  {
        ingresarUsuario(usuario);
        ingresarPass(pass);
        clickAElementoVisible(botonSignIn);
    }

    public void iniciarLoginYCompletarFormulario(String usuario, String pass)  {
        clickAElementoVisible(linkSignIn);
        logearUsuario(usuario, pass);
    }

    public boolean esperarYValidarVistaCuentaDeUsuario()    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(mensajeBienvenidaUsuario));
            return driver.findElement(mensajeBienvenidaUsuario).isDisplayed();
        }   catch   (Exception e)   {
            return false;
        }
    }

    public boolean validarError(By locator, String mensaje)  {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            WebElement mensajeError = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return mensajeError.getText().contains(mensaje);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validarErrorCredencialesLogin()  {
        return validarError(mensajeErrorLoginIncorrecto, "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.");
    }
}
