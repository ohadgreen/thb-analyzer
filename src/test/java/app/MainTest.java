package app;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void loadStopWordsTest() throws IOException {
        Main main = new Main();
        main.loadStopWords();
        main.STOP_WORDS_SET.stream().limit(10).forEach(System.out::println);
        System.out.println("stop words count: " + main.STOP_WORDS_SET.size());
    }
}