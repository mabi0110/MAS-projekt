package com.example.biblioteka.controllers;

import com.example.biblioteka.BookDao;
import com.example.biblioteka.model.Book;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
@ManagedBean
@RequestScoped
@Getter
@Setter
public class BookController {

    private String selectedTitle;
    private String selectedPublisher;
    private int selectedPublicationYear;
    private final BookDao bookDao = new BookDao();


    public void saveBook() {
        Book book = createBook();
        System.out.println(book);
        bookDao.save(book);
    }

    private Book createBook() {
        return new Book(selectedTitle, selectedPublisher, selectedPublicationYear);
    }
}
