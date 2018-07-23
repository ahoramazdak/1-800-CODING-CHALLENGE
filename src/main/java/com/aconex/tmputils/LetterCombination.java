/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aconex.tmputils;

import com.aconex.phoneword.encoders.PhoneNumberEncoder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
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
    /**
     * File reference to the resources directory.
     */
    private static final File RESOURCES_DIRECTORY = new File(
            "src" + File.separator + "test" + File.separator + "resources");

    final static LinkedHashMap<String, Collection<String>> phone_words = new LinkedHashMap<>();

    public static void main(String[] args) {
//        tpK("345", "");
        final LetterCombination lc = new LetterCombination();

//        List<String> word_collection;
//        word_collection = new LinkedList<>();

        HashMap<String, List<String>> testMap = new HashMap<>();
//        testMap.put(asciidigit, letterCombinations(asciidigit));
        try (Stream<String> lines = Files.lines(Paths.get(ReadWriteTextFile.frw_words_au_len_more_2))) {

            lines.forEach(line -> {
                try (Stream<String> myphonenumlist = Files.lines(Paths.get(ReadWriteTextFile.phone_number_sample_list))) {
                    myphonenumlist.forEach(myphonenum -> {
                        String digit = myphonenum.toString();
                        phone_words.putIfAbsent(digit, new ArrayList<String>());

                        String asciidigit = lc.getASCIIDigits(digit);
                        if (asciidigit.contains(lc.toNormalPhoneNumber(line))) {
                            phone_words.get(digit).add(line);
                        }
//                        word_collection = lines.filter(line -> asciidigit.contains(lc.toNormalPhoneNumber(line)))
//                                .map(String::toUpperCase)
//                                .collect(Collectors.toList());
//                        phone_words.put(digit, word_collection);

                    });
                } catch (IOException ex) {
                    Logger.getLogger(LetterCombination.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

//            word_collection.stream().forEach((coll) -> {
//                phone_words.get(digit).add(coll.toString());
//                System.out.print(coll);
//            });
        } catch (IOException ex) {
            Logger.getLogger(LetterCombination.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (String num : phone_words.keySet()) {
            wordsss = Collections.unmodifiableList((List<String>) phone_words.get(
                    num));
            Collection<String> ph = lc.phoneWord(lc.getASCIIDigits(num));
            System.out.print(num + " : ");
            ph.stream().forEach(phword -> {
                System.out.print(phword + "\t");
            });
            System.out.println();
        }

    }

    public final static Collection<String> buildEncodedNumberStrings(
            final String root, final Collection<String> partlyEncodedNumbers) {
        final Collection<String> encodedNumberStrings = new ArrayList<>();
        for (String partlyEncodedNumber : partlyEncodedNumbers) {
            StringBuilder encodedNumberBuilder = new StringBuilder();
            encodedNumberBuilder.append(root).append("-").append(partlyEncodedNumber);
            encodedNumberStrings.add(encodedNumberBuilder.toString());
        }
        return encodedNumberStrings;
    }

    /**
     * Recursively traverses each passed in number and encodes with the
     * available words for {@link #numberToWordMapping} as per algorithm
     * described in {@link #encode(String)}.
     *
     * @param number number to be encoded.
     * @param startIndex index of the number digit to start with.
     * @param isLeftDigit indicates if the number's digit to the left of the
     * start index was encoded as a numeric character [0-9].
     * @return {@link Collection} of encoded numbers as {@link String}.
     */
    protected void combi(String pre, String inp, String succ, Collection<String> phoneWordCombination) {
        boolean added = false;
        if (pre.length() == 0) {
            if (succ.length() != 0) {
                for (String p : phoneWord(succ)) {
                    if (p.length() != 0) {
                        phoneWordCombination.add(inp.concat("-").concat(p));
                        added = true;
                    }

                }
            }
            if (!added) {
                if (succ.length() > 0) {
                    phoneWordCombination.add(inp.concat("-").concat(succ));
                } else {
                    phoneWordCombination.add(inp);
                }
            }
        } else if (succ.length() == 0) {
            for (String p : phoneWord(pre)) {
                if (p.length() != 0) {
                    phoneWordCombination.add(p.concat("-").concat(inp));
                    added = true;
                }

            }
            if (!added) {
                phoneWordCombination.add(pre.concat("-").concat(inp));
            }
        } else {
            for (String p : phoneWord(pre)) {
                for (String s : phoneWord(succ)) {
                    if (p.length() != 0 && s.length() != 0) {
                        phoneWordCombination.add(p.concat("-").concat(inp).concat("-").concat(s));
                    } else if (p.length() == 0 && s.length() != 0) {
                        phoneWordCombination.add(inp.concat("-").concat(s));
                    } else if (p.length() != 0 && s.length() == 0) {
                        phoneWordCombination.add(p.concat("-").concat(inp));
                    } else {
                        phoneWordCombination.add(inp);
                    }
                    added = true;

                }
            }
            if (!added) {
                phoneWordCombination.add(pre.concat("-").concat(inp).concat("-").concat(succ));
            }
        }

    }

    static List<String> wordsss;

    protected Collection<String> phoneWord(String n) {
        Collection<String> phoneWordCombination = new ArrayList<>();
//        if (words.stream().anyMatch(w -> !n.contains(w))) {
//            return phoneWordCombination;
//        }
        for (String w : wordsss) {
            String wtodig = toNormalPhoneNumber(w);
            if (n.indexOf(wtodig) >= 0) {
                if (n.indexOf(wtodig) == 0) {
                    combi("", w, n.substring(n.indexOf(wtodig) + w.length()), phoneWordCombination);

                } else if ((n.indexOf(wtodig) + wtodig.length()) == n.length()) {
                    combi(n.substring(0, n.indexOf(wtodig)), w, "", phoneWordCombination);
                } else {
                    combi(n.substring(0, n.indexOf(wtodig)), w, n.substring(n.indexOf(wtodig) + w.length()), phoneWordCombination);

                }
            }

        }

        return phoneWordCombination;
    }

    private final Collection<String> encodeNumber(
            final String number, final int startIndex, final boolean isLeftDigit) {
        Collection<String> encodedNumbers = new ArrayList<>();
        for (int currentIndex = startIndex; currentIndex < number.length(); currentIndex++) {
            final String currentNumber = number.substring(startIndex, currentIndex + 1);
            final List<String> words = Collections.unmodifiableList((List<String>) phone_words.get(
                    currentNumber));
            final boolean isCurrentDigit = (currentNumber.length() == 1);
            final boolean isCurrentLastDigit = (currentIndex == number.length() - 1);
            final Collection<String> partlyEncodedNumbers;
            if (!isCurrentLastDigit) {
                if (!words.isEmpty()) {
                    partlyEncodedNumbers = encodeNumber(number.substring(currentIndex + 1), 0, false);
                    words.stream().forEach((word) -> {
                        if (partlyEncodedNumbers.isEmpty() && isCurrentDigit) {
                            encodedNumbers.add(word);
                        } else if (!partlyEncodedNumbers.isEmpty()) {
                            encodedNumbers.addAll(PhoneNumberEncoder.buildEncodedNumberStrings(word, partlyEncodedNumbers));
                        }
                    });
                    continue;
                } else if (!isLeftDigit && isCurrentDigit && !phone_words.containsKey(number)) {
                    partlyEncodedNumbers = encodeNumber(number.substring(currentIndex + 1), 0, true);
                    if (!partlyEncodedNumbers.isEmpty()) {
                        encodedNumbers.addAll(buildEncodedNumberStrings(currentNumber, partlyEncodedNumbers));
                    }
                    continue;
                }
            } else if (words.isEmpty() && !isLeftDigit && isCurrentDigit) {
                encodedNumbers.add(currentNumber);
            } else if (!words.isEmpty()) {
                words.stream().forEach((word) -> encodedNumbers.add(word));
            }
        }
//        validateAndRemove(encodedNumbers, number);
        return encodedNumbers;
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
