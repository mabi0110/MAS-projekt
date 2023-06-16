package com.example.biblioteka.controllers;

import com.example.biblioteka.BookDao;
import com.example.biblioteka.BorrowDao;
import com.example.biblioteka.PersonDao;
import com.example.biblioteka.model.Book;
import com.example.biblioteka.model.Borrow;
import com.example.biblioteka.utils.RedirectUtil;
import com.example.biblioteka.model.Person;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@ManagedBean
@SessionScoped
@Getter
@Setter
public class UserController {
    private String selectedFirstName;
    private String selectedLastName;
    private final PersonDao personDao = new PersonDao();
    private final BorrowDao borrowDao = new BorrowDao();
    private final BookDao bookDao = new BookDao();
    private Person searchedPerson;

    public List<Book> getBooksBorrowedByUser(){
        return bookDao.findBooksBorrowedByUser(searchedPerson.getId());
    }


    public List<Borrow> getListOfUserBorrows() {
        return borrowDao.findBorrowsWithUserId(searchedPerson.getId());
    }

    public int getNumberOfUserBorrows() {
        return getListOfUserBorrows().size();
    }

    public void showUserDetails() throws IOException {
        searchedPerson = getPersonFromDb();
        RedirectUtil.redirectToUserDetailsPage();
    }

    private Person getPersonFromDb() {
        Optional<Person> personOptional = personDao.findUserWithFirstAndLastName(selectedFirstName, selectedLastName);
        return personOptional.orElse(null);
    }
}
