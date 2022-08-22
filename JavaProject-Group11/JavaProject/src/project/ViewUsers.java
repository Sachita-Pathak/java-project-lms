package project;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is the Class with the that opens the View Users page of the GUI Project.
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
public class ViewUsers {

    /**
     * This method view_users_gui() makes use of the rs2xml.jar library to display all the users available in
     * the database.
     */
    public static void view_users_gui() {

        //Creating the new JFrame: Users List
        JFrame view_users_list_frame = new JFrame(" View Users List");

        // Creating an instance of the Connection class for database operations:
        Connection db_connection = DatabaseConnector.connect_to_database();
        String sql = "select * from users";
        try {

            //Creates a Statement Class's Instance to be used to perform queries:
            Statement statement_lib = db_connection.createStatement();
            statement_lib.executeUpdate("USE library_management");
            statement_lib = db_connection.createStatement();

            //Executing the query:
            ResultSet resultSet = statement_lib.executeQuery(sql);

            JTable book_list = new JTable();
            book_list.setModel(DbUtils.resultSetToTableModel(resultSet));
            JScrollPane scrollerPane = new JScrollPane(book_list);

            //Adding all the GUI components to the JFrame window:
            view_users_list_frame.add(scrollerPane);
            view_users_list_frame.setSize(800, 400);
            view_users_list_frame.setVisible(true);
            view_users_list_frame.setLocationRelativeTo(null);
        } catch (SQLException e1) {
            //Opening the Message Dialog using the JOptionPane to display an error message to the user:
            JOptionPane.showMessageDialog(null, e1);
        }
    }
}
