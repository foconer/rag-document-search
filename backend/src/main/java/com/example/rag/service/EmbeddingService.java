package com.example.rag.service;

import java.util.List;

public interface EmbeddingService {

    List<Double> createEmbedding(String text);

}
