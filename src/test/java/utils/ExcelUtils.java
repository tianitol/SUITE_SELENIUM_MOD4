package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    public static Object[][] leerDatosDesdeExcel(String rutaArchivo, String hoja) {
        try (FileInputStream fis = new FileInputStream(rutaArchivo);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(hoja);

            if (sheet == null) {
                throw new RuntimeException("La hoja '" + hoja + "' no existe en el archivo.");
            }

            int filas = sheet.getLastRowNum();
            int columnas = sheet.getRow(0).getPhysicalNumberOfCells();

            List<Object[]> datosList = new ArrayList<>();

            for (int i = 1; i <= filas; i++) {
                Row fila = sheet.getRow(i);
                if (fila == null) continue;

                Object[] filaDatos = new Object[columnas];
                boolean filaTieneContenido = false;

                for (int j = 0; j < columnas; j++) {
                    Cell celda = fila.getCell(j);
                    String valor = (celda != null) ? celda.toString().trim() : "";
                    filaDatos[j] = valor;

                    if (!valor.isEmpty()) {
                        filaTieneContenido = true;
                    }
                }

                // Solo agregar la fila si tiene al menos un valor no vacío
                if (filaTieneContenido) {
                    datosList.add(filaDatos);
                }
            }

            return datosList.toArray(new Object[0][]);

        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo Excel: " + e.getMessage());
        }
    }
}
