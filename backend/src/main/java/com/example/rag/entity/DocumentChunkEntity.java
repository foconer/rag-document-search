package com.example.rag.entity;

import jakarta.persistence.*;
import com.pgvector.PGvector;

@Entity
@Table(name = "document_chunks")
public class DocumentChunkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer chunkNumber;

    @Column(nullable = false)
    private Integer tokenCount;

    @Column(columnDefinition = "vector(768)")
    private PGvector embedding;

    public DocumentChunkEntity() {
    }

    public DocumentChunkEntity(
            String content,
            Integer chunkNumber,
            Integer tokenCount,
            PGvector embedding) {
        this.content = content;
        this.chunkNumber = chunkNumber;
        this.tokenCount = tokenCount;
        this.embedding = embedding;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Integer getChunkNumber() {
        return chunkNumber;
    }

    public Integer getTokenCount() {
        return tokenCount;
    }

    public PGvector getEmbedding() {
        return embedding;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setChunkNumber(Integer chunkNumber) {
        this.chunkNumber = chunkNumber;
    }

    public void setTokenCount(Integer tokenCount) {
        this.tokenCount = tokenCount;
    }

    public void setEmbedding(PGvector embedding) {
        this.embedding = embedding;
    }
}