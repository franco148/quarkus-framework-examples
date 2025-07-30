package info.batey.ergonomics;

import java.lang.management.ManagementFactory;

public class Ergonomics {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println("Total Memory: " + Runtime.getRuntime().totalMemory() / 1000000 + "Mb");
        System.out.println("Max Memory: " + Runtime.getRuntime().maxMemory() / 1000000 + "Mb");
        System.out.println("Free Memory: " + Runtime.getRuntime().freeMemory() / 1000000 + "Mb");
        long memorySize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory
                .getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
        System.out.println("RAM: " + memorySize / 1000000 + "Mb");
    }
}
