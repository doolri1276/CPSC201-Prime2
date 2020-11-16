package com.kyungminum;

public class PrimeWorkBiteTester {

    private static int threadNum=4;
    private static int range = 10000000;
    private static int biteSize = 25000;



    public static void main(String[] args) {
        double dualTime = runDual();
        runMulti(dualTime);

    }

    public static double runDual(){
        PrimeWork work = new PrimeWork(3, range, biteSize);
        PrimeThreadBite[] threads= new PrimeThreadBite[2];
        for(int i=0;i<threads.length;i++){
            threads[i] = new PrimeThreadBite(work, "THREAD "+(i+1));

        }

        long start = System.nanoTime();

        for(PrimeThreadBite thread: threads)
            thread.start();


        Thread.currentThread().yield();


        try{
            for(PrimeThreadBite thread: threads) {
                thread.join();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.nanoTime();
        long nTime = end-start;

        System.out.println("\n\n[The Program Results]");
        System.out.println("===============================================");

        for(PrimeThreadBite thread: threads)
            thread.print();

        int totalPrimes=0;
        long totalTime=0;

        for(PrimeThreadBite thread: threads)
            totalPrimes+=thread.getpCount();

        for(PrimeThreadBite thread: threads)
            totalTime+=thread.getSTimeDouble();

        System.out.println("RANGE: "+range+" BITE SIZE: "+biteSize);
        System.out.println("TOTAL PRIMES: "+totalPrimes);
        System.out.println("TOTAL THREAD TIME: "+totalTime);
        System.out.println("RUNNING TIME: "+getsTime(nTime));

        return getsTimeDouble(nTime);
    }

    public static void runMulti(double dualTime){
        PrimeWork work = new PrimeWork(3, range, biteSize);
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

        System.out.println("\n\n[The Program Results]");
        System.out.println("===============================================");

        for(PrimeThreadBite thread: threads)
            thread.print();

        int totalPrimes=0;
        double totalTime=0;

        for(PrimeThreadBite thread: threads)
            totalPrimes+=thread.getpCount();

        for(PrimeThreadBite thread: threads)
            totalTime+=thread.getSTimeDouble();

        System.out.println("RANGE: "+range+" BITE SIZE: "+biteSize);
        System.out.println("TOTAL PRIMES: "+totalPrimes);
        System.out.println("TOTAL THREAD TIME: "+totalTime);
        System.out.println("RUNNING TIME: "+getsTime(nTime));
        System.out.println("SPEED UP: "+(dualTime- getsTimeDouble(nTime)));

    }

    public static String getsTime(long nTime){
        return String.format("%7.5f", nTime/1000000000.0);
    }

    public static double getsTimeDouble(long nTime){
        return  (nTime/1000000000.0);
    }
}
