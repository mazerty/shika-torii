package fr.mazerty.shika.torii.service;

import fr.mazerty.shika.ishi.bean.User;
import fr.mazerty.shika.ishi.dao.UserDao;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.File;

@RunWith(Arquillian.class)
public class UserServiceTest {

    @Inject
    private UserDao userDao;

    @Deployment
    public static WebArchive deployment() { // TODO factoriser
        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "fr.mazerty.shika")
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/web.xml"))
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml").resolve("org.jooq:jooq").withTransitivity().asFile());
    }

    @Test
    public void authenticate() {
        userDao.selectByEmailAndApplication(new User(), "torii");
    }

}
