package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

/**
 * Created by SarahS on 2018/01/18.
 */

public class TweetList implements MyObservable, MyObserver {
    private Tweet mostRecentTweet;
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();

    public void add(Tweet tweet) {
        mostRecentTweet = tweet;
        tweets.add(tweet);
    }

    public Tweet getMostRecentTweet() {return mostRecentTweet;}

    public int count() {return tweets.size();}

    private volatile ArrayList<MyObserver> observers = new ArrayList<MyObserver>();

    public void addObserver(MyObserver observer){ observers.add(observer); }

    private void notifyAllObservers() {
        for (MyObserver observer : observers) {
            observer.myNotify(this);
        }
    }

    public void myNotify(MyObservable observable) {notifyAllObservers();}
}
