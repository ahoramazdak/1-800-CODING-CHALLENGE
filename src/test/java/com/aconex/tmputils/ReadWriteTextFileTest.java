/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aconex.tmputils;

import com.aconex.phonewords.utils.ReadWriteTextFile;
import java.io.FileWriter;
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
public class ReadWriteTextFileTest {
    
    public ReadWriteTextFileTest() {
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
     * Test of main method, of class ReadWriteTextFile.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        ReadWriteTextFile.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeTestFile method, of class ReadWriteTextFile.
     * @throws java.lang.Exception
     */
    @Test
    public void testMakeTestFile() throws Exception {
        System.out.println("makeTestFile");
        String fr = "";
        int frLineCounts = 0;
        FileWriter fw = null;
        int lines = 0;
        ReadWriteTextFile.makeTestFile(fr, frLineCounts, fw, lines);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readFile method, of class ReadWriteTextFile.
     */
    @Test
    public void testReadFile() throws Exception {
        System.out.println("readFile");
        String filepath = "";
        ReadWriteTextFile.readFile(filepath);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
