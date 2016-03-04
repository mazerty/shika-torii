package fr.mazerty.shika.torii;

import fr.mazerty.shika.ishi.Application;
import org.mybatis.cdi.Mapper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

public class ApplicationProducer {

    @Mapper
    @Inject
    private ApplicationDao applicationDao;

    @Named
    @ApplicationScoped
    @Produces
    public Application getApplication() {
        return applicationDao.selectByName("torii");
    }

}
