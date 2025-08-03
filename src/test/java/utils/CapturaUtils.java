package utils;

import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import java.io.File;
import java.io.IOException;

public class CapturaUtils {

    private WebDriver driver;

    public CapturaUtils(WebDriver driver)   {
        this.driver = driver;
    }

    public  void tomarCaptura(ExtentTest test, String navegador) {
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
