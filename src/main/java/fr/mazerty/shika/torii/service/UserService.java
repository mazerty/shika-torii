package fr.mazerty.shika.torii.service;

import fr.mazerty.shika.torii.bean.User;
import fr.mazerty.shika.torii.exception.AuthenticationFailure;

import java.io.Serializable;

public interface UserService extends Serializable {

    User authenticate(User user) throws AuthenticationFailure;

}
