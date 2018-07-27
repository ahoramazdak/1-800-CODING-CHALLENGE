package com.aconex.phonewords.entities;

import com.aconex.phonewords.utils.Utility;
import java.util.Objects;

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
        return number.replaceAll(Utility.regex_not_digit, "");
    }
    public boolean containNumber(DigitNumber anotherNumber){
        return getASCIIDigits().contains(anotherNumber.getASCIIDigits());
    }

    @Override
    public boolean equals(Object obj) {
        return this.getASCIIDigits().equals(((DigitNumber)obj).getASCIIDigits());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.number);
        return hash;
    }
    
}
