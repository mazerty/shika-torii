package fr.mazerty.shika.torii.service;

import fr.mazerty.shika.torii.bean.User;
import fr.mazerty.shika.torii.dao.UserDao;
import fr.mazerty.shika.torii.exception.AuthenticationFailure;
import org.mindrot.jbcrypt.BCrypt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import static org.apache.commons.lang3.StringUtils.defaultString;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    private static final String DUMMY_HASH = "$2a$13$byD4Ftv39Z76hUfd01URsePSdaV722c7J7NcLfs6o3KdJsAwHhEjq";

    @Inject
    private UserDao userDao;

    @Override
    public User authenticate(User user) throws AuthenticationFailure {
        // jbcrypt doesn't handle null strings very well so...
        String userPassword = defaultString(user.getPassword());

        User found = userDao.selectByEmail(user.getEmail());
        if (found == null) {
            // if no user has been found, we check the password against a dummy hash anyway so that there's no
            // significant difference in response time that can be used to guess existing users in the database
            BCrypt.checkpw(userPassword, DUMMY_HASH);
            throw new AuthenticationFailure();
        } else {
            if (BCrypt.checkpw(userPassword, found.getPassword())) {
                return found;
            } else {
                throw new AuthenticationFailure();
            }
        }
    }

}
