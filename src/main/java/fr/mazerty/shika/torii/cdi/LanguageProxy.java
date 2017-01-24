package fr.mazerty.shika.torii.cdi;

import com.vaadin.server.VaadinService;

import javax.enterprise.context.SessionScoped;
import javax.servlet.http.Cookie;
import java.io.Serializable;
import java.util.Arrays;

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
                && Arrays.stream(Language.values())
                .anyMatch(language -> language.name().equals(cookie.getValue()));
    }

    public String l(String code) {
        return language.getResourceBundle().getString(code);
    }

    public Language get() {
        return language;
    }

    public void set(Language language) {
        this.language = language;

        Cookie cookie = new Cookie(COOKIE_NAME, language.name());
        cookie.setPath("/");
        cookie.setMaxAge(Integer.MAX_VALUE);
        VaadinService.getCurrentResponse().addCookie(cookie);
    }

}
