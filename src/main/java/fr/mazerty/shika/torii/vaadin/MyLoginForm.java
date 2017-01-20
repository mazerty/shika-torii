package fr.mazerty.shika.torii.vaadin;

import com.vaadin.ui.*;
import fr.mazerty.shika.ishi.vaadin.MyBeanFieldGroup;
import fr.mazerty.shika.ishi.vaadin.MyButton;
import fr.mazerty.shika.ishi.vaadin.MyPasswordField;
import fr.mazerty.shika.ishi.vaadin.MyTextField;
import fr.mazerty.shika.torii.bean.User;
import fr.mazerty.shika.torii.cdi.ResourceBundleWrapper;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

class MyLoginForm extends LoginForm {

    private MyBeanFieldGroup<User> bfg;
    private MyTextField email;

    @Inject
    private ResourceBundleWrapper rbw;

    @PostConstruct
    public void postConstruct() {
        bfg = new MyBeanFieldGroup<>(User.class);
    }

    @Override
    protected TextField createUsernameField() {
        return email = bfg.buildAndBind(rbw.l("loginform.username.caption"), "email", MyTextField.class);
    }

    @Override
    protected PasswordField createPasswordField() {
        return bfg.buildAndBind(rbw.l("loginform.password.caption"), "password", MyPasswordField.class);
    }

    @Override
    protected Button createLoginButton() {
        MyButton login = new MyButton(rbw.l("loginform.button.caption"));
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

    public User getBean() {
        return bfg.getBean();
    }

    public void setBean(User bean) {
        bfg.setBean(bean);
    }

}
