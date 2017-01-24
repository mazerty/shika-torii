package fr.mazerty.shika.torii.vaadin;

import com.vaadin.ui.*;
import fr.mazerty.shika.ishi.vaadin.MyBeanFieldGroup;
import fr.mazerty.shika.ishi.vaadin.MyPasswordField;
import fr.mazerty.shika.ishi.vaadin.MyTextField;
import fr.mazerty.shika.ishi.vaadin.PrimaryButton;
import fr.mazerty.shika.torii.bean.User;
import fr.mazerty.shika.torii.cdi.LanguageProxy;

class MyLoginForm extends LoginForm {

    private LanguageProxy lp;

    private MyBeanFieldGroup<User> bfg;
    private MyTextField email;
    private MyPasswordField password;
    private Button login;

    MyLoginForm(LanguageProxy lp) {
        this.lp = lp;
        this.bfg = new MyBeanFieldGroup<>(User.class);
    }

    @Override
    protected TextField createUsernameField() {
        return email = bfg.buildAndBind(null, "email", MyTextField.class);
    }

    @Override
    protected PasswordField createPasswordField() {
        return password = bfg.buildAndBind(null, "password", MyPasswordField.class);
    }

    @Override
    protected Button createLoginButton() {
        return login = new PrimaryButton();
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

    void refreshCaptions() {
        email.setCaption(lp.l("loginform.username.caption"));
        password.setCaption(lp.l("loginform.password.caption"));
        login.setCaption(lp.l("loginform.button.caption"));
    }

    public User getBean() {
        return bfg.getBean();
    }

    public void setBean(User bean) {
        bfg.setBean(bean);
    }

}
