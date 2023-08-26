/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medicineshop;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
         Class.forName("org.sqlite.JDBC");//Invoking Driver
        Connection con=DriverManager.getConnection("jdbc:sqlite:C://sqlite//med.db");//Establish Connection
        return con;
    }
}