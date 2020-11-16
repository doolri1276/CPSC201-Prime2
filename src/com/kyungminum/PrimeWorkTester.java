package com.kyungminum;

public class PrimeWorkTester {



    public static void main(String[] args) {
        PrimeWork work = new PrimeWork(3, 1000, 80);

        while (work.moreWork())
            System.out.println(""+work.getWork());
    }
}
