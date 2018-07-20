package com.aconex.phoneword.parameters;
import com.aconex.phoneword.parameters.EnglishParameters;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EnglishParametersTest {
    //Number to letter mapping number literals correspond to the EXPECTED_MAPPINGS lists index;
    public static final List<Set<String>> EXPECTED_MAPPINGS = Collections.unmodifiableList(
            new ArrayList<Set<String>>() {{
//                //0
//                add(Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("0"))));
//                //1
//                add(Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("1"))));
                //2
                add(Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("A", "B", "C"))));
                //3
                add(Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("D", "E", "F"))));
                //4
                add(Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("G", "H", "I"))));
                //5
                add(Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("J", "K", "L"))));
                //6
                add(Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("M", "N", "O"))));
                //7
                add(Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("P", "Q", "R", "S"))));
                //8
                add(Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("T", "U", "V"))));
                //9
                add(Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("W", "X", "Y", "Z"))));
            }}
    );

    @Test
    public void testAllParametersClassFieldsAreFinal(){
        //Loop trough all the fields and prove they are declared as final.
        for(Field field : EnglishParameters.class.getDeclaredFields()){
            assertTrue(String.format(
                    "%s class field %s must be declared as final!", EnglishParameters.class.getSimpleName(), field.getName()),
                    Modifier.isFinal(field.getModifiers()));
        }
    }

    @Test
    public void testMappingLettersAreImmutable(){
        //Prove it is not possible to modify the existing letters in the mapping.
        for (Set<String> letters : EnglishParameters.DIGIT_TO_LETTER_MAPPING.values()){
            //Try adding new items.
            try {
                letters.add("MUTABLE");
                fail(String.format(
                        "Adding new items to the DIGIT_TO_LETTER_MAPPING letters set %s must not be allowed.",
                        letters
                ));
            } catch (UnsupportedOperationException e){
                continue;
            }
            //Try removing existing items.
            try {
                //Try removing the lowest index as it must always exist.
                letters.remove(0);
                fail(String.format(
                        "Removing existing items from the DIGIT_TO_LETTER_MAPPING letters set %s must not be allowed.",
                        letters
                ));
            } catch (UnsupportedOperationException e){
                continue;
            }
        }
    }

    @Test
    public void testMappingNumbersAreImmutable(){
        //Prove it is not possible to modify the existing number mapping.
        //Loop through all the keys(numbers) and try to update each of them.
        for (Integer number : EnglishParameters.DIGIT_TO_LETTER_MAPPING.keySet()){
            //Try updating exiting items.
            try {
                EnglishParameters.DIGIT_TO_LETTER_MAPPING.put(number, new HashSet<String>(Arrays.asList("MUTABLE")));
                fail(String.format(
                        "Updating existing key %s in the DIGIT_TO_LETTER_MAPPING numbers map must not be allowed.",
                        number
                ));
            } catch (UnsupportedOperationException e){
                continue;
            }
            //Try removing existing numbers.
            try {
                EnglishParameters.DIGIT_TO_LETTER_MAPPING.remove(number);
                fail(String.format(
                        "Removing existing key %s from the DIGIT_TO_LETTER_MAPPING numbers map must not be allowed.",
                        number
                ));
            } catch (UnsupportedOperationException e){
                continue;
            }
        }
    }

    @Test
    public void testAllNumberToLetterMappingsAreCorrect(){
        //Prove that the expected and actual mappings have the same length.
        assertThat(String.format("There must be %s numbers in the DIGIT_TO_LETTER_MAPPING!", EXPECTED_MAPPINGS.size()),
                EnglishParameters.DIGIT_TO_LETTER_MAPPING.values().size(), is(EXPECTED_MAPPINGS.size()));
        //Loop through the expected and actual number mappings and prove that they have the same letters.
        for (Integer number : EnglishParameters.DIGIT_TO_LETTER_MAPPING.keySet()){
            Set<String> currentExpectedMapping = EXPECTED_MAPPINGS.get(number);
            Set<String> currentActualMapping = EnglishParameters.DIGIT_TO_LETTER_MAPPING.get(number);
            assertThat(String.format("Number %s must be mapped to %d letter(s)", number, currentExpectedMapping.size()),
                    currentActualMapping.size(), is(currentExpectedMapping.size()));
            //Prove that the actual letters are equal to the expected letters.
            assertEquals(String.format("Expected %s and actual %s letters in the mapping for number %s must be equal.",
                    currentExpectedMapping, currentActualMapping, number),
                    currentExpectedMapping, currentActualMapping);
        }
    }
}
