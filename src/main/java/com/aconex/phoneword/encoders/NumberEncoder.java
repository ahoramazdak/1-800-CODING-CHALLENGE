package com.aconex.phoneword.encoders;

import com.aconex.phoneword.entities.number.EncodedNumber;

import java.util.Collection;

/**
 * Interface providing a contract for the implementing encoders.
 */
public interface NumberEncoder {
    /**
     * Encode the passed in number using the specified number to word mapping and return a collection with all possible
     * encodings.
     * @param number {@link String} with the number to be encoded.
     * @return {@link Collection} of encoded and formatted {@link EncodedNumber} numbers.
     */
    Collection<EncodedNumber> encode(String number);
}
