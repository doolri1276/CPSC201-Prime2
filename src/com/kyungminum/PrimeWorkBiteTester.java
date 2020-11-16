package com.kyungminum;

import javax.swing.plaf.SplitPaneUI;

public class PrimeWorkBiteTester {

    private static int threadNum=4;



    public static void main(String[] args) {
        PrimeWork work = new PrimeWork(3, 1000000, 5000);
        PrimeThreadBite[] threads= new PrimeThreadBite[threadNum];
        for(int i=0;i<threads.length;i++){
            threads[i] = new PrimeThreadBite(work, "THREAD "+(i+1));
//            System.out.println("currentThread : "+Thread.currentThread().getName());
        }

        long start = System.nanoTime();

        for(PrimeThreadBite thread: threads)
            thread.start();

//        System.out.println("currentThread : "+Thread.currentThread().getName());
        Thread.currentThread().yield();
//        System.out.println("currentThread : "+Thread.currentThread().getName());

        try{
            for(PrimeThreadBite thread: threads) {
                thread.join();
//                System.out.println("currentThread : "+Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.nanoTime();
        long nTime = end-start;

        for(PrimeThreadBite thread: threads)
            thread.print();

        int totalPrimes=0;

        for(PrimeThreadBite thread: threads)
            totalPrimes+=thread.getpCount();

        System.out.println("\nMain # Seconds Used = "+getsTime(nTime));
        System.out.println("Total Primes : "+ totalPrimes);
    }

    public static String getsTime(long nTime){
        return String.format("%7.5f", nTime/1000000000.0);
    }
}
