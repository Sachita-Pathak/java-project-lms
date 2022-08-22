package project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * This is the Class with the that opens the initial login page of the GUI Project.
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
public class LoginPage {

    /**
     * This method user_login_page() opens a JFrame Box and lets the user enter the following details:
     * 1. User name
     * 2. Password
     * Validations are put there to ensure that Correct type of input is given by the user.
     * i.e. User name and Password must be a non-empty string
     * If the user input is valid then only it checks of the record match with any user in the database.
     * If the user information that matches the above values is an admin user, then the admin page is loaded
     * Else if it is the User role, then, the User Role Page is loaded.
     * If no value is matched, then Incorrect Username & Password message is displayed in a message box.
     */
    public static void user_login_page() {

        //Creating the new JFrame: User Login Page
        JFrame user_login_frame = new JFrame("User Login Page");
        JLabel username_label, password_label;

        // Adding the label: User name
        username_label = new JLabel("User name");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        username_label.setBounds(35, 16, 100, 31);

        // Adding the label: Password
        password_label = new JLabel("Password");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        password_label.setBounds(35, 51, 100, 31);

        // Adding the JTextField for: User name
        JTextField username_text_field = new JTextField();

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        username_text_field.setBounds(115, 16, 200, 31);

        // Adding the JTextField for: Password
        JPasswordField password_tet_field = new JPasswordField();

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        password_tet_field.setBounds(115, 51, 200, 31);

        // Adding the JButton for the operation: Login
        JButton login_button = new JButton("Login");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        login_button.setBounds(135, 91, 100, 26);
        login_button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                //First Validating if the User Input is valid:
                String user_name = username_text_field.getText().trim();
                String password = password_tet_field.getText().trim();

                if (user_name.equals("")) {
                    //Opening the Message Dialog using the JOptionPane to display a message to the user:
                    JOptionPane.showMessageDialog(null, "Please enter an username");
                } else if (password.equals("")) {
                    //Opening the Message Dialog using the JOptionPane to display a message to the user:
                    JOptionPane.showMessageDialog(null, "Please enter a password");
                } else {
                    // Creating an instance of the Connection class for database operations:
                    Connection db_connection = DatabaseConnector.connect_to_database();
                    try {

                        //Creates a Statement Class's Instance to be used to perform queries:
                        Statement statement_lib = db_connection.createStatement();
                        statement_lib.executeUpdate("USE library_management");

                        PreparedStatement prepared_statement = db_connection.prepareStatement("SELECT * FROM users WHERE username=? AND password=?",
                                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        prepared_statement.setString(1, user_name);
                        prepared_statement.setString(2, password);

                        //Executing the query:
                        ResultSet resultSet = prepared_statement.executeQuery();

                        if (resultSet.next() == false) {

                            //Opening the Message Dialog using the JOptionPane to display an error message to the user:
                            JOptionPane.showMessageDialog(null, "The Username/Password entered did not match!");

                        } else {

                            // This destroys the user_login_frame window and gets it cleaned up by OS
                            user_login_frame.dispose();

                            resultSet.beforeFirst();
                            while (resultSet.next()) {
                                String is_admin = resultSet.getString("is_admin");
                                String user_id = resultSet.getString("user_id");
                                if (is_admin.equals("1")) {
                                    AdminPage.menu_for_admin_role();
                                } else {
                                    String notification = NotificationFetcher.fetch_latest_return_deadline(user_id);
                                    if (!notification.equals("")) {
                                        //Opening the Message Dialog using the JOptionPane to display an error message to the user:
                                        JOptionPane.showMessageDialog(null, notification);
                                    }
                                    UserRolePage.menu_for_user_role(user_id);
                                }
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        password_tet_field.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    login_button.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        username_text_field.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    login_button.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        //Adding all the GUI components to the JFrame window:
        user_login_frame.add(password_tet_field);
        user_login_frame.add(login_button);
        user_login_frame.add(username_text_field);
        user_login_frame.add(username_label);
        user_login_frame.add(password_label);

        user_login_frame.setSize(405, 185);
        user_login_frame.setLayout(null);
        user_login_frame.setVisible(true);
        user_login_frame.setLocationRelativeTo(null);
        //Configuring such that, JFrame Window gets closed on clicking on the close button:
        user_login_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
