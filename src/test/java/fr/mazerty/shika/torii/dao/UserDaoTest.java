package fr.mazerty.shika.torii.dao;

import fr.mazerty.arquillian.MyDaoTest;
import fr.mazerty.shika.torii.bean.User;
import fr.mazerty.shika.torii.dao.jooq.Tables;
import fr.mazerty.shika.torii.dao.jooq.tables.records.TApplicationRecord;
import fr.mazerty.shika.torii.dao.jooq.tables.records.TUserApplicationRecord;
import fr.mazerty.shika.torii.dao.jooq.tables.records.TUserRecord;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest extends MyDaoTest {

    private static final int ID_FOO = 432;
    private static final String APPLI_FOO = "appli_foo";

    private static final int ID_BAR = 99;
    private static final String APPLI_BAR = "appli_bar";

    private static final int ID_TOTO = 112;
    private static final String TOTO_GMAIL_COM = "toto@gmail.com";
    private static final String PASSWORD = "123456";

    private static final String TITI_MSN_COM = "titi@msn.com";

    private User userToto = newUserWithEmail(TOTO_GMAIL_COM);
    private User userTiti = newUserWithEmail(TITI_MSN_COM);

    private User newUserWithEmail(String email) {
        User user = new User();
        user.setEmail(email);

        return user;
    }

    @Before
    public void before() {
        delete(Tables.T_USER_APPLICATION,
                Tables.T_USER,
                Tables.T_APPLICATION);
        insert(new TApplicationRecord(ID_FOO, APPLI_FOO),
                new TApplicationRecord(ID_BAR, APPLI_BAR),
                new TUserRecord(ID_TOTO, TOTO_GMAIL_COM, PASSWORD),
                new TUserApplicationRecord(ID_TOTO, ID_FOO));
    }

    @Inject
    private UserDao userDao;

    @Test
    public void nominal() {
        User user = userDao.selectByEmailAndApplication(userToto, APPLI_FOO);

        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(ID_TOTO);
        assertThat(user.getEmail()).isEqualTo(TOTO_GMAIL_COM);
        assertThat(user.getPassword()).isEqualTo(PASSWORD);
    }

    @Test
    public void emailInconnu() {
        assertThat(userDao.selectByEmailAndApplication(userTiti, APPLI_FOO)).isNull();
    }

    @Test
    public void userNonHabilite() {
        assertThat(userDao.selectByEmailAndApplication(userToto, APPLI_BAR)).isNull();
    }

}
