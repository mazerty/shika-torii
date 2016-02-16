package fr.mazerty.shika.torii;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class Session implements Serializable {

    public boolean isLoggedIn() {
        return false;
    }

    public void logOut() {
    }

}
