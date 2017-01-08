package fr.mazerty.shika.torii.vaadin;

import com.vaadin.ui.*;
import fr.mazerty.shika.ishi.vaadin.MyBeanFieldGroup;
import fr.mazerty.shika.ishi.vaadin.MyButton;
import fr.mazerty.shika.ishi.vaadin.MyTextField;
import fr.mazerty.shika.torii.bean.User;

class MyLoginForm extends LoginForm {

    private MyBeanFieldGroup<User> bfg;
    private MyTextField email;

    MyLoginForm(MyBeanFieldGroup<User> bfg) {
        this.bfg = bfg;
    }

    @Override
    protected TextField createUsernameField() {
        return email = bfg.buildAndBind("Email", "email", MyTextField.class);
    }

    @Override
    protected PasswordField createPasswordField() {
        return bfg.buildAndBind("Password", "password", PasswordField.class);
    }

    @Override
    protected Button createLoginButton() {
        MyButton login = new MyButton("Log in");
        login.setPrimary();

        return login;
    }

    @Override
    protected Component createContent(TextField userNameField, PasswordField passwordField, Button loginButton) {
        FormLayout formLayout = new FormLayout(userNameField, passwordField, loginButton);
        formLayout.setSizeUndefined();
        formLayout.setMargin(true);

        return formLayout;
    }

    @Override
    public void focus() {
        email.focus();
    }

}
