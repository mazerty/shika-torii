package fr.mazerty.shika.torii.session;

import fr.mazerty.shika.torii.bean.User;
import fr.mazerty.shika.torii.exception.AuthenticationFailure;
import fr.mazerty.shika.torii.service.UserService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;

@SessionScoped
public class Session implements Serializable {

    private User loggedUser;

    @Inject
    private UserService userService;

    public boolean isLoggedIn() {
        return loggedUser != null;
    }

    public void logOut() {
        loggedUser = null;
    }

    public void login(User user) throws AuthenticationFailure {
        loggedUser = userService.authenticate(user);
    }

}
