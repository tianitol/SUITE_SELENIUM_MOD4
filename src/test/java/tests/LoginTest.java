package tests;

import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.CapturaUtils;
import utils.ReporteManager;

public class LoginTest extends BaseTest {
    public LoginTest(String navegador)   {
        super(navegador);
    }

    @Test
    public void loginExitoso()  {
        ExtentTest test = ReporteManager.getTest();
        LoginPage login = new LoginPage(driver);
        CapturaUtils capturaUtils = new CapturaUtils(driver);

        try {
            login.iniciarLoginYCompletarFormulario("anaene@email.com", "123asdF.");
            Assert.assertTrue(login.esperarYValidarVistaCuentaDeUsuario(), "'Welcome usuario' no está visible después del inicio de sesión");
            test.pass("'Welcome usuario' está visible después de iniciar sesión");
        }   catch(Exception e)  {
                test.fail("Falló el test: " + e.getMessage());
                Assert.fail("Test fallido con excepción: " + e.getMessage());
        }   finally {
            capturaUtils.tomarCaptura(test, navegador);
        }
    }

    @Test
    public void loginFallidoPorCredencialesInvalidas()  {
        ExtentTest test = ReporteManager.getTest();
        LoginPage login = new LoginPage(driver);
        CapturaUtils capturaUtils = new CapturaUtils(driver);

        try {
            login.iniciarLoginYCompletarFormulario("anaene@mailto.com", "123asdF.");
            Assert.assertTrue(login.validarErrorCredencialesLogin(), "No se mostró el mensaje de error por credenciales inválidas");
            test.pass("Se muestra mensaje de error 'Credenciales inválidas'");
        }   catch (Exception e) {
            test.fail("Falló el test: " + e.getMessage());
        }   finally {
            capturaUtils.tomarCaptura(test, navegador);
        }
    }
}
