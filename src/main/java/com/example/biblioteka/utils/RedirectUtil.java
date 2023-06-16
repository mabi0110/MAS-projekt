package com.example.biblioteka.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean
public class RedirectUtil {

    public static final String GENERAL_FORMS = "/generalForms";
    public static final String EMPLOYEE_PAGES = "/employeePages";
    public static final String USER_PAGES = "/userPages";
    public static final String INDEX_PAGE = "/index.xhtml";
    public static final String LOGIN_FORM = "/loginForm.xhtml";
    public static final String REGISTER_FORM = "/registerForm.xhtml";
    public static final String USER_MAIN_PAGE = "/userPage.xhtml";
    public static final String EMPLOYEE_MAIN_PAGE = "/employeePage.xhtml";
    public static final String ADDITIONAL_USER_DATA = "/additionalUserDataForm.xhtml";
    private static final String USER_DETAILS_PAGE = "/userDetailsPage.xhtml";
    private static final String BOOK_DETAILS_PAGE = "/bookDetailsPage.xhtml";
    private static final String FIND_BOOK_FORM = "/findBookForm.xhtml";


    public static void redirectToIndexPage() throws IOException {
        redirect(GENERAL_FORMS + INDEX_PAGE);
    }

    public static void redirectToLoginForm() throws IOException {
        redirect(GENERAL_FORMS + LOGIN_FORM);
    }

    public static void redirectToRegisterForm() throws IOException {
        redirect(GENERAL_FORMS + REGISTER_FORM);
    }

    public static void redirectToAdditionalUserDataForm() throws IOException {
        redirect(USER_PAGES + ADDITIONAL_USER_DATA);
    }

    public static void redirectToUserDetailsPage() throws IOException {
        redirect(EMPLOYEE_PAGES + USER_DETAILS_PAGE);
    }

    public static void redirectToBookDetailsPage() throws IOException {
        redirect(EMPLOYEE_PAGES + BOOK_DETAILS_PAGE);
    }

    public static void redirectToFindBookForm() throws IOException {
        redirect(EMPLOYEE_PAGES + FIND_BOOK_FORM);
    }

    public static void redirectToUserPage() throws IOException {
        redirect(USER_PAGES + USER_MAIN_PAGE);
    }

    public static void redirectToEmployeePage() throws IOException {
        redirect(EMPLOYEE_PAGES + EMPLOYEE_MAIN_PAGE);
    }

    private static void redirect(String path) throws IOException{
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + path);
    }
}
