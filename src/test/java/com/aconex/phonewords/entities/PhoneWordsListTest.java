/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aconex.phonewords.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
public class PhoneWordsListTest {

    public PhoneWordsListTest() {
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
     * Test of getStartTime method, of class PhoneWordsList.
     */
    @Test
    public void testGetStartTime() {
        System.out.println("getStartTime");
        long expResult = 0L;
        PhoneWordsList.setStartTime(expResult);
        long result = PhoneWordsList.getStartTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStartTime method, of class PhoneWordsList.
     */
    @Test
    public void testSetStartTime() {
        System.out.println("setStartTime");
        long aStartTime = 10L;
        PhoneWordsList.setStartTime(aStartTime);
        long result = PhoneWordsList.getStartTime();
        assertEquals(aStartTime, result);
    }

    /**
     * Test of getTimeToRun method, of class PhoneWordsList.
     */
    @Test
    public void testGetTimeToRun() {
        System.out.println("getTimeToRun");
        long expResult = 10L;
        long result = PhoneWordsList.getTimeToRun();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTimeToRun method, of class PhoneWordsList.
     */
    @Test
    public void testSetTimeToRun() {
        System.out.println("setTimeToRun");
        long alTimeToRun = 10L;
        PhoneWordsList.setTimeToRun(alTimeToRun);
        long result = PhoneWordsList.getTimeToRun();
        assertEquals(alTimeToRun, result);
    }

    /**
     * Test of getRunTime method, of class PhoneWordsList.
     */
    @Test
    public void testGetRunTime() {
        System.out.println("getRunTime");
        PhoneWordsList instance = new PhoneWordsList();
        long expResult = 0L;
        long result = instance.getRunTime();
        assertTrue(expResult < result);
    }

    /**
     * Test of isTimeOut method, of class PhoneWordsList.
     */
    @Test
    public void testIsTimeOut() {
        System.out.println("isTimeOut");
        PhoneWordsList instance = new PhoneWordsList();
        boolean expResult = true;
        boolean result = instance.isTimeOut();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTimeOut method, of class PhoneWordsList.
     */
    @Test
    public void testSetTimeOut() {
        System.out.println("setTimeOut");
        PhoneWordsList instance = new PhoneWordsList();
        instance.setTimeOut();
        boolean expResult = true;
        boolean result = instance.isTimeOut();
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class PhoneWordsList.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        String word = "amin";
        PhoneWordsList instance = new PhoneWordsList();
        instance.add(word);
        assertArrayEquals(instance.getPhoneWordsList().toArray(), new ArrayList<>(Arrays.asList("amin")).toArray());
    }

    /**
     * Test of addAll method, of class PhoneWordsList.
     */
    @Test
    public void testAddAll() {
        System.out.println("addAll");
        PhoneWordsList words = new PhoneWordsList();
        words.add("amin");
        words.add("keikanlou");
        PhoneWordsList instance = new PhoneWordsList();
        instance.addAll(words);
        assertTrue(instance.getPhoneWordsList().containsAll(new ArrayList<>(Arrays.asList("amin", "keikanlou"))));
    }

    /**
     * Test of getPhoneWordsList method, of class PhoneWordsList.
     */
    @Test
    public void testGetPhoneWordsList() {
        System.out.println("getPhoneWordsList");
        PhoneWordsList instance = new PhoneWordsList();
        instance.add("amin");
        instance.add("keikanlou");
        Collection<String> expResult = new ArrayList<>(Arrays.asList("keikanlou", "amin"));
        Collection<String> result = instance.getPhoneWordsList();
        assertEquals(result.size(), expResult.size());
        assertTrue(result.containsAll(expResult));
    }

    /**
     * Test of getPhoneWordsList method, of class PhoneWordsList.
     */
    @Test
    public void testGetPhoneWordsListNotHave() {
        System.out.println("getPhoneWordsList Not Have");
        PhoneWordsList instance = new PhoneWordsList();
        instance.add("amin");
        instance.add("you");
        Collection<String> expResult = new ArrayList<>(Arrays.asList("keikanlou", "amin"));
        Collection<String> result = instance.getPhoneWordsList();
        assertEquals(result.size(), expResult.size());
        assertFalse(result.containsAll(expResult));
    }

    /**
     * Test of toString method, of class PhoneWordsList.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        PhoneWordsList instance = new PhoneWordsList();
        String expResult = "PhoneWordsList{phoneWords size=0 generated Completely(true) in";
        String result = instance.toString();
        assertTrue(result.startsWith(expResult));
    }

    /**
     * Test of isEmpty method, of class PhoneWordsList.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        PhoneWordsList instance = new PhoneWordsList();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

}
