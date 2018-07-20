#Phone Number Encoder


### Instructions
Project should be built and run using Java 1.8.

Required program arguments and values: ```-d <absolute path without spaces to the dictionary file> -i <absolute path without spaces to the input file with phone numbers>``` 

Example: 

*  Windows ```-d C:\temp\dictionary.txt -i C:\temp\input.txt```
*  Linux ```-d /tmp/dictionary.txt -i /tmp/input.txt```

Dictionary files for test obtained and generated from these sites:

https://www.wordgamedictionary.com/english-word-list/download/english.txt
https://raw.githubusercontent.com/marcoagpinto/aoo-mozilla-en-dict/master/en_AU%20(Kevin%20Atkinson)/wordlist_kevin_en_AU_20180416_123599w.txt


we set some assumption about size and length

    MAX_ENGLISH_DICTIONARY_SIZE = 90000;

    MAX_ENGLISH_DICTIONARY_WORD_LENGTH = 50;

    MAX_PHONE_NUMBER_LENGTH = 50;


From IDE execute *com.aconex.numberencoding.lists.EncodedPhoneNumberList.main()* passing the required arguments.

Alternatively build with Maven ```mvn clean install``` and execute in the terminal 
```java -jar number-encoding-1.0-SNAPSHOT.jar -d path_to_dictionary -i path_to_phone_numbers_file``` 

