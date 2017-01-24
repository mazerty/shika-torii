package fr.mazerty.shika.torii.cdi;

import fr.mazerty.shika.torii.bean.User;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class Session implements Serializable {

    private User loggedUser;

    public boolean isLoggedIn() {
        return loggedUser != null;
    }

    public void logout() {
        loggedUser = null;
    }

    public void login(User user) {
        loggedUser = user;
    }

    public boolean isAdmin() {
        return isLoggedIn() && Boolean.TRUE.equals(loggedUser.getAdmin());
    }

}
