package fr.mazerty.shika.torii;

import org.mybatis.cdi.Mapper;

import javax.inject.Inject;

public class UserServiceImpl implements UserService {

    @Inject
    @Mapper
    private UserDao userDao;

    @Override
    public User isAuthorized(User user) {
        return userDao.selectByEmailAndPassword(user);
    }

}
