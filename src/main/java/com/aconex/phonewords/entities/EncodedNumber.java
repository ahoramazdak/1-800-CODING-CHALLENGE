package com.aconex.phonewords.entities;

import com.aconex.phonewords.utils.Utility;

/**
 * Represents an encoded number and provides specific method implementations.
 */
public final class EncodedNumber {

    /**
     * Encoded representation of the {@link #digitNumber}.
     */
    private final String encodedNumber;
    private final String digitNumber;
    private String validEncodedNumber;
    private String regExpValidator = Utility.regex_valid_phonewords;

    public EncodedNumber(String encodedNumber) {
        this.encodedNumber = encodedNumber;
        validateEncodeNumber();
        digitNumber = toNormalPhoneNumber();
    }

    public void validateEncodeNumber() {
        validEncodedNumber = encodedNumber.replaceAll(regExpValidator, "").toUpperCase();
    }

    public void validateEncodeNumber(String regExpValidator) {
        this.regExpValidator = regExpValidator;
        validateEncodeNumber();
    }

    public String getEncodedNumber() {
        return encodedNumber;
    }

    public String getValidEncodedNumber() {

        return validEncodedNumber;
    }

    public DigitNumber getDigitNumber() {
        return new DigitNumber(digitNumber);
    }

    public String toNormalPhoneNumber() {
        String normal = "";
        for (char c : validEncodedNumber.toUpperCase().toCharArray()) {
            normal += getKeypadNumber(c);
        }
        return normal;
    }

    private char getKeypadNumber(char characterToConvert) {
        if (Character.isDigit(characterToConvert)) {
            return characterToConvert;
        } else {
            switch (characterToConvert) {
                case 'A':
                case 'B':
                case 'C':
                    return '2';
                case 'D':
                case 'E':
                case 'F':
                    return '3';
                case 'G':
                case 'H':
                case 'I':
                    return '4';
                case 'J':
                case 'K':
                case 'L':
                    return '5';
                case 'M':
                case 'N':
                case 'O':
                    return '6';
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                    return '7';
                case 'T':
                case 'U':
                case 'V':
                    return '8';
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                    return '9';
                default:
                    return '?';
            }
        }
    }

}
