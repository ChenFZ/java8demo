package com.chenfz.trino;

import java.sql.ResultSet;

/**
 * @author Miles
 * @create 2025-02-08-10:59
 */
public class RunTrinoQuery {
    public static void main(String[] args) {
        try {
            ResultSet resultSet = TrinoQuery.executeQuery("SELECT * FROM your_table");
            ExcelExporter.exportToExcel(resultSet, "result.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
