package fr.mazerty.shika.torii.dao;

import fr.mazerty.shika.torii.bean.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

import static fr.mazerty.shika.torii.dao.jooq.Tables.T_USER;

@ApplicationScoped
public class JooqUserDao extends JooqDao implements UserDao {

    @Override
    public User selectByEmail(String email) {
        return dslContext.selectFrom(T_USER)
                .where(T_USER.EMAIL.equal(email))
                .fetchAnyInto(User.class);
    }

    @Override
    public List<User> selectAll() {
        return dslContext.selectFrom(T_USER).fetchInto(User.class);
    }

}
