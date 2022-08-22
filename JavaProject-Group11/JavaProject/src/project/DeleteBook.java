package project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * This is the Class with the that opens the Delete Book page of the GUI Project.
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
public class DeleteBook {

    /**
     * This method delete_book_gui() opens a JFrame Box and lets the user enter the following details:
     * 1. Book ID(BID)
     * 2. Book Name
     * Validations are put there to ensure that Correct type of input is given by the user.
     * i.e. Book ID(BID) and Book Name must be a non-empty string and Book ID(BID) should also be an integer
     * If the user input is valid then only it deletes the record with that book id from the database.
     */
    public static void delete_book_gui(){

        //Creating the new JFrame: Enter Books Details
        JFrame delete_book_frame = new JFrame("Enter Books Details");
        JLabel book_id_label, book_name_label;

        // Adding the label: Book ID(BID)
        book_id_label = new JLabel("Book ID(BID)");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        book_id_label.setBounds(30, 15, 100, 30);

        // Adding the label: Book Name
        book_name_label = new JLabel("Book Name");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        book_name_label.setBounds(30, 50, 100, 30);

        // Adding the JTextField for: Book ID(BID)
        JTextField book_id_text_field = new JTextField();

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        book_id_text_field.setBounds(120, 15, 200, 30);

        // Adding the JTextField for: Book Name
        JTextField book_name_text_field = new JTextField();

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        book_name_text_field.setBounds(120, 50, 200, 30);

        // Adding the JButton for the operation: Delete Books
        JButton delete_book_button = new JButton("Delete Books");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        delete_book_button.setBounds(110, 170, 120, 25);
        delete_book_button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String book_id = book_id_text_field.getText().trim();
                String book_name = book_name_text_field.getText().trim();

                // Creating an instance of the Connection class for database operations:
                Connection db_connection = DatabaseConnector.connect_to_database();

                try {
                    boolean isIdInteger = CommonUtils.isInteger(book_id);

                    //First Validating if the User Input is valid:
                    if (book_id.equals("")) {
                        //Opening the Message Dialog using the JOptionPane to display a message to the user:
                        JOptionPane.showMessageDialog(null, "Please Enter the Book ID!");
                    } else if (!isIdInteger) {
                        //Opening the Message Dialog using the JOptionPane to display a message to the user:
                        JOptionPane.showMessageDialog(null, "Book id needs to be an integer!");
                    } else if (book_name.equals("")) {
                        //Opening the Message Dialog using the JOptionPane to display a message to the user:
                        JOptionPane.showMessageDialog(null, "Please Enter the Book Name!");
                    } else {

                        //Creates a Statement Class's Instance to be used to perform queries:
                        Statement statement_lib = db_connection.createStatement();

                        statement_lib.executeUpdate("USE library_management");

                        PreparedStatement prepared_statement = db_connection.prepareStatement("SELECT * FROM books WHERE book_id=? and book_name=?",
                                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        prepared_statement.setString(1, book_id);
                        prepared_statement.setString(2, book_name);

                        //Executing the query:
                        ResultSet resultSet = prepared_statement.executeQuery();

                        if (resultSet.next() == false) {
                            //Opening the Message Dialog using the JOptionPane to display a message to the user:
                            JOptionPane.showMessageDialog(null, "No such Book with that Book Id and Book Name!");
                        } else {
                            statement_lib.executeUpdate("DELETE FROM books WHERE book_id = " + book_id + " and book_name = '" + book_name + "'");

                            //Opening the Message Dialog using the JOptionPane to display a message to the user:
                            JOptionPane.showMessageDialog(null, "Book Deleted!");

                            // This destroys the delete_book_frame window and gets it cleaned up by OS
                            delete_book_frame.dispose();

                        }
                    }
                } catch (SQLException e1) {
                    //Opening the Message Dialog using the JOptionPane to display an error message to the user:
                    JOptionPane.showMessageDialog(null, e1);
                } catch (Exception ex) {
                    //Opening the Message Dialog using the JOptionPane to display an error message to the user:
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }

        });

        //Adding all the GUI components to the JFrame window:
        delete_book_frame.add(book_id_label);
        delete_book_frame.add(book_name_label);
        delete_book_frame.add(book_id_text_field);
        delete_book_frame.add(book_name_text_field);
        delete_book_frame.add(delete_book_button);
        delete_book_frame.setSize(350, 250);
        delete_book_frame.setLayout(null);
        delete_book_frame.setVisible(true);
        delete_book_frame.setLocationRelativeTo(null);
    }
}
