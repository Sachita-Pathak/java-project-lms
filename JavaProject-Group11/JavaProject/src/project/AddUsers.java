package project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is the Class with the that opens the Add User page of the GUI Project.
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
public class AddUsers {
    /**
     * This method add_users_gui() opens a JFrame Box and lets the user enter the following details:
     * 1. Username
     * 2. Password
     * Validations are put there to ensure that Correct type of input is given by the user.
     * i.e. Username and Password must be a non-empty string
     * If the user input is valid then only it adds the values into the database.
     */
    public static void add_users_gui(){

        //Creating the new JFrame: Enter User Details
        JFrame add_new_user_frame = new JFrame("Enter User Details");
        JLabel username_label, password_label;

        // Adding the label: Username
        username_label = new JLabel("Username");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        username_label.setBounds(30, 15, 100, 30);

        // Adding the label: Password
        password_label = new JLabel("Password");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        password_label.setBounds(30, 50, 100, 30);

        // Adding the JTextField for: Username
        JTextField username_text_field = new JTextField();

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        username_text_field.setBounds(110, 15, 200, 30);

        // Adding the JTextField for: Password
        JPasswordField password_text_field = new JPasswordField();

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        password_text_field.setBounds(110, 50, 200, 30);

        // Adding the JTextField for: Admin
        JRadioButton admin_radio_button = new JRadioButton("Admin");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        admin_radio_button.setBounds(55, 80, 200, 30);

        // Adding the JTextField for: User
        JRadioButton user_radio_button = new JRadioButton("User", true);

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        user_radio_button.setBounds(130, 80, 200, 30);

        //Adding the ButtonGroup for: Admin Role or, User Role:
        ButtonGroup user_roles_button_group = new ButtonGroup();
        user_roles_button_group.add(admin_radio_button);
        user_roles_button_group.add(user_radio_button);

        // Adding the JButton for the operation: Create
        JButton create_button_field = new JButton("Create");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        create_button_field.setBounds(130, 130, 100, 25);
        create_button_field.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String username = username_text_field.getText().trim();
                String password = password_text_field.getText().trim();
                Boolean is_admin = false;

                if (admin_radio_button.isSelected()) {
                    is_admin = true;
                }

                //First Validating if the User Input is valid:
                if (username.equals("")) {
                    //Opening the Message Dialog using the JOptionPane to display a message to the user:
                    JOptionPane.showMessageDialog(null, "Please Enter UserName!");
                } else if (password.equals("")) {
                    //Opening the Message Dialog using the JOptionPane to display a message to the user:
                    JOptionPane.showMessageDialog(null, "Please Enter Password!");
                } else {
                    // Creating an instance of the Connection class for database operations:
                    Connection db_connection = DatabaseConnector.connect_to_database();

                    try {

                        //Creates a Statement Class's Instance to be used to perform queries:
                        Statement statement_lib = db_connection.createStatement();
                        statement_lib.executeUpdate("USE library_management");
                        statement_lib.executeUpdate("INSERT INTO users(username,password,is_admin) VALUES ('" + username + "','" + password + "'," + is_admin + ")");

                        //Opening the Message Dialog using the JOptionPane to display a message to the user:
                        JOptionPane.showMessageDialog(null, "User added!");

                        // This destroys the add_new_user_frame window and gets it cleaned up by OS
                        add_new_user_frame.dispose();

                    } catch (SQLException e1) {
                        //Opening the Message Dialog using the JOptionPane to display an error message to the user:
                        JOptionPane.showMessageDialog(null, e1);
                    }
                }
            }

        });


        //Adding all the GUI components to the JFrame window:
        add_new_user_frame.add(create_button_field);
        add_new_user_frame.add(user_radio_button);
        add_new_user_frame.add(admin_radio_button);
        add_new_user_frame.add(username_label);
        add_new_user_frame.add(password_label);
        add_new_user_frame.add(username_text_field);
        add_new_user_frame.add(password_text_field);
        add_new_user_frame.setSize(350, 200);
        add_new_user_frame.setLayout(null);
        add_new_user_frame.setVisible(true);
        add_new_user_frame.setLocationRelativeTo(null);
    }
}
