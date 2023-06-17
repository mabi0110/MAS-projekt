package com.example.biblioteka.controllers;

import com.example.biblioteka.BookDao;
import com.example.biblioteka.BorrowDao;
import com.example.biblioteka.model.Book;
import com.example.biblioteka.model.Borrow;
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
    private String selectedISBN;
    private String selectedPublisher;
    private int selectedPublicationYear;
    private Book searchedBook;
    private String searchedBookTitle;
    private final BookDao bookDao = new BookDao();
    private final BorrowDao borrowDao = new BorrowDao();

    public Borrow getBorrowWithBookId() {
        Optional<Borrow> borrowOptional = borrowDao.findBorrowWithBookId(searchedBook.getId());
        return borrowOptional.orElse(null);
    }
    public void saveBook() throws IOException {
        Book book = createBook();
        bookDao.save(book);
        RedirectUtil.redirectToEmployeePage();
    }

    private Book createBook() {
        return new Book(selectedTitle, selectedISBN, selectedPublisher, selectedPublicationYear);
    }

    public void showBookDetails() throws IOException {
        searchedBook = getBookFromDb();
        RedirectUtil.redirectToBookDetailsPage();
    }

    private Book getBookFromDb() {
        Optional<Book> bookOptional = bookDao.findBookWithTitle(searchedBookTitle);
        return bookOptional.orElse(null);
    }
}
