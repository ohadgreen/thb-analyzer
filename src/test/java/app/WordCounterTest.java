package app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class WordCounterTest {

    private WordCounter wordCounter = new WordCounter();
    private List<String> rawTweets;
    private List<String> cleanWords;

    @BeforeEach
    void init() {
        String t1 = "One Two Three. Four, 5, 678";
        String t2 = "as after of zero; Two Three Four Five!!";
        String t3 = "Three Four Five! SIX, Seven, Eight?, cats&dogs";
        String t4 = "six Seven, \n Eight, nine.ten El even";

        rawTweets = Arrays.asList(t1, t2, t3, t4);

        Main.STOP_WORDS_SET.add("as");
        Main.STOP_WORDS_SET.add("after");
        Main.STOP_WORDS_SET.add("of");

        cleanWords = Arrays.asList("one", "two", "three", "four", "zero", "two", "three", "four", "five", "three", "four", "five", "six", "seven", "eight", "cats", "dogs", "six", "seven", "eight", "nine", "ten");
    }

    @Test
    void splitTweetBatchToListOfWords() {
        List<String> words = wordCounter.splitTweetBatchToListOfWords(rawTweets);

        System.out.println(words);
        assertNotNull(words);
    }

    @Test
    void countWordsTest1() {
        Map<String, Integer> wordsMap = new HashMap<>();
        wordCounter.wordCounter(cleanWords, wordsMap);

        System.out.println(wordsMap);
        assertEquals(1, wordsMap.get("one"));
    }

    @Test
    void sortHashMapTest() {

        Map<String, Integer> unsortedMap = new HashMap<>();
        unsortedMap.put("Nine", 9);
        unsortedMap.put("Five", 5);
        unsortedMap.put("Seven", 7);
        unsortedMap.put("One", 1);
        unsortedMap.put("Eight", 8);
        unsortedMap.put("Two", 2);

        Map<String, Integer> sortedMap = unsortedMap.entrySet().stream()
                .sorted((k1, k2) -> -k1.getValue().compareTo(k2.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        System.out.println(sortedMap);

    }
}