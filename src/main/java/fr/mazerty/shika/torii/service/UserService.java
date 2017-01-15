package fr.mazerty.shika.torii.service;

import fr.mazerty.shika.torii.bean.User;
import fr.mazerty.shika.torii.exception.AuthenticationFailure;

public interface UserService {

    /**
     * Checks if a given user is authorized to access the application.
     *
     * @param user {@link User} instance whose email and password fields are to be challenged
     * @return a new {@link User} instance containing all the existing data about the user
     * @throws AuthenticationFailure if the user doesn't exist or if the email and password don't match
     */
    User authenticate(User user) throws AuthenticationFailure;

}
