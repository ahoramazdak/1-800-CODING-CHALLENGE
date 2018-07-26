/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aconex.phonewords.keypad;

import com.aconex.phonewords.entities.DigitNumber;
import com.aconex.phonewords.entities.EncodedNumber;

/**
 *
 * @author amin
 */
public class KeypadNumber {
    private final DigitNumber dNumber;
    private EncodedNumber eNumber;
     /**
     * Removes all the characters from the the number except for ASCII numbers
     * [0-9].
     *
     * @param dNumber
     */
    public KeypadNumber(DigitNumber dNumber) {
        this.dNumber=dNumber;
    }
    public KeypadNumber(EncodedNumber encNumber) {
        this.eNumber=encNumber;
        dNumber=eNumber.getDigitNumber();
    }
    public DigitNumber getDigitNumber(){
        return dNumber;
    }
    public boolean hasWord(EncodedNumber encodeNumber) {
        return dNumber.containNumber(encodeNumber.getDigitNumber());
    }
    
}
