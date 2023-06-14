package com.example.biblioteka.controllers;

import com.example.biblioteka.PersonDao;
import com.example.biblioteka.RedirectUtil;
import com.example.biblioteka.model.Person;
import lombok.Getter;
import lombok.Setter;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

@Getter
@Setter
@ManagedBean
@SessionScoped
public class LoginController implements Serializable {
    private final PersonDao personDao = new PersonDao();
    private String selectedLogin;
    private String selectedPassword;
    private Person loggedPerson;

    public void login() throws IOException {
        loggedPerson = getPersonFromDb();
        if ((("USER").equals(loggedPerson.getAccountType()))) {
            RedirectUtil.redirectToUserPage();
        } else if ((("EMPLOYEE").equals(loggedPerson.getAccountType()))){
            RedirectUtil.redirectToEmployeePage();
        }
    }
    private Person getPersonFromDb() {
        Optional<Person> personOptional = personDao.findPersonWithLoginAndPassword(selectedLogin, selectedPassword);
        return personOptional.orElse(null);
    }


}
