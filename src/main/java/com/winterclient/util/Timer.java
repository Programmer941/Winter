package com.winterclient.util;

public class Timer {

    private boolean isFinished;
    private int waitTime;
    private long endTime;

    public Timer(int time){
        waitTime=time;
        endTime= System.currentTimeMillis()+time*1000;
    }

    public void reset(){
        endTime= System.currentTimeMillis()+waitTime*1000;
    }

    public boolean isFinished(){
        return System.currentTimeMillis()>endTime;
    }
}
