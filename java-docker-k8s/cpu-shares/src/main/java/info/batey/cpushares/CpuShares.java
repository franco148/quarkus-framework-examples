package info.batey.cpushares;

import java.util.concurrent.ForkJoinPool;

public class CpuShares {
    public static void main(String[] args) {
        int numberOfCores = Runtime.getRuntime().availableProcessors();
        System.out.printf("I have %d cores. I am going to create my thread pool with that many threads.\n", numberOfCores);

        // This is what lots of Java frameworks do!
        // Jetty, Tomcat do it for acceptor/selector pools
        // Akka/Ratpack and all likely any other async framework you use will do it for their main pool

        System.out.printf("This is the size of the fork join pool used to run your streams: %d\n", ForkJoinPool.commonPool().getParallelism());
    }
}
