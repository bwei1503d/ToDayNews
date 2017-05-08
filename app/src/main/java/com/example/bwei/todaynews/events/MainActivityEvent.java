package com.example.bwei.todaynews.events;

/**
 * Created by muhanxi on 17/5/1.
 */

public class MainActivityEvent {

    public boolean white ;

    public MainActivityEvent(boolean white){
        this.white = white;
    }


    public boolean isWhite() {
        return white;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }
}
