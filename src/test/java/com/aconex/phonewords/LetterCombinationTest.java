/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aconex.phonewords;

import com.aconex.phonewords.entities.PhoneWordsList;
import java.util.Collection;
import java.util.Set;
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
public class LetterCombinationTest {
    
    public LetterCombinationTest() {
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
     * Test of getWords method, of class LetterCombination.
     */
    @Test
    public void testGetWords() {
        System.out.println("getWords");
        LetterCombination instance = new LetterCombination();
        Set<String> expResult = null;
        Set<String> result = instance.getWords();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWords method, of class LetterCombination.
     */
    @Test
    public void testSetWords() {
        System.out.println("setWords");
        Collection<String> aWords = null;
        LetterCombination instance = new LetterCombination();
        instance.setWords(aWords);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of phoneWordsGenerator method, of class LetterCombination.
     */
    @Test
    public void testPhoneWordsGenerator() {
        System.out.println("phoneWordsGenerator");
        long lTimeOut = 0L;
        LetterCombination instance = new LetterCombination();
        instance.phoneWordsGenerator(lTimeOut);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findWordsInPhoneNumbers method, of class LetterCombination.
     */
    @Test
    public void testFindWordsInPhoneNumbers() {
        System.out.println("findWordsInPhoneNumbers");
        String dicFilePath = "";
        LetterCombination instance = new LetterCombination();
        instance.findWordsInPhoneNumbers(dicFilePath);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readPhoneNumber method, of class LetterCombination.
     */
    @Test
    public void testReadPhoneNumber() {
        System.out.println("readPhoneNumber");
        LetterCombination instance = new LetterCombination();
        instance.readPhoneNumber();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of combi method, of class LetterCombination.
     */
    @Test
    public void testCombi() {
        System.out.println("combi");
        String pre = "";
        String inp = "";
        String succ = "";
        LetterCombination instance = new LetterCombination();
        PhoneWordsList expResult = null;
        PhoneWordsList result = instance.combi(pre, inp, succ);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of phoneWord method, of class LetterCombination.
     */
    @Test
    public void testPhoneWord() {
        System.out.println("phoneWord");
        String num = "";
        LetterCombination instance = new LetterCombination();
        PhoneWordsList expResult = null;
        PhoneWordsList result = instance.phoneWord(num);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
