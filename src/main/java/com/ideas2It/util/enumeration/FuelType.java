package com.ideas2It.util.enumeration;

/**
 * These are the fuel source are available for two wheeler
 *
 * @version 1.0
 * @author arunkumar
 */  
public enum FuelType {
    PETROL("1"),
    DIESEL("2"),
    BATTERY("3");
    
    private String value;
    FuelType(String value) {
        this.value = value;
    }
     
    /**
     * Based on user given input It's return fueltype
     *
     * @param userChoice
     * @return it's return fueltype
     */
    public static FuelType getFuelType(String userChoice) {
        for (FuelType fuel: FuelType.values()) {
            if (fuel.value.equals(userChoice)) {
                return fuel;
            }
        }
        return null; 
    }
}






 

    