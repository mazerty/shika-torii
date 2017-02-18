package fr.mazerty.shika.torii.service;

import fr.mazerty.shika.torii.bean.User;
import fr.mazerty.shika.torii.dao.UserDao;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

import static fr.mazerty.shika.torii.service.UserServiceTest.UNKNOWN_EMAIL;

@ApplicationScoped
public class MockUserDao implements UserDao {

    @Override
    public User selectByEmail(String email) {
        if (UNKNOWN_EMAIL.equals(email)) {
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
