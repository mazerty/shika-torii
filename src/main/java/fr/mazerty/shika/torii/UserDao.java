package fr.mazerty.shika.torii;

import fr.mazerty.shika.ishi.User;

public interface UserDao {

    User selectByEmailAndPassword(User user);

}
