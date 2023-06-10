package com.example.biblioteka;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.Serializable;

@ManagedBean
public class LoginController implements Serializable {

    private static final String LOGIN_BUTTON="loginForm:loginButton";
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

    public void login() throws IOException {
        if (("user").equals(login) && ("user").equals(password)) {
            RedirectUtil.redirectToUserPage();
        } else if (("admin").equals(login) && ("admin").equals(password)) {
            RedirectUtil.redirectToEmployeePage();
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(LOGIN_BUTTON, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niepoprawne dane logowania", null));
            RedirectUtil.redirectToLoginForm();
        }
    }
}
