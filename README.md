#1-800-CODING-CHALLENGE


### Instructions
Project should be built and run using Java 1.8.

Required program arguments and values: ```-d <absolute path without spaces to the dictionary file> -i <absolute path without spaces to the input file with phone numbers>``` 

Example: 

*  Windows ```-d C:\temp\dictionary.txt -i C:\temp\input.txt```
*  Linux ```-d /tmp/dictionary.txt -i /tmp/input.txt```


Dictionary files for test obtained and generated from these sites:

https://www.wordgamedictionary.com/english-word-list/download/english.txt

https://raw.githubusercontent.com/marcoagpinto/aoo-mozilla-en-dict/master/en_AU%20(Kevin%20Atkinson)/wordlist_kevin_en_AU_20180416_123599w.txt


we set some assumption about size and timeout
that set in Utilty.class

    regexp_words_with_length_4_50 for finding words with length from 4 till 50 
    TimeOut=100l timeout for recursive function to return for finding phonewords for each number
    MAX_PHONE_NUMBER_LENGTH = 50;


From IDE execute *com.aconex.phonewords.PhoneWords.main()* passing the required arguments.

Alternatively build with Maven ```mvn clean install``` and execute in the terminal 
```java -jar target/1-800-CODING-CHALLENGE-1.0-ACONEX.jar  -d path_to_dictionary -i path_to_phone_numbers_file``` 

