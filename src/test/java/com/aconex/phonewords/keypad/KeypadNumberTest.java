/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aconex.phonewords.keypad;

import com.aconex.phonewords.entities.DigitNumber;
import com.aconex.phonewords.entities.EncodedNumber;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author amin
 */
public class KeypadNumberTest {
    
    public KeypadNumberTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDigitNumber method, of class KeypadNumber.
     */
    @Test
    public void testGetDigitNumber() {
        System.out.println("getDigitNumber");
        KeypadNumber instance = new KeypadNumber(new DigitNumber("%7%22%2401^^^$$$"));
        DigitNumber expResult = new DigitNumber("dhgkjh7fsf22$&&$2&&4HFS$01");
        DigitNumber result = instance.getDigitNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of hasWord method, of class KeypadNumber.
     */
    @Test
    public void testHasWord() {
        System.out.println("hasWord");
        EncodedNumber encodeNumber = new EncodedNumber("$$$a#%M%i_N$@");
        KeypadNumber instance = new KeypadNumber(new DigitNumber("dlfgj2ii6_4jg6"));
        boolean expResult = true;
        boolean result = instance.hasWord(encodeNumber);
        assertEquals(expResult, result);
     }
    
}
