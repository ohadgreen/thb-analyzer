package model.tweet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String id_str;
    private String name;
    @JsonProperty("screen_name")
    private String screenName;
    @JsonProperty("profile_image_url")
    private String profileImageUrl;
    private int followers_count;

    public User() {
    }

    public String getId_str() {
        return id_str;
    }

    public void setId_str(String id_str) {
        this.id_str = id_str;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    @Override
    public String toString() {
        return "User{" +
                "id_str='" + id_str + '\'' +
                ", name='" + name + '\'' +
                ", screen_name='" + screenName + '\'' +
                ", profile_image_url='" + profileImageUrl + '\'' +
                ", followers_count=" + followers_count +
                '}';
    }
}
