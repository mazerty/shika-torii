package fr.mazerty.shika.torii.cdi;

import com.vaadin.server.VaadinService;

import javax.enterprise.context.SessionScoped;
import javax.servlet.http.Cookie;
import java.io.Serializable;
import java.util.Arrays;

/**
 * CDI proxy for the {@link Language} currently used to display the labels in a given session.
 * The selected language is stored in and read from a cookie, and defaults to English.
 */
@SessionScoped
public class LanguageProxy implements Serializable {

    private static final String COOKIE_NAME = "language";

    private Language language = Arrays.stream(VaadinService.getCurrentRequest().getCookies())
            .filter(this::isValid)
            .findAny()
            .map(cookie -> Language.valueOf(cookie.getValue()))
            .orElse(Language.ENGLISH);

    private boolean isValid(Cookie cookie) {
        return COOKIE_NAME.equals(cookie.getName())
                && Arrays.stream(Language.values()) // a valid cookie value has to match the existing values of the enum
                .anyMatch(language -> language.name().equals(cookie.getValue()));
    }

    /**
     * Shortcut method to get the label to a given code
     *
     * @param code identifier of the label to be displayed
     * @return the label for the selected language
     */
    public String l(String code) {
        return language.getResourceBundle().getString(code);
    }

    public Language get() {
        return language;
    }

    /**
     * Saves the new selected language in this session bean and in a cookie for future use
     *
     * @param language the new selected language
     */
    public void set(Language language) {
        this.language = language;

        Cookie cookie = new Cookie(COOKIE_NAME, language.name());
        cookie.setPath("/");
        cookie.setMaxAge(Integer.MAX_VALUE);
        VaadinService.getCurrentResponse().addCookie(cookie);
    }

}
