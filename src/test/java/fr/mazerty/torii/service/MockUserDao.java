package fr.mazerty.torii.service;

import fr.mazerty.torii.bean.User;
import fr.mazerty.torii.dao.UserDao;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class MockUserDao implements UserDao {

    @Override
    public User selectByEmail(String email) {
        if (UserServiceTest.UNKNOWN_EMAIL.equals(email)) {
            return null;
        }

        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> selectAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insert(User user) {
        throw new UnsupportedOperationException(); // TODO when mails have been implemented
    }

}
