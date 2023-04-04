package com.example.search.controller;

import com.example.search.domain.SearchResult;
import com.example.search.service.SearchService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@RestController
@Api(value = "Search API", description = "Operations for searching")
public class SearchController {
    private final SearchService searchService;
    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }
    @GetMapping("/weather/search")
    @ApiOperation(value = "Search for students and entries", response = SearchResult.class)
    @HystrixCommand(fallbackMethod = "fallbackSearch")
    public ResponseEntity<?> search() throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(searchService.getSearchResult(), HttpStatus.OK);
    }

    public ResponseEntity<SearchResult> fallbackSearch() {
        // Fallback method implementation
        return ResponseEntity.ok(new SearchResult(new ArrayList<>(), new ArrayList<>()));
    }
}
