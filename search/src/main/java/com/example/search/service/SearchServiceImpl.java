package com.example.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
@Service
public class SearchServiceImpl implements SearchService{

    private final RestTemplate restTemplate;

    @Autowired
    public SearchServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<?> getSearchResult() throws ExecutionException, InterruptedException {
        String entryServiceUrl = "http://localhost:8400/demo/entry";
        String studentServiceUrl = "http://localhost:8500/homework/students";
        CompletableFuture<?> entryFuture = CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject(entryServiceUrl, Collection.class)
        );
        CompletableFuture<?> studentFuture = CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject(studentServiceUrl, Collection.class)
        );
        try {
            List<Object> res = new ArrayList<>();
            res.add(entryFuture.get());
            res.add(studentFuture.get());
            return res;
        } catch (Exception e) {
            throw e;
        }
    }
}
