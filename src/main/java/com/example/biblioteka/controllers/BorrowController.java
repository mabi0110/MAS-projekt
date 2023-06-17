package com.example.biblioteka.controllers;

import com.example.biblioteka.BookDao;
import com.example.biblioteka.BorrowDao;
import com.example.biblioteka.PersonDao;
import com.example.biblioteka.model.Book;
import com.example.biblioteka.model.Borrow;
import com.example.biblioteka.model.Person;
import com.example.biblioteka.utils.RedirectUtil;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ManagedBean
@SessionScoped
@Getter
@Setter
public class BorrowController {
    private String selectedFirstName;
    private String selectedLastName;
    private Person searchedPerson;
    private Book searchedBook;
    private Borrow searchedBorrow;
    private String searchedBookTitle;
    private final BorrowDao borrowDao = new BorrowDao();
    private final BookDao bookDao = new BookDao();
    private final PersonDao personDao = new PersonDao();


    public List<Borrow> getListOfUserBorrows() {
        return borrowDao.findBorrowsWithUserId(searchedPerson.getId());
    }

    public int getNumberOfUserBorrows() {
        return getListOfUserBorrows().size();
    }

    public List<Book> getBooksBorrowedByUser(){
        return bookDao.findBooksBorrowedByUser(searchedPerson.getId());
    }

    public void showUserDetails() throws IOException {
        searchedPerson = getPersonFromDb();
        RedirectUtil.redirectToUserDetailsPage();
    }

    private Person getPersonFromDb() {
        Optional<Person> personOptional = personDao.findUserWithFirstAndLastName(selectedFirstName, selectedLastName);
        return personOptional.orElse(null);
    }

    public void showBookDetails() throws IOException {
        searchedBook = getBookFromDb();
        RedirectUtil.redirectToBookDetailsPage();
    }

    private Book getBookFromDb() {
        Optional<Book> bookOptional = bookDao.findBookWithTitle(searchedBookTitle);
        return bookOptional.orElse(null);
    }

    public Borrow getBorrowWithBookId() {
        Optional<Borrow> borrowOptional = borrowDao.findBorrowWithBookId(searchedBook.getId());
        searchedBorrow = borrowOptional.orElse(null);
        return searchedBorrow;
    }

    public List<Borrow> getBorrowsWithUserId() {
        return borrowDao.findBorrowsWithUserId(searchedPerson.getId());
    }

    public void borrowBook() throws IOException {
        if (searchedBorrow == null){
            searchedBorrow = createBorrow();
            borrowDao.borrowBook(searchedBorrow);
            RedirectUtil.redirectToEmployeePage();
        }
    }

    private Borrow createBorrow() {
        return new Borrow(searchedPerson.getId(), searchedBook.getId(),LocalDate.now());
    }

}
