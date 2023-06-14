package com.example.biblioteka.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private int id;
    private String title;
    private String publisher;
    private int year;
    private Author author;

}
