package com.example.biblioteka.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private int id;
    private String title;
    private int isbn;
    private String publisher;
    private int publicationYear;
    private int authorId;

    public Book(int id, String title, int isbn, String publisher, int publicationYear, int authorId) {
        this(title, isbn, publisher, publicationYear, authorId);
        this.id = id;
    }

    public Book(String title, int isbn, String publisher, int publicationYear, int authorId) {
        this(title, isbn, publisher, publicationYear);
        this.authorId = authorId;
    }
    public Book(String title, int isbn, String publisher, int publicationYear) {
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
    }

    public Book(int id, String title, int isbn, String publisher, int publicationYear) {
        this(title, isbn, publisher, publicationYear);
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn=" + isbn +
                ", publisher='" + publisher + '\'' +
                ", publicationYear=" + publicationYear +
                ", authorId=" + authorId +
                '}';
    }
}
