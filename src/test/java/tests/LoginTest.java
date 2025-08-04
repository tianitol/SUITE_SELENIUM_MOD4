package tests;

import com.aventstack.extentreports.ExtentTest;
import dataproviders.DatosTestProvider;
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
        String nombreTest = "loginExitoso-" + navegador;
        ExtentTest test = ReporteManager.crearTest(nombreTest);

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
        String nombreTest = "loginFallidoPorCredencialesInvalidas-" + navegador;
        ExtentTest test = ReporteManager.crearTest(nombreTest);

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


    @Test(dataProvider = "datosLogin", dataProviderClass = DatosTestProvider.class)
    public void loginMasivo(String email, String password, String resultadoEsperado) {

// Sanitizar datos para evitar caracteres especiales
        String emailSanitizado = email.replaceAll("[^a-zA-Z0-9]", "_");
        String passwordSanitizado = password.replaceAll("[^a-zA-Z0-9]", "_");
        String resultadoSanitizado = resultadoEsperado.replaceAll("[^a-zA-Z0-9]", "_");


// Generar nombre único para el test
        String nombreTest = String.format("loginMasivo-%s-%s-%s-%s", navegador, emailSanitizado, passwordSanitizado, resultadoSanitizado);
        ExtentTest test = ReporteManager.crearTest(nombreTest);

        LoginPage login = new LoginPage(driver);
        CapturaUtils capturaUtils = new CapturaUtils(driver);

        try {
            login.iniciarLoginYCompletarFormulario(email, password);
            boolean loginExitoso = login.esperarYValidarVistaCuentaDeUsuario();
            boolean correoRequerido = login.validarCorreoRequerido();
            boolean passRequerida = login.validarPassRequerida();

            if (resultadoEsperado.equalsIgnoreCase("valido")) {
                Assert.assertTrue(loginExitoso, "Login válido falló");
                test.pass("Login exitoso con credenciales válidas");
            } else if (resultadoEsperado.equalsIgnoreCase("invalido")){
                Assert.assertTrue(login.validarErrorCredencialesLogin(), "No se mostró error esperado");
                test.pass("Login fallido correctamente con credenciales inválidas");
            } else if (resultadoEsperado.equalsIgnoreCase("correoRequerido")) {
                Assert.assertTrue(correoRequerido, "No se mostró error esperado");
                test.pass("Login fallido correctamente, faltante campo 'email' Requerido");
            } else if (resultadoEsperado.equalsIgnoreCase("passRequerida")){
                Assert.assertTrue(passRequerida, "No se mostró error esperado");
                test.pass("Login fallido correctamente, faltante campo 'password' Requerido");
            }

        } catch (Exception e) {
            test.fail("Excepción: " + e.getMessage());
            Assert.fail("Error en test: " + e.getMessage());
        } finally {
            capturaUtils.tomarCaptura(test, navegador);
        }
    }


}
