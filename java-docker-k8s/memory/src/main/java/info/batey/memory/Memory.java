package info.batey.memory;

import java.lang.management.ManagementFactory;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Memory {
    public static void main(String[] args) throws Exception {
        System.out.println("Running Java Version: " + System.getProperty("java.version"));
        if (args.length != 1) {
            System.out.println("Specify one of offheap heap or thread");
            System.exit(-1);
        }
        printMemoryUsage();
        switch (args[0]) {
            case "offheap":
                 allocateOffHeapMemory();
                 break;
            case "heap":
                allocateHeapMemory();
                break;
            case "thread":
                createThreads();
                break;
        }
    }

    public static void printMemoryUsage() {
        System.out.println("Total Memory: " + Runtime.getRuntime().totalMemory() / 1000000 + "Mb");
        System.out.println("Max Memory: " + Runtime.getRuntime().maxMemory() / 1000000 + "Mb");
        System.out.println("Free Memory: " + Runtime.getRuntime().freeMemory() / 1000000 + "Mb");
        long memorySize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory
                .getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
        System.out.println("RAM: " + memorySize / 1000000 + "Mb");
    }

    // Lots of libraries and frameworks do this!
    public static void allocateOffHeapMemory() throws Exception {
        System.out.println("Allocating offheap memory");
        List<ByteBuffer> allTheData = new ArrayList<>();
        while (true) {
            System.out.println("Allocating 100MB");
            allTheData.add(ByteBuffer.allocateDirect(100_000_000));
            Thread.sleep(1000);
        }
    }

    // Again, lots of frameworks create threads on demand with large potential pool sizes
    public static void createThreads() throws Exception {
        System.out.println("Starting thread creation");
        while (true) {
            System.out.println("Allocating 1000 threads. Heap size: " +  Runtime.getRuntime().totalMemory() / 1000000 + "Mb");
            for (int i = 0; i < 1000; i++) {
                new Thread(() -> {
                    try {
                        TimeUnit.HOURS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
            Thread.sleep(100);

        }
    }

    public static void allocateHeapMemory() throws Exception {
        System.out.println("Allocating heap memory");
        List<byte[]> allTheData = new ArrayList<>();
        while (true) {
            System.out.println("Allocating 100MB");
            allTheData.add(new byte[100_000_000]);
            Thread.sleep(1000);
        }
    }
}
