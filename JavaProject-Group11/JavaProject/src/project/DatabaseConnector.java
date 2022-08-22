package project;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * This is the Class that provides the method for Database connection to the Mysql Server for the GUI Project.
 *
 * <<<<<<<<<<<<<<<<<<<<<<<<<<<<Library Management System>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
 *
 * ---------------------------------Project Members-----------------------------------
 *           [ Bijay Koirala  ]                  [ C0846275 ]
 *           [ Sachita Pathak ]                  [ C0843066 ]
 * -----------------------------------------------------------------------------------
 *           [ Course Name ]                     [ Programming Java SE 04 (CSAM) ]
 * -----------------------------------------------------------------------------------
 *
 *
 * @author Bijay Koirala (C0846275) & Sachita Pathak (C0843066)
 * @since 2022-07-15
 *
 */
public class DatabaseConnector {


    private static String databasePassword;

    /**
     * This is a setter for static member databasePassword
     * @param databasePassword
     */
    public static void setDatabasePassword(String databasePassword) {
        DatabaseConnector.databasePassword = databasePassword;
    }

    /**
     * This method connects to the local databaAse and returns the instance of the Connection class for the
     * use by other clases to perform database operations.
     * @return Connection
     */
    public static Connection connect_to_database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection db_connection = DriverManager.getConnection("jdbc:mysql://localhost/mysql?user=root&password=" + databasePassword);
            return db_connection;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
