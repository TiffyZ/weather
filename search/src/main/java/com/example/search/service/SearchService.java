package com.example.search.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
@Service
public interface SearchService {
    List<?> getSearchResult() throws ExecutionException, InterruptedException;
}
