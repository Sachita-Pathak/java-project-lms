package project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the Class with the that opens the Admin Role page with its options of operations of the GUI Project.
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
public class AdminPage {

    /**
     * This method menu_for_admin_role() is opened when a user with admin role logs in and
     * then this opens a JFrame Box and has the following buttons as options:
     * -- Books Operation:-
     * 1. View Books
     * 2. Add Book
     * 3. Update Books
     * 4. Delete Books
     * -- Users Operation:-
     * 5. View Users
     * 6. Add User
     * 7. Update Users
     * 8. Delete Users
     * -- Library Operation:-
     * 9. View Issued Books
     * 10. Issue Book
     * 11. Return Book
     * User Will be able to click on the above buttons to do their own choice of operations.
     */
    public static void menu_for_admin_role() {

        //Creating the new JFrame: Admin Functions
        JFrame admin_functions_frame = new JFrame("Admin Functions");

        //>>>>>>>>>>>>>>>>>>>>>>>>BOOKS OPERATION<<<<<<<<<<<<<<<<<<<<<<<<<<<//
        // Adding the label: Books Operation
        JLabel books_operation_label = new JLabel("Books Operation:");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        books_operation_label.setBounds(20, 20, 500, 25);

        // Adding the JButton for the operation: View Books
        JButton view_button = new JButton("View Books");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        view_button.setBounds(20, 60, 160, 25);
        view_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewBooks.view_books_gui();
            }
        });

        // Adding the JButton for the operation: Add Book
        JButton add_book_button = new JButton("Add Book");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        add_book_button.setBounds(190, 60, 120, 25);
        add_book_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddBook.add_book_gui();
            }
        });

        // Adding the JButton for the operation: Update Books
        JButton update_books_button = new JButton("Update Books");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        update_books_button.setBounds(320, 60, 120, 25);
        update_books_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UpdateBook.update_book_gui();
            }
        });

        // Adding the JButton for the operation: Delete Books
        JButton delete_books_button = new JButton("Delete Books");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        delete_books_button.setBounds(450, 60, 120, 25);
        delete_books_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteBook.delete_book_gui();
            }
        });

        //>>>>>>>>>>>>>>>>>>>>>>>>USERS OPERATION<<<<<<<<<<<<<<<<<<<<<<<<<<<//
        // Adding the label: Users Operation
        JLabel users_operation_label = new JLabel("Users Operation:");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        users_operation_label.setBounds(20, 100, 500, 25);

        // Adding the JButton for the operation: View Users
        JButton view_users_button = new JButton("View Users");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        view_users_button.setBounds(20, 140, 160, 25);
        view_users_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewUsers.view_users_gui();
            }
        });

        // Adding the JButton for the operation: Add User
        JButton add_user_button = new JButton("Add User");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        add_user_button.setBounds(190, 140, 120, 25);
        add_user_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddUsers.add_users_gui();
            }
        });

        // Adding the JButton for the operation: Update Users
        JButton update_users_button = new JButton("Update Users");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        update_users_button.setBounds(320, 140, 120, 25);
        update_users_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UpdateUsers.update_users_gui();
            }
        });

        // Adding the JButton for the operation: Delete Users
        JButton delete_users_button = new JButton("Delete Users");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        delete_users_button.setBounds(450, 140, 120, 25);
        delete_users_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteUsers.delete_users_gui();
            }
        });

        //>>>>>>>>>>>>>>>>>>>>>>>>LIBRARY OPERATION<<<<<<<<<<<<<<<<<<<<<<<<<<<//
        // Adding the label: Library Operation
        JLabel lib_operation_label = new JLabel("Library Operation:");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        lib_operation_label.setBounds(20, 180, 500, 25);

        // Adding the JButton for the operation: View Issued Books
        JButton issued_button = new JButton("View Issued Books");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        issued_button.setBounds(20, 220, 160, 25);
        issued_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewIssuedBooks.view_issued_books_gui();
            }
        });

        // Adding the JButton for the operation: Issue Book
        JButton issue_book_button = new JButton("Issue Book");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        issue_book_button.setBounds(190, 220, 120, 25);
        issue_book_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IssueBook.issue_book_gui();
            }
        });

        // Adding the JButton for the operation: Return Book
        JButton return_book_button = new JButton("Return Book");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        return_book_button.setBounds(320, 220, 120, 25);
        return_book_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ReturnBook.return_book_gui();
            }
        });


        //Adding all the GUI components to the JFrame window:

        //Books Operations:
        admin_functions_frame.add(books_operation_label);
        admin_functions_frame.add(update_books_button);
        admin_functions_frame.add(delete_books_button);
        admin_functions_frame.add(add_book_button);
        admin_functions_frame.add(view_button);

        //Users Operation:
        admin_functions_frame.add(users_operation_label);
        admin_functions_frame.add(view_users_button);
        admin_functions_frame.add(add_user_button);
        admin_functions_frame.add(update_users_button);
        admin_functions_frame.add(delete_users_button);

        //Library Operations:
        admin_functions_frame.add(lib_operation_label);
        admin_functions_frame.add(return_book_button);
        admin_functions_frame.add(issue_book_button);
        admin_functions_frame.add(issued_button);
        admin_functions_frame.setSize(600, 300);
        admin_functions_frame.setLayout(null);
        admin_functions_frame.setVisible(true);
        admin_functions_frame.setLocationRelativeTo(null);
        //Configuring such that, JFrame Window gets closed on clicking on the close button:
        admin_functions_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
