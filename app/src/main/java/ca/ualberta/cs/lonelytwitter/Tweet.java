package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by SarahS on 2018/01/17.
 */

public abstract class Tweet extends Object implements Tweetable, MyObservable {
    private String message;
    protected Date date;

    public Tweet(String message){
        this.message = message;
        this.date = new Date();
    }

    public Tweet(String message, Date date){
        this.message = message;
        this.date = date;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message) throws TweetTooLongException {
        if (message.length() <= 140) {
            this.message = message;
        } else {
            throw new TweetTooLongException();
        }
        notifyAllObservers();
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){

        this.date = date;
        notifyAllObservers();
    }

    public abstract Boolean isImportant();

    @Override
    public String toString() {return date.toString() + " | " + message; }

    private volatile ArrayList<MyObserver> observers = new ArrayList<MyObserver>();

    public void addObserver(MyObserver observer) {
        observers.add(observer);
    }

    private void notifyAllObservers() {
        for (MyObserver observer : observers) {
            observer.myNotify(this);
        }
    }

}
