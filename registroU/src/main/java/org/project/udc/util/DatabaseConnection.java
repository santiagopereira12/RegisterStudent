package org.project.udc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String url = "jdbc:mysql://localhost:3306/proyecto";
    private static String user = "root";
    private static String password = "Dn09Sv04++";
    private static Connection myConn;

    public static Connection getInstance() throws SQLException {
        if (myConn == null){
            myConn = DriverManager.getConnection(url,user,password);
        }
        return myConn;
    }
}
