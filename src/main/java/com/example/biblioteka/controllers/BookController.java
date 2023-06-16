package com.example.biblioteka.controllers;

import com.example.biblioteka.BookDao;
import com.example.biblioteka.model.Book;
import com.example.biblioteka.utils.RedirectUtil;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.util.Optional;

@ManagedBean
@SessionScoped
@Getter
@Setter
public class BookController {
    private String selectedTitle;
    private int selectedISBN;
    private String selectedPublisher;
    private int selectedPublicationYear;
    private Book searchedBook;
    private final BookDao bookDao = new BookDao();
    public void saveBook() {
        Book book = createBook();
        System.out.println(book);
        bookDao.save(book);
    }

    private Book createBook() {
        return new Book(selectedTitle, selectedISBN, selectedPublisher, selectedPublicationYear);
    }

    public void showBookDetails() throws IOException {
        searchedBook = getBookFromDb();
        RedirectUtil.redirectToBookDetailsPage();
    }

    private Book getBookFromDb() {
        Optional<Book> bookOptional = bookDao.findBookWithTitle(selectedTitle);
        return bookOptional.orElse(null);
    }
}
