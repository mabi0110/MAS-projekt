package com.example.biblioteka;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

@ManagedBean
public class LoginController {

    private String login;

    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login(){
        if(("admin").equals(login)&&("admin").equals(password)){
            return "userPage.xhtml?faces-redirect=true";
        } else {
            FacesMessage message = new FacesMessage("Niepoprawne dane logowania");
            FacesContext context = FacesContext.getCurrentInstance();
            //context.addMessage();
            FacesContext.getCurrentInstance().addMessage("loginButton", message);
            return "login.xhtml?faces-redirect=true";
        }

    }
}
