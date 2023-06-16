package com.example.biblioteka.controllers;

import com.example.biblioteka.PersonDao;
import com.example.biblioteka.utils.RedirectUtil;
import com.example.biblioteka.model.Person;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.IOException;

@ManagedBean
@RequestScoped
@Getter
@Setter
public class RegisterController {

    private String selectedFirstName;
    private String selectedLastName;
    private String selectedLogin;
    private String selectedPass;
    private String selectedAccountType;
    private final PersonDao personDao = new PersonDao();


    private Person createPerson(){
        return new Person(selectedFirstName, selectedLastName, selectedLogin, selectedPass, selectedAccountType);
    }
    public void register() throws IOException {
        Person person = createPerson();
        personDao.save(person);
        if (("USER").equals(selectedAccountType)) {
            RedirectUtil.redirectToAdditionalUserDataForm();
        } else {
            RedirectUtil.redirectToLoginForm();
        }
    }
}
