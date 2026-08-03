package com.example.rag.service;

import com.example.rag.dto.SearchResult;
import com.example.rag.entity.DocumentChunkEntity;
import com.example.rag.repository.DocumentChunkRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SearchService {

    private final OllamaEmbeddingService ollamaEmbeddingService;
    private final DocumentChunkRepository documentChunkRepository;

    public SearchService(
            OllamaEmbeddingService ollamaEmbeddingService,
            DocumentChunkRepository documentChunkRepository) {

        this.ollamaEmbeddingService = ollamaEmbeddingService;
        this.documentChunkRepository = documentChunkRepository;
    }

    public List<SearchResult> search(String query) {

        // Generate embedding for user question
        List<Double> embedding =
                ollamaEmbeddingService.createEmbedding(query);

        float[] vector = new float[embedding.size()];

        for (int i = 0; i < embedding.size(); i++) {
            vector[i] = embedding.get(i).floatValue();
        }
        
        String vectorstr = Arrays.toString(vector);

        List<DocumentChunkEntity> chunks =
                documentChunkRepository.findSimilar(vectorstr);

        return chunks.stream()
                .map(chunk -> new SearchResult(
                        chunk.getContent(),
                        null))
                .toList();
    }
}