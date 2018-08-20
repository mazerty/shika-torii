package fr.mazerty.torii.vaadin;

import com.vaadin.data.Binder;
import com.vaadin.ui.*;
import fr.mazerty.torii.bean.User;
import fr.mazerty.torii.cdi.LanguageProxy;
import org.vaadin.viritin.button.PrimaryButton;

/**
 * Custom bean-backed {@link LoginForm}
 */
class MyLoginForm extends LoginForm {

    private LanguageProxy lp;

    private final Binder<User> binder = new Binder<>();
    private final TextField email = new TextField();
    private final PasswordField password = new PasswordField();
    private final Button login = new PrimaryButton();

    MyLoginForm(LanguageProxy lp) {
        this.lp = lp;

        binder.forField(email).bind(User::getEmail, User::setEmail);
        binder.forField(password).bind(User::getPassword, User::setPassword);
    }

    @Override
    protected TextField createUsernameField() {
        return email;
    }

    @Override
    protected PasswordField createPasswordField() {
        return password;
    }

    @Override
    protected Button createLoginButton() {
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

    /**
     * Since the language can be changed while this form is displayed, we need a method to refresh the captions
     */
    void refreshCaptions() {
        email.setCaption(lp.l("user.email.caption"));
        password.setCaption(lp.l("user.password.caption"));
        login.setCaption(lp.l("loginform.button.caption"));
    }

    public User getBean() {
        return binder.getBean();
    }

    public void setBean(User bean) {
        binder.setBean(bean);
    }

}
