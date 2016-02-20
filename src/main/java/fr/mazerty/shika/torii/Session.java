package fr.mazerty.shika.torii;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class Session implements Serializable {

    private User user;

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logOut() {
        user = null;
    }

    public void login(User user) throws Exception {
        this.user = user;
    }

}
