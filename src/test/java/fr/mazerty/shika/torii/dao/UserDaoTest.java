package fr.mazerty.shika.torii.dao;

import fr.mazerty.arquillian.MyDaoTest;
import fr.mazerty.shika.torii.bean.User;
import fr.mazerty.shika.torii.dao.jooq.Tables;
import fr.mazerty.shika.torii.dao.jooq.tables.records.TUserRecord;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest extends MyDaoTest {

    private static final Integer TOTO_ID = 112;
    private static final String TOTO_EMAIL = "toto@gmail.com";
    private static final String TOTO_PASSWORD = "123456";
    private static final Boolean TOTO_ADMIN = Boolean.TRUE;

    private static final String TITI_EMAIL = "titi@msn.com";

    @Before
    public void before() {
        delete(Tables.T_USER);
        insert(new TUserRecord(TOTO_ID, TOTO_EMAIL, TOTO_PASSWORD, TOTO_ADMIN));
    }

    @Inject
    private UserDao userDao;

    @Test
    public void nominal() {
        User user = userDao.selectByEmail(TOTO_EMAIL);

        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(TOTO_ID);
        assertThat(user.getEmail()).isEqualTo(TOTO_EMAIL);
        assertThat(user.getPassword()).isEqualTo(TOTO_PASSWORD);
        assertThat(user.getAdmin()).isEqualTo(TOTO_ADMIN);
    }

    @Test
    public void emailInconnu() {
        assertThat(userDao.selectByEmail(TITI_EMAIL)).isNull();
    }

}
