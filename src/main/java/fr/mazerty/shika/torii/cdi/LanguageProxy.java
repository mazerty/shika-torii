package fr.mazerty.shika.torii.cdi;

import fr.mazerty.shika.torii.vaadin.Language;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class LanguageProxy implements Serializable {

    private Language language = Language.ENGLISH; // default language

    public String l(String code) {
        return language.getResourceBundle().getString(code);
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

}
