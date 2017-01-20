package fr.mazerty.shika.torii.cdi;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * For internationalization, we need a session-specific {@link PropertyResourceBundle}.
 * But a {@link PropertyResourceBundle} is neither {@link Serializable} nor has a default constructor.
 * It also needs to be easily replaced, should be user want to change the displayed language.
 * Hence this wrapper.
 */
@SessionScoped
public class ResourceBundleWrapper implements Serializable {

    private ResourceBundle rb = PropertyResourceBundle.getBundle("labels"); // links to resources/labels.properties

    public String l(String s) {
        return rb.getString(s);
    }

}
