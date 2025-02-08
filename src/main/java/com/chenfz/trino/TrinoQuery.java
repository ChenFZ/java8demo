package com.chenfz.trino;

import java.sql.*;

/**
 * @author Miles
 * @create 2025-02-08-10:57
 */
public class TrinoQuery {
    public static ResultSet executeQuery(String sql) throws SQLException {
        String url = "jdbc:trino://your-trino-server:8080/catalog/schema";
        Connection connection = DriverManager.getConnection(url, "username", null);
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }
}
