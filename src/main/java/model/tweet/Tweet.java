package model.tweet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tweet {
    @JsonProperty("created_at")
    private String createdAt;
    private String id_str;
    private String text;
    @JsonProperty("extended_tweet")
    private ExtendedTweet extendedTweet;
    private User user;
    private String geo;
    private Place place;
    private String lang;
    private int favouriteCount;
    private int retweetCount;

    public Tweet() {
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getId_str() {
        return id_str;
    }

    public void setId_str(String id_str) {
        this.id_str = id_str;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ExtendedTweet getExtendedTweet() {
        return extendedTweet;
    }

    public void setExtendedTweet(ExtendedTweet extendedTweet) {
        this.extendedTweet = extendedTweet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String language) {
        this.lang = language;
    }

    public int getFavouriteCount() {
        return favouriteCount;
    }

    public void setFavouriteCount(int favouriteCount) {
        this.favouriteCount = favouriteCount;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id_str='" + id_str + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", text='" + text + '\'' +
                ", extended_tweet=" + extendedTweet +
                ", user=" + user +
                ", lang='" + lang + '\'' +
                ", favouriteCount=" + favouriteCount +
                ", retweetCount=" + retweetCount +
                '}';
    }
}
