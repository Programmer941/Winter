package com.winterclient.gui.animation;

import static com.winterclient.gui.animation.Easings.getEasingValue;

public class Animation {

    boolean increasing;

    public float value, start, end, delay, difference;

    private long lastTime;

    private float changePerMillisecond;

    private Easing easing;

    public Animation(float startingValue) {
        this.start = startingValue;
        this.value=startingValue;
        this.end = startingValue;
    }

    public void goTo(float goalValue, float duration, float delay, Easing easing) {
        this.start = getValue();
        this.value=start;
        this.end = goalValue;
        this.increasing = end > start;
        this.difference = Math.abs(end - start);
        this.changePerMillisecond = difference / (long) (duration * 1000);
        this.lastTime = System.currentTimeMillis();
        this.delay = delay * 1000;
        this.easing = easing;
    }

    public void goTo(float goalValue, float duration, float delay) {
        goTo(goalValue, duration, delay, Easing.EASE_OUT_SINE);
    }

    public void goTo(float goalValue, float duration) {
        goTo(goalValue, duration, 0);
    }


    private float linearValue() {
        if (value == end) {
            return value;
        }

        float difference = (changePerMillisecond) * (System.currentTimeMillis() - lastTime);

        if (increasing) {
            value += difference;

            if (value > end) {
                value = end;
            }

        } else {
            value -= difference;

            if (value < end) {
                value = end;
            }

        }
        this.lastTime = System.currentTimeMillis();
        return value;
    }

    public float getValue() {

if(delay>0){
    delay-=(System.currentTimeMillis() - lastTime);
    this.lastTime = System.currentTimeMillis();
    return value;
}

        if (easing == Easing.LINEAR) {
            return linearValue();
        }

        if (value == end) {
            return end;
        }

        float dif = end - start;
        return getEasingValue((linearValue() - start) / dif, easing) * dif + start;
    }

    public void setValue(float v){
        this.start = v;
        this.value=v;
        this.end = v;
    }

    public boolean finished(){
        return end==getValue();
    }

}
