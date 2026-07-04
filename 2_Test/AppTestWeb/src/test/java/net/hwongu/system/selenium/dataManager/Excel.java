package net.hwongu.system.selenium.dataManager;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class Excel {

    public static String[][] leerExcel(String rutaArchivo) {
        String[][] lista = null;
        int i = 0;
        String valor = "";

        try {
            // Abrir el archivo de Excel
            FileInputStream archivo = new FileInputStream(new File(rutaArchivo));
            XSSFWorkbook archivoExcel = new XSSFWorkbook(archivo);
            XSSFSheet hojaExcel = archivoExcel.getSheetAt(0);

            // Obtener las filas de la hoja
            Iterator<Row> filas = hojaExcel.iterator();

            // Omitir la primera fila (encabezados)
            filas.next();

            // Inicializar la matriz para los datos
            lista = new String[hojaExcel.getLastRowNum()][];

            while (filas.hasNext()) {
                Row filaActual = filas.next();
                Iterator<Cell> celdas = filaActual.cellIterator();

                // Inicializar la fila de la matriz
                lista[i] = new String[filaActual.getLastCellNum()];

                int j = 0;
                while (celdas.hasNext()) {
                    Cell celda = celdas.next();

                    // Manejar diferentes tipos de celda
                    switch (celda.getCellType()) {
                        case STRING:
                            valor = celda.getStringCellValue();
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(celda)) {
                                valor = celda.getDateCellValue().toString();
                            } else {
                                valor = String.valueOf(celda.getNumericCellValue());
                            }
                            break;
                        case BOOLEAN:
                            valor = String.valueOf(celda.getBooleanCellValue());
                            break;
                        case FORMULA:
                            valor = celda.getCellFormula();
                            break;
                        default:
                            valor = "";
                            break;
                    }

                    // Asignar el valor a la matriz
                    lista[i][j] = valor;
                    j++;
                }
                i++;
            }

            // Cerrar el archivo de Excel
            archivoExcel.close();
            archivo.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
