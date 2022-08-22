package project;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is the Class with the that opens the View Issued Books page of the GUI Project.
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
public class ViewIssuedBooks {

    /**
     * This method view_issued_books_gui() makes use of the rs2xml.jar library to display all the issued books
     * available in the database.
     */
    public static void view_issued_books_gui(){

        //Creating the new JFrame: Users List
        JFrame view_issued_books_frame = new JFrame("View Issued Books List");

        // Creating an instance of the Connection class for database operations:
        Connection db_connection = DatabaseConnector.connect_to_database();
        String sql = "select * from issued";
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
            view_issued_books_frame.add(scrollerPane);
            view_issued_books_frame.setSize(800, 400);
            view_issued_books_frame.setVisible(true);
            view_issued_books_frame.setLocationRelativeTo(null);
        } catch (SQLException e1) {
            //Opening the Message Dialog using the JOptionPane to display an error message to the user:
            JOptionPane.showMessageDialog(null, e1);
        }
    }
}
