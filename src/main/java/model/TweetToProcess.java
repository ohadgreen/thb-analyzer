package model;

public class TweetToProcess {
    private TwitterPullTask pullTask;
    private String tweetAsString;
    private boolean isLastTweet;

    public TweetToProcess() {
    }

    public TweetToProcess(TwitterPullTask pullTask, String tweetAsString, boolean isLastTweet) {
        this.pullTask = pullTask;
        this.tweetAsString = tweetAsString;
        this.isLastTweet = isLastTweet;
    }

    public TwitterPullTask getPullTask() {
        return pullTask;
    }

    public void setPullTask(TwitterPullTask pullTask) {
        this.pullTask = pullTask;
    }

    public String getTweetAsString() {
        return tweetAsString;
    }

    public void setTweetAsString(String tweetAsString) {
        this.tweetAsString = tweetAsString;
    }

    public boolean isLastTweet() {
        return isLastTweet;
    }

    public void setLastTweet(boolean lastTweet) {
        isLastTweet = lastTweet;
    }
}
