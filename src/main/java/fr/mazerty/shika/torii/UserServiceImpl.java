package fr.mazerty.shika.torii;

public class UserServiceImpl implements UserService {

    @Override
    public boolean isAuthorized(User user) {
        return true;
    }

}
