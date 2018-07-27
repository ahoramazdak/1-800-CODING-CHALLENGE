/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aconex.phonewords.entities;

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
public class EncodedNumberTest {
    
    public EncodedNumberTest() {
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
     * Test of validateEncodeNumber method, of class EncodedNumber.
     */
    @Test
    public void testValidateEncodeNumber_0args() {
        System.out.println("validateEncodeNumber");
        EncodedNumber instance = new EncodedNumber("$#%amin*&$*_1360");
        instance.validateEncodeNumber();
        assertEquals(instance.getValidEncodedNumber(), "AMIN1360");
    }

    /**
     * Test of validateEncodeNumber method, of class EncodedNumber.
     */
    @Test
    public void testValidateEncodeNumber_String() {
        System.out.println("validateEncodeNumber");
        String regExpValidator = "[\\W\\d]";
        EncodedNumber instance = new EncodedNumber("$#%amin*&$*_1360");
        instance.validateEncodeNumber(regExpValidator);
        assertEquals(instance.getValidEncodedNumber(), "AMIN_");
    }

    /**
     * Test of getEncodedNumber method, of class EncodedNumber.
     */
    @Test
    public void testGetEncodedNumber() {
        System.out.println("getEncodedNumber");
        EncodedNumber instance = new EncodedNumber("$#%amin*&$*_1360");
        String expResult = "$#%amin*&$*_1360";
        String result = instance.getEncodedNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of getValidEncodedNumber method, of class EncodedNumber.
     */
    @Test
    public void testGetValidEncodedNumber() {
        System.out.println("getValidEncodedNumber");
        EncodedNumber instance = new EncodedNumber("$#%amin*&$*_1360");
        String expResult = "AMIN1360";
        String result = instance.getValidEncodedNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDigitNumber method, of class EncodedNumber.
     */
    @Test
    public void testGetDigitNumber() {
        System.out.println("getDigitNumber");
        EncodedNumber instance = new EncodedNumber("$#%amin*&$*_1360");
        DigitNumber expResult = new DigitNumber("26461360");
        DigitNumber result = instance.getDigitNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of toNormalPhoneNumber method, of class EncodedNumber.
     */
    @Test
    public void testToNormalPhoneNumber() {
        System.out.println("toNormalPhoneNumber");
        EncodedNumber instance = new EncodedNumber("$#%amin*&$*_1360");
        String expResult = "26461360";
        String result = instance.toNormalPhoneNumber();
        assertEquals(expResult, result);
    }
    
}
