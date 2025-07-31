package br.com.alura.literalura.service;

import br.com.alura.literalura.model.Livro;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class GutendexClient {
    private final HttpClient client;
    private final ObjectMapper mapper = new ObjectMapper();

    public GutendexClient() {
        this.client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS) // Seguir redirecionamentos automaticamente
                .build();
    }

    public Livro buscarLivroPorTitulo(String titulo) throws Exception {
        // Usar https://www.gutendex.com como base
        String url = "https://www.gutendex.com/books?search=" + titulo.replace(" ", "%20");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Log para depuração
        System.out.println("URL: " + url);
        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Erro na requisição: " + response.statusCode());
        }

        // Parse JSON
        var jsonNode = mapper.readTree(response.body());
        var results = jsonNode.get("results");
        if (results == null || results.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o título: " + titulo);
            return null;
        }

        // Pega o primeiro livro da lista
        return mapper.treeToValue(results.get(0), Livro.class);
    }
}