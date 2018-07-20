package com.aconex.phoneword.validators.dictionary;

import com.aconex.phoneword.validators.dictionary.EnglishDictionaryValidator;
import com.aconex.phoneword.dictionaries.EnglishDictionary;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class EnglishDictionaryValidatorTest {

    @Test
    public void testValidateWordCharacters(){
        EnglishDictionaryValidator englishDictionaryValidator = new EnglishDictionaryValidator();
        EnglishDictionary englishDictionary = new EnglishDictionary(
                50, 50, englishDictionaryValidator);
        //Test valid words
        String wordWithValidASCIIChars = "A\"A\"DBU\"fvnernO\"vU\"a\"o\"u\"";
        assertTrue(String.format("Word %s must be valid!", wordWithValidASCIIChars),
                englishDictionaryValidator.isWordValid(englishDictionary, wordWithValidASCIIChars));
        //Test invalid words
        String [] wordsWithInvalidASCIIChars = new String[]{
                "1ervoeron",
                "\"ervoeron",
                "\"\"ervoeron",
                "ervoeron\"\"",
                "erv\"\"eron",
                "AAAbbbooo123",
                "A\"\"AAbbbooo",
                "A1\"AAbbbooo",
                "1243",
                "dfsdf@%^^&$",
                ""
        };
        for (String wordWithInvalidASCIIChars : wordsWithInvalidASCIIChars){
            assertFalse(String.format("Word %s must be invalid!", wordWithInvalidASCIIChars),
                    englishDictionaryValidator.isWordValid(englishDictionary, wordWithInvalidASCIIChars));
        }
    }
}
