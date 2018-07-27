/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aconex.phonewords;

import com.aconex.phonewords.entities.PhoneWordsList;
import com.aconex.phonewords.keypad.KeypadNumber;
import com.aconex.phonewords.entities.DigitNumber;
import com.aconex.phonewords.entities.EncodedNumber;
import com.aconex.phonewords.utils.Utility;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author amin
 */
public class PhoneWordsCombination {

    /**
     * File reference to the resources directory.
     */
    private static final File RESOURCES_DIRECTORY = new File(
            "src" + File.separator + "test" + File.separator + "resources");

    final static LinkedHashMap<String, Collection<String>> phone_words = new LinkedHashMap<>();
    private Set<String> words;

    public void clearPhoneWords(){
        phone_words.clear();
    }
    /**
     * @return the words
     */
    public Set<String> getWords() {
        return Collections.unmodifiableSet(words);
    }

    /**
     * @param aWords the words to set
     */
    public void setWords(Collection<String> aWords) {
        words = Collections.unmodifiableSet((Set<String>) aWords);
    }

//    public static void main(String[] args) {
//        final PhoneWordsCombination lc = new PhoneWordsCombination();
//
//        lc.readPhoneNumber();
//
//        lc.findWordsInPhoneNumbers(Utility.fr_english);
//
//        lc.phoneWordsGenerator(TimeUnit.MILLISECONDS.convert(100l, TimeUnit.MILLISECONDS));
//
//    }
    public LinkedHashMap<String, Collection<String>> phoneWordsGenerator(long lTimeOut) {
        LinkedHashMap<String, Collection<String>> phone_words_genrated = new LinkedHashMap<>();

        for (String num : phone_words.keySet()) {
            setWords(phone_words.get(
                    num));
            PhoneWordsList.setStartTime(System.currentTimeMillis());
            PhoneWordsList.setTimeToRun(lTimeOut);
            PhoneWordsList ph = phoneWord(new DigitNumber(num).getASCIIDigits()/*kp.getASCIIDigits(num)*/);
            System.out.print(num + " : ");
            ph.getPhoneWordsList().stream().forEach(phword -> {
                System.out.print(phword + "\t");
            });
            System.out.println();
            System.out.println(ph.toString());
            phone_words_genrated.put(num, ph.getPhoneWordsList());
        }
        return phone_words_genrated;
    }

    public LinkedHashMap<String, Collection<String>> findWordsInPhoneNumbers(String dicFilePath) {
        //        String regExpValidWords="[AI]|(ME)|(AM)|(HE)|(OR)|\\w{3,50}";
        String regExpValidWords = Utility.regexp_words_with_length_4_50;

        try (Stream<String> lines = Files.lines(Paths.get(dicFilePath))) {

            lines.forEach(line -> {
                phone_words.keySet().stream().forEach((digit) -> {
                    add_phone_words(line, regExpValidWords, digit);
                });
            });

        } catch (IOException ex) {
            Logger.getLogger(PhoneWordsCombination.class.getName()).log(Level.SEVERE, null, ex);
        }
        return phone_words;
    }

    public Set<String> readPhoneNumber(String phoneNumberFilePath) {
        try (Stream<String> myphonenumlist = Files.lines(Paths.get(phoneNumberFilePath))) {
            myphonenumlist.forEach(myphonenum -> {
                phone_words.putIfAbsent(myphonenum, new HashSet<>());

            });
        } catch (IOException ex) {
            Logger.getLogger(PhoneWordsCombination.class.getName()).log(Level.SEVERE, null, ex);
        }
        return phone_words.keySet();
    }

    public Set<String> readPhoneNumberFromInp(List<String> phoneNumberList) {

        phoneNumberList.stream().forEach((phoneNumber) -> {
            phone_words.putIfAbsent(phoneNumber, new HashSet<>());
        });
        return phone_words.keySet();
    }

