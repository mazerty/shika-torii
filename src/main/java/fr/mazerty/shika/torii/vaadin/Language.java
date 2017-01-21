package fr.mazerty.shika.torii.vaadin;

import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public enum Language {

    ENGLISH("English", Locale.ENGLISH),
    FRENCH("Français", Locale.FRENCH);

    private String caption;
    private ResourceBundle resourceBundle;

    Language(String caption, Locale locale) {
        this.caption = caption;
        this.resourceBundle = PropertyResourceBundle.getBundle("labels", locale);
    }

    @Override
    public String toString() {
        return caption;
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

}
