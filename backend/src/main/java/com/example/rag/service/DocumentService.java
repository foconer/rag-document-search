package com.example.rag.service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.rag.dto.UploadResponse;
import com.example.rag.entity.DocumentChunkEntity;

import java.io.IOException;
import java.util.List;

import com.example.rag.model.DocumentChunk;
import com.example.rag.repository.DocumentChunkRepository;
import com.pgvector.PGvector;

@Service
public class DocumentService {

    private final TextCleanupService textCleanupService;
    private final ChunkingService chunkingService;
    private final EmbeddingService embeddingService;
    private final DocumentChunkRepository documentChunkRepository;

    public DocumentService(TextCleanupService textCleanupService,
        ChunkingService chunkingService,
        EmbeddingService embeddingService,
        DocumentChunkRepository documentChunkRepository) {
        this.textCleanupService = textCleanupService;
        this.chunkingService = chunkingService;
        this.embeddingService = embeddingService;
        this.documentChunkRepository = documentChunkRepository;
    }

    public UploadResponse upload(MultipartFile file) {

        try (PDDocument document = Loader.loadPDF(file.getBytes())) {

            PDFTextStripper stripper = new PDFTextStripper();
        
            String extractedText = stripper.getText(document);
        
            String cleanedText = textCleanupService.clean(extractedText);
        
            List<DocumentChunk> chunks = chunkingService.chunk(cleanedText, 500);

            chunks.forEach(chunk -> {

                List<Double> embedding =
                        embeddingService.createEmbedding(
                                chunk.getContent()
                        );

                DocumentChunkEntity entity = new DocumentChunkEntity();

                entity.setContent(chunk.getContent());
                entity.setChunkNumber(chunk.getChunkNumber());
                entity.setTokenCount(chunk.getTokenCount());

                float[] vector = new float[embedding.size()];

                for (int i = 0; i < embedding.size(); i++) {
                  vector[i] = embedding.get(i).floatValue();
                }

                entity.setEmbedding(new PGvector(vector));

                documentChunkRepository.save(entity);
    
                System.out.println(
                        "Chunk "
                        + chunk.getChunkNumber()
                        + " tokens="
                        + chunk.getTokenCount()
                        + " embeddingSize="
                        + embedding.size()
                );
            });
        
            return new UploadResponse(
                    file.getOriginalFilename(),
                    document.getNumberOfPages(),
                    cleanedText.length(),
                    "UPLOADED"
            );
        
        } catch (IOException e) {
            throw new RuntimeException("Failed to process PDF", e);
        }
    }
}