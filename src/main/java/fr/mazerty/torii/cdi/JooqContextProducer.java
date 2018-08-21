package fr.mazerty.torii.cdi;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@ApplicationScoped
public class JooqContextProducer {

    private static final String DATASOURCE_JNDI_NAME = "java:/comp/env/jdbc/postgresql";

    /**
     * @return Jooq context to operate the database
     */
    @ApplicationScoped
    @Produces
    public DSLContext produce() throws NamingException {
        return DSL.using(new DefaultConfiguration()
                .set(SQLDialect.POSTGRES_10)
                .set((DataSource) new InitialContext().lookup(DATASOURCE_JNDI_NAME)));
    }

}
