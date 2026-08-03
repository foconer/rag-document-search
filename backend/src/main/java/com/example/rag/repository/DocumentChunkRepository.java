package com.example.rag.repository;

import com.example.rag.entity.DocumentChunkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface DocumentChunkRepository extends JpaRepository<DocumentChunkEntity, Long> {
    @Query(value = """
            SELECT *
            FROM document_chunks
            ORDER BY embedding <=> CAST(:embedding AS vector)
            LIMIT 5
            """, nativeQuery = true)
    List<DocumentChunkEntity> findSimilar(
            @Param("embedding") String embedding);
}