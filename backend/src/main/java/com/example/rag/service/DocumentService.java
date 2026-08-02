package com.example.rag.service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.rag.dto.UploadResponse;
import java.io.IOException;
import java.util.List;

import com.example.rag.model.DocumentChunk;

@Service
public class DocumentService {

    private final TextCleanupService textCleanupService;
    private final ChunkingService chunkingService;

    public DocumentService(TextCleanupService textCleanupService,
        ChunkingService chunkingService) {
        this.textCleanupService = textCleanupService;
        this.chunkingService = chunkingService;
    }

    public UploadResponse upload(MultipartFile file) {

        try (PDDocument document = Loader.loadPDF(file.getBytes())) {

            PDFTextStripper stripper = new PDFTextStripper();
        
            String extractedText = stripper.getText(document);
        
            String cleanedText = textCleanupService.clean(extractedText);
        
            List<DocumentChunk> chunks = chunkingService.chunk(cleanedText, 500);

            chunks.forEach(chunk ->
                System.out.println(
                        "Chunk "
                        + chunk.getChunkNumber()
                        + " tokens="
                        + chunk.getTokenCount()
                )
            );
        
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