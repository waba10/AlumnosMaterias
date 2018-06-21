/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication13.data;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

    private static Connection instance;
    private static java.sql.Connection connection;
    private static final String password = "root";
    private static final String user = "root";

    private Connection() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getInstance() {
        if (instance == null) {
            synchronized (Connection.class) {
                if (instance == null) {
                    instance = new Connection();
                }
            }
        }

        return instance;
    }

    public java.sql.Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mariadb://localhost:3306/estudiantes", user, password);
    }
}
