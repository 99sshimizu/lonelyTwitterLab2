package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by SarahS on 2018/01/18.
 */

public class NormalTweet extends Tweet {
    public NormalTweet(String tweet, Date date){ super(tweet, date);}

    public NormalTweet(String tweet){super(tweet);}

    public Boolean isImportant() {return Boolean.FALSE;}
}
