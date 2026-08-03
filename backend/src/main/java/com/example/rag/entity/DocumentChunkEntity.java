package com.example.rag.entity;

import jakarta.persistence.*;

import org.hibernate.annotations.Array;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

    @JdbcTypeCode(SqlTypes.VECTOR)
    @Array(length = 768) // Match your embedding dimensions exactly (e.g., 768 or 1536)
    @Column(name = "embedding")
    private float[] embedding;

    public DocumentChunkEntity() {
    }

    public DocumentChunkEntity(
            String content,
            Integer chunkNumber,
            Integer tokenCount,
            float[] embedding) {
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

    public  float[] getEmbedding() {
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

    public void setEmbedding(float[] embedding) {
        this.embedding = embedding;
    }
}