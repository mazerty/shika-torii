package fr.mazerty.shika.torii;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;

@SessionScoped
public class Session implements Serializable {

    private boolean loggedIn;

    @Inject
    private UserService userService;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void logOut() {
        loggedIn = false;
    }

    public void login(User user) throws Exception {
        loggedIn = userService.isAuthorized(user);
    }

}
