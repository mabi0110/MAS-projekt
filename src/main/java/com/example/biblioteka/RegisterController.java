package com.example.biblioteka;

import lombok.Getter;
import lombok.Setter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean
@RequestScoped
public class RegisterController {

    @Getter
    @Setter
    private String selectedAccountType;


    public void register() throws IOException {
        if (("employee").equals(selectedAccountType)) {
            RedirectUtil.redirectToAdditionalUserDataPage();
        } else {
            RedirectUtil.redirectToAdditionalUserDataPage();
        }
    }
}
