/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles connection and methods to interact with database
 *
 * @author
 */
public class DBConnection {

    //Connection to database
    private static Connection con;

    //Inicializing connection in static field
    //@Edgars
    static {
        try {
            String url = "jdbc:mysql://192.168.0.102/e_aprupe";
            String user = "root";
            String pass = "abcd1234";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Connection successfully established! \n");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean login(String log, String pass) {

        return true;
    }

    /**
     * Resets database every time we run program TEST PURPOSE
     */
    public static void resetDatabase() {
        dropDatabases();
        initDatabases();
        addAccount("accountID", "password", 1);

    }

    /**
     * Drops databases
     *
     * @Veronika
     */
    private static void dropDatabases() {
        //TODO drop Accounts table

        try {
            Statement st = con.createStatement();
            st.executeUpdate("DROP TABLE accounts");
            System.out.println("DONE");
        } catch (SQLException ex) {
            System.out.println("Couldn't drop table!");
        }

    }

    /**
     * Creates databases
     *
     * @Veronika
     */
    private static void initDatabases() {
        //TODO create Accounts Table ( string(id), string(password), int(piekluvesL)

        try {
            Statement st = con.createStatement();
            st.executeUpdate("CREATE TABLE  accounts ( "
                    + "accountID varchar(20) NOT NULL UNIQUE,"
                    + "accountPassword varchar(32) NOT NULL,"
                    + "accountAccessLevel INT(1) NOT NULL);");
            System.out.println("DONE");
        } catch (SQLException ex) {
            System.out.println("Couldn't create table!");
        }

    }

    private static void addPatient() {
    }

    private static void addAccount(String acoountID, String accountPassword, int accountAccessLevel) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO accounts(accountID, accountPassword, accountAccessLevel) VALUES ('" + acoountID + "','" + accountPassword + "','" + accountAccessLevel + "')");
            System.out.println("DONE");
        } catch (SQLException ex) {
            System.out.println("Couldn't add account!");
        }

    }

    public static void main(String[] args) {
        DBConnection.resetDatabase();


    }
}
