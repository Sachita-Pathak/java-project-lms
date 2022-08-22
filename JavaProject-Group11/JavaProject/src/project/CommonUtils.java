package project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * This is the Class that provides Common Operational methods for the GUI Project.
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
public class CommonUtils {

    /**
     * This method add_days(Date date, int days) takes a java.util.Date value as its first parameter
     * and the integer number of days as its second parameter. It then adds the number of days to the
     * date and then returns the new date.
     * @param date This is the first paramter to add_days method
     * @param days This is the second paramter to add_days method
     * @return Date This returns the date after the additions of days parameter to the date parameter.
     */
    public static java.util.Date add_days(java.util.Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return c.getTime();
    }

    /**
     * This method checks the validity of the date String and its format.
     * @param dateStr This is the parameter to the isValid method in isValid method. It is supposed to be a date.
     * @return boolean This returns if the dateStr passed to the method is a date string in "dd-MM-YYYY" format or not
     */
    public static boolean isValid(String dateStr) {
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * This method checks if the input parameter in the string format has integer value or not.
     * @param int_str THis is the string parameter to this method.
     * @return boolean This return true if the string is an integer number.
     */
    public static boolean isInteger(String int_str) {
        try{
            int int_val = Integer.parseInt(int_str);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    /**
     * This method checks if the input parameter in the string format has double value or not.
     * @param double_str This is the string parameter to this method.
     * @return boolean This return true if the string is an double number.
     */
    public static boolean isDouble(String double_str) {
        try{
            double double_val = Double.parseDouble(double_str);
            return true;
        } catch (Exception ex){
            return false;
        }
    }
}
