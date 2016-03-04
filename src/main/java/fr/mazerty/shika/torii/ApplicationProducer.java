package fr.mazerty.shika.torii;

import javax.enterprise.inject.Produces;

public class ApplicationProducer {

    @Produces
    public String getApplication() {
        return "torii";
    }

}
