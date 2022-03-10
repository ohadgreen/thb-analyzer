package app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.ConsumerInit;
import model.TweetToProcess;
import model.TwitterPullTask;
import model.tweet.Tweet;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    private static final String CONSUMER_GROUP = "twitter.analyzer";
    private final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final int TWEETS_PROCESS_BATCH_SIZE = 100;
    public static Set<String> STOP_WORDS_SET = new HashSet<>();

    public static void main(String[] args) throws IOException {
        System.out.println("java.version: " + System.getProperty("java.version"));
        Main main = new Main();
        main.loadStopWords();
        main.processRawTweets();
    }

    protected void loadStopWords() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("stop_words_en.txt").getFile());
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        for (String line; (line = reader.readLine()) != null;) {
            STOP_WORDS_SET.add(line);
        }
    }

    private void processRawTweets() throws JsonProcessingException {
        ConsumerInit twitterJobsConsumer = new ConsumerInit(CONSUMER_GROUP);
        KafkaConsumer<String, String> twitterJobsKafkaConsumer = twitterJobsConsumer.initConsumer();
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("Raw tweets consumer starting...");

        List<String> rawTweetsBatch = new ArrayList<>();
        int tweetsCount = 0;
        WordCounter wordCounter = new WordCounter();

        while (true) {
            ConsumerRecords<String, String> consumerRecords = twitterJobsKafkaConsumer.poll(Duration.ofMillis(1000));

            for (ConsumerRecord<String, String> rec : consumerRecords) {
                TweetToProcess tweetToProcess = objectMapper.readValue(rec.value(), TweetToProcess.class);
                tweetsCount ++;
                LOGGER.debug("processing {}, #{}", tweetToProcess.getPullTask().getId(), tweetsCount);

                String rawTweetAsString = tweetToProcess.getTweetAsString();
                Tweet tweet = objectMapper.readValue(rawTweetAsString, Tweet.class);
                String fullText = tweet.getExtendedTweet() == null ? tweet.getText() : tweet.getExtendedTweet().getFullText();

                rawTweetsBatch.add(fullText);

                if (tweetsCount % TWEETS_PROCESS_BATCH_SIZE == 0) {
                    List<String> words = wordCounter.splitTweetBatchToListOfWords(rawTweetsBatch);
                    Map<String, Integer> tweetsWordsMap = new HashMap<>();
                    wordCounter.wordCounter(words, tweetsWordsMap);
                    rawTweetsBatch = new ArrayList<>();
                    System.out.println("tweets: " + tweetsCount + " - " + tweetsWordsMap);
                }

                twitterJobsKafkaConsumer.commitSync();
            }
        }
    }
}
