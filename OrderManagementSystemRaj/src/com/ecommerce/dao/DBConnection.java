

package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    
    private static final String URL =
        "jdbc:mysql://localhost:3306/ecommerce_db";

    private static final String USER = "root";

    private static final String PASSWORD = "RAJESH@2811";

    
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {

        try {
            Connection con = getConnection();

            System.out.println("Database Connected Successfully!");

            con.close();

        } catch (SQLException e) {

            System.out.println("Connection Failed!");
            System.out.println(e.getMessage());
        }
    }
}

    
