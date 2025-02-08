package com.chenfz.trino;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @author Miles
 * @create 2025-02-08-10:58
 */
public class ExcelExporter {
    public static void exportToExcel(ResultSet resultSet, String outputPath) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Trino Results");

        // 创建表头
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        Row headerRow = sheet.createRow(0);
        for (int i = 1; i <= columnCount; i++) {
            Cell cell = headerRow.createCell(i - 1);
            cell.setCellValue(metaData.getColumnName(i));
        }

        // 填充数据
        int rowNum = 1;
        while (resultSet.next()) {
            Row row = sheet.createRow(rowNum++);
            for (int i = 1; i <= columnCount; i++) {
                Cell cell = row.createCell(i - 1);
                Object value = resultSet.getObject(i);
                if (value != null) {
                    cell.setCellValue(value.toString());
                } else {
                    cell.setCellValue("");
                }
            }
        }

        // 写入文件
        try (FileOutputStream outputStream = new FileOutputStream(outputPath)) {
            workbook.write(outputStream);
        }
        workbook.close();
    }
}
