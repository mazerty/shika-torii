package fr.mazerty.shika.torii.vaadin;

import com.vaadin.ui.Notification;
import fr.mazerty.shika.ishi.vaadin.MyBeanFieldGroup;
import fr.mazerty.shika.ishi.vaadin.MyWindow;
import fr.mazerty.shika.torii.bean.User;
import fr.mazerty.shika.torii.exception.AuthenticationFailure;
import fr.mazerty.shika.torii.service.UserService;
import fr.mazerty.shika.torii.session.Session;

import javax.inject.Inject;

import static com.vaadin.ui.Notification.Type.ERROR_MESSAGE;

public class LoginWindow extends MyWindow {

    private final MyBeanFieldGroup<User> bfg;
    private final MyLoginForm loginForm;

    @Inject
    private Session session;
    @Inject
    private UserService userService;

    public LoginWindow() {
        super("Login");

        bfg = new MyBeanFieldGroup<>(User.class);

        loginForm = new MyLoginForm(bfg);
        loginForm.addLoginListener(event -> {
            try {
                session.login(userService.authenticate(bfg.getBean()));
                navigateTo(MainView.MAIN_VIEW_NAME);
            } catch (AuthenticationFailure authenticationFailure) {
                Notification.show(authenticationFailure.getMessage(), ERROR_MESSAGE);
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
