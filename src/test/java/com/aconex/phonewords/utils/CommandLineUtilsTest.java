/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aconex.phonewords.utils;

import com.aconex.phonewords.entities.Arguments;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class CommandLineUtilsTest {
    
    public CommandLineUtilsTest() {
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
     * Test of parseCommandLine method, of class CommandLineUtils.
     */
    @Test
    public void testParseCommandLine1() {
        System.out.println("parseCommandLine1");
        String[] args = {"4234","fdg423","5334%^"};
        Map<Arguments, List<String>> expResult = new HashMap<>();
        expResult.put(Arguments.num, new ArrayList<>(Arrays.asList(args)));
        Map<Arguments, List<String>> result = CommandLineUtils.parseCommandLine(args);
//        assertEquals(expResult.size(), result.size());
        assertEquals(expResult.get(Arguments.num), result.get(Arguments.num));
        
        
    }
   /**
     * Test of parseCommandLine method, of class CommandLineUtils.
     */
    @Test
    public void testParseCommandLine2() {
        System.out.println("parseCommandLine2");
        String[] args = {"-i",Utility.phone_number_sample_list};
        Map<Arguments, List<String>> expResult = new HashMap<>();
        expResult.put(Arguments.i, new ArrayList<>(Arrays.asList(args[1])));
        Map<Arguments, List<String>> result = CommandLineUtils.parseCommandLine(args);
//        assertEquals(expResult.size(), result.size());
        assertEquals(expResult.get(Arguments.i), result.get(Arguments.i));
    }
   /**
     * Test of parseCommandLine method, of class CommandLineUtils.
     */
    @Test
    public void testParseCommandLine3() {
        System.out.println("parseCommandLine3");
        String[] args = {"-i",Utility.phone_number_sample_list,"-d",Utility.myDic};
        Map<Arguments, List<String>> expResult =  new HashMap<>();
        expResult.put(Arguments.i, new ArrayList<>(Arrays.asList(args[1])));
        expResult.put(Arguments.d, new ArrayList<>(Arrays.asList(args[3])));
        Map<Arguments, List<String>> result = CommandLineUtils.parseCommandLine(args);
        assertEquals(expResult.get(Arguments.i), result.get(Arguments.i));
        assertEquals(expResult.get(Arguments.i), result.get(Arguments.i));
    }
      /**
     * Test of parseCommandLine method, of class CommandLineUtils.
     */
    @Test
    public void testParseCommandLine4() {
        System.out.println("parseCommandLine4");
        String[] args = {"-d",Utility.myDic,"345","345hh"};
        Map<Arguments, List<String>> expResult =  new HashMap<>();
        expResult.put(Arguments.d, new ArrayList<>(Arrays.asList(args[1])));
        expResult.put(Arguments.num, new ArrayList<>(Arrays.asList(args[2],args[3])));
        Map<Arguments, List<String>> result = CommandLineUtils.parseCommandLine(args);
        assertEquals(expResult.get(Arguments.d), result.get(Arguments.d));
        assertEquals(expResult.get(Arguments.num), result.get(Arguments.num));
    }
    
}
