package project;

import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * This is the Class that provides a notification to the User about the book with nearest deadline to return.
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
public class NotificationFetcher {

    /**
     * This fetch_latest_return_deadline() method provides a message to the User about the deadline of the book to return
     * if the User has any pending book to return. If the User has more than one books to return, it will
     * only fetch the book with the latest deadline to notify the user in the GUI Project.
     *
     * @param user_id It is the User Id of the user that's logged in.
     * @return It returns the String of message of which book has nearest deadline to be returned
     */
    public static String fetch_latest_return_deadline(String user_id) {
        String returnNotification = "";
        try {
            // Creating an instance of the Connection class for database operations:
            Connection db_connection = DatabaseConnector.connect_to_database();
            Statement statement_lib = db_connection.createStatement();
            statement_lib.executeUpdate("USE library_management");

            PreparedStatement prepared_statement = db_connection.prepareStatement("select book_name, issued_date, period from issued join books on books.book_id = issued.book_id where user_id=? and return_date is null",
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            prepared_statement.setString(1, user_id);

            //Executing the query:
            ResultSet resultSet = prepared_statement.executeQuery();

            java.util.Date latestDeadline = new java.util.Date(0);

            if (resultSet.next() != false) {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    String book_name = resultSet.getString("book_name");
                    String issued_date_str = resultSet.getString("issued_date");
                    String period = resultSet.getString("period");
                    java.util.Date issued_date = new SimpleDateFormat("dd-MM-yyyy").parse(issued_date_str);
                    java.util.Date deadline_date = CommonUtils.add_days(issued_date, Integer.parseInt(period));

                    if (latestDeadline.compareTo(new java.util.Date(0)) == 0 || latestDeadline.compareTo(deadline_date) > 0) {
                        latestDeadline = deadline_date;
                        String formattedDate = new SimpleDateFormat("dd-MM-yyyy").format(deadline_date);
                        returnNotification = "Reminder! Your latest Books Return Deadline is: " + formattedDate + " for Book: " + book_name;
                    }
                }
            }
        } catch (SQLException | ParseException e1) {
            //Opening the Message Dialog using the JOptionPane to display an error message to the user:
            JOptionPane.showMessageDialog(null, e1);
        }
        return returnNotification;
    }
}