    private void add_phone_words(String line, String regExpValidWords, String digit) {
        EncodedNumber enumber = new EncodedNumber(line);
        enumber.validateEncodeNumber();
        String dicNormalizedWord = enumber.getValidEncodedNumber();
        if (dicNormalizedWord.matches(regExpValidWords)) {
            KeypadNumber kp = new KeypadNumber(new DigitNumber(digit));
            if (kp.hasWord(enumber)) {
                phone_words.get(digit).add(dicNormalizedWord);
            }
        }
    }

    /**
     * Recursively traverses each passed in input word and strings before (pre)
     * and after (succ) with calling recursively phoneword for words existed in
     * pre and succ and return its combination
     *
     * @param pre
     * @param inp
     * @param succ
     * @return PhoneWordsList
     * @amin number number to be encoded.
     */
    protected PhoneWordsList combinations(String pre, String inp, String succ) {
        PhoneWordsList phoneWordCombination = new PhoneWordsList();
        if (inp.length() != 0) {

            if (pre.length() == 0) {
                if (succ.length() != 0) {
                    phoneWord(succ).getPhoneWordsList().stream().forEach((p) -> {
                        phoneWordCombination.add(inp.concat("-").concat(p));
                    });
                }
                if (phoneWordCombination.isEmpty()) {
                    if (succ.length() > 0) {
                        phoneWordCombination.add(inp.concat("-").concat(succ));
                    } else {
                        phoneWordCombination.add(inp);
                    }
                }
            } else if (succ.length() == 0) {
                phoneWord(pre).getPhoneWordsList().stream().forEach((p) -> {
                    phoneWordCombination.add(p.concat("-").concat(inp));
                });
                if (phoneWordCombination.isEmpty()) {
                    phoneWordCombination.add(pre.concat("-").concat(inp));
                }
            } else {
                boolean preCombinationExist = false;
                for (String p : phoneWord(pre).getPhoneWordsList()) {
                    preCombinationExist = true;
                    phoneWord(succ).getPhoneWordsList().stream().forEach((s) -> {
                        phoneWordCombination.add(p.concat("-").concat(inp).concat("-").concat(s));
                    });
                }
                if (!preCombinationExist) {
                    for (String p : phoneWord(succ).getPhoneWordsList()) {
                        if (pre.length() != 0) {
                            phoneWordCombination.add(pre.concat("-").concat(inp).concat("-").concat(p));
                        } else {
                            phoneWordCombination.add(inp.concat("-").concat(p));
                        }

                    }
                }
                if (phoneWordCombination.isEmpty()) {
                    phoneWordCombination.add(pre.concat("-").concat(inp).concat("-").concat(succ));
                }
            }
        }
        return phoneWordCombination;

    }

    /**
     * Recursively traverses each passed in num (digit phone number) with
     * calling recursively combinations for words combination in it
     *
     * @param num
     * @return PhoneWordsList
     * @amin number number to be encoded.
     */
    protected PhoneWordsList phoneWord(String num) {

        PhoneWordsList phoneWordSet = new PhoneWordsList();
        if (phoneWordSet.isTimeOut()) {
            return phoneWordSet;
        }
        getWords().stream().forEach((w) -> {
            String wordToNum = new EncodedNumber(w).toNormalPhoneNumber();
            if (num.contains(wordToNum)) {
                if (num.indexOf(wordToNum) == 0) {
                    phoneWordSet.addAll(combinations("", w, num.substring(num.indexOf(wordToNum) + w.length())));

                } else if ((num.indexOf(wordToNum) + wordToNum.length()) == num.length()) {
                    phoneWordSet.addAll(combinations(num.substring(0, num.indexOf(wordToNum)), w, ""));
                } else {
                    phoneWordSet.addAll(combinations(num.substring(0, num.indexOf(wordToNum)), w, num.substring(num.indexOf(wordToNum) + w.length())));

                }
            }
        });

        return phoneWordSet;
    }

}
