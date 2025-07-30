package info.batey.cpuquota;

import java.util.concurrent.ForkJoinPool;

public class CpuQuota {
    public static void main(String[] args) {
        System.out.println("Running Java Version: " + System.getProperty("java.version"));
        int numberOfCores = Runtime.getRuntime().availableProcessors();
        System.out.printf("I have %d cores. I am going to create my thread pool with that many threads.\n", numberOfCores);

        // This is what lots of Java frameworks do!
        // Jetty, Tomcat do it for acceptor/selector pools
        // Akka/Ratpack and all likely any other async framework you use will do it for their main pool

        // Internal thread pools for GC & compilation

        System.out.println("How about the JVMs common pool?");
        System.out.printf("Parallelism: %d\n", ForkJoinPool.commonPool().getParallelism());
    }
}
