/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aconex.tmputils;

import static com.aconex.tmputils.ReadWriteTextFile.fr_words;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author amin
 */
public class LetterCombination {

    private static final HashMap<Character, String> map = new HashMap<>(8);

    static {
        map.put('0', "0");
        map.put('1', "1");
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
    }

    ;
    public static List<String> letterCombinations(String digits) {
        LinkedList<String> results = new LinkedList<>();
        results.add("");

        for (int i = 0; i < digits.length(); i++) {
            String letters = map.get(digits.charAt(i));
            for (int j = results.size(); j > 0; j--) {
                String intermediateResult = results.poll();
                for (int k = 0; k < letters.length(); k++) {
                    results.add(intermediateResult + letters.charAt(k));
                }
            }
        }
        return results;
    }

    /**
     * Removes all the characters from the the number except for ASCII numbers
     * [0-9].
     *
     * @return {@link String} as ASCII digits [0-9].
     */
    final String getASCIIDigits(String number) {
        return number.replaceAll("[^0-9]+", "");
    }

    public static void main(String[] args) {
//        tpK("345", "");
        final LetterCombination lc = new LetterCombination();
        LinkedHashMap<String, Collection<String>> phone_wprds;
        phone_wprds = new LinkedHashMap<>();
        List<String> word_collection;
        word_collection = new LinkedList<>();
        final String digit = "02462013-98625";
        final String asciidigit = lc.getASCIIDigits(digit);
        HashMap<String, List<String>> testMap = new HashMap<>();
        testMap.put(asciidigit, letterCombinations(asciidigit));
        try (Stream<String> lines = Files.lines(Paths.get(ReadWriteTextFile.frw_words_au))) {
            word_collection=lines.filter(line -> {
                if (phone_wprds.get(asciidigit) == null) {
                    phone_wprds.put(asciidigit, new ArrayList<>(Arrays.asList(line)));
                } else {
                    phone_wprds.get(asciidigit).add(line);
                }

                return asciidigit.contains(lc.toNormalPhoneNumber(line));
            })
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());

            word_collection.stream().forEach((coll) -> {
                System.out.print(coll);
            });
        } catch (IOException ex) {
            Logger.getLogger(LetterCombination.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String toNormalPhoneNumber(String phoneNumber) {
        String normal = "";
        for (char c : phoneNumber.toUpperCase().toCharArray()) {
            normal += getKeypadNumber(c);
        }
        return normal;
    }

    public static char getKeypadNumber(char characterToConvert) {
        if (Character.isDigit(characterToConvert)) {
            return characterToConvert;
        } else {
            switch (characterToConvert) {
                case 'A':
                case 'B':
                case 'C':
                    return '2';
                case 'D':
                case 'E':
                case 'F':
                    return '3';
                case 'G':
                case 'H':
                case 'I':
                    return '4';
                case 'J':
                case 'K':
                case 'L':
                    return '5';
                case 'M':
                case 'N':
                case 'O':
                    return '6';
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                    return '7';
                case 'T':
                case 'U':
                case 'V':
                    return '8';
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                    return '9';
                default:
                    return '?';
            }
        }
    }
    public static String keyPad[][] = {
        {"0"}, {"1"}, {"A", "B", "C"}, {"D", "E", "F"}, {"G", "H", "I"},
        {"J", "K", "L"}, {"M", "N", "O"}, {"P", "Q", "R", "S"},
        {"T", "U", "V"}, {"W", "X", "Y", "Z"}
    };

    public static void tpK(String num, String combination) {
        if (num.length() == 0) {
            System.out.println(combination);
        } else {
            for (String letter : keyPad[Character.getNumericValue(num.charAt(0))]) {
                tpK(num.substring(1), combination.concat(letter));
            }
        }
    }
}
