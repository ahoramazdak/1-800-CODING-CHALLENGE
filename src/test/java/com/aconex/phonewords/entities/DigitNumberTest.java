/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aconex.phonewords.entities;

import java.util.Objects;
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
public class DigitNumberTest {
    
    public DigitNumberTest() {
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
     * Test of getNumber method, of class DigitNumber.
     */
    @Test
    public void testGetNumber() {
        System.out.println("getNumber");
        DigitNumber instance = new DigitNumber(",0.%$-\"123_af4");
        String expResult = ",0.%$-\"123_af4";
        String result = instance.getNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of getASCIIDigits method, of class DigitNumber.
     */
    @Test
    public void testGetASCIIDigits() {
        System.out.println("getASCIIDigits");
        DigitNumber instance = new DigitNumber(",0.%$-\"123_af4");
        String expResult = "01234";
        String result = instance.getASCIIDigits();
        assertEquals(expResult, result);
    }

    /**
     * Test of containNumber method, of class DigitNumber.
     */
    @Test
    public void testContainNumber() {
        System.out.println("containNumber");
        DigitNumber instance =new DigitNumber(",0.%$-\"123_af4");
        DigitNumber anotherNumber = new DigitNumber("$-\"kdf0$%(,.%*()_af1");
        boolean expResult = true;
        boolean result = instance.containNumber(anotherNumber);
        assertEquals(expResult, result);
     }
    /**
     * Test of containNumber method, of class DigitNumber not contain a number.
     */
    @Test
    public void testNotContainNumber() {
        System.out.println("not containNumber");
        DigitNumber instance =new DigitNumber(",0.%$-\"123_af4");
        DigitNumber anotherNumber = new DigitNumber(",0.%$-\"kdf$%(*()_af2");
        boolean expResult = false;
        boolean result = instance.containNumber(anotherNumber);
        assertEquals(expResult, result);
      }
    /**
     * Test of equals method, of class DigitNumber .
     */
    @Test
    public void testequal() {
        System.out.println("equal");
        DigitNumber instance =new DigitNumber(",0.%$-\"123_af4");
        DigitNumber anotherNumber = new DigitNumber(",0.%$-\"1kdf$%(*()_af2\'3dfgdsg4");
        boolean expResult = true;
        boolean result = instance.equals(anotherNumber);
        assertEquals(expResult, result);
      }

 
    /**
     * Test of hashCode method, of class DigitNumber.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        DigitNumber instance = new DigitNumber(",0.%$-\"123_af4");
        int expResult = 67 * 7 + Objects.hashCode(",0.%$-\"123_af4");
        int result = instance.hashCode();
        assertEquals(expResult, result);
     }
    
}
