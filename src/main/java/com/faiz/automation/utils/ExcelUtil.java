package com.faiz.automation.utils;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    public static Object[][] getSheetData(
            String filePath,
            String sheetName) {

        try {

            FileInputStream fis =
                    new FileInputStream(filePath);

            XSSFWorkbook workbook =
                    new XSSFWorkbook(fis);

            XSSFSheet sheet =
                    workbook.getSheet(sheetName);

            int rowCount =
                    sheet.getPhysicalNumberOfRows();

            int colCount =
                    sheet.getRow(0)
                         .getPhysicalNumberOfCells();

            Object[][] data =
                    new Object[rowCount - 1][colCount];

            DataFormatter formatter =
                    new DataFormatter();

            for (int i = 1; i < rowCount; i++) {

                Row row =
                        sheet.getRow(i);

                for (int j = 0; j < colCount; j++) {

                    data[i - 1][j] =
                            formatter.formatCellValue(
                                    row.getCell(j));
                }
            }

            workbook.close();
            fis.close();

            return data;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to read Excel file",
                    e);
        }
    }
}