package com.kyungminum;

public class Range {
    public int lo;
    public int hi;

    public Range(int lo, int hi) {
        this.lo=lo;
        this.hi=hi;
    }

    @Override
    public String toString() {
        return "["+lo+","+hi+"]";
    }
}
