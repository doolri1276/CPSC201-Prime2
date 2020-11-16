package com.kyungminum;

public class PrimeThreadBite extends Thread{

    private int lo;
    private int hi;
    private long nTime;
    private int pCount;
    private int bCount;
    private PrimeWork myWork;

    public PrimeThreadBite(PrimeWork myWork, String name){
        super(name);
        this.myWork = myWork;
    }

    public boolean isPrime(int n){
        if(n%2==0) return false;
        int limit = (int)Math.ceil(Math.sqrt((double)n));

        for(int divisor = 3; divisor <= limit; divisor +=2)
            if(n % divisor == 0)
                return false;

        return true;
    }

    @Override
    public void run() {
        super.run();

        long start = System.nanoTime();

        while(myWork.moreWork()){
            System.out.println(currentThread().getName() + " getting a bite");
            Range range = myWork.getWork();
            if(range == null)
                break;
            for (int num = range.lo; num<= range.hi; num+=2){
                if(isPrime(num)){
                    pCount++;
                }
            }
        }

        long end = System.nanoTime();
        nTime = end - start;
    }

    public int getpCount() {
        return pCount;
    }

    public long getnTime() {
        return nTime;
    }

    public String getSTime(){
        return String.format("%7.5f",nTime/1000000000.0);
    }
}
