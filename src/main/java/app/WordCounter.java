package app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordCounter {

    protected void wordCounter(List<String> wordsToAnalyze, Map<String, Integer> wordsMap) {
        for (String word : wordsToAnalyze) {
            if (wordsMap.containsKey(word)) {
                wordsMap.put(word, wordsMap.get(word) +1);
            } else {
                wordsMap.put(word, 1);
            }
        }
    }

    protected List<String> splitTweetBatchToListOfWords(List<String> rawTweetsBatch) {
        try {
            List<String> wordsArray = new ArrayList<>();
            for (String rawTweet : rawTweetsBatch) {
                String[] cleanWords = rawTweet.split("\\W+");
                for (String word : cleanWords) {
                    String cleanWord = word.trim().toLowerCase();
                    if (!Main.STOP_WORDS_SET.contains(cleanWord)) {
                        wordsArray.add(cleanWord);
                    }
                }
            }
            return wordsArray;
        } catch (Exception e) {
            System.out.println("split tweets to words error - " + e.getMessage());
            return null;
        }
    }
}
