package project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * This is the Class with the that opens the Update users page of the GUI Project.
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
public class UpdateUsers {

    /**
     * This method update_users_gui() opens a JFrame Box and lets the user enter the following details:
     * 1. User ID(BID)
     * 2. User Name
     * 3. Password
     * 4. User Role (Radio Button: Admin or User option)
     * Validations are put there to ensure that Correct type of input is given by the user.
     * i.e. User ID must be an integer, User Name must be a non-empty string and At least one radio button should be clicked.
     * If the user input is valid then only it updates the values of the book into the database.
     */
    public static void update_users_gui(){

        //Creating the new JFrame: Enter User Details
        JFrame update_user_details_frame = new JFrame("Enter User Details");
        JLabel user_id_label, password_label, user_name_label;

        // Adding the label: User ID(UID)
        user_id_label = new JLabel("User ID(UID)");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        user_id_label.setBounds(30, 15, 100, 30);

        // Adding the label: User Name
        user_name_label = new JLabel("User Name");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        user_name_label.setBounds(30, 50, 100, 30);

        // Adding the label: Password
        password_label = new JLabel("Password");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        password_label.setBounds(30, 85, 100, 30);

        // Adding the JTextField for: User ID(UID)
        JTextField user_id_text_field = new JTextField();

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        user_id_text_field.setBounds(120, 15, 150, 30);

        // Adding the JTextField for: User Name
        JTextField user_name_text_field = new JTextField();

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        user_name_text_field.setBounds(120, 50, 200, 30);

        // Adding the JTextField for: Password
        JTextField password_text_field = new JTextField();

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        password_text_field.setBounds(120, 85, 200, 30);

        // Adding the JTextField for: Admin
        JRadioButton admin_button = new JRadioButton("Admin");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        admin_button.setBounds(45, 135, 100, 30);

        // Adding the JTextField for: User
        JRadioButton user_button = new JRadioButton("User", true);

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        user_button.setBounds(140, 135, 100, 30);

        //Adding the ButtonGroup for: Admin Role or, User Role:
        ButtonGroup user_roles_button_group = new ButtonGroup();
        user_roles_button_group.add(admin_button);
        user_roles_button_group.add(user_button);

        // Adding the JButton for the operation: Update User
        JButton update_user_button = new JButton("Update User");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        update_user_button.setBounds(130, 170, 100, 25);
        update_user_button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String user_id = user_id_text_field.getText().trim();
                String user_name = user_name_text_field.getText().trim();
                String user_password = password_text_field.getText().trim();
                int is_admin = 0;

                if (admin_button.isSelected()) {
                    is_admin = 1;
                }

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
                } else if (user_password.equals("")) {
                    //Opening the Message Dialog using the JOptionPane to display a message to the user:
                    JOptionPane.showMessageDialog(null, "Please Enter Password!");
                } else {
                    // Creating an instance of the Connection class for database operations:
                    Connection db_connection = DatabaseConnector.connect_to_database();

                    try {

                        //Creates a Statement Class's Instance to be used to perform queries:
                        Statement statement_lib = db_connection.createStatement();

                        statement_lib.executeUpdate("USE library_management");

                        PreparedStatement prepared_statement = db_connection.prepareStatement("SELECT * FROM users WHERE user_id=?",
                                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        prepared_statement.setString(1, user_id);

                        //Executing the query:
                        ResultSet resultSet = prepared_statement.executeQuery();

                        if (resultSet.next() == false) {
                            //Opening the Message Dialog using the JOptionPane to display a message to the user:
                            JOptionPane.showMessageDialog(null, "No such User with that User Id!");
                        } else {

                            statement_lib.executeUpdate("UPDATE users SET username='" + user_name + "', password='" + user_password + "', is_admin=b'" + is_admin + "' WHERE user_id = " + user_id);

                            //Opening the Message Dialog using the JOptionPane to display a message to the user:
                            JOptionPane.showMessageDialog(null, "User Updated!");

                            // This destroys the update_user_details_frame window and gets it cleaned up by OS
                            update_user_details_frame.dispose();

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
        update_user_details_frame.add(password_label);
        update_user_details_frame.add(user_name_label);
        update_user_details_frame.add(update_user_button);
        update_user_details_frame.add(user_id_label);
        update_user_details_frame.add(user_id_text_field);
        update_user_details_frame.add(user_name_text_field);
        update_user_details_frame.add(password_text_field);
        update_user_details_frame.add(admin_button);
        update_user_details_frame.add(user_button);
        update_user_details_frame.setSize(350, 250);
        update_user_details_frame.setLayout(null);
        update_user_details_frame.setVisible(true);
        update_user_details_frame.setLocationRelativeTo(null);
    }
}
