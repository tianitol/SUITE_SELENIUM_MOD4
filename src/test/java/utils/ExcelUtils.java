package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    public static Object[][] leerDatosDesdeExcel(String rutaArchivo, String hoja)    {
        try (FileInputStream fis = new FileInputStream(rutaArchivo);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(hoja);

            if (sheet == null) {
                throw new RuntimeException("La hoja '" + hoja + "' no existe en el archivo.");
            }
            int filas = sheet.getLastRowNum();
            int columnas = sheet.getRow(0).getPhysicalNumberOfCells();

            Object[][]  datos = new Object[filas - 1][columnas];

            for (int i = 1; i < filas; i++) {
                Row fila = sheet.getRow(i);

                if (fila == null || fila.getCell(0) == null || fila.getCell(0).toString().trim().isEmpty()) {
                    continue;
                }

                    for (int j = 0; j < columnas; j++)    {
                    Cell celda = fila.getCell(j);
                    datos[i - 1][j] = (celda != null) ? celda.toString().trim() : "";
                }
            }
            return datos;
        }   catch (IOException e)   {
            throw new RuntimeException("Error al leer el archivo Excel: " + e.getMessage());
        }
    }
}
