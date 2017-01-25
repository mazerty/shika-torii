package fr.mazerty.shika.torii.service;

import fr.mazerty.shika.torii.bean.User;
import fr.mazerty.shika.torii.dao.UserDao;

import javax.enterprise.context.ApplicationScoped;

import static fr.mazerty.shika.torii.service.UserServiceTest.UNKNOWN_EMAIL;

@ApplicationScoped
public class MockUserDao implements UserDao {

    @Override
    public User selectByEmail(String email) {
        if (UNKNOWN_EMAIL.equals(email)) {
            return null;
        }

        throw new RuntimeException("uncovered test case");
    }

}
