package com.example.homework.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnoreProperties("teachers")
    private Student student;
}
/*
Elizabeth	Johnson
Michael	Davis
Sarah	Patel
 */