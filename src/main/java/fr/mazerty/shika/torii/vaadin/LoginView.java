package fr.mazerty.shika.torii.vaadin;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import fr.mazerty.shika.ishi.vaadin.MyView;
import fr.mazerty.shika.torii.cdi.LanguageProxy;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * Cette vue est automatiquement présentée lors de la connexion à l'application
 */
@CDIView(LoginView.VIEW_NAME)
public class LoginView extends MyView {

    static final String VIEW_NAME = "login";

    @Inject
    private LanguageProxy lp;
    @Inject
    private LoginWindow loginWindow;

    private ComboBox cbLanguage;

    @PostConstruct
    public void postConstruct() {
        cbLanguage = new ComboBox();
        cbLanguage.setNullSelectionAllowed(false);
        cbLanguage.addItems(Language.ENGLISH, Language.FRENCH);
        // TODO changevaluelistener

        HorizontalLayout horizontalLayout = new HorizontalLayout(cbLanguage);
        horizontalLayout.setHeightUndefined();
        horizontalLayout.setWidth(100, Unit.PERCENTAGE);
        horizontalLayout.setComponentAlignment(cbLanguage, Alignment.MIDDLE_RIGHT);
        horizontalLayout.setMargin(true);

        addComponent(horizontalLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        cbLanguage.select(lp.getLanguage());

        show(loginWindow);
    }

}
