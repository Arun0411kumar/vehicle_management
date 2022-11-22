package com.ideas2It.util;

import java.util.regex.Pattern;

import com.ideas2It.util.constant.Constants;
import com.ideas2It.util.customException.VehicleManagementException;

/**
 * The validation class it's validate given input
 * simply validate the user given details
 *
 * @version 1.0
 * @author arunkumar
 */
public class Validation {

    /**
     * It's get collection of alphabet and it's return valid or not.
     *
     * @param alphabet 
     * @return given alphabet is valid or not.
     */
    public static boolean isValidAlphabet(String alphabet) {
        return Pattern.matches("[a-zA-Z]*\\s*[a-zA-Z]*", alphabet);
    }

    /**
     * It gets year and it's return valid date.
     *
     * @param calender  
     * @return valid date or invalid message.
     */
    public static String validateDate(String calender) throws VehicleManagementException {
        String date[] = calender.split("/");
        String dateOfManufacture = Constants.EMPTY_STRING;
        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);
        if (1 == month || 3 == month || 5 == month || 7 == month  
            || 8 == month || 10 == month || 12 == month) {
            if (0 < day && 31 >= day && 1980 <= year) {
            	dateOfManufacture = calender;
            } 
        } else if (2 == month) {
            if (0 == year % 4) {
                if (0 == year % 100) {
                    if (0 == year % 400) {
                        if (0 < day && 29 >= day && 1980 <= year) {
                        	dateOfManufacture = calender;
                        }
                    } else if (0 < day && 28 >= day && 1980 <= year) {
                    	dateOfManufacture = calender;
                    }
                } else if (0 < day && 29 >= day && 1980 <= year) {
                	dateOfManufacture = calender;
                }
            } else if (0 < day && 28 >= day && 1980 <= year) {
            	dateOfManufacture = calender;               
            }
        } else if (4 == month || 6 == month || 9 == month || 11 == month) {
            if (0 < day && 30 >= day && 1980 <= year) {
            	dateOfManufacture = calender;
            }
        } else {
            throw new VehicleManagementException(Constants.INVALID);  
        }
        return dateOfManufacture;
    }
}











    