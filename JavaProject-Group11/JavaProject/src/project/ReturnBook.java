package project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**
 * This is the Class with the that opens the Return Book page of the GUI Project.
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
public class ReturnBook {

    /**
     * This method return_book_gui() opens a JFrame Box and lets the user enter the following details:
     * 1. Issue ID(BID)
     * 2. Return Date(DD-MM-YYYY)
     * Validations are put there to ensure that Correct type of input is given by the user.
     * i.e. Issue ID must be an integer
     * and, the Return Date must be a date in (dd-MM-YYYY) format
     * If the user input is valid then only it updates the values as the returned book into the database.
     * Also, it calculates the fine amount and updates it to the database and informs the user through
     * a dialog box if any.
     */
    public static void return_book_gui(){

        //Creating the new JFrame: Enter Details
        JFrame return_book_frame = new JFrame("Enter Details");
        JLabel issue_id_label, return_date_label;

        // Adding the label: Issue ID(IID)
        issue_id_label = new JLabel("Issue ID(IID)");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        issue_id_label.setBounds(30, 15, 100, 30);

        // Adding the label: Return Date(DD-MM-YYYY)
        return_date_label = new JLabel("Return Date(DD-MM-YYYY)");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        return_date_label.setBounds(30, 50, 150, 30);

        // Adding the JTextField for: Issue ID(IID)
        JTextField issue_id_text_field = new JTextField();

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        issue_id_text_field.setBounds(110, 15, 200, 30);

        // Adding the JTextField for: Return Date(DD-MM-YYYY)
        JTextField return_text_field = new JTextField();

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        return_text_field.setBounds(180, 50, 130, 30);

        // Adding the JButton for the operation: Return
        JButton return_button = new JButton("Return");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        return_button.setBounds(130, 170, 100, 25);
        return_button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String issue_id = issue_id_text_field.getText().trim();
                String return_date = return_text_field.getText().trim();

                //First Validating if the User Input is valid:
                if (issue_id.equals("")) {
                    //Opening the Message Dialog using the JOptionPane to display a message to the user:
                    JOptionPane.showMessageDialog(null, "Please Enter Issued Id!");
                } else if (return_date.equals("")) {
                    //Opening the Message Dialog using the JOptionPane to display a message to the user:
                    JOptionPane.showMessageDialog(null, "Please Enter Return Date!");
                } else if (!CommonUtils.isInteger(issue_id)) {
                    //Opening the Message Dialog using the JOptionPane to display a message to the user:
                    JOptionPane.showMessageDialog(null, "Issued Id must be an Integer!");
                } else if (!CommonUtils.isValid(return_date)) {
                    //Opening the Message Dialog using the JOptionPane to display a message to the user:
                    JOptionPane.showMessageDialog(null, "Return Date must be in the DD-MM-YYYY Format!");
                } else {
                    // Creating an instance of the Connection class for database operations:
                    Connection db_connection = DatabaseConnector.connect_to_database();

                    try {

                        //Creates a Statement Class's Instance to be used to perform queries:
                        Statement statement_lib = db_connection.createStatement();
                        statement_lib.executeUpdate("USE library_management");
                        String date1 = null;
                        String existing_return_date = null;
                        int days = 0;
                        String date2 = return_date;

                        //Executing the query:
                        ResultSet rs_returned_date = statement_lib.executeQuery("SELECT return_date FROM issued WHERE issued_id=" + issue_id);

                        while (rs_returned_date.next()) {
                            existing_return_date = rs_returned_date.getString(1);
                        }

                        //First Validating if the User Input is valid:
                        if (existing_return_date == null) {

                            //Executing the query:
                            ResultSet rs_issued_date = statement_lib.executeQuery("SELECT issued_date FROM issued WHERE issued_id=" + issue_id);

                            while (rs_issued_date.next()) {
                                date1 = rs_issued_date.getString(1);
                            }
                            //Again Validating if the User Input is valid:
                            if (date1 != null) {
                                try {
                                    Date date_1 = new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse(date1).getTime());
                                    Date date_2 = new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse(date2).getTime());
                                    long diff = date_2.getTime() - date_1.getTime();
                                    days = (int) (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
                                } catch (ParseException e1) {
                                    //Opening the Message Dialog using the JOptionPane to display an error message to the user:
                                    JOptionPane.showMessageDialog(null, "Please enter a valid date");
                                }

                                if (days != 0) {
                                    // The User Input is valid so executing the operation:
                                    statement_lib.executeUpdate("UPDATE issued SET return_date='" + return_date + "' WHERE issued_id=" + issue_id);

                                    // This destroys the return_book_frame window and gets it cleaned up by OS
                                    return_book_frame.dispose();

                                    // Creating an instance of the Connection class for database operations:
                                    Connection db_connection1 = DatabaseConnector.connect_to_database();

                                    //Creates a Statement Class's Instance to be used to perform queries:
                                    Statement statement_lib1 = db_connection.createStatement();
                                    statement_lib1.executeUpdate("USE library_management");

                                    //Executing the query:
                                    ResultSet rs_issue_period = statement_lib1.executeQuery("SELECT period FROM issued WHERE issued_id=" + issue_id);

                                    String diff = null;
                                    while (rs_issue_period.next()) {
                                        diff = rs_issue_period.getString(1);
                                    }
                                    int diff_int = Integer.parseInt(diff);
                                    if (days >= diff_int) {

                                        int fine = (days - diff_int) * 10;

                                        statement_lib1.executeUpdate("UPDATE issued SET fine=" + fine + " WHERE issued_id=" + issue_id);
                                        String fine_str = ("Fine: Rs. " + fine);
                                        //Opening the Message Dialog using the JOptionPane to display added fine to the user:
                                        JOptionPane.showMessageDialog(null, fine_str);

                                    }

                                    //Opening the Message Dialog using the JOptionPane to display a message to the user:
                                    JOptionPane.showMessageDialog(null, "Book Returned!");

                                    // This destroys the return_book_frame window and gets it cleaned up by OS
                                    return_book_frame.dispose();
                                }
                            } else {
                                //Opening the Message Dialog using the JOptionPane to display a message to the user:
                                JOptionPane.showMessageDialog(null, "Such Issue Id does not exist");
                            }
                        } else {
                            //Opening the Message Dialog using the JOptionPane to display a message to the user:
                            JOptionPane.showMessageDialog(null, "This Issue Id has already returned book.");
                        }
                    } catch (SQLException e1) {
                        //Opening the Message Dialog using the JOptionPane to display an error message to the user:
                        JOptionPane.showMessageDialog(null, e1);
                    }
                }
            }

        });

        //Adding all the GUI components to the JFrame window:
        return_book_frame.add(return_date_label);
        return_book_frame.add(return_button);
        return_book_frame.add(issue_id_label);
        return_book_frame.add(issue_id_text_field);
        return_book_frame.add(return_text_field);
        return_book_frame.setSize(350, 250);
        return_book_frame.setLayout(null);
        return_book_frame.setVisible(true);
        return_book_frame.setLocationRelativeTo(null);
    }
}
