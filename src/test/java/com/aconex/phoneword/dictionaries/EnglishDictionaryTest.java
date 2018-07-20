package com.aconex.phoneword.dictionaries;

import com.aconex.phoneword.dictionaries.EnglishDictionary;
import com.aconex.phoneword.helpers.TestHelpers;
import com.aconex.phoneword.parameters.EnglishParameters;
import com.aconex.phoneword.validators.dictionary.DictionaryValidator;
import com.aconex.phoneword.validators.dictionary.EnglishDictionaryValidator;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class EnglishDictionaryTest {

    /**
     * File reference to the resources directory.
     */
    private static final File RESOURCES_DIRECTORY = new File(
            "src" + File.separator + "test" + File.separator + "resources");
    /**
     * Dictionary validator to be used in all instances of {@link EnglishDictionary}
     */
    private static final DictionaryValidator DICTIONARY_VALIDATOR = new EnglishDictionaryValidator();

    @Test
    public void testValidEnglishDictionaryCanBeLoaded(){
        String fullPathToDictionaryFile = RESOURCES_DIRECTORY.getAbsolutePath() + File.separator +
                "valid_dictionary_size_73113.txt";
        //Prove the file exists! This is important!
        TestHelpers.testFileExists(fullPathToDictionaryFile);
        int expectedValidDictionarySize = 73113;
        EnglishDictionary validEnglishDictionary = new EnglishDictionary(
                EnglishParameters.MAX_ENGLISH_DICTIONARY_SIZE,
                EnglishParameters.MAX_ENGLISH_DICTIONARY_WORD_LENGTH,
                DICTIONARY_VALIDATOR
                );
        assertTrue("Valid dictionaries must be loaded successfully.",
                validEnglishDictionary.load(fullPathToDictionaryFile));
        assertThat(String.format("Loaded dictionaries size must be %s.", expectedValidDictionarySize),
                expectedValidDictionarySize, is(validEnglishDictionary.wordCount()));
    }

    @Test
    public void testEnglishDictionaryWithInvalidSizeCanNotBeLoaded(){
        String fullPathToDictionaryFile = RESOURCES_DIRECTORY.getAbsolutePath() + File.separator +
                "invalid_dictionary_size_83113.txt";
        //Prove the file exists! This is important!
        TestHelpers.testFileExists(fullPathToDictionaryFile);
        int expectedInvalidDictionarySize = 0;
        EnglishDictionary invalidEnglishDictionary = new EnglishDictionary(
                EnglishParameters.MAX_ENGLISH_DICTIONARY_SIZE,
                EnglishParameters.MAX_ENGLISH_DICTIONARY_WORD_LENGTH,
                DICTIONARY_VALIDATOR);
        assertFalse("Invalid dictionaries must NOT be loaded.", invalidEnglishDictionary.load(fullPathToDictionaryFile));
        assertThat(String.format("Loaded dictionaries size must be %s.", expectedInvalidDictionarySize),
                expectedInvalidDictionarySize, is(invalidEnglishDictionary.wordCount()));
    }

    @Test
    public void testEnglishDictionaryWithInvalidWordLengthCanNotBeLoaded(){
        String fullPathToDictionaryFile = RESOURCES_DIRECTORY.getAbsolutePath() + File.separator +
                "dictionary_size_73113_with_invalid_word_length.txt";
        //Prove the file exists! This is important!
        TestHelpers.testFileExists(fullPathToDictionaryFile);
        int expectedDictionarySize = 0;
        EnglishDictionary englishDictionary = new EnglishDictionary(
                EnglishParameters.MAX_ENGLISH_DICTIONARY_SIZE,
                EnglishParameters.MAX_ENGLISH_DICTIONARY_WORD_LENGTH,
                DICTIONARY_VALIDATOR);
        assertFalse("Dictionary with invalid word length must NOT be loaded.",
                englishDictionary.load(fullPathToDictionaryFile));
        assertThat(String.format("Loaded dictionaries size must be %s.", expectedDictionarySize),
                expectedDictionarySize, is(englishDictionary.wordCount()));
    }

    @Test
    public void testFindExistingWord(){
        final String fullPathToDictionaryFile = RESOURCES_DIRECTORY.getAbsolutePath() + File.separator +
                "valid_dictionary_size_73113.txt";
        final int expectedValidDictionarySize = 73113;
        final Map<String, Integer> existingWordsAndExpectedIndexes = new HashMap<String, Integer>()
        {{
            put("Abbau", 11);
            put("zynismusfo\"rdernd", 73112);
            put("zynismusfordernd", 73112);
            put("Innenschwimmba\"der", 21638);
            put("Innenschwimmbader", 21638);
            put("Ausflu\"sse", 3221);
            put("Ausflusse", 3221);
        }};
        //Prove the file exists! This is important!
        TestHelpers.testFileExists(fullPathToDictionaryFile);
        EnglishDictionary validEnglishDictionary = new EnglishDictionary(
                EnglishParameters.MAX_ENGLISH_DICTIONARY_SIZE,
                EnglishParameters.MAX_ENGLISH_DICTIONARY_WORD_LENGTH,
                DICTIONARY_VALIDATOR);
        //Load the dictionary!
        validEnglishDictionary.load(fullPathToDictionaryFile);
        assertThat(String.format("Loaded dictionary size must be %s.", expectedValidDictionarySize),
                expectedValidDictionarySize, is(validEnglishDictionary.wordCount()));

        for (Map.Entry<String, Integer> entry : existingWordsAndExpectedIndexes.entrySet()){
            final String existingWordToFind = entry.getKey();
            final int expectedExistingWordIndex = entry.getValue();
            assertThat(String.format("Word %s must be found at index %s.", existingWordToFind, expectedExistingWordIndex),
                    validEnglishDictionary.findWord(existingWordToFind), is(expectedExistingWordIndex));
        }
    }

    @Test
    public void testFindNonExistingWord(){
        final String fullPathToDictionaryFile = RESOURCES_DIRECTORY.getAbsolutePath() + File.separator +
                "valid_dictionary_size_73113.txt";
        final int expectedValidDictionarySize = 73113;
        final Map<String, Integer> nonExistingWordsAndExpectedIndexes = new HashMap<String, Integer>()
        {{
            put("AbbauR", -13);
            put("zynismusfo\"rderndE", -73114);
            put("zynismusforderndG", -73114);
            put("Innenschwimmba\"derK", -21640);
            put("InnenschwimmbaderT", -21640);
            put("Ausflu\"sseW", -3223);
            put("AusflusseX", -3223);
            put("@$%^%$&$@5", -1);
            put("01235", -1);
            put("gggehwrtbkbi", -60678);
            put("GBA\"hf", -15560);
            put("kjk-du", -63081);
        }};
        //Prove the file exists! This is important!
        TestHelpers.testFileExists(fullPathToDictionaryFile);
        EnglishDictionary validEnglishDictionary = new EnglishDictionary(
                EnglishParameters.MAX_ENGLISH_DICTIONARY_SIZE,
                EnglishParameters.MAX_ENGLISH_DICTIONARY_WORD_LENGTH,
                DICTIONARY_VALIDATOR);
        //Load the dictionary!
        validEnglishDictionary.load(fullPathToDictionaryFile);
        assertThat(String.format("Loaded dictionary size must be %s.", expectedValidDictionarySize),
                expectedValidDictionarySize, is(validEnglishDictionary.wordCount()));

        for (Map.Entry<String, Integer> entry : nonExistingWordsAndExpectedIndexes.entrySet()){
            final String nonExistingWordToFind = entry.getKey();
            final int expectedNonExistingWordIndex = entry.getValue();
            assertThat(String.format("Word %s must be found at index %s.", nonExistingWordToFind, expectedNonExistingWordIndex),
                    validEnglishDictionary.findWord(nonExistingWordToFind), is(expectedNonExistingWordIndex));
        }
    }
}
