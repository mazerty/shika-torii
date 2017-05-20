package fr.mazerty.shika.torii.vaadin;

import com.vaadin.annotations.Title;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import fr.mazerty.shika.ishi.vaadin.ViewUI;
import fr.mazerty.shika.torii.cdi.Session;

import javax.inject.Inject;

/**
 * Custom {@link UI}, entrypoint of the application
 */
@Title("Torii")
public class MyUI extends ViewUI {

    @Inject
    private CDIViewProvider cdiViewProvider;
    @Inject
    private Session session;

    /**
     * Entrypoint method of the application
     */
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        super.init(vaadinRequest);

        navigator.addProvider(cdiViewProvider);
        navigator.addViewChangeListener(new MyViewChangeListener());

        navigator.navigateTo(LoginView.VIEW_NAME);
    }

    /**
     * The heart of the application's security. Every change of view is challenged here against the user's permissions.
     */
    private class MyViewChangeListener implements ViewChangeListener {

        @Override
        public boolean beforeViewChange(ViewChangeEvent event) {
            boolean goingToLoginView = LoginView.VIEW_NAME.equals(event.getViewName());
            boolean goingToAdminView = AdminView.VIEW_NAME.equals(event.getViewName());

            if (goingToLoginView) {
                // anyone can access the login view but doing so will log you out
                session.logout();
                return true;
            } else if (session.isAdmin() || session.isLoggedIn() && !goingToAdminView) {
                // an admin can go anywhere, so does a normal user except for the admin view
                return true;
            } else {
                // any other attempt is forbidden and redirects to the login view
                navigator.navigateTo(LoginView.VIEW_NAME);
                return false;
            }
        }

        @Override
        public void afterViewChange(ViewChangeEvent event) {
        }

    }

}
