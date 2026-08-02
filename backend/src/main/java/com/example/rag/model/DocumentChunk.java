package com.example.rag.model;

public class DocumentChunk {

    private int chunkNumber;
    private String content;
    private int tokenCount;

    public DocumentChunk(
            int chunkNumber,
            String content,
            int tokenCount) {

        this.chunkNumber = chunkNumber;
        this.content = content;
        this.tokenCount = tokenCount;
    }

    public int getChunkNumber() {
        return chunkNumber;
    }

    public String getContent() {
        return content;
    }

    public int getTokenCount() {
        return tokenCount;
    }
}
