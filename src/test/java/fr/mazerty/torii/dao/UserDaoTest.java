package fr.mazerty.torii.dao;

import fr.mazerty.arquillian.MyDaoTest;
import fr.mazerty.torii.bean.User;
import fr.mazerty.torii.dao.jooq.Tables;
import fr.mazerty.torii.dao.jooq.tables.records.TUserRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest extends MyDaoTest {

    private static final Integer TOTO_ID = 112;
    private static final String TOTO_EMAIL = "toto@gmail.com";
    private static final String TOTO_PASSWORD = "123456";
    private static final Boolean TOTO_ADMIN = Boolean.TRUE;

    private static final Integer TATA_ID = 32;
    private static final String TATA_EMAIL = "tata@hotmail.com";
    private static final String TATA_PASSWORD = "password";
    private static final Boolean TATA_ADMIN = Boolean.FALSE;

    private static final String TITI_EMAIL = "titi@msn.com";

    @Inject
    private UserDao userDao;

    @Before
    public void before() {
        insert(new TUserRecord(TOTO_ID, TOTO_EMAIL, TOTO_PASSWORD, TOTO_ADMIN));
        insert(new TUserRecord(TATA_ID, TATA_EMAIL, TATA_PASSWORD, TATA_ADMIN));
    }

    @Test
    public void selectByEmail_nominal() {
        testUserAgainstValues(userDao.selectByEmail(TOTO_EMAIL), TOTO_ID, TOTO_EMAIL, TOTO_PASSWORD, TOTO_ADMIN);
        testUserAgainstValues(userDao.selectByEmail(TATA_EMAIL), TATA_ID, TATA_EMAIL, TATA_PASSWORD, TATA_ADMIN);
    }

    @Test
    public void selectByEmail_emailInconnu() {
        assertThat(userDao.selectByEmail(TITI_EMAIL)).isNull();
    }

    private void testUserAgainstValues(User user, Integer id, String email, String password, Boolean admin) {
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.getAdmin()).isEqualTo(admin);
    }

    @Test
    public void selectAll_nominal() {
        List<User> users = userDao.selectAll();
        assertThat(users).hasSize(2);

        users.sort(Comparator.comparingInt(User::getId));
        testUserAgainstValues(users.get(0), TATA_ID, TATA_EMAIL, TATA_PASSWORD, TATA_ADMIN);
        testUserAgainstValues(users.get(1), TOTO_ID, TOTO_EMAIL, TOTO_PASSWORD, TOTO_ADMIN);
    }

    @After
    public void after() {
        delete(Tables.T_USER);
    }

}
