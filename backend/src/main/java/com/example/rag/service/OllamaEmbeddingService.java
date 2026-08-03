package com.example.rag.service;

import com.example.rag.dto.OllamaEmbeddingRequest;
import com.example.rag.dto.OllamaEmbeddingResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class OllamaEmbeddingService implements EmbeddingService {
    
    private final RestClient restClient;
    private final String model;

    public OllamaEmbeddingService(RestClient.Builder builder,
        @Value("${ollama.url:http://localhost:11434}") String ollamaUrl,
        @Value("${ollama.model:nomic-embed-text}") String model
    ) {
        this.restClient = builder
                .baseUrl(ollamaUrl)
                .build();
        
        this.model = model;
    }

    @Override
    public List<Double> createEmbedding(String text) {
        OllamaEmbeddingRequest request =
                new OllamaEmbeddingRequest(
                        model,
                        text
                );

        OllamaEmbeddingResponse response =
                restClient.post()
                        .uri("/api/embed")
                        .body(request)
                        .retrieve()
                        .body(OllamaEmbeddingResponse.class);

        return response.embeddings().get(0);
    }
}
