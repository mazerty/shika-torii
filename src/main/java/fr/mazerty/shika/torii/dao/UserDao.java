package fr.mazerty.shika.torii.dao;

import fr.mazerty.shika.torii.bean.User;

import java.util.List;

public interface UserDao {

    /**
     * Returns all information pertaining to the user whose email is given as parameter, or null if no user has been found.
     *
     * @param email email to look for
     * @return {@link User} instance corresponding to the given email, or null if no user has been found
     */
    User selectByEmail(String email);

    /**
     * @return {@link List} of all the {@link User}s in the database
     */
    List<User> selectAll();

    /**
     * Inserts the given {@link User} in the database
     */
    void insert(User user);

}
