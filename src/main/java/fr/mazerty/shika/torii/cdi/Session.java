package fr.mazerty.shika.torii.cdi;

import fr.mazerty.shika.torii.bean.User;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class Session implements Serializable {

    private User user;

    public void login(User user) {
        this.user = user;
    }

    public void logout() {
        user = null;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public boolean isAdmin() {
        return isLoggedIn() && Boolean.TRUE.equals(user.getAdmin());
    }

}
