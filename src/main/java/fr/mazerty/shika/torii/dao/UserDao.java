package fr.mazerty.shika.torii.dao;

import fr.mazerty.shika.torii.bean.User;

public interface UserDao {

    User selectByEmailAndApplication(User user, String application);

}
