package project;

/**
 * This is the Class with the Main method of the Following Project:
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

public class LMS {

    /**
     * This is the main method of the Library Management System Project
     * @param args Needs one parameter i.e. password of the local host of MySQL database
     */
    public static void main(String[] args) {

        if(args.length == 0){
            System.out.println("One parameter is required i.e: the password of your local database (MySQL server)");
        } else {

            String mySqlPassword = args[0];
            DatabaseConnector.setDatabasePassword(mySqlPassword);

            System.out.println("Password set! Now, loading the Login page");

            // This Static Method user_login_page() of LoginPage Class opens the initial login page of the GUI Project:
            LoginPage.user_login_page();
        }

    }
}
