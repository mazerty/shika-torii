package fr.mazerty.shika.torii;

import fr.mazerty.shika.ishi.Application;
import fr.mazerty.shika.ishi.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    User selectByEmailAndApplication(@Param("user") User user, @Param("application") Application application);

}
