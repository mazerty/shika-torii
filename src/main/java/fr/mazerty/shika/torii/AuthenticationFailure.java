package fr.mazerty.shika.torii;

public class AuthenticationFailure extends Exception {

    public AuthenticationFailure() {
        super("Wrong email or password");
    }

}
