/*
 * This file is generated by jOOQ.
*/
package fr.mazerty.shika.torii.dao.jooq;


import fr.mazerty.shika.torii.dao.jooq.tables.TApplication;
import fr.mazerty.shika.torii.dao.jooq.tables.TUser;
import fr.mazerty.shika.torii.dao.jooq.tables.TUserApplication;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in torii
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>torii.t_application</code>.
     */
    public static final TApplication T_APPLICATION = fr.mazerty.shika.torii.dao.jooq.tables.TApplication.T_APPLICATION;

    /**
     * The table <code>torii.t_user</code>.
     */
    public static final TUser T_USER = fr.mazerty.shika.torii.dao.jooq.tables.TUser.T_USER;

    /**
     * The table <code>torii.t_user_application</code>.
     */
    public static final TUserApplication T_USER_APPLICATION = fr.mazerty.shika.torii.dao.jooq.tables.TUserApplication.T_USER_APPLICATION;
}