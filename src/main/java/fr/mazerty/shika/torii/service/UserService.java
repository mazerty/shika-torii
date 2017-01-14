package fr.mazerty.shika.torii.service;

import fr.mazerty.shika.torii.bean.User;
import fr.mazerty.shika.torii.exception.AuthenticationFailure;

public interface UserService {

    User authenticate(User user) throws AuthenticationFailure;

}
