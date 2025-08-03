package tests;

import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import pages.RegistroPage;
import utils.ReporteManager;

import java.io.File;
import java.io.IOException;

public class RegistroTest extends BaseTest{

    public RegistroTest(String navegador)   {
        super(navegador);
    }

    @Factory
    public static Object[] factoryMethod()  {
        return new Object[] {
                new RegistroTest("chrome"),
                new RegistroTest("edge")
        };
    }


    @Test
    public void registroExitoso() {
        ExtentTest test = ReporteManager.crearTest("Registro exitoso");

        RegistroPage registro = new RegistroPage(driver);

        try {
            registro.iniciarRegistroYCompletarFormulario("maria", "ruiz", "marixa" + System.currentTimeMillis() + "@mail.com", "12345ASDs");
            Assert.assertTrue(registro.esperarYValidarVistaCuentaDeUsuario(), "La vista 'My Account' no está visible después del registro");
            test.pass("La vista 'My Account' está visible después del registro");
            tomarCaptura(test);

        } catch (Exception e) {
            test.fail("Falló el test: " + e.getMessage());

            tomarCaptura(test);
            Assert.fail("Test fallido con excepciín: " + e.getMessage());
        }
    }

    @Test
    public void registroFallidoPorCorreoYaRegistrado()   {
        ExtentTest test = ReporteManager.crearTest("Registro fallido - correo ya registrado");
        RegistroPage registro = new RegistroPage(driver);

        try {
            registro.iniciarRegistroYCompletarFormulario("Usuario", "Test", "usuario@mail.com", "12345ASDf.");

            Assert.assertTrue(registro.validarErrorEmailYaRegistrado(), "No se mostró el mensaje de error por correo ya registrado");
            test.pass("Se muestra mensaje de error 'Correo ya utilizado'");

            tomarCaptura(test);

        }   catch (Exception e) {
                test.fail("Falló el test: " + e.getMessage());

                tomarCaptura(test);

        }
    }

    private void tomarCaptura(ExtentTest test) {
            //Captura de pantalla
            File captura = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String ruta = "target/captura_" + test.getModel().getName() + "_" + test.getStatus() + "_" + navegador + ".png";
            try {
                FileUtils.copyFile(captura, new File(ruta));
                test.addScreenCaptureFromPath(ruta);
            }   catch   (IOException ioException)  {
                test.warning("No se pudo guardar la captura de pantalla: " + ioException.getMessage());
            }
    }
}
