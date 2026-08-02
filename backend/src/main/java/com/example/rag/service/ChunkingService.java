package com.example.rag.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.example.rag.model.DocumentChunk;

@Service
public class ChunkingService {

    private final TokenService tokenService;

    public ChunkingService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public List<DocumentChunk> chunk(String text, int maxTokens) {

        List<DocumentChunk> chunks = new ArrayList<>();

        String[] words = text.split("\\s+");

        StringBuilder currentChunk = new StringBuilder();

        int chunkNumber = 1;
        for (String word : words) {

            String candidate = currentChunk + " " + word;

            if (tokenService.countTokens(candidate) > maxTokens) {

                chunks.add(
                    new DocumentChunk(
                        chunkNumber++,
                        currentChunk.toString().trim(),
                        tokenService.countTokens(
                            currentChunk.toString()
                        )
                    )
                );

                currentChunk = new StringBuilder(word);

            } else {

                currentChunk.append(" ")
                        .append(word);
            }
        }

        if (!currentChunk.isEmpty()) {
            chunks.add(
                new DocumentChunk(
                    chunkNumber,
                    currentChunk.toString().trim(),
                    tokenService.countTokens(
                        currentChunk.toString()
                    )
                )
            );
        }

        return chunks;
    }
}