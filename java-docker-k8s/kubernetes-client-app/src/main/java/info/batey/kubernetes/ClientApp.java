package info.batey.kubernetes;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.TimeUnit;

public class ClientApp {
    public static void main(String[] args) throws Exception {
        var host = System.getenv("SERVER_APP_SERVICE_HOST");
        var port = Integer.parseInt(System.getenv("SERVER_APP_SERVICE_PORT"));
        var hostPort = String.format("http://%s:%d", host, port);
        System.out.println("Connecting to " + hostPort);
        while (true) {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(hostPort))
                    .build();
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(System.out::println)
                    .join();
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
