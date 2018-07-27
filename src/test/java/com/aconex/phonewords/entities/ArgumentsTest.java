/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aconex.phonewords.entities;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
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
public class ArgumentsTest {

    public ArgumentsTest() {
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
     * Test of argument enum values not having a value.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testArgumentsNotHaveAValue() {
        System.out.println("Arguments do not have n");
        assertThat(Arguments.valueOf("n"), is(nullValue()));
    }

    /**
     * Test of argument enum values.
     */
    @Test
    public void testArguments() {
        System.out.println("arguments");
        assertThat(Arguments.valueOf("d"), is(notNullValue()));
        assertThat(Arguments.valueOf("i"), is(notNullValue()));
        assertThat(Arguments.valueOf("num"), is(notNullValue()));
    }

}
