package com.ideas2It.util.customException;

/*
 * This class is used to create custom exception
 *
 * @version 1.0
 * @author arunkumar			
 */
public class VehicleManagementException extends Exception {
	/*
	 * This method convert String message as a Exception
	 * 
	 * @param message
	 */    
	public VehicleManagementException(String message) {
        super(message);
    }
}