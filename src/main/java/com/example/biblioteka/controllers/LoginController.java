package com.example.biblioteka.controllers;

import com.example.biblioteka.PersonDao;
import com.example.biblioteka.RedirectUtil;
import com.example.biblioteka.model.Person;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

@ManagedBean
@RequestScoped
@Getter
@Setter
public class LoginController implements Serializable {
    private final PersonDao personDao = new PersonDao();
    private String selectedLogin;
    private String selectedPassword;

    public void login() throws IOException {
        Optional<Person> personWithLoginAndPassword = personDao.findPersonWithLoginAndPassword(selectedLogin, selectedPassword);
        Person person = personWithLoginAndPassword.get();
        if ((("USER").equals(person.getAccountType()))) {
            RedirectUtil.redirectToUserPage();
        } else if ((("EMPLOYEE").equals(person.getAccountType()))){
            RedirectUtil.redirectToEmployeePage();
        }
    }


}
