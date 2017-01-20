package fr.mazerty.shika.torii.vaadin;

import com.vaadin.ui.Notification;
import fr.mazerty.shika.ishi.vaadin.MyBeanFieldGroup;
import fr.mazerty.shika.ishi.vaadin.MyWindow;
import fr.mazerty.shika.torii.bean.User;
import fr.mazerty.shika.torii.service.UserService;
import fr.mazerty.shika.torii.session.Session;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import static com.vaadin.ui.Notification.Type.ERROR_MESSAGE;

public class LoginWindow extends MyWindow {

    private MyBeanFieldGroup<User> bfg;
    private MyLoginForm loginForm;

    @Inject
    private Session session;
    @Inject
    private UserService userService;

    @PostConstruct
    public void postConstruct() {
        setCaption("Login");

        bfg = new MyBeanFieldGroup<>(User.class);

        loginForm = new MyLoginForm(bfg);
        loginForm.addLoginListener(event -> {
            User match = userService.authenticate(bfg.getBean());
            if (match == null) {
                Notification.show("Wrong email or password", ERROR_MESSAGE);
            } else {
                session.login(match);
                navigateTo(MainView.MAIN_VIEW_NAME);
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
