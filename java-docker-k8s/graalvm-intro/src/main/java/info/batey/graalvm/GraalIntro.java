package info.batey.graalvm;

public class GraalIntro {
    public static void main(String[] args) {
        var javaVersion = System.getProperty("java.version");
        System.out.println("Hello from GraalVM. Java version: " + javaVersion);
    }
}
