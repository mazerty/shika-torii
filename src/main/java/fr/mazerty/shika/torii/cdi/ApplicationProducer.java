package fr.mazerty.shika.torii.cdi;

import javax.enterprise.inject.Produces;

/**
 * Producer de la String nommant l'application (utilisée dans UserServiceImpl)
 */
public class ApplicationProducer { // TODO extends ou implements

    @Produces
    public String getApplication() {
        return "torii";
    }

}
