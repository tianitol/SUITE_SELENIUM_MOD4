package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import pages.RegistroPage;

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
    public void testPrueba() {
        boolean linkBlessPresente = driver.findElement(By.xpath("//*[@id=\"shopify-section-sections--18814866882798__footer\"]/footer/div[2]/div[2]/div/small[1]/a")).isDisplayed();
        Assert.assertTrue(linkBlessPresente, "El link de Bless no está disponible en la página principal");

    }

    @Test
    public void registroExitoso()   {
        RegistroPage registro = new RegistroPage(driver);
        registro.iniciarRegistroYCompletarFormulario("Nic", "Hen", "nic@mail.com", "12345ASD");

        Assert.assertTrue(registro.estaVisibleLinkCerrarSesion(),"El link 'Cerrar sesión' no está visible después del registro");
    }
}
