package com.example.rag.service;

import org.springframework.stereotype.Service;

@Service
public class TextCleanupService {

    public String clean(String text) {

        return text
                .replaceAll("\\s+", " ")
                .trim();
    }
}