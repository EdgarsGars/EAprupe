/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Handles connection and methods to interact with database
 * @author Edgar
 */
public class DBConnection {
    //Connection to database
    private static Connection con;

    //Inicializing connection in static field
    static {
        try {
            String url = "jdbc:mysql://localhost/e_aprupe";
            String user = "root";
            String pass = "abcd1234";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Connection successfully established! \n");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static boolean login(String log, String pass){
        
        
        
        return true;
    }
    
    
    
}
