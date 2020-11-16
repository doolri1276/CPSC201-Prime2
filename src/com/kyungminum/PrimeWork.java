package com.kyungminum;



public class PrimeWork {
    private int lo;
    private int hi;
    private int current;
    private int bite;
    private boolean complete;

    public PrimeWork(int min, int max, int bite){
        lo=min;
        hi=max;
        current=lo;
        this.bite=bite;

    }

    public /*synchronized*/ Range getWork(){
        Range range;
        synchronized(this) {
            if (!complete) {
                if (current + bite > hi) {
                    range = new Range(current, hi);
                    complete = true;
                } else {
                    range = new Range(current, current + bite - 1);
                    current += bite;
                }
                return range;
            }
        }
        return null;
    }

    public synchronized boolean moreWork(){
        return !complete;
    }
}
