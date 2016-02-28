package fr.mazerty.shika.torii;

import java.io.Serializable;

public interface UserService extends Serializable {

    User authenticate(User user) throws AuthenticationFailure;

}
