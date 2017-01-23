package fr.mazerty.shika.torii.vaadin;

import com.vaadin.cdi.CDIView;
import com.vaadin.data.Property;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import fr.mazerty.shika.ishi.vaadin.MyView;
import fr.mazerty.shika.torii.bean.User;
import fr.mazerty.shika.torii.cdi.LanguageProxy;
import fr.mazerty.shika.torii.service.UserService;
import fr.mazerty.shika.torii.session.Session;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import static com.vaadin.ui.Notification.Type.ERROR_MESSAGE;

@CDIView(LoginView.VIEW_NAME)
public class LoginView extends MyView {

    static final String VIEW_NAME = "login";

    @Inject
    private LanguageProxy lp;
    @Inject
    private Session session;
    @Inject
    private UserService userService;

    private ComboBox cbLanguage;
    private MyLoginForm loginForm;

    @PostConstruct
    public void postConstruct() {
        cbLanguage = new ComboBox();
        cbLanguage.setNullSelectionAllowed(false);
        cbLanguage.addItems(Language.ENGLISH, Language.FRENCH);
        cbLanguage.addValueChangeListener(this::changeLanguage);

        loginForm = new MyLoginForm(lp);
        loginForm.addLoginListener(this::login);
        Panel panel = new Panel(loginForm);
        panel.setSizeUndefined();

        addComponents(cbLanguage, panel);
        setComponentAlignment(cbLanguage, Alignment.MIDDLE_RIGHT);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
        setMargin(true);
    }

    private void changeLanguage(Property.ValueChangeEvent valueChangeEvent) {
        lp.saveLanguage((Language) valueChangeEvent.getProperty().getValue());
        loginForm.refreshCaptions();
    }

    private void login(@SuppressWarnings("unused") LoginForm.LoginEvent loginEvent) {
        User match = userService.authenticate(loginForm.getBean());
        if (match == null) {
            Notification.show(lp.l("loginwindow.error"), ERROR_MESSAGE);
        } else {
            session.login(match);
            navigateTo(MainView.VIEW_NAME);
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        cbLanguage.select(lp.getLanguage());

        loginForm.setBean(new User());
        loginForm.focus();
    }

}
