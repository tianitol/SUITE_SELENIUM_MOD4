package dataproviders;

import org.testng.annotations.DataProvider;
import utils.ExcelUtils;

public class DatosTestProvider {
    @DataProvider(name = "datosLogin")
    public static Object[][] datosLogin()   {
        return ExcelUtils.leerDatosDesdeExcel("src/test/resources/datosLogin.xlsx", "Login");
    }

   /* @DataProvider(name = "datosRegistro")
    public static Object[][] datosRegistro()    {
        return ExcelUtils.leerDatosDesdeExcel("src/test/resources/datosRegistro.xlsx", "Registro");
    }
    */
}
