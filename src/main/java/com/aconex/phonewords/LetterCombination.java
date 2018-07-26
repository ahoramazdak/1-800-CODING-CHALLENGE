/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aconex.phonewords;

import com.aconex.phonewords.utils.ReadWriteTextFile;
import com.aconex.phonewords.entities.PhoneWordsList;
import com.aconex.phonewords.keypad.KeypadNumber;
import com.aconex.phonewords.entities.DigitNumber;
import com.aconex.phonewords.entities.EncodedNumber;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author amin
 */
public class LetterCombination {

    /**
     * File reference to the resources directory.
     */
    private static final File RESOURCES_DIRECTORY = new File(
            "src" + File.separator + "test" + File.separator + "resources");

    final static LinkedHashMap<String, Collection<String>> phone_words = new LinkedHashMap<>();

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
//        final LetterCombination lc = new LetterCombination();
//
//        lc.readPhoneNumber();
//
//        lc.findWordsInPhoneNumbers(ReadWriteTextFile.fr_english);
//
//        lc.phoneWordsGenerator(TimeUnit.MILLISECONDS.convert(100l, TimeUnit.MILLISECONDS));
//
//    }

    public void phoneWordsGenerator(long lTimeOut) {
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
        }
    }

    public void findWordsInPhoneNumbers(String dicFilePath) {
        //        String regExpValidWords="[AI]|(ME)|(AM)|(HE)|(OR)|\\w{3,50}";
        String regExpValidWords = "\\w{4,50}";

        try (Stream<String> lines = Files.lines(Paths.get(dicFilePath))) {

            lines.forEach(line -> {
                phone_words.keySet().stream().forEach((digit) -> {
                    add_phone_words(line, regExpValidWords, digit);
                });
            });

        } catch (IOException ex) {
            Logger.getLogger(LetterCombination.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void readPhoneNumber(String phoneNumberFilePath) {
        try (Stream<String> myphonenumlist = Files.lines(Paths.get(phoneNumberFilePath))) {
            myphonenumlist.forEach(myphonenum -> {
                String digit = myphonenum;
                phone_words.putIfAbsent(digit, new HashSet<>());

            });
        } catch (IOException ex) {
            Logger.getLogger(LetterCombination.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void add_phone_words(String line, String regExpValidWords, String digit) {
        EncodedNumber enumber = new EncodedNumber(line);
        enumber.validateEncodeNumber();
        String dicNormalizedWord = enumber.getValidEncodedNumber();
//                        System.out.println(line+" -> "+dicNormalizedWord);
        if (dicNormalizedWord.matches(regExpValidWords)) {
            KeypadNumber kp = new KeypadNumber(new DigitNumber(digit));

//            String asciidigit = kp.getASCIIDigits();
            if (kp.hasWord(enumber)) {
                phone_words.get(digit).add(dicNormalizedWord);
            }
        }
    }

    /**
     * Recursively traverses each passed in number and encodes with the
     * available words for {@link #numberToWordMapping} as per algorithm
     * described in {@link #encode(String)}.
     *
     * @param pre
     * @param inp
     * @param succ
     * @return
     * @amin number number to be encoded.
     */
    protected PhoneWordsList combi(String pre, String inp, String succ) {
        PhoneWordsList phoneWordCombin = new PhoneWordsList();
        if (pre.length() == 0) {
            if (succ.length() != 0) {
                phoneWord(succ).getPhoneWordsList().stream().forEach((p) -> {
                    phoneWordCombin.add(inp.concat("-").concat(p));
                });
            }
            if (phoneWordCombin.isEmpty()) {
                if (succ.length() > 0) {
                    phoneWordCombin.add(inp.concat("-").concat(succ));
                } else {
                    phoneWordCombin.add(inp);
                }
            }
        } else if (succ.length() == 0) {
            phoneWord(pre).getPhoneWordsList().stream().forEach((p) -> {
                phoneWordCombin.add(p.concat("-").concat(inp));
            });
            if (phoneWordCombin.isEmpty()) {
                phoneWordCombin.add(pre.concat("-").concat(inp));
            }
        } else {
            boolean preCombinationExist = false;
            for (String p : phoneWord(pre).getPhoneWordsList()) {
                preCombinationExist = true;
                phoneWord(succ).getPhoneWordsList().stream().forEach((s) -> {
                    phoneWordCombin.add(p.concat("-").concat(inp).concat("-").concat(s));
                });
            }
            if (!preCombinationExist) {
                for (String p : phoneWord(succ).getPhoneWordsList()) {
                    if (pre.length() != 0) {
                        phoneWordCombin.add(pre.concat("-").concat(inp).concat("-").concat(p));
                    } else {
                        phoneWordCombin.add(inp.concat("-").concat(p));
                    }

                }
            }
            if (phoneWordCombin.isEmpty()) {
                phoneWordCombin.add(pre.concat("-").concat(inp).concat("-").concat(succ));
            }
        }
        return phoneWordCombin;

    }

    private Set<String> words;

    protected PhoneWordsList phoneWord(String num) {

        PhoneWordsList phoneWordSet = new PhoneWordsList();
        if (phoneWordSet.isTimeOut()) {
            return phoneWordSet;
        }
        getWords().stream().forEach((w) -> {
            String wordToNum = new EncodedNumber(w).toNormalPhoneNumber();
            if (num.contains(wordToNum)) {
                if (num.indexOf(wordToNum) == 0) {
                    phoneWordSet.addAll(combi("", w, num.substring(num.indexOf(wordToNum) + w.length())));

                } else if ((num.indexOf(wordToNum) + wordToNum.length()) == num.length()) {
                    phoneWordSet.addAll(combi(num.substring(0, num.indexOf(wordToNum)), w, ""));
                } else {
                    phoneWordSet.addAll(combi(num.substring(0, num.indexOf(wordToNum)), w, num.substring(num.indexOf(wordToNum) + w.length())));

                }
            }
        });

        return phoneWordSet;
    }

}
