/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aconex.phonewords;

import com.aconex.phonewords.entities.Arguments;
import com.aconex.phonewords.utils.CommandLineUtils;
import static com.aconex.phonewords.utils.Utility.Default_Dictionary_File;
import static com.aconex.phonewords.utils.Utility.TimeOut;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author amin
 */
public class PhoneWords {

    

    /**
     * Get user input and validate. Valid user input -d value -i value.
     *
     * @param args {@link String} array with input args.
     * @return {@link Map} containing Arguments as keys and a lists of argument
     * options as values or null;
     */
    static Map<Arguments, List<String>> parseInput(String[] args) {
        Map<Arguments, List<String>> params = CommandLineUtils.parseCommandLine(args);
        //must contain one argument and one option per argument.
        if ((params != null) && (!params.isEmpty())
                && (((params.get(Arguments.d).size() == 1)
                && (params.get(Arguments.i).size() == 1)
                && params.get(Arguments.num).isEmpty())
                || ((!params.get(Arguments.i).isEmpty())
                && params.get(Arguments.num).isEmpty())
                || ((params.get(Arguments.i).isEmpty())
                && !params.get(Arguments.num).isEmpty()))) {
            return params;
        }
        return null;
    }

    /**
     * Print usage instructions.
     */
    private static void printUsage() {
        StringBuilder usageBuilder = new StringBuilder();
        String lineSeparator = System.getProperty("line.separator");
        usageBuilder.append("Required arguments and values: ");
        usageBuilder.append(String.format("-%s <absolute path without spaces to the dictionary file> ", Arguments.d));
        usageBuilder.append(String.format("-%s <absolute path without spaces to the input file with phone numbers> ", Arguments.i));
        usageBuilder.append(lineSeparator);
        usageBuilder.append("Example:");
        usageBuilder.append(lineSeparator);
        usageBuilder.append(String.format("  * Windows -%s C:\\temp\\dictionary.txt -%s C:\\temp\\input.txt", Arguments.d, Arguments.i));
        usageBuilder.append(lineSeparator);
        usageBuilder.append(String.format("  * Linux -%s /tmp/dictionary.txt -%s /tmp/input.txt", Arguments.d, Arguments.i));
        usageBuilder.append(lineSeparator);
        usageBuilder.append(String.format("  * Or\t -%s /tmp/input.txt  Hint \"There should exist a dictionary file at current directory with name %s \"", Arguments.i, Default_Dictionary_File));
        usageBuilder.append(lineSeparator);
        usageBuilder.append(String.format(
                "  * Or\t number1 number2 ... nymberN  Hint \"There should exist a dictionary file at current directory with name %s \"", Default_Dictionary_File));
        System.out.println(usageBuilder.toString());
    }

    /**
     * Prints a message to the System.out and return false.
     *
     * @param message {@link String} containing the text ro be printed.
     * @return false;
     */
    private boolean abortLoading(String message) {
        System.out.println(message);
        return false;
    }

    /**
     * Main method to initialize PHONEWORDS.
     *
     * @param args
     */
    public static void main(String[] args) {
        Map<Arguments, List<String>> params = parseInput(args);
        PhoneWordsCombination phoneWordsCombination = new PhoneWordsCombination();
        if (params != null) {
            String pathToDictionaryFile = null;
            String pathToInputFile;
            if (!params.get(Arguments.d).isEmpty()) {
                pathToDictionaryFile = params.get(Arguments.d).get(0);
                if (!new File(pathToDictionaryFile).exists()) {
                    if (!new File(Default_Dictionary_File).exists()) {
                        System.err.println(String.format("Files %s and default Dictionary %s do not exist!", pathToDictionaryFile, Default_Dictionary_File));
                        System.exit(1);
                    } else {
                        pathToDictionaryFile = new File(Default_Dictionary_File).getAbsolutePath();
                        System.err.println(String.format("File %s does not exist! We Use Default Dictionary %s", pathToDictionaryFile, Default_Dictionary_File));
                    }
                }
            } else {
                if (!new File(Default_Dictionary_File).exists()) {
                    System.err.println(String.format("File %s does not exist!", pathToDictionaryFile));
                    System.exit(1);
                } else {
                    pathToDictionaryFile = new File(Default_Dictionary_File).getAbsolutePath();
                    System.out.println(String.format("We Use Default Dictionary %s", pathToDictionaryFile));
                }
            }
            if (!params.get(Arguments.i).isEmpty()) {
                pathToInputFile = params.get(Arguments.i).get(0);
                if (!new File(pathToInputFile).exists()) {
                    System.err.println(String.format("File %s does not exist!", pathToInputFile));
                    System.exit(1);
                } else {
                    phoneWordsCombination.readPhoneNumber(pathToInputFile);
                }
            } else {
                if (!params.get(Arguments.num).isEmpty()) {
                    phoneWordsCombination.readPhoneNumberFromInp(params.get(Arguments.num));
                }
            }
            phoneWordsCombination.findWordsInPhoneNumbers(pathToDictionaryFile);
//            phoneWordsCombination.findWordsInPhoneNumbers(ReadWriteTextFile.fr_english);
            phoneWordsCombination.phoneWordsGenerator(TimeUnit.MILLISECONDS.convert(TimeOut, TimeUnit.MILLISECONDS));
        } else {
            System.err.println("Error occurred when parsing input arguments.");
            printUsage();
            System.exit(1);
        }

    }

}
