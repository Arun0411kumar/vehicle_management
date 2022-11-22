package com.ideas2It.util.enumeration;

/**
 * These are the vehicle types are available for two wheeler
 *
 * @version 1.0
 * @author arunkumar
 */  
public enum Type {
    GEAR("1"),
    NONGEAR("2"),
    SCOTTER("3");
    
    private String value;
    Type(String value) {
        this.value = value;
    }
     
    /**
     * Based on user given input It's return brand
     *
     * @param userChoice
     * @return it's return fueltype
     */
    public static Type getType(String userChoice) {
        for (Type type: Type.values()) {
            if (type.value.equals(userChoice)) {
                return type;
            }
        }
        return null; 
    }
}