package project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the Class with the that opens the User Role's page with the Opens for the operations
 * that the User Role has in this GUI Project.
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
public class UserRolePage {

    /**
     * This method menu_for_user_role() is opened when a user with user role logs in and
     * then this opens a JFrame Box and has the following buttons as options:
     * -- User Role Functions:-
     * 1. View All Books
     * 2. View My Books
     * User Will be able to click on the above buttons to do their own choice of operations.
     * @param user_id This is the parameter of this method which is the user_id of the logged in user.
     */
    public static void menu_for_user_role(String user_id) {

        //Creating the new JFrame: User Role Functions
        JFrame user_role_functions = new JFrame("User Role Functions");

        // Adding the JButton for the operation: View All Books
        JButton view_button = new JButton("View All Books");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        view_button.setBounds(20, 20, 120, 25);
        view_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewBooks.view_books_gui();
            }
        });

        // Adding the JButton for the operation: View My Books
        JButton my_books_list = new JButton("View My Books");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        my_books_list.setBounds(150, 20, 120, 25);
        my_books_list.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewMyBooks.view_my_books_gui(user_id);
            }
        });


        //Adding all the GUI components to the JFrame window:
        user_role_functions.add(my_books_list);
        user_role_functions.add(view_button);
        user_role_functions.setSize(300, 100);
        user_role_functions.setLayout(null);
        user_role_functions.setVisible(true);
        user_role_functions.setLocationRelativeTo(null);
        //Configuring such that, JFrame Window gets closed on clicking on the close button:
        user_role_functions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
