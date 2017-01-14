package fr.mazerty.shika.torii.exception;

public class AuthenticationFailure extends Exception {

    public AuthenticationFailure() {
        super("Wrong email or password"); // TODO localization
    }

}
