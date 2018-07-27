package com.aconex.phonewords.utils;

import com.aconex.phonewords.PhoneWords;
import com.aconex.phonewords.entities.Arguments;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CommandLineUtils {

    /**
     * Parses an array of strings and returns a map containing Arguments as keys
     * and a lists of argument options as values.
     *
     * @param args {@link String} array of Arguments to be parsed.
     * @return {@link Map} containing Arguments as keys and a lists of argument
     * options as values, or null.
     */
    public static final Map<Arguments, List<String>> parseCommandLine(String[] args) {
        final Map<Arguments, List<String>> params = new HashMap<>();

        for (Arguments op : Arguments.values()) {
            params.put(op, new ArrayList<>());
        }

        for (int i = 0; i < args.length; i++) {
            final String a = args[i];
            if (a.charAt(0) == '-') {
                if (a.length() < 2) {
                    System.out.println("Error at argument " + a);
                    return null;
                } else {
                    if (!contains(a.substring(1))) {
                        System.out.println("Error at argument " + a);
                        return null;
                    }
                }

                if (i < args.length - 1) {
//                    if(PhoneWords.Arguments.valueOf(a.substring(1)))
                    for (Arguments op : Arguments.values()) {
                        if (op.name().equals(a.substring(1))) {
                            params.get(op).add(args[++i]);
                            break;
                        }
                    }

                }

            } else {

                params.get(Arguments.num).add(a);
            }

        }
        return params;
    }

    public static boolean contains(String test) {

        for (Arguments c : Arguments.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }

        return false;
    }
}
