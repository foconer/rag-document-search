package com.example.rag.dto;

public record SearchResult(
    String content,
    Double score
) {}
