package com.example.biblioteka.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private int id;
    private String title;
    private String publisher;
    private int publicationYear;
    private int authorId;


    public Book(String title, String publisher, int publicationYear, int authorId) {
        this(title, publisher, publicationYear);
        this.authorId = authorId;
    }
    public Book(String title, String publisher, int publicationYear) {
        this.title = title;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
    }

    public Book(int id, String title, String publisher, int publicationYear) {
        this(title, publisher, publicationYear);
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publicationYear=" + publicationYear +
                ", authorId=" + authorId +
                '}';
    }
}
