package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistroTest extends BaseTest{

    @Test(dataProvider = "navegadores")
    public void testRegistroEnAmbsNavegadores(String navegador) {
        setUp(navegador);
        boolean linkBlessPresente = driver.findElement(By.xpath("//*[@id=\"shopify-section-sections--18814866882798__footer\"]/footer/div[2]/div[2]/div/small[1]/a")).isDisplayed();
        Assert.assertTrue(linkBlessPresente, "El link de Bless no está disponible en la página principal");

    }
}
