package com.ideas2It.util.enumeration;

/**
 * These are the colours are available for two wheeler
 *
 * @version 1.0
 * @author arunkumar
 */
public enum Colours {
    BLUE("1"),
    BLACK("2"),
    YELLOW("3"),
    RED("4"),
    WHITE("5");

    private String value;
    Colours (String value) {
        this.value = value;
    }

   /**
    * Based on user given input it's return colour
    *
    * @param userChoice - specifying choice of colour
    * @return It's return colour based on user choice
    */
    public static Colours getColours(String userChoice) {
        for (Colours colour: Colours.values()) { 
            if (colour.value.equals(userChoice)) {
                return colour;
            }
        }
        return null; 
    }
}