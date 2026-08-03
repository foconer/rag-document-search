package com.example.rag.dto;

public record OllamaEmbeddingRequest (
    String model,
    String input
) {
}
