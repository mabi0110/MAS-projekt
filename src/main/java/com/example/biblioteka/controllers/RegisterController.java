package com.example.biblioteka.controllers;

import com.example.biblioteka.PersonDao;
import com.example.biblioteka.model.Person;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.IOException;
import java.util.List;

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

//    public List<Person> getPeopleFromDb(){
//        String sql = "SELECT id, firstName, lastName, login, pass, accountType from person where id=1";
//        List<Person> list = personDao.findAllWithQuery(sql);
//        for (Person person : list) {
//            System.out.println(person);
//        }
//        return personDao.findAllWithQuery(sql);
//    }


    private Person createPerson(){
        return new Person(selectedFirstName, selectedLastName, selectedLogin, selectedPass, selectedAccountType);
    }
    public void register() throws IOException {
        if (("USER").equals(selectedAccountType)) {
            Person person = createPerson();
            System.out.println(person);
            personDao.save(person);
            RedirectUtil.redirectToAdditionalUserDataForm();
        } else {
            RedirectUtil.redirectToEmployeePage();
        }
    }
}
