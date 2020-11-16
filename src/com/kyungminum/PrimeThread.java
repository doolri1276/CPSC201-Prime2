package com.kyungminum;

public class PrimeThread extends Thread {
    private int lo;
    private int hi;
    private long nTime;
    private int pCount;

    public PrimeThread(int lo, int hi) {
        if (lo % 2 == 0)
            lo++;
        this.lo = lo;
        this.hi = hi;
    }

    public boolean isPrime(int n) {
        if (n % 2 == 0) {
            return false;
        }
        int limit = (int) Math.ceil(Math.sqrt((double) n));
        for (int divisor = 3; divisor <= limit; divisor += 2) {
            if (n % divisor == 0) {
                return false;
            }
        }
        return true;
    }

    public void run() {
        long start = System.nanoTime();
        for (int num = lo; num <= hi; num += 2) {
            if (isPrime(num)) {
                pCount++;
                //System.out.println("" + num);
            }
        }
        long end = System.nanoTime();
        nTime = end - start;
    }

    public int getPCount() {
        return pCount;
    }

    public long getNTime() {
        return nTime;
    }

    public double getSTime() {
        return nTime / 1000000000.0;
    }
}
