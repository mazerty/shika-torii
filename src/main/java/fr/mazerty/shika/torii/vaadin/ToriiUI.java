package fr.mazerty.shika.torii.vaadin;

import com.vaadin.annotations.Title;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import fr.mazerty.shika.ishi.vaadin.MyUI;
import fr.mazerty.shika.torii.cdi.Session;

import javax.inject.Inject;

@Title("Torii")
public class ToriiUI extends MyUI {

    @Inject
    private Session session;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        super.init(vaadinRequest);

        navigator.addViewChangeListener(new MyViewChangeListener());
    }

    @Override
    protected String getDefaultView() {
        return LoginView.VIEW_NAME;
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
