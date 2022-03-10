package model.tweet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtendedTweet {
    @JsonProperty("full_text")
    private String fullText;
    @JsonProperty("display_text_range")
    private List<Integer> displayTextRange;

    public ExtendedTweet() {
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public List<Integer> getDisplayTextRange() {
        return displayTextRange;
    }

    public void setDisplayTextRange(List<Integer> displayTextRange) {
        this.displayTextRange = displayTextRange;
    }

    @Override
    public String toString() {
        return "ExtendedTweet{" +
                "extended_tweet='" + fullText + '\'' +
                ", display_text_range=" + displayTextRange +
                '}';
    }
}
