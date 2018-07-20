package com.aconex.phoneword.dictionaries;

import com.aconex.phoneword.validators.dictionary.DictionaryValidator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;

/**
 * English dictionary implementation.
 */
public class EnglishDictionary extends AbstractDictionary {
    

    /**
     * Constructs an instance of the dictionary with the passed in parameters and loads the data from the
     * dictionary file.
     *
     * @param maxDictionarySize maximum allowed dictionary size.
     * @param maxWordLength     maximum allowed word length in the dictionary.
     * @param dictionaryValidator reference to an instance of the {@link DictionaryValidator}.
     */
    public EnglishDictionary(
            final int maxDictionarySize,
            final int maxWordLength,
            final DictionaryValidator dictionaryValidator) {
        super(maxDictionarySize, maxWordLength,dictionaryValidator);
    }

    /**
     * Validate and load the specified dictionary file into memory.
     * Sort the dictionary using {@link #comparator}.
     * Silently handles {@link FileNotFoundException} and {@link IOException} exceptions.
     * Check if the method finishes successfully and handle accordingly if it fails.
     * @param fileName full path to the file containing the dictionary.
     * @return true if loading succeeded, false otherwise.
     */
    @Override
    public boolean load(String fileName) {
        //System.out.println(String.format("Loading dictionary from file %s.", fileName));
        if (!super.load(fileName)) return false;
        //System.out.println(String.format("Sorting dictionary."));
        Collections.sort(super.getDictionaryStore());
        return true;
    }

    /**
     * Searches for the specified in word using {@link #comparator}.
     * @param word {@link String} word to find.
     * @return the index of the search key, if it is contained in the lists; otherwise, (-(insertion point) - 1).
     */
    @Override
    public int findWord(String word) {
        return Collections.binarySearch(this.dictionaryStore, word);
    }
}
