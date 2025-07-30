package info.batey.debug;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class ServerApp {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting server...");
        HttpServer server = HttpServer.create(
                new InetSocketAddress("0.0.0.0", 8080), 100
        );
        server.createContext("/ready", new StringResponse("ready"));
        server.createContext("/alive", new StringResponse("alive"));
        server.createContext("/", new StringResponse("hello from server app"));
        server.start();
        System.out.println("Server started!");
        startMemoryLeak();
    }

    private static void startMemoryLeak() throws Exception {
        List<byte[]> allTheData = new ArrayList<>();
        while (true) {
            System.out.println("Allocating 1MB");
            allTheData.add(new byte[1_000_000]);
            Thread.sleep(1000);
        }
    }

    private static class StringResponse implements HttpHandler {
        private final String response;
        private StringResponse(String response) {
            this.response = response;
        }
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            OutputStream outputStream = httpExchange.getResponseBody();
            httpExchange.sendResponseHeaders(200, response.length());
            outputStream.write(response.getBytes());
            outputStream.flush();
            outputStream.close();
        }
    }
}
