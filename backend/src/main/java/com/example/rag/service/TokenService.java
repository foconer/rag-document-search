package com.example.rag.service;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final Encoding encoding;

    public TokenService() {
        this.encoding = Encodings.newDefaultEncodingRegistry()
                .getEncodingForModel("gpt-4")
                .orElseThrow(() -> 
                    new RuntimeException("Encoding not found"));
    }

    public int countTokens(String text) {
        return encoding.countTokens(text);
    }
}