package fr.mazerty.shika.torii;

import java.io.Serializable;

public interface UserService extends Serializable {

    boolean isAuthorized(User user);

}
