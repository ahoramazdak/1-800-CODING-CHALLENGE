package com.aconex.phonewords.entities;

/**
 * Wraps a {@link String} number.
 */
public class DigitNumber {
    private final String number;

    public DigitNumber(final String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
    public String getASCIIDigits() {
        return number.replaceAll("[^0-9]+", "");
    }
    public boolean containNumber(DigitNumber anotherNumber){
        return getASCIIDigits().contains(anotherNumber.getASCIIDigits());
    }
}
