package com.example.search.domain;

import com.example.homework.domain.Student;
import com.example.springbootdemo.domain.Entry;

import java.util.List;

public class SearchResult {
    private List<Student> students;
    private List<Entry> entries;

    public SearchResult(List<Student> students, List<Entry> entries) {
        this.students = students;
        this.entries = entries;
    }
}
