package org.lettsn.stoatWebhook;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SuppressWarnings("unused")
public class Webhook {
    private final URI uri;
    private final HttpClient client;

    public Webhook(String uri) throws URISyntaxException {
        this.uri = new URI(uri);
        this.client = HttpClient.newHttpClient();
    }

    public Webhook(String id, String token) throws URISyntaxException {
        String uriRaw = String.format("https://api.revolt.chat/webhooks/%s/%s", id, token);
        this.uri = new URI(uriRaw);
        this.client = HttpClient.newHttpClient();
    }

    public String getKey() {
        return String.valueOf((int) System.currentTimeMillis() / 1000);
    }

    public int sendMessage(Message message) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.uri.toString()))
                .POST(HttpRequest.BodyPublishers.ofString(message.toJson())) // GET is default
                .header("Idempotency-Key", String.valueOf(this.getKey()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode();
    }
}
