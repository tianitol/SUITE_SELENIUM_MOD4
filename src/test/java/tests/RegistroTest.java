package tests;

import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistroPage;
import utils.CapturaUtils;
import utils.ReporteManager;

public class RegistroTest extends BaseTest{

    public RegistroTest(String navegador)   {
        super(navegador);
    }

    @Test
    public void registroExitoso() {
        ExtentTest test = ReporteManager.getTest();
        RegistroPage registro = new RegistroPage(driver);
        CapturaUtils capturaUtils = new CapturaUtils(driver);

        try {
            registro.iniciarRegistroYCompletarFormulario("maria", "ruiz", "marixa" + System.currentTimeMillis() + "@mail.com", "12345ASDs");
            Assert.assertTrue(registro.esperarYValidarVistaCuentaDeUsuario(), "La vista 'My Account' no está visible después del registro");
            test.pass("La vista 'My Account' está visible después del registro");
        } catch (Exception e) {
            test.fail("Falló el test: " + e.getMessage());
            Assert.fail("Test fallido con excepción: " + e.getMessage());
        }   finally {
            capturaUtils.tomarCaptura(test, navegador);
        }
    }

    @Test
    public void registroFallidoPorCorreoYaRegistrado()   {
        ExtentTest test = ReporteManager.getTest();
        RegistroPage registro = new RegistroPage(driver);
        CapturaUtils capturaUtils = new CapturaUtils(driver);

        try {
            registro.iniciarRegistroYCompletarFormulario("Usuario", "Test", "usuario@mail.com", "12345ASDf.");

            Assert.assertTrue(registro.validarErrorEmailYaRegistrado(), "No se mostró el mensaje de error por correo ya registrado");
            test.pass("Se muestra mensaje de error 'Correo ya utilizado'");
        }   catch (Exception e) {
                test.fail("Falló el test: " + e.getMessage());
        }   finally {
            capturaUtils.tomarCaptura(test, navegador);
        }
    }


}
