package fr.mazerty.shika.torii;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.IOException;

public class SqlSessionFactoryProducer {

    private static final String MYBATIS_CONFIG_PATH = "mybatis.xml";

    @Produces
    @ApplicationScoped
    public SqlSessionFactory produce() throws IOException {
        return new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(MYBATIS_CONFIG_PATH));
    }

}
