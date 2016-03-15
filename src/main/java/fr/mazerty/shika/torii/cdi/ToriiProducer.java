package fr.mazerty.shika.torii.cdi;

import fr.mazerty.shika.ishi.cdi.SpecificProducer;

import javax.enterprise.inject.Produces;

public class ToriiProducer implements SpecificProducer {

    @Produces
    @Override
    public String getApplication() {
        return "torii";
    }

}
