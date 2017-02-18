package fr.mazerty.shika.torii.service;

import fr.mazerty.shika.torii.bean.User;
import fr.mazerty.shika.torii.dao.UserDao;
import org.mindrot.jbcrypt.BCrypt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.defaultString;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    private static final Integer LOG_ROUNDS = 15;
    private static final String DUMMY_HASH = BCrypt.hashpw("whatTheHell?", BCrypt.gensalt(LOG_ROUNDS));

    @Inject
    private UserDao userDao;

    @Override
    public User authenticate(User user) {
        // jbcrypt doesn't handle null strings very well so...
        String userPassword = defaultString(user.getPassword());

        User match = userDao.selectByEmail(user.getEmail());
        if (match == null) {
            // if no matching user has been found, we check the password against a dummy hash anyway so that there's no
            // significant difference in response time that can be used to guess existing users in the database
            BCrypt.checkpw(userPassword, DUMMY_HASH);
            return null;
        } else return BCrypt.checkpw(userPassword, match.getPassword()) ? match : null;
    }

    @Override
    public List<User> list() {
        return userDao.selectAll();
    }

    @Override
    public void create(User user) {
        user.setPassword(DUMMY_HASH); // TODO generate random password and send it by mail
        userDao.insert(user);
    }

}
