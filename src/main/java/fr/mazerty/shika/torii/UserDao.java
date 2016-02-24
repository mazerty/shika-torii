package fr.mazerty.shika.torii;

public interface UserDao {

    User selectByEmailAndPassword(User user);

}
