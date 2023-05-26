package com.example.biblioteka;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class RegisterController {
    private String selectedAccountType;

    public String getSelectedAccountType() {
        return selectedAccountType;
    }

}
