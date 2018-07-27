/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aconex.phonewords;

import com.aconex.phonewords.entities.Arguments;
import com.aconex.phonewords.utils.Utility;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
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
public class PhoneWordsTest {
    PhoneWordsCombination combination;

    public PhoneWordsTest() {
                combination=new PhoneWordsCombination();

    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
                combination.clearPhoneWords();

    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class PhoneWords.
     */
    /**
     * File reference to the resources directory.
     */
    private static final File RESOURCES_DIRECTORY = new File(
            "src" + File.separator + "test" + File.separator + "resources");

    @Test
    public void testPhoneNumberListIsLoadedCorrectly(){

        String fullPathPhoneNumberFile = Utility.phone_number_sample_list;
        int expectedPhoneNumberListSize = 9;
        final Collection<String> loadedPhoneNumberList = new HashSet<>(Arrays.asList("112.1",
"5624-8.2",
"4824" ,
"0721/608-4067" ,
"10/783--5" ,
"1078-913-5" ,
"381482" ,
"04824" ,
"4693746974435876895557678646896875675785095431210"));
        
//Prove the file exists! This is important!
        File file = new File(fullPathPhoneNumberFile);
        //Prove that the file exists!
        assertTrue(String.format("File %s must exist!", file.getAbsolutePath()), file.exists());

        Set<String> readPhoneNumber = combination.readPhoneNumber(fullPathPhoneNumberFile);
        
        //Load the phone number and test.
        assertEquals("Valid phone number file must be loaded successfully.",
                readPhoneNumber,loadedPhoneNumberList);
        assertThat(String.format("There must be %s phone numbers in %s.",
                expectedPhoneNumberListSize, fullPathPhoneNumberFile),
                loadedPhoneNumberList.size(), is(expectedPhoneNumberListSize));
    }

    @Test
    public void testParseInput(){
        String[] validArgs1 = new String[] {"-d", "C:\\temp\\dictionary.txt", "-i", "C:\\temp\\input.txt"};
        String[] validArgs2 = new String[] {"-i", "C:\\temp\\input.txt"};
        String[] validArgs3 = new String[] {"-d", "C:\\temp\\dictionary.txt", "3453", "86fgh34"};
        String[] invalidArgs1 = new String[] {"-d", "C:\\temp", " ", " spaces \\dictionary.txt", "-i", "C:\\temp\\input.txt"};
        String[] invalidArgs2 = new String[] {"asetbeb", "wtrbr", "srbgt", "-i", "C:\\temp\\input.txt"};
        String[] invalidArgs3 = new String[] {"-d", "C:\\temp \\dictionary.txt", "srtb", "-i", "C:\\temp\\input.txt"};

        //Test valid args and options.
        Map<Arguments, List<String>> actualParams = PhoneWords.parseInput(validArgs1);
        assertNotNull("Parameter d must not be null.", actualParams.get(Arguments.d));
        assertNotNull("Parameter i must not be null.", actualParams.get(Arguments.i));
        assertThat("Parameter d must have 1 option.", actualParams.get(Arguments.d).size(), is(1));
        assertThat("Parameter i must have 1 option.", actualParams.get(Arguments.i).size(), is(1));
        assertThat(String.format("Parameter d option must be %s", validArgs1[1]),
                actualParams.get(Arguments.d).get(0), is(validArgs1[1]));
        assertThat(String.format("Parameter d option must be %s", validArgs1[1]),
                actualParams.get(Arguments.i).get(0), is(validArgs1[3]));
       //Test valid args and options.
        actualParams = PhoneWords.parseInput(validArgs2);
        assertNotNull("Parameter i must not be null.", actualParams.get(Arguments.i));
        assertThat("Parameter i must have 1 option.", actualParams.get(Arguments.i).size(), is(1));
        assertTrue(String.format("Parameter d option must be empty"),
                actualParams.get(Arguments.d).isEmpty());
        assertThat(String.format("Parameter i option must be %s", validArgs2[1]),
                actualParams.get(Arguments.i).get(0), is(validArgs2[1]));
       //Test valid args and options.
        actualParams = PhoneWords.parseInput(validArgs3);
        
        assertThat("Parameter num must have 2 option.", actualParams.get(Arguments.num).size(), is(2));
        assertThat("Parameter d must have 1 option.", actualParams.get(Arguments.d).size(), is(1));
        assertThat("Parameter i must have no option.", actualParams.get(Arguments.i).size(), is(0));
        assertThat(String.format("Parameter d option must be %s", validArgs3[1]),
                actualParams.get(Arguments.d).get(0), is(validArgs3[1]));
        assertThat(String.format("Parameter num option must be %s , %s", validArgs3[2], validArgs3[3]),
                actualParams.get(Arguments.num), is(new ArrayList<>(Arrays.asList(validArgs3[2],validArgs3[3]))));

        //Test invalid args and options.
        assertNull(String.format("Args %s must be invalid!", Arrays.asList(invalidArgs1)),
                PhoneWords.parseInput(invalidArgs1));
        assertNull(String.format("Args %s must be invalid!", Arrays.asList(invalidArgs2)),
                PhoneWords.parseInput(invalidArgs2));
        assertNull(String.format("Args %s must be invalid!", Arrays.asList(invalidArgs3)),
                PhoneWords.parseInput(invalidArgs3));
    }    
}
