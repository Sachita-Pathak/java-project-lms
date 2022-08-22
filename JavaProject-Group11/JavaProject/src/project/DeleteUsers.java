package project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * This is the Class with the that opens the Delete Users page of the GUI Project.
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
public class DeleteUsers {

    /**
     * This method delete_users_gui() opens a JFrame Box and lets the user enter the following details:
     * 1. User ID(UID)
     * 2. User Name
     * Validations are put there to ensure that Correct type of input is given by the user.
     * i.e. User ID(UID) and User Name must be a non-empty string and Book ID(BID) should also be an integer
     * If the user input is valid then only it deletes the record with that user id from the database.
     */
    public static void delete_users_gui() {

        //Creating the new JFrame: Enter User Details
        JFrame delete_user_frame = new JFrame("Enter User Details");
        JLabel user_id_label, user_name_label;

        // Adding the label: User ID(UID)
        user_id_label = new JLabel("User ID(UID)");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        user_id_label.setBounds(30, 15, 100, 30);

        // Adding the label: User Name
        user_name_label = new JLabel("User Name");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        user_name_label.setBounds(30, 50, 150, 30);

        // Adding the JTextField for: User ID(UID)
        JTextField user_id_text_field = new JTextField();

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        user_id_text_field.setBounds(110, 15, 200, 30);

        // Adding the JTextField for: User Name
        JTextField user_name_text_field = new JTextField();

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        user_name_text_field.setBounds(110, 50, 200, 30);

        // Adding the JButton for the operation: Delete User
        JButton delete_user_button = new JButton("Delete User");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        delete_user_button.setBounds(120, 170, 100, 25);
        delete_user_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user_id = user_id_text_field.getText().trim();
                String user_name = user_name_text_field.getText().trim();
                // Creating an instance of the Connection class for database operations:
                Connection db_connection = DatabaseConnector.connect_to_database();

                boolean isUserIdInt = CommonUtils.isInteger(user_id);

                //First Validating if the User Input is valid:
                if (user_id.equals("")) {
                    //Opening the Message Dialog using the JOptionPane to display a message to the user:
                    JOptionPane.showMessageDialog(null, "Please Enter User Id!");
                } else if (!isUserIdInt) {
                    //Opening the Message Dialog using the JOptionPane to display a message to the user:
                    JOptionPane.showMessageDialog(null, "User Id must be an Integer!");
                } else if (user_name.equals("")) {
                    //Opening the Message Dialog using the JOptionPane to display a message to the user:
                    JOptionPane.showMessageDialog(null, "Please Enter UserName!");
                } else {
                    try {

                        //Creates a Statement Class's Instance to be used to perform queries:
                        Statement statement_lib = db_connection.createStatement();

                        statement_lib.executeUpdate("USE library_management");


                        PreparedStatement prepared_statement = db_connection.prepareStatement("SELECT * FROM users WHERE user_id=? and username=?",
                                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        prepared_statement.setString(1, user_id);
                        prepared_statement.setString(2, user_name);

                        //Executing the query:
                        ResultSet resultSet = prepared_statement.executeQuery();

                        if (resultSet.next() == false) {
                            //Opening the Message Dialog using the JOptionPane to display a message to the user:
                            JOptionPane.showMessageDialog(null, "No such User with that User Id and User Name!");
                        } else {
                            statement_lib.executeUpdate("DELETE FROM users WHERE user_id = " + user_id + " and username = '" + user_name + "'");

                            //Opening the Message Dialog using the JOptionPane to display a message to the user:
                            JOptionPane.showMessageDialog(null, "User Deleted!");

                            // This destroys the delete_user_frame window and gets it cleaned up by OS
                            delete_user_frame.dispose();
                        }
                    } catch (SQLException e1) {
                        //Opening the Message Dialog using the JOptionPane to display an error message to the user:
                        JOptionPane.showMessageDialog(null, e1);
                    } catch (Exception ex) {
                        //Opening the Message Dialog using the JOptionPane to display an error message to the user:
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
        });

        //Adding all the GUI components to the JFrame window:
        delete_user_frame.add(user_name_label);
        delete_user_frame.add(delete_user_button);
        delete_user_frame.add(user_id_label);
        delete_user_frame.add(user_id_text_field);
        delete_user_frame.add(user_name_text_field);
        delete_user_frame.setSize(350, 250);
        delete_user_frame.setLayout(null);
        delete_user_frame.setVisible(true);
        delete_user_frame.setLocationRelativeTo(null);
    }
}
