package fr.mazerty.shika.torii.vaadin;

import fr.mazerty.shika.ishi.vaadin.MyBeanFieldGroup;
import fr.mazerty.shika.ishi.vaadin.MyWindow;
import fr.mazerty.shika.torii.bean.User;
import fr.mazerty.shika.torii.exception.AuthenticationFailure;
import fr.mazerty.shika.torii.session.Session;

import javax.inject.Inject;

public class LoginWindow extends MyWindow {

    private final MyBeanFieldGroup<User> bfg;
    private final MyLoginForm loginForm;

    @Inject
    private Session session;

    public LoginWindow() {
        super("Login");

        bfg = new MyBeanFieldGroup<>(User.class);

        loginForm = new MyLoginForm(bfg);
        loginForm.addLoginListener(event -> {
            try {
                session.login(bfg.getBean());
                navigateTo(MainView.MAIN_VIEW_NAME);
            } catch (AuthenticationFailure authenticationFailure) {
                handleFailure(authenticationFailure);
            }
        });

        setContent(loginForm);
        setClosable(false);
        setResizable(false);
    }

    @Override
    protected void enter() {
        bfg.setBean(new User());
        loginForm.focus();
    }

}
