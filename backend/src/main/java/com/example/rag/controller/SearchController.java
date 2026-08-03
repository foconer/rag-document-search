package com.example.rag.controller;

import com.example.rag.dto.SearchResult;
import com.example.rag.service.SearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@CrossOrigin(origins = "*")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public List<SearchResult> search(
            @RequestParam String query) {

        return searchService.search(query);
    }
}