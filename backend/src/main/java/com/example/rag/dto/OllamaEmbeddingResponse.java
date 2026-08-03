package com.example.rag.dto;

import java.util.List;

public record OllamaEmbeddingResponse(
        List<List<Double>> embeddings
) {
}