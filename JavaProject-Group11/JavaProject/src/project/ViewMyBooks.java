package project;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is the Class with the that opens the View My Books page of the GUI Project.
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
public class ViewMyBooks {

    /**
     * This method view_my_books_gui() makes use of the rs2xml.jar library to display all the books ever issued by the
     * user with the user_id in the passed parameter (that is available in the database).
     * @param user_id This is the parameter of this method which is the user_id of the logged in user.
     */
    public static void view_my_books_gui(String user_id) {

        //Creating the new JFrame: View My Books
        JFrame view_my_books_frame = new JFrame("View My Books");
        int int_user_id = Integer.parseInt(user_id);

        // Creating an instance of the Connection class for database operations:
        Connection db_connection = DatabaseConnector.connect_to_database();

        String sql = "select distinct issued.*, books.book_name, books.genre, books.price " +
                "from issued join books on issued.book_id = books.book_id where (issued.user_id=" + int_user_id + ") group by issued.issued_id";
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
            view_my_books_frame.add(scrollerPane);
            view_my_books_frame.setSize(800, 400);
            view_my_books_frame.setVisible(true);
            view_my_books_frame.setLocationRelativeTo(null);
        } catch (SQLException e1) {
            //Opening the Message Dialog using the JOptionPane to display an error message to the user:
            JOptionPane.showMessageDialog(null, e1);
        }
    }
}
