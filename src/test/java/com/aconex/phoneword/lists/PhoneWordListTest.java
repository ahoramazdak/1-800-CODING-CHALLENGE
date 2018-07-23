package com.aconex.phoneword.lists;


import com.aconex.phoneword.lists.PhoneWordList;
import com.aconex.phoneword.dictionaries.AbstractDictionary;
import com.aconex.phoneword.encoders.NumberEncoder;
import com.aconex.phoneword.entities.number.EncodedNumber;
import com.aconex.phoneword.mapping.PhoneWordMapping;
import com.aconex.phoneword.parameters.EnglishParameters;
import com.aconex.phoneword.validators.dictionary.AbstractDictionaryValidator;
import com.aconex.phoneword.validators.number.PhoneNumberValidator;
import org.junit.Test;

import java.io.File;
import java.util.*;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PhoneWordListTest {

    /**
     * File reference to the resources directory.
     */
    private static final File RESOURCES_DIRECTORY = new File(
            "src" + File.separator + "test" + File.separator + "resources");

    @Test
    public void testPhoneNumberListIsLoadedCorrectly(){

        String fullPathPhoneNumberFile = RESOURCES_DIRECTORY.getAbsolutePath() + File.separator +
                "phone_number_list.txt";
        int expectedPhoneNumberListSize = 1000;
        final Collection<EncodedNumber> loadedPhoneNumberList = new ArrayList<>();
        

//Prove the file exists! This is important!
        File file = new File(fullPathPhoneNumberFile);
        //Prove that the file exists!
        assertTrue(String.format("File %s must exist!", file.getAbsolutePath()), file.exists());

        //Create a dummy dictionary validator.
        AbstractDictionaryValidator dummyDictionaryValidator = new AbstractDictionaryValidator(){};
        //Create a dummy dictionary.
        AbstractDictionary dummyDictionary = new AbstractDictionary(
                EnglishParameters.MAX_ENGLISH_DICTIONARY_SIZE,
                EnglishParameters.MAX_ENGLISH_DICTIONARY_WORD_LENGTH,
                dummyDictionaryValidator)
        {};
        //Create a dummy honeNumberValidator.
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator(
                EnglishParameters.MAX_PHONE_NUMBER_LENGTH);
        //Create a dummy encoder that adds numbers to the lists,
        // we just want to test that all numbers in a valid file are loaded correctly.
        NumberEncoder dummyNumberEncoder = (String number) -> {
            loadedPhoneNumberList.add(new EncodedNumber(number, "ENCODED"));
            return null;
        };
        PhoneWordMapping phoneNumberToWordMapping= new PhoneWordMapping(
                EnglishParameters.DIGIT_TO_LETTER_MAPPING);
        //Create an instance of the PhoneWordList.
        PhoneWordList encodedPhoneNumberList =  new PhoneWordList(
                phoneNumberValidator, dummyDictionary, dummyNumberEncoder, phoneNumberToWordMapping);
        //Load the phone number and test.
        assertTrue("Valid phone number file must be loaded successfully.",
                encodedPhoneNumberList.load(fullPathPhoneNumberFile));
        assertThat(String.format("There must be %s phone numbers in %s.",
                expectedPhoneNumberListSize, fullPathPhoneNumberFile),
                loadedPhoneNumberList.size(), is(expectedPhoneNumberListSize));
    }

    @Test
    public void testParseInput(){
        String[] validArgs1 = new String[] {"-d", "C:\\temp\\dictionary.txt", "-i", "C:\\temp\\input.txt"};
        String[] validArgs2 = new String[] {"-i", "C:\\temp\\input.txt"};
        String[] validArgs3 = new String[] {"-d", "C:\\temp\\dictionary.txt", "-i", "C:\\temp\\input.txt"};
        String[] invalidArgs1 = new String[] {"-d", "C:\\temp", " ", " spaces \\dictionary.txt", "-i", "C:\\temp\\input.txt"};
        String[] invalidArgs2 = new String[] {"asetbeb", "wtrbr", "srbgt", "-i", "C:\\temp\\input.txt"};
        String[] invalidArgs3 = new String[] {"-d", "C:\\temp \\dictionary.txt", "srtb", "-i", "C:\\temp\\input.txt"};

        //Test valid args and options.
        Map<String, List<String>> actualParams = PhoneWordList.parseInput(validArgs1);
        assertNotNull("Parameter d must not be null.", actualParams.get("d"));
        assertNotNull("Parameter i must not be null.", actualParams.get("i"));
        assertThat("Parameter d must have 1 option.", actualParams.get("d").size(), is(1));
        assertThat("Parameter i must have 1 option.", actualParams.get("i").size(), is(1));
        assertThat(String.format("Parameter d option must be %s", validArgs1[1]),
                actualParams.get("d").get(0), is(validArgs1[1]));
        assertThat(String.format("Parameter d option must be %s", validArgs1[1]),
                actualParams.get("i").get(0), is(validArgs1[3]));

        //Test invalid args and options.
        assertNull(String.format("Args %s must be invalid!", Arrays.asList(invalidArgs1)),
                PhoneWordList.parseInput(invalidArgs1));
        assertNull(String.format("Args %s must be invalid!", Arrays.asList(invalidArgs2)),
                PhoneWordList.parseInput(invalidArgs2));
        assertNull(String.format("Args %s must be invalid!", Arrays.asList(invalidArgs3)),
                PhoneWordList.parseInput(invalidArgs3));
    }
}
