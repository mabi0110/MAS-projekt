package com.example.biblioteka.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Author {
    private int id;
    private String firstName;
    private String lastName;
    private int bookId;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Author(int id, String firstName, String lastName) {
        this(firstName, lastName);
        this.id = id;
    }

    public Author(String firstName, String lastName, int bookId) {
        this(firstName, lastName);
        this.bookId = bookId;
    }
}
