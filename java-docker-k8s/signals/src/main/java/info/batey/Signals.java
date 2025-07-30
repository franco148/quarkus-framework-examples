package info.batey;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Signals {
    public static void main(String[] args) throws Exception {
        System.out.println("Signal me..");

        var threadPool = Executors.newCachedThreadPool();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Initiating graceful shutdown");
            threadPool.shutdown();
            try {
                threadPool.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                System.err.println("Unable to shutdown gracefully: " + e.getMessage());
            }
            System.out.println("Shutting down");
        }));

        var scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Give me your application request");
            var task = scanner.nextLine();
            System.out.println("Executing important user request! " + task);
            threadPool.execute(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    System.err.println("Oh darn, I did not complete my task!");
                }
                System.out.println("Task finished: " + task);
            });
        }


    }




}
