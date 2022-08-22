package project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * This is the Class with the that opens the Update Book page of the GUI Project.
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
public class UpdateBook {

    /**
     * This method update_book_gui() opens a JFrame Box and lets the user enter the following details:
     * 1. Book ID(BID)
     * 2. Book Name
     * 3. Genre
     * 4. Price
     * Validations are put there to ensure that Correct type of input is given by the user.
     * i.e. Book ID must be an integer, Book Name and Genre must be a non-empty string and Price must be a double value
     * If the user input is valid then only it updates the values of the book into the database.
     */
    public static void update_book_gui(){

        //Creating the new JFrame: Enter Books Details
        JFrame update_existing_books_frame = new JFrame("Enter Books Details");
        JLabel book_id_label, book_name_label, genre_label, price_label;

        // Adding the label: Book ID(BID)
        book_id_label = new JLabel("Book ID(BID)");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        book_id_label.setBounds(30, 15, 100, 30);

        // Adding the label: Book Name
        book_name_label = new JLabel("Book Name");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        book_name_label.setBounds(30, 50, 100, 30);

        // Adding the label: Genre
        genre_label = new JLabel("Genre");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        genre_label.setBounds(30, 85, 100, 30);

        // Adding the label: Price
        price_label = new JLabel("Price");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        price_label.setBounds(30, 120, 100, 30);

        // Adding the JTextField for: Book ID(BID)
        JTextField book_id_text_field = new JTextField();

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        book_id_text_field.setBounds(120, 15, 200, 30);

        // Adding the JTextField for: Book Name
        JTextField book_name_text_field = new JTextField();

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        book_name_text_field.setBounds(120, 50, 200, 30);

        // Adding the JTextField for: Genre
        JTextField genre_text_field = new JTextField();

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        genre_text_field.setBounds(120, 85, 200, 30);

        // Adding the JTextField for: Price
        JTextField price_text_field = new JTextField();

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        price_text_field.setBounds(120, 120, 200, 30);

        // Adding the JButton for the operation: Update Books
        JButton update_book_button = new JButton("Update Books");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        update_book_button.setBounds(130, 170, 120, 25);
        update_book_button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String book_id = book_id_text_field.getText().trim();
                String book_name = book_name_text_field.getText().trim();
                String book_genre = genre_text_field.getText().trim();
                String book_price = price_text_field.getText().trim();

                // Creating an instance of the Connection class for database operations:
                Connection db_connection = DatabaseConnector.connect_to_database();

                try {

                    //Creates a Statement Class's Instance to be used to perform queries:
                    Statement statement_lib = db_connection.createStatement();

                    statement_lib.executeUpdate("USE library_management");

                    PreparedStatement prepared_statement = db_connection.prepareStatement("SELECT * FROM books WHERE book_id=?",
                            ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    prepared_statement.setString(1, book_id);

                    //Executing the query:
                    ResultSet resultSet = prepared_statement.executeQuery();

                    boolean isBookIdInt = CommonUtils.isInteger(book_id);
                    boolean isPriceDouble = CommonUtils.isDouble(book_price);

                    //First Validating if the User Input is valid:
                    if (!isBookIdInt) {
                        //Opening the Message Dialog using the JOptionPane to display a message to the user:
                        JOptionPane.showMessageDialog(null, "Book Id must be an integer!");
                    } else if (!isPriceDouble) {
                        //Opening the Message Dialog using the JOptionPane to display a message to the user:
                        JOptionPane.showMessageDialog(null, "Price must be a valid number !");
                    } else if (resultSet.next() == false) {
                        //Opening the Message Dialog using the JOptionPane to display a message to the user:
                        JOptionPane.showMessageDialog(null, "No such Book with that Book Id!");
                    } else if (book_name.equals("")) {
                        //Opening the Message Dialog using the JOptionPane to display a message to the user:
                        JOptionPane.showMessageDialog(null, "Please enter the Book Name!");
                    } else if (book_genre.equals("")) {
                        //Opening the Message Dialog using the JOptionPane to display a message to the user:
                        JOptionPane.showMessageDialog(null, "Please enter the Book Genre!");
                    } else {
                        statement_lib.executeUpdate("UPDATE books SET book_name='" + book_name + "', genre='" + book_genre + "', price='" + book_price + "' WHERE book_id = " + book_id);

                        //Opening the Message Dialog using the JOptionPane to display a message to the user:
                        JOptionPane.showMessageDialog(null, "Book Updated!");

                        // This destroys the update_existing_books_frame window and gets it cleaned up by OS
                        update_existing_books_frame.dispose();
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
        update_existing_books_frame.add(book_id_label);
        update_existing_books_frame.add(book_name_label);
        update_existing_books_frame.add(genre_label);
        update_existing_books_frame.add(price_label);
        update_existing_books_frame.add(book_id_text_field);
        update_existing_books_frame.add(book_name_text_field);
        update_existing_books_frame.add(genre_text_field);
        update_existing_books_frame.add(price_text_field);
        update_existing_books_frame.add(update_book_button);
        update_existing_books_frame.setSize(350, 250);
        update_existing_books_frame.setLayout(null);
        update_existing_books_frame.setVisible(true);
        update_existing_books_frame.setLocationRelativeTo(null);
    }
}
