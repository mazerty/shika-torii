package fr.mazerty.shika.torii.vaadin;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.ViewChangeListener;
import fr.mazerty.shika.ishi.vaadin.MyView;

import javax.inject.Inject;

/**
 * Cette vue est automatiquement présentée lors de la connexion à l'application
 */
@CDIView(LoginView.VIEW_NAME)
public class LoginView extends MyView {

    static final String VIEW_NAME = "login";

    @Inject
    private LoginWindow loginWindow;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        show(loginWindow);
    }

}
