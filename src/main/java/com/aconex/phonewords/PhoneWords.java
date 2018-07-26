/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aconex.phonewords;

import com.aconex.phonewords.utils.ReadWriteTextFile;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author amin
 */
public class PhoneWords {
    public static void main(String[] args) {
        LetterCombination lc = new LetterCombination();

        lc.readPhoneNumber(ReadWriteTextFile.phone_number_sample_list);

        lc.findWordsInPhoneNumbers(ReadWriteTextFile.fr_english);

        lc.phoneWordsGenerator(TimeUnit.MILLISECONDS.convert(100l, TimeUnit.MILLISECONDS));

    }
    
}
