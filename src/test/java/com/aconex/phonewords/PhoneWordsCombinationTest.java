/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aconex.phonewords;

import com.aconex.phonewords.entities.PhoneWordsList;
import com.aconex.phonewords.utils.Utility;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.core.Is.is;
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
public class PhoneWordsCombinationTest {

    public PhoneWordsCombinationTest() {
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
     * Test of getWords method, of class PhoneWordsCombination.
     */
    @Test
    public void testGetWords() {
        System.out.println("getWords");
        PhoneWordsCombination instance = new PhoneWordsCombination();
        instance.setWords(new HashSet<>(Arrays.asList("amin")));
        Set<String> expResult = new HashSet<>(Arrays.asList("amin"));
        Set<String> result = instance.getWords();
        assertEquals(expResult, result);
    }

    /**
     * Test of setWords method, of class PhoneWordsCombination.
     */
    @Test
    public void testSetWords() {
        System.out.println("setWords");
        Collection<String> aWords = new HashSet<>(Arrays.asList("amin", "keikanlou"));
        PhoneWordsCombination instance = new PhoneWordsCombination();
        instance.setWords(aWords);
        Set<String> result = instance.getWords();
        assertEquals(aWords, result);
    }

    /**
     * Test of phoneWordsGenerator method, of class PhoneWordsCombination.
     */
    @Test
    public void testPhoneWordsGenerator() {
        System.out.println("phoneWordsGenerator");
        long lTimeOut = 100L;
        PhoneWordsCombination instance = new PhoneWordsCombination();
        instance.clearPhoneWords();
        LinkedHashMap<String, Collection<String>> phoneWordsGenerator = instance.phoneWordsGenerator(lTimeOut);
        assertThat(phoneWordsGenerator.size(), is(0));
        instance.setWords(new HashSet<>(Arrays.asList("amin")));
        instance.readPhoneNumberFromInp(new ArrayList<>(Arrays.asList("2646")));
        instance.findWordsInPhoneNumbers(Utility.myDic);
        phoneWordsGenerator = instance.phoneWordsGenerator(lTimeOut);
        LinkedHashMap<String, Collection<String>> expectedRes = new LinkedHashMap<>();
        expectedRes.put("2646", new HashSet<>(Arrays.asList("AMIN")));
        assertTrue(phoneWordsGenerator.keySet().size() > 0);
        assertEquals(expectedRes.keySet(), phoneWordsGenerator.keySet());
        assertTrue(phoneWordsGenerator.keySet().contains("2646"));
        assertTrue(expectedRes.get("2646").containsAll(phoneWordsGenerator.get("2646")));

    }

    /**
     * Test of findWordsInPhoneNumbers method, of class PhoneWordsCombination.
     */
    @Test
    public void testFindWordsInPhoneNumbers() {
        System.out.println("findWordsInPhoneNumbers");
        String dicFilePath = Utility.myDic;
        PhoneWordsCombination instance = new PhoneWordsCombination();
        instance.readPhoneNumberFromInp(new ArrayList<>(Arrays.asList("2646")));
        LinkedHashMap<String, Collection<String>> findWordsInPhoneNumbers = instance.findWordsInPhoneNumbers(dicFilePath);
        LinkedHashMap<String, Collection<String>> expectedRes = new LinkedHashMap<>();
        expectedRes.put("2646", new ArrayList<>(Arrays.asList("AMIN")));
        assertTrue(findWordsInPhoneNumbers.keySet().size() > 0);
        assertEquals(expectedRes.keySet(), findWordsInPhoneNumbers.keySet());
        assertTrue(findWordsInPhoneNumbers.keySet().contains("2646"));
        assertTrue(expectedRes.get("2646").containsAll(findWordsInPhoneNumbers.get("2646")));
    }

    /**
     * Test of readPhoneNumber method, of class PhoneWordsCombination.
     */
    @Test
    public void testReadPhoneNumber() {
        System.out.println("readPhoneNumber");
        String phoneNumberFilePath = Utility.phone_number_sample_list;
        PhoneWordsCombination instance = new PhoneWordsCombination();
        Set<String> readPhoneNumber = instance.readPhoneNumber(phoneNumberFilePath);
        assertTrue(readPhoneNumber.containsAll(new ArrayList<>(Arrays.asList("112.1", "5624-8.2", "4824", "0721/608-4067"))));
    }

    /**
     * Test of readPhoneNumberFromInp method, of class PhoneWordsCombination.
     */
    @Test
    public void testReadPhoneNumberFromInp() {
        System.out.println("readPhoneNumberFromInp");
        List<String> phoneNumberList = new ArrayList<>(Arrays.asList("2401", "fhh324_34", "53/45/4", "2401"));
        PhoneWordsCombination instance = new PhoneWordsCombination();
        Set<String> readPhoneNumberFromInp = instance.readPhoneNumberFromInp(phoneNumberList);

        assertTrue(readPhoneNumberFromInp.containsAll(phoneNumberList));
    }

    /**
     * Test of combinations method, of class PhoneWordsCombination.
     */
    @Test
    public void testCombinations() {
        System.out.println("combinations");
        String pre = "";
        String inp = "";
        String succ = "";
        PhoneWordsCombination instance = new PhoneWordsCombination();
        PhoneWordsList expResult = new PhoneWordsList();
        PhoneWordsList result = instance.combinations(pre, inp, succ);
        assertEquals(expResult.getPhoneWordsList().size(), result.getPhoneWordsList().size());
        assertTrue(expResult.getPhoneWordsList().containsAll(result.getPhoneWordsList()));
        assertTrue(String.format("%d < %d", expResult.getRunTime(), result.getRunTime()), expResult.getRunTime() == result.getRunTime());
    }

    /**
     * Test of phoneWord method, of class PhoneWordsCombination.
     */
    @Test
    public void testPhoneWord() {
        System.out.println("phoneWord");
        String num = "2646";
        PhoneWordsCombination instance = new PhoneWordsCombination();
        PhoneWordsList expResult = new PhoneWordsList();
        expResult.add("AMIN");

        instance.setWords(new HashSet<>(Arrays.asList("AMIN")));
        PhoneWordsList result = instance.phoneWord(num);
        assertTrue(result.getPhoneWordsList().size() > 0);
        assertTrue(expResult.getPhoneWordsList().containsAll(result.getPhoneWordsList()));

    }

}
