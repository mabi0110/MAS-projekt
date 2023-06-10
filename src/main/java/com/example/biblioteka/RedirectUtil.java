package com.example.biblioteka;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean
public class RedirectUtil {

    public static final String GENERAL_FORMS = "/generalForms";
    public static final String INDEX_PAGE = "/index.xhtml";
    public static final String LOGIN_FORM = "/loginForm.xhtml";
    public static final String REGISTER_FORM = "/registerForm.xhtml";
    public static final String USER_PAGE = "/userPage.xhtml";
    public static final String EMPLOYEE_PAGE = "/employeePage.xhtml";
    public static final String ADDITIONAL_USER_DATA = "/additionalUserDataForm.xhtml";


    public static void redirectToIndexPage() throws IOException {
        redirect(GENERAL_FORMS + INDEX_PAGE);
    }

    public static void redirectToLoginForm() throws IOException {
        redirect(GENERAL_FORMS + LOGIN_FORM);
    }

    public static void redirectToRegisterForm() throws IOException {
        redirect(GENERAL_FORMS + REGISTER_FORM);
    }

    public static void redirectToAdditionalUserDataPage() throws IOException {
        redirect(GENERAL_FORMS + ADDITIONAL_USER_DATA);
    }

    public static void redirectToUserPage() throws IOException {
        redirect(USER_PAGE);
    }

    public static void redirectToEmployeePage() throws IOException {
        redirect(EMPLOYEE_PAGE);
    }

    private static void redirect(String path) throws IOException{
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + path);
    }
}
