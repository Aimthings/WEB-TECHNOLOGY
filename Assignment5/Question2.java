public class Question2{

    private final static int MAX = 100000;

    private volatile static int maxDivisorCount = 0;

    private volatile static int intWithMaxDivisorCount;

    private static void checkMax(int maxCountFromThread, int intWithMaxFromThread) {
        if (maxCountFromThread > maxDivisorCount) {
            maxDivisorCount = maxCountFromThread;
            intWithMaxDivisorCount = intWithMaxFromThread;
        }
    }

    private static class CountDivisorsThread extends Thread {
        int min, max;
        public CountDivisorsThread(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public void run() {

            int maxDivisors = 0;
            int whichInt = 0;
            for (int i = min; i < max; i++) {				//from min to max, check the number of divisors the dumb way
                int divisors = countDivisors(i);
                if (divisors > maxDivisors) {
                    maxDivisors = divisors;
                    whichInt = i;
                }
            }
            checkMax(maxDivisors,whichInt);				//compare with the global max, since each thread returns it's local max
        }
    }

    private static void countDivisorsWithThreads(int numberOfThreads) {
        System.out.println("\nCounting divisors using " + numberOfThreads + " threads");

        long startTime = System.currentTimeMillis();

        CountDivisorsThread[] subThread = new CountDivisorsThread[numberOfThreads];
        int integersPerThread = MAX/numberOfThreads;
        int start = 1;
        int end = start + integersPerThread - 1;
        for (int i = 0; i < numberOfThreads; i++) {
            if (i == numberOfThreads - 1) {
                end = MAX;
            }
            subThread[i] = new CountDivisorsThread( start, end );
            start = end+1;
            end = start + integersPerThread - 1;
        }

        maxDivisorCount = 0;
        for (int i = 0; i < numberOfThreads; i++)
            subThread[i].start();


        for (int i = 0; i < numberOfThreads; i++) {
            while (subThread[i].isAlive()) {
                try {
                    subThread[i].join();
                }
                catch (InterruptedException e) {
                }
            }
        }
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("\nThe largest number of divisors " + "for numbers between 1 and " + MAX + " is " + maxDivisorCount);
        System.out.println("An integer with that many divisors is " + intWithMaxDivisorCount);
        System.out.println("Total elapsed time:  " + (elapsedTime/1000.0) + " seconds.\n");
    }

    public static int countDivisors(int N) {
        int count = 0;
        for (int i = 1; i <= N ; i++) {
            if ( N % i == 0 )
                count ++;
        }
        return count;
    }

    public static void countDivisorsNaive() {

        double startTime = System.currentTimeMillis();
        int divCount = 0;
        long whatNum = 0;
        for(long i=1; i<=100000; i++) {
            int currCount=0;
            for(int j=1; j<=i; j++) {
                if(i%j==0) {
                    currCount++;
                }
            }
            if(currCount>divCount) {
                divCount=currCount;
                whatNum=i;
            }
        }

        double timeUsed = System.currentTimeMillis() - startTime;

        System.out.println("\nDoing the Naive way, the number with max. divisors is: "+whatNum+" with: "+divCount+" divisors");
        System.out.println("Time required for Naive way is: " + (timeUsed/1000) + " seconds");
    }
    public static void main(String[] args) {
        int numberOfThreads = 10;

        System.out.println("Counting using Naive way:\n");
        countDivisorsNaive();
        countDivisorsWithThreads(numberOfThreads);
    }


}