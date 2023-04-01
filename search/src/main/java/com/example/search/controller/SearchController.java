package com.example.search.controller;

import com.example.homework.domain.Student;
import com.example.search.domain.SearchResult;
import com.example.springbootdemo.domain.Entry;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@RestController
@Api(value = "Search API", description = "Operations for searching")
public class SearchController {
    @Autowired
    private final RestTemplate restTemplate;
    private String entryServiceUrl = "http://localhost:8400/entry";
    private String studentServiceUrl = "http://localhost:8500/students";

    public SearchController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @GetMapping("/weather/search/")
    @ApiOperation(value = "Search for students and entries", response = SearchResult.class)
    @HystrixCommand(fallbackMethod = "fallbackSearch")
    public ResponseEntity<SearchResult> search() {
        CompletableFuture<List<Entry>> entryFuture = CompletableFuture.supplyAsync(() ->
                (List<Entry>) restTemplate.getForObject(entryServiceUrl, List.class)
                        .stream()
                        .collect(Collectors.toList())
        );
        CompletableFuture<List<Student>> studentFuture = CompletableFuture.supplyAsync(() ->
                (List<Student>) restTemplate.getForObject(studentServiceUrl, List.class)
                        .stream()
                        .collect(Collectors.toList())
        );

        CompletableFuture<SearchResult> combinedFuture = studentFuture.thenCombine(entryFuture, SearchResult::new);

        try {
            SearchResult searchResult = combinedFuture.get(50000, TimeUnit.SECONDS);
            return ResponseEntity.ok(searchResult);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<SearchResult> fallbackSearch() {
        // Fallback method implementation
        return ResponseEntity.ok(new SearchResult(new ArrayList<>(), new ArrayList<>()));
    }
}
