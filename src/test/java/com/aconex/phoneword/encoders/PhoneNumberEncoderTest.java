package com.aconex.phoneword.encoders;

import com.aconex.phoneword.encoders.PhoneNumberEncoder;
import com.aconex.phoneword.dictionaries.EnglishDictionary;
import com.aconex.phoneword.entities.number.EncodedNumber;
import com.aconex.phoneword.helpers.TestHelpers;
import com.aconex.phoneword.mapping.PhoneWordMapping;
import com.aconex.phoneword.parameters.EnglishParameters;
import com.aconex.phoneword.validators.dictionary.EnglishDictionaryValidator;
import org.junit.Test;

import java.io.File;
import java.util.*;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PhoneNumberEncoderTest {

    /**
     * File reference to the resources directory.
     */
    private static final File RESOURCES_DIRECTORY = new File(
            "src" + File.separator + "test" + File.separator + "resources");

    /**
     *  correct program output.
     */
    private static final Map<String, List<String>> EXPECTED_PROGRAM_OUTPUT =
            Collections.unmodifiableMap(new HashMap<String, List<String>>() {{
                //empty
                put("571-7050", Collections.unmodifiableList(new ArrayList<>()));
                put("0721/608-4067", Collections.unmodifiableList(new ArrayList<>()));
                put("1078-913-5", Collections.unmodifiableList(new ArrayList<>()));
                //with words
                put("5624-82", Collections.unmodifiableList(Arrays.asList("JOB-HUB","LOB-HUB","LOCI-TA","JOB-I-TA","JOB-IT-A","LOB-I-TA","LOB-IT-A","56-CHUB","5-MA-HUB","5-NAG-TA","5-OBIT-A","56-A-HUB","56-BIT-A","56-CIT-A","5-MA-I-TA","5-MA-IT-A")));
                put("4824", Collections.unmodifiableList(Arrays.asList("ITCH", "HUB-4", "4-TAG","IT-24","4-TA-4","I-82-I","48-A-4","I-8-AI")));
                put("10/783--5", Collections.unmodifiableList(Arrays.asList("neu o\"d 5", "je bo\"s 5", "je Bo\" da")));
                put("381482", Collections.unmodifiableList(Arrays.asList("so 1 Tor")));
                put("04824", Collections.unmodifiableList(Arrays.asList("0 Torf", "0 fort", "0 Tor 4")));
            }});

    private static final Map<String, List<String>> EXPECTED_PROGRAM_OUTPUT_SPEC_DIGIT_ENC =
            Collections.unmodifiableMap(new HashMap<String, List<String>>() {{
                put("4-753168781103080", Collections.unmodifiableList(Arrays.asList(
                        "ISLE-1-MUST-110308","ISLE-1-OUST-110308",
"IRK-31-MUST-110308","IRK-31-OUST-110308",
"ISLE-1-OUR-8110308","IS-531-MUST-110308",
"IS-531-OUST-110308","ISLE-16-UP-8110308",
"ISLE-16-US-8110308","I-7531-MUST-110308",
"I-7531-OUST-110308")));
                put("1556/0", Collections.unmodifiableList(new ArrayList<>()));
                put("3879", Collections.unmodifiableList(Arrays.asList(
                        "fury","fur-9","3-try","3-up-9","3-us-9")));
                put("3/-03", Collections.unmodifiableList(new ArrayList<>()));

            }});

    private static final Map<String, List<String>> EXPECTED_PROGRAM_OUTPUT_DIGITS_AND_SINGLE_LETTERS =
            Collections.unmodifiableMap(new HashMap<String, List<String>>() {{
                //empty
                put("1234", new ArrayList<>());
                //with words
                put("123", Collections.unmodifiableList(Arrays.asList(
                        "J x 3",
                        "N x 3")));
                put("45", Collections.unmodifiableList(Arrays.asList(
                        "4 a")));
                put("8", Collections.unmodifiableList(Arrays.asList(
                        "O")));
                put("09", Collections.unmodifiableList(Arrays.asList(
                        "E H")));

            }});

    /**
     * Sample phone number list.
     */
    private static final Set<String> SAMPLE_PHONE_NUMBER_LIST =
            Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(
                    "112",
                    "5624-82",
                    "4824",
                    "0721/608-4067",
                    "10/783--5",
                    "1078-913-5",
                    "381482",
                    "04824"
            )));

    private static final Set<String> SAMPLE_PHONE_NUMBER_LIST_SPEC_DIGIT_ENC =
            Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(
                    "4-753168781103080",
                    "1556/0",
                    "3879",
                    "3/-03"
            )));

    private static final Set<String> SAMPLE_PHONE_NUMBER_LIST_DIGITS_AND_SINGLE_LETTERS =
            Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(
                    "1234",
                    "123",
                    "45",
                    "8",
                    "09"
            )));


    @Test
    public void testGetASCIIDigits(){
        //Mapping is not required for this test
        PhoneNumberEncoder numberEncoder =  new PhoneNumberEncoder(null);
        //expected, input pairs.
        Map<String, String> numbers =  Collections.unmodifiableMap(new HashMap<String, String>(){{
            put("4313245", "q4313245jkjnjnk^%&**");
            put("444", "44////4");
            put("22", "22");
            put("107835", "10/783--5");
        }});

       for (Iterator<Map.Entry<String, String>> i = numbers.entrySet().iterator(); i.hasNext();){
           final Map.Entry<String, String> entry = i.next();
           final String expectedNumber = entry.getKey();
           final String actualNumber = numberEncoder.getASCIIDigits(expectedNumber);
           assertThat(String.format("Number must be equal to %s .", i.next().getKey()),
                   actualNumber, is(expectedNumber));
       }
    }

    @Test
    public void testGetASCIILettersAndNumbers(){
        //Mapping is not required for this test
        PhoneNumberEncoder numberEncoder =  new PhoneNumberEncoder(null);
        //expected, input pairs.
        Map<String, String> words =  Collections.unmodifiableMap(new HashMap<String, String>(){{
            put("q4313245jkjnjnk", "q4313245jkjnjnk^%&**");
            put("444", "44////4");
            put("aaaaaabvrbvr", "aa aaaa bvrb vr");
            put("107835rtynortfnrt", "10/783--5 rtyno\"rtfnrt");
        }});

        for (Iterator<Map.Entry<String, String>> i = words.entrySet().iterator(); i.hasNext();){
            final Map.Entry<String, String> entry = i.next();
            final String expectedWord = entry.getKey();
            final String actualWord = numberEncoder.getASCIILettersAndDigits(expectedWord);
            assertThat(String.format("Word must be equal to %s .", i.next().getKey()),
                    actualWord, is(expectedWord));
        }
    }

    @Test
    public void testValidateAndRemove(){
        //Mapping is not required for this test
        PhoneNumberEncoder numberEncoder =  new PhoneNumberEncoder(null);
        Collection<String> invalidEncodedNumbers = new ArrayList<>(Arrays.asList(
                "aever ete4v t",
                "erv67347347 ,.,. ",
                "ab df edr653sg ",
                "ab df edr653sg#$%^$^&%^#& "

        ));
        Collection<String> validEncodedNumbers = new ArrayList<>(Arrays.asList(
                "dfghjki",
                "xc vg 5 fg",
                "ertv bn 9 "
        ));
        String numberToValidateAgainst = "1234567";
        //Validate valid collection
        numberEncoder.validateAndRemove(validEncodedNumbers, numberToValidateAgainst);
        assertThat("The size of the validEncodedNumbers must be  be 3",
                validEncodedNumbers.size(), is(3));
        //Validate invalid collection
        numberEncoder.validateAndRemove(invalidEncodedNumbers, numberToValidateAgainst);
        assertThat("The size of the invalidEncodedNumbers must be  be 3",
                invalidEncodedNumbers.size(), is(0));

    }

    @Test
    public void testBuildEncodedNumberStrings(){
        //Mapping is not required for this test
        PhoneNumberEncoder numberEncoder =  new PhoneNumberEncoder(null);
        Collection<String> partlyEncodedNumbers = Collections.unmodifiableList(new ArrayList<>(Arrays.asList(
                "aever ete4v 9",
                "erv67347347",
                "ab df edr653sg ",
                "ab df edr653sg "

        )));
        Collection<String> actualEncodedNumbers = numberEncoder.buildEncodedNumberStrings(
                "word", partlyEncodedNumbers);
        assertThat("Size of expected and actual encoded number lists must be equal.",
                actualEncodedNumbers.size(), is(partlyEncodedNumbers.size()));

        for (final String partlyEncodedNumber : partlyEncodedNumbers){
            final String expectedEncodedNumber = "word" + " " + partlyEncodedNumber;
            assertTrue(String.format("Actual encoded numbers must contain %s .", expectedEncodedNumber),
                    actualEncodedNumbers.contains(expectedEncodedNumber));
        }
    }

    @Test
    public void testFindWord(){
        String fullPathToSampleDictionaryFile = RESOURCES_DIRECTORY.getAbsolutePath() + File.separator +
                "en_sample_dictionary.txt";
        //Prove that the files exist! This is important!
        TestHelpers.testFileExists(fullPathToSampleDictionaryFile);
        //Initialise and load the dictionary for the file.
        EnglishDictionary englishDictionary = new EnglishDictionary(
                EnglishParameters.MAX_ENGLISH_DICTIONARY_SIZE,
                EnglishParameters.MAX_ENGLISH_DICTIONARY_WORD_LENGTH,
                new EnglishDictionaryValidator());
        assertTrue("Sample dictionary file must be loaded successfully.",
                englishDictionary.load(fullPathToSampleDictionaryFile));
        //Map the dictionary words to numbers.
        PhoneWordMapping phoneNumberToWordMapping = new PhoneWordMapping(
                EnglishParameters.DIGIT_TO_LETTER_MAPPING);
        phoneNumberToWordMapping.mapWords(englishDictionary.getDictionaryStore());
        PhoneNumberEncoder phoneNumberEncoder = new PhoneNumberEncoder(phoneNumberToWordMapping);
        //existing words
        assertTrue("Word Fest for number 4304 must be found!", phoneNumberEncoder.findWord("4034"));
        assertTrue("Word for number 4824 must be found!", phoneNumberEncoder.findWord("4824"));
        assertTrue("Word for number 1075 must be found!", phoneNumberEncoder.findWord("1075"));
        assertTrue("Word for number 562482 must be found!", phoneNumberEncoder.findWord("562482"));
        assertTrue("Word for number 5624825 must be found!", phoneNumberEncoder.findWord("5624825"));
        //not existing words
        assertFalse("Word for number 04824 must NOT be found!", phoneNumberEncoder.findWord("04824"));
        assertFalse("Word for number 048 must NOT be found!", phoneNumberEncoder.findWord("048"));
        assertFalse("Word for number 10755 must NOT be found!", phoneNumberEncoder.findWord("10755"));
        assertFalse("Word for number 56248255 must NOT be found!", phoneNumberEncoder.findWord("56248255"));
        assertFalse("Word for number 112 must NOT be found!", phoneNumberEncoder.findWord("112"));
    }

    @Test
    public void testEncodeNumbers(){
        performTestEncodeNumbers("sample_dictionary.txt", SAMPLE_PHONE_NUMBER_LIST, EXPECTED_PROGRAM_OUTPUT);
    }

    @Test
    public void testEncodeNumbersSpecDigitEnc(){
        performTestEncodeNumbers("sample_dictionary_spec_digit_enc.txt",
                SAMPLE_PHONE_NUMBER_LIST_SPEC_DIGIT_ENC, EXPECTED_PROGRAM_OUTPUT_SPEC_DIGIT_ENC);
    }

    @Test
    public void testEncodeNumbersDigitsAndSingleLetters(){
        performTestEncodeNumbers("sample_dictionary_letters_only.txt",
                SAMPLE_PHONE_NUMBER_LIST_DIGITS_AND_SINGLE_LETTERS, EXPECTED_PROGRAM_OUTPUT_DIGITS_AND_SINGLE_LETTERS);
    }

    /**
     * Test encode numbers with different dictionaries and sample phone numbers.
      * @param dictionaryFileName dictionary file name.
     * @param samplePhoneNumbers a {@link Set} with phone numbers as {@link String}.
     * @param expectedProgramOutput a {@link Map} with expected program output. Keys represent phone numbers and values
     *                              represent a {@link List} with encoded phone numbers as {@link String}.
     */
    private void performTestEncodeNumbers(String dictionaryFileName,
                                          Set<String> samplePhoneNumbers,
                                          Map<String, List<String>> expectedProgramOutput){
        String fullPathToSampleDictionaryFile = RESOURCES_DIRECTORY.getAbsolutePath() + File.separator +
                dictionaryFileName;
        //Prove that the files exist! This is important!
        TestHelpers.testFileExists(fullPathToSampleDictionaryFile);
        //Initialise and load the dictionary for the file.
        EnglishDictionary englishDictionary = new EnglishDictionary(
                EnglishParameters.MAX_ENGLISH_DICTIONARY_SIZE,
                EnglishParameters.MAX_ENGLISH_DICTIONARY_WORD_LENGTH,
                new EnglishDictionaryValidator());
        assertTrue("Sample dictionary file must be loaded successfully.",
                englishDictionary.load(fullPathToSampleDictionaryFile));
        //Map the dictionary words to numbers.
        PhoneWordMapping phoneNumberToWordMapping = new PhoneWordMapping(
                EnglishParameters.DIGIT_TO_LETTER_MAPPING);
        phoneNumberToWordMapping.mapWords(englishDictionary.getDictionaryStore());
        //Load and encode the phone numbers from the input file.
        for (Iterator<String> i = samplePhoneNumbers.iterator(); i.hasNext();) {
            final String number = i.next();
            Collection<EncodedNumber> actualOutput = new PhoneNumberEncoder(phoneNumberToWordMapping).encode(
                    number);
            List<String> expectedOutput = expectedProgramOutput.get(number);
            final int expectedNumberOfEncodedNumbers =  expectedOutput.size();
            assertThat(String.format("There must be %s encoded numbers for number %s .",
                    expectedNumberOfEncodedNumbers, number), actualOutput.size(), is(expectedOutput.size()));
            for (EncodedNumber actualEncodedNumber: actualOutput){
                assertTrue(String.format("Expected encoded numbers must not contain %s",
                        actualEncodedNumber.getEncodedNumber()),
                        expectedOutput.contains(actualEncodedNumber.getEncodedNumber()));
            }
        }
    }
}
