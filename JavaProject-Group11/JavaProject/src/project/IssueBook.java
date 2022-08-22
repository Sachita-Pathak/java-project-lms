package project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is the Class with the that opens the Issue Book page of the GUI Project.
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
public class IssueBook {

    /**
     * This method issue_book_gui() opens a JFrame Box and lets the user enter the following details:
     * 1. Book ID(BID)
     * 2. User ID(UID)
     * 3. Period(days)
     * 4. Issued Date(DD-MM-YYYY)
     * Validations are put there to ensure that Correct type of input is given by the user.
     * i.e. Book ID, User ID and Period must be an integer
     * and, the Issued Date must be a date in (dd-MM-YYYY) format
     * If the user input is valid then only it adds the values as the issued record into the database.
     */
    public static void issue_book_gui(){

        //Creating the new JFrame: Enter Details
        JFrame issue_books_list_frame = new JFrame("Enter Details");
        JLabel book_id_label, user_id_label, period_days_label, issued_date_label;

        // Adding the label: Book ID(BID)
        book_id_label = new JLabel("Book ID(BID)");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        book_id_label.setBounds(30, 15, 100, 30);

        // Adding the label: User ID(UID)
        user_id_label = new JLabel("User ID(UID)");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        user_id_label.setBounds(30, 53, 100, 30);

        // Adding the label: Period(days)
        period_days_label = new JLabel("Period(days)");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        period_days_label.setBounds(30, 90, 100, 30);

        // Adding the label: Issued Date(DD-MM-YYYY)
        issued_date_label = new JLabel("Issued Date(DD-MM-YYYY)");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        issued_date_label.setBounds(30, 127, 150, 30);

        // Adding the JTextField for: Book ID(BID)
        JTextField book_id_text_field = new JTextField();

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        book_id_text_field.setBounds(110, 15, 200, 30);

        // Adding the JTextField for: User ID(UID)
        JTextField user_id_text_field = new JTextField();

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        user_id_text_field.setBounds(110, 53, 200, 30);

        // Adding the JTextField for: Period(days)
        JTextField period_text_field = new JTextField();

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        period_text_field.setBounds(110, 90, 200, 30);

        // Adding the JTextField for: Issued Date(DD-MM-YYYY)
        JTextField issue_text_field = new JTextField();

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        issue_text_field.setBounds(180, 130, 130, 30);

        // Adding the JButton for the operation: Issue
        JButton issue_button = new JButton("Issue");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        issue_button.setBounds(130, 170, 100, 25);
        issue_button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String user_id = user_id_text_field.getText().trim();
                String book_id = book_id_text_field.getText().trim();
                String period = period_text_field.getText().trim();
                String issued_date = issue_text_field.getText().trim();

                boolean is_user_id_int_value = CommonUtils.isInteger(user_id);
                boolean is_book_id_int_value = CommonUtils.isInteger(book_id);
                boolean is_period_int_value = CommonUtils.isInteger(period);

                //First Validating if the User Input is valid:
                if (user_id.equals("")) {
                    //Opening the Message Dialog using the JOptionPane to display a message to the user:
                    JOptionPane.showMessageDialog(null, "Please Enter User Id!");
                } else if (book_id.equals("")) {
                    //Opening the Message Dialog using the JOptionPane to display a message to the user:
                    JOptionPane.showMessageDialog(null, "Please Enter Book Id!");
                } else if (period.equals("")) {
                    //Opening the Message Dialog using the JOptionPane to display a message to the user:
                    JOptionPane.showMessageDialog(null, "Please Enter Period!");
                } else if (issued_date.equals("")) {
                    //Opening the Message Dialog using the JOptionPane to display a message to the user:
                    JOptionPane.showMessageDialog(null, "Please Enter Issued Date!");
                } else if (!is_user_id_int_value) {
                    //Opening the Message Dialog using the JOptionPane to display a message to the user:
                    JOptionPane.showMessageDialog(null, "User Id must be an Integer!");
                } else if (!is_book_id_int_value) {
                    //Opening the Message Dialog using the JOptionPane to display a message to the user:
                    JOptionPane.showMessageDialog(null, "Book Id must be an Integer!");
                } else if (!is_period_int_value) {
                    //Opening the Message Dialog using the JOptionPane to display a message to the user:
                    JOptionPane.showMessageDialog(null, "Period must be an Integer!");
                } else if (!CommonUtils.isValid(issued_date)) {
                    //Opening the Message Dialog using the JOptionPane to display a message to the user:
                    JOptionPane.showMessageDialog(null, "The Issued date must be in DD-MM-YYYY format!");
                } else {
                    // Creating an instance of the Connection class for database operations:
                    Connection db_connection = DatabaseConnector.connect_to_database();

                    try {

                        //Creates a Statement Class's Instance to be used to perform queries:
                        Statement statement_lib = db_connection.createStatement();
                        statement_lib.executeUpdate("USE library_management");
                        statement_lib.executeUpdate("INSERT INTO issued(user_id,book_id,issued_date,period) VALUES ('" + user_id + "','" + book_id + "','" + issued_date + "'," + period + ")");

                        //Opening the Message Dialog using the JOptionPane to display a message to the user:
                        JOptionPane.showMessageDialog(null, "Book Issued!");

                        // This destroys the issue_books_list_frame window and gets it cleaned up by OS
                        issue_books_list_frame.dispose();

                    } catch (SQLException e1) {
                        //Opening the Message Dialog using the JOptionPane to display an error message to the user:
                        JOptionPane.showMessageDialog(null, e1);
                    }
                }
            }

        });


        //Adding all the GUI components to the JFrame window:
        issue_books_list_frame.add(period_days_label);
        issue_books_list_frame.add(issued_date_label);
        issue_books_list_frame.add(issue_button);
        issue_books_list_frame.add(book_id_label);
        issue_books_list_frame.add(user_id_label);
        issue_books_list_frame.add(user_id_text_field);
        issue_books_list_frame.add(book_id_text_field);
        issue_books_list_frame.add(period_text_field);
        issue_books_list_frame.add(issue_text_field);
        issue_books_list_frame.setSize(350, 250);
        issue_books_list_frame.setLayout(null);
        issue_books_list_frame.setVisible(true);
        issue_books_list_frame.setLocationRelativeTo(null);
    }
}
