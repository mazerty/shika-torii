package fr.mazerty.torii.service;

import fr.mazerty.arquillian.MyArquillianTest;
import fr.mazerty.torii.bean.User;
import fr.mazerty.torii.dao.JooqUserDao;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

public class UserServiceTest extends MyArquillianTest {

    @Deployment
    public static WebArchive deployment() {
        return defaultDeployment()
                .deleteClass(JooqUserDao.class)
                .addClass(MockUserDao.class);
    }

    static final String UNKNOWN_EMAIL = "unknown@gmail.com";

    @Inject
    private UserService userService;

    @Test
    public void unknownUser() {
        User user = new User();
        user.setEmail(UNKNOWN_EMAIL);
        user.setPassword("ThatsObviouslyWrong");

        assertThat(userService.authenticate(user)).isNull();
    }
    // TODO complete the tests when we have a bcrypt password generator

}
