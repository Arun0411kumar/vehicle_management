package com.ideas2It.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This DateUtil is used to calculate the life time of the vehicle 
 *
 * @version 1.0
 * @author arunkumar
 */
public class DateUtil {
    public static Date currentDate = new Date();

   /**
    * It's Sent valid date or It's throws exception 
    *
    * @param value - date in String data type
    * @return it's return year between current date and manufacturing date
    */ 
   public static Date getDate(String value) throws ParseException {
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");     
       Date date = simpleDateFormat.parse(value); 
       return date;
   }

   /**
    * It's Sent valid date or It's throws exception 
    *
    * @param value - date in String data type
    * @return it's return year between current date and manufacturing date
    */ 
   public static Date convertDate(String value) throws ParseException {
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");     
       Date date = simpleDateFormat.parse(value); 
       return date;
   }

   /**
    * It's calculating life time of the vehicle
    *
    * @param dateOfManufacturing
    * @param currentDate
    * @return it's return year between current date and manufacturing date
    */
    public static int differenceBetweenYear(Date dateOfManufacturing, Date currentDate) {  
        if (null == dateOfManufacturing) {
            return 0;
        }
        long lifeTimeInMiliSecond = currentDate.getTime() - dateOfManufacturing.getTime();
        int lifeTimeInYear = (int)(lifeTimeInMiliSecond / (1000l * 60 * 60 * 24 * 365));
        int lifeTimeInMonth = (int)(lifeTimeInMiliSecond / (1000l * 60 * 60 * 24 * 30));
        int lifeTimeInDay = (int)(lifeTimeInMiliSecond / (1000l * 60 * 60 * 24));
        if (0 == lifeTimeInYear) {
            return lifeTimeInMonth; 
        } else if (0 == lifeTimeInMonth) {
            return lifeTimeInDay;
        }
        return lifeTimeInYear;
    } 

}