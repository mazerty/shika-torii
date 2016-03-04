package fr.mazerty.shika.torii;

import fr.mazerty.shika.ishi.Application;

public interface ApplicationDao {

    Application selectByName(String name);

}
