package com.ideas2It.util.logger;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * This class is used to store the all messages
 * in the log fill 
 *
 * @version 1.0
 * @author arunkumar
 */ 
public class VehicleManagementLogger {
    private static Logger logger = LogManager.getLogger(VehicleManagementLogger.class);
   
    /**
     * This method was print the debug and stored it.
     *
     * @param message
     */ 
    public static void displayVehicleDebug(String message) {
        logger.debug(message);
    }

    /**
     * This method was print the information and stored it.
     *
     * @param message
     */ 
    public static void displayVehicleInfo(String message) {
        logger.info(message);
    }

    /**
     * This method was print the error and stored it.
     *
     * @param exception
     */ 
    public static void displayVehicleError(Exception exception) {
        logger.error(exception);
    }

    /**
     * This method was print the error and stored it.
     *
     * @param exception
     * @param message
     */ 
    public static void displayVehicleError(Exception exception, String message) {
        logger.error(message, exception);
    }
}