package com.example.myapplication;

import java.util.Timer;
import java.util.TimerTask;

public class Clock {
    public int milSec;
    int sec =0;

    public Clock(int time) {
        this.milSec = time;
    }
//Just understanding how it works
    public void test() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                sec++;
                System.out.println("Seconds passed" + sec);

            }
        };
        timer.schedule(task, 1000,1000);
       
    }

}

