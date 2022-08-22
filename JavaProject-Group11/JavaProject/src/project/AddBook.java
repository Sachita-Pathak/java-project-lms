package project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is the Class with the that opens the Add Book page of the GUI Project.
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
public class AddBook {

    /**
     * This method add_book_gui() opens a JFrame Box and lets the user enter the following details:
     * 1. Book Name
     * 2. Genre
     * 3. Price
     * Validations are put there to ensure that Correct type of input is given by the user.
     * i.e. Book Name and Genre must be a non-empty string and Price must be a double value
     * If the user input is valid then only it adds the values into the database.
     */
    public static void add_book_gui(){

        //Creating the new JFrame: Enter Book Details
        JFrame add_new_book_frame = new JFrame("Enter Book Details");
        JLabel book_name_label, genre_label, price_label;

        // Adding the label: Book Name
        book_name_label = new JLabel("Book Name");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        book_name_label.setBounds(30, 15, 100, 30);

        // Adding the label: Genre
        genre_label = new JLabel("Genre");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        genre_label.setBounds(30, 53, 100, 30);

        // Adding the label: Price
        price_label = new JLabel("Price");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        price_label.setBounds(30, 90, 100, 30);

        // Adding the JTextField for: Book Name
        JTextField book_name_text_field = new JTextField();

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        book_name_text_field.setBounds(110, 15, 200, 30);

        // Adding the JTextField for: Genre
        JTextField genre_text_field = new JTextField();

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        genre_text_field.setBounds(110, 53, 200, 30);

        // Adding the JTextField for: Price
        JTextField price_text_field = new JTextField();

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        price_text_field.setBounds(110, 90, 200, 30);

        // Adding the JButton for the operation: Add Book
        JButton create_button_field = new JButton("Add Book");

        // Adding x, y axis and width, height to the GUI components as below for the UI Design
        create_button_field.setBounds(130, 130, 100, 25);
        create_button_field.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String book_name = book_name_text_field.getText().trim();
                String genre = genre_text_field.getText().trim();
                String price = price_text_field.getText().trim();

                //First Validating if the User Input is valid:
                if (genre.equals("")) {
                    //Opening the Message Dialog using the JOptionPane to display a message to the user:
                    JOptionPane.showMessageDialog(null, "Please enter the Genre.");
                } else if (book_name.equals("")) {
                    //Opening the Message Dialog using the JOptionPane to display a message to the user:
                    JOptionPane.showMessageDialog(null, "Please enter the Book name.");
                } else if (!CommonUtils.isDouble(price)){
                    //Opening the Message Dialog using the JOptionPane to display a message to the user:
                    JOptionPane.showMessageDialog(null, "Price must be a valid number.");
                } else {
                    // Creating an instance of the Connection class for database operations:
                    Connection db_connection = DatabaseConnector.connect_to_database();

                    try {

                        //Creates a Statement Class's Instance to be used to perform queries:
                        Statement statement_lib = db_connection.createStatement();
                        statement_lib.executeUpdate("USE library_management");
                        statement_lib.executeUpdate("INSERT INTO books (book_name,genre,price) VALUES ('" + book_name + "','" + genre + "'," + price + ")");

                        //Opening the Message Dialog using the JOptionPane to display a message to the user:
                        JOptionPane.showMessageDialog(null, "Book added!");

                        // This destroys the add_new_book_frame window and gets it cleaned up by OS
                        add_new_book_frame.dispose();

                    } catch (SQLException e1) {
                        //Opening the Message Dialog using the JOptionPane to display an error message to the user:
                        JOptionPane.showMessageDialog(null, e1);
                    }
                }
            }
        });

        //Adding all the GUI components to the JFrame window:
        add_new_book_frame.add(price_label);
        add_new_book_frame.add(create_button_field);
        add_new_book_frame.add(book_name_label);
        add_new_book_frame.add(genre_label);
        add_new_book_frame.add(book_name_text_field);
        add_new_book_frame.add(genre_text_field);
        add_new_book_frame.add(price_text_field);
        add_new_book_frame.setSize(350, 200);
        add_new_book_frame.setLayout(null);
        add_new_book_frame.setVisible(true);
        add_new_book_frame.setLocationRelativeTo(null);
    }
}
