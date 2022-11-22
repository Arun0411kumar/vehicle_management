package com.ideas2It.util.enumeration;

/**
 * These are the brands are available for two wheeler
 *
 * @version 1.0
 * @author arunkumar
 */  
public enum Brand {
    HONDA("1"),
    PULSER("2"),
    KAWASAKI("3"),
    DUCATI("4");
    
    private String value;
    Brand(String value) {
        this.value = value;
    }
     
    /**
     * Based on user given input It's return brand
     *
     * @param userChoice
     * @return it's return fueltype
     */
    public static Brand getBrand(String userChoice) {
        for (Brand brand: Brand.values()) {
            if (brand.value.equals(userChoice)) {
                return brand;
            }
        }
        return null; 
    }
}