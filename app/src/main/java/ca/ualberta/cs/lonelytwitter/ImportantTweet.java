package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by SarahS on 2018/01/18.
 */

public class ImportantTweet extends Tweet {
    public ImportantTweet(String tweet, Date date) {
        super(tweet, date);
        this.setMessage(tweet);
        this.date = date;
    }

    public  ImportantTweet(String tweet) {super(tweet+"!!!");}

    public Boolean isImportant(){return Boolean.TRUE;}

    @Override
    public String getMessage() {return "!!!" + super.getMessage();}
}
